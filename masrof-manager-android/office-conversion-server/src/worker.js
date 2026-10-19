import { Worker } from 'bullmq';
import IORedis from 'ioredis';
import { execFile } from 'node:child_process';
import { promisify } from 'node:util';
import { mkdir, readdir, rm, stat, writeFile } from 'node:fs/promises';
import path from 'node:path';
import { S3Client, PutObjectCommand } from '@aws-sdk/client-s3';
const exec = promisify(execFile);
const redis = new IORedis(process.env.REDIS_URL || 'redis://127.0.0.1:6379', { maxRetriesPerRequest: null });
const root = '/tmp/masrof-conversions';
const s3 = new S3Client({ endpoint: process.env.S3_ENDPOINT, region: process.env.S3_REGION || 'us-east-1', forcePathStyle: true, credentials: { accessKeyId: process.env.S3_ACCESS_KEY || '', secretAccessKey: process.env.S3_SECRET_KEY || '' } });
async function run(command, args, timeout) { return exec(command, args, { timeout, maxBuffer: 4 * 1024 * 1024, env: { ...process.env, HOME: '/tmp' } }); }
async function convert(job) {
  const meta = await redis.get(`office:job:${job.id}`); if (!meta) return;
  const data = JSON.parse(meta); const dir = path.dirname(data.input); const out = path.join(dir, 'output'); await mkdir(out, { recursive: true });
  const profile = `file://${path.join(dir, 'lo-profile')}`; const warnings = [];
  await job.updateProgress(10); await redis.set(`office:job:${job.id}`, JSON.stringify({ ...data, status: 'processing', progress: 10, updatedAt: Date.now() }));
  let target = data.targetFormat;
  if (target === 'png' || target === 'jpg') { target = 'pdf'; warnings.push('تم إنشاء PDF وسيتم تحويل صفحاته إلى صور في مرحلة إخراج لاحقة.'); }
  const filter = target === 'docx' ? 'docx' : target === 'xlsx' ? 'xlsx' : target === 'odt' ? 'odt' : target === 'ods' ? 'ods' : 'pdf';
  try {
    await run('soffice', ['--headless','--nologo','--nodefault','--nofirststartwizard',`-env:UserInstallation=${profile}`,'--convert-to',filter,'--outdir',out,data.input], Number(process.env.MAX_CONVERSION_SECONDS || 180) * 1000);
  } catch (error) { throw new Error(`LibreOffice conversion failed: ${String(error.stderr || error.message).slice(0, 500)}`); }
  let files = await readdir(out); let outputName = files.find(x => x.toLowerCase().endsWith(`.${filter}`));
  if (!outputName) throw new Error('لم ينتج LibreOffice ملفًا صالحًا.');
  const output = path.join(out, outputName); const info = await stat(output); if (!info.size) throw new Error('الملف الناتج فارغ.');
  if (data.sourceFormat === 'pdf' && ['docx','odt'].includes(filter)) warnings.push('تحويل PDF يعتمد على طبقة النص؛ ملفات PDF الممسوحة ضوئيًا تحتاج OCR وقد تختلف الأشكال العائمة.');
  let outputKey;
  if (process.env.STORAGE_MODE === 's3') { outputKey = `conversions/${job.id}/${outputName}`; await s3.send(new PutObjectCommand({ Bucket: process.env.S3_BUCKET, Key: outputKey, Body: (await import('node:fs')).createReadStream(output), ContentType: mime(filter) })); }
  const result = { ...data, status: 'completed', progress: 100, output: process.env.STORAGE_MODE === 's3' ? undefined : output, outputKey, outputName, mime: mime(filter), warnings, updatedAt: Date.now(), size: info.size };
  await redis.set(`office:job:${job.id}`, JSON.stringify(result), 'EX', Number(process.env.JOB_TTL_HOURS || 24) * 3600);
  await job.updateProgress(100);
}
function mime(ext) { return ({ pdf:'application/pdf', docx:'application/vnd.openxmlformats-officedocument.wordprocessingml.document', xlsx:'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet', odt:'application/vnd.oasis.opendocument.text', ods:'application/vnd.oasis.opendocument.spreadsheet' })[ext] || 'application/octet-stream'; }
const worker = new Worker('office-conversions', async job => { await convert(job); }, { connection: redis, concurrency: 1, lockDuration: 240000 });
worker.on('completed', job => console.log(`completed ${job.id}`)); worker.on('failed', (job, error) => console.error(`failed ${job?.id}`, error));
console.log('LibreOffice conversion worker started');
