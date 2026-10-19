import Fastify from 'fastify';
import multipart from '@fastify/multipart';
import { Queue } from 'bullmq';
import IORedis from 'ioredis';
import { mkdir, writeFile, stat, unlink } from 'node:fs/promises';
import { randomUUID } from 'node:crypto';
import path from 'node:path';
import { S3Client, GetObjectCommand, HeadObjectCommand } from '@aws-sdk/client-s3';

const app = Fastify({ logger: { transport: process.env.NODE_ENV === 'production' ? undefined : { target: 'pino-pretty' } }, bodyLimit: 110 * 1024 * 1024 });
await app.register(multipart, { limits: { fileSize: Number(process.env.MAX_UPLOAD_MB || 100) * 1024 * 1024, files: 1 } });
const redis = new IORedis(process.env.REDIS_URL || 'redis://127.0.0.1:6379', { maxRetriesPerRequest: null });
const queue = new Queue('office-conversions', { connection: redis });
const root = '/tmp/masrof-conversions';
const jobs = new Map();
const allowed = new Set(['pdf','doc','docx','docm','dot','dotx','odt','rtf','txt','xls','xlsx','xlsm','xlt','xltx','ods','csv','ppt','pptx','odp']);
const targets = new Set(['pdf','docx','xlsx','odt','ods','png','jpg']);
const s3 = new S3Client({ endpoint: process.env.S3_ENDPOINT, region: process.env.S3_REGION || 'us-east-1', forcePathStyle: true, credentials: { accessKeyId: process.env.S3_ACCESS_KEY || '', secretAccessKey: process.env.S3_SECRET_KEY || '' } });
const auth = (request, reply) => { const expected = process.env.API_TOKEN; if (expected && expected !== 'change-me' && request.headers.authorization !== `Bearer ${expected}`) return reply.code(401).send({ error: 'unauthorized' }); };
function ext(name) { return path.extname(name).slice(1).toLowerCase(); }
function safeName(name) { return path.basename(name).replace(/[^a-zA-Z0-9._-]/g, '_').slice(0, 180) || 'document'; }
function publicJob(job) { return { id: job.id, status: job.status, progress: job.progress || 0, sourceFormat: job.sourceFormat, targetFormat: job.targetFormat, warnings: job.warnings || [], createdAt: job.createdAt, updatedAt: job.updatedAt, expiresAt: job.expiresAt, downloadUrl: job.status === 'completed' ? `/api/conversions/${job.id}/download` : undefined }; }
app.get('/healthz', async () => ({ ok: true, service: 'masrof-office-conversion', queue: await queue.getJobCounts() }));
app.get('/metrics', async (_request, reply) => { const counts = await queue.getJobCounts(); reply.type('text/plain').send(Object.entries(counts).map(([k,v]) => `masrof_conversion_queue{state="${k}"} ${v}`).join('\n') + '\n'); });
app.post('/api/conversions', { preHandler: auth }, async (request, reply) => {
  const data = await request.file();
  if (!data) return reply.code(400).send({ error: 'file_required' });
  const filename = safeName(data.filename); const sourceFormat = ext(filename);
  const targetFormat = String(data.fields?.targetFormat?.value || request.headers['x-target-format'] || '').toLowerCase();
  if (!allowed.has(sourceFormat)) return reply.code(415).send({ error: 'unsupported_source_format', sourceFormat });
  if (!targets.has(targetFormat)) return reply.code(400).send({ error: 'unsupported_target_format', targetFormat });
  if (sourceFormat === targetFormat) return reply.code(400).send({ error: 'same_source_and_target' });
  const id = randomUUID(); const dir = path.join(root, id); await mkdir(dir, { recursive: true });
  const input = path.join(dir, filename); await writeFile(input, await data.toBuffer());
  const now = Date.now(); const job = { id, status: 'queued', progress: 0, input, filename, sourceFormat, targetFormat, createdAt: now, updatedAt: now, expiresAt: now + Number(process.env.JOB_TTL_HOURS || 24) * 3600000, warnings: [] };
  jobs.set(id, job); await redis.set(`office:job:${id}`, JSON.stringify(job), 'EX', Number(process.env.JOB_TTL_HOURS || 24) * 3600); await queue.add('convert', { id }, { jobId: id, removeOnComplete: 1000, removeOnFail: 1000 });
  return reply.code(202).send(publicJob(job));
});
app.get('/api/conversions/:id', { preHandler: auth }, async (request, reply) => { const raw = await redis.get(`office:job:${request.params.id}`); const job = raw ? JSON.parse(raw) : jobs.get(request.params.id); if (!job) return reply.code(404).send({ error: 'not_found' }); return publicJob(job); });
app.get('/api/conversions/:id/download', { preHandler: auth }, async (request, reply) => { const raw = await redis.get(`office:job:${request.params.id}`); const job = raw ? JSON.parse(raw) : jobs.get(request.params.id); if (!job || job.status !== 'completed') return reply.code(404).send({ error: 'output_not_ready' }); reply.header('Content-Disposition', `attachment; filename="${safeName(job.outputName)}"`).type(job.mime || 'application/octet-stream'); if (job.outputKey && process.env.STORAGE_MODE === 's3') { const object = await s3.send(new GetObjectCommand({ Bucket: process.env.S3_BUCKET, Key: job.outputKey })); return reply.send(object.Body); } if (!job.output) return reply.code(404).send({ error: 'output_not_ready' }); const info = await stat(job.output); reply.header('Content-Length', info.size); return reply.send((await import('node:fs')).createReadStream(job.output)); });
app.delete('/api/conversions/:id', { preHandler: auth }, async (request, reply) => { const job = jobs.get(request.params.id); if (!job) return reply.code(404).send({ error: 'not_found' }); job.status = 'deleted'; if (job.input) await unlink(job.input).catch(() => {}); if (job.output) await unlink(job.output).catch(() => {}); jobs.delete(job.id); return { ok: true }; });
app.setErrorHandler((error, _request, reply) => { app.log.error(error); reply.code(error.statusCode || 500).send({ error: 'server_error', message: error.statusCode ? error.message : 'حدث خطأ داخلي.' }); });
await mkdir(root, { recursive: true }); await app.listen({ port: Number(process.env.PORT || 8088), host: '0.0.0.0' });
