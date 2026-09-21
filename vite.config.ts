import tailwindcss from '@tailwindcss/vite';
import react from '@vitejs/plugin-react';
import path from 'path';
import { execSync } from 'child_process';
import { defineConfig, Plugin } from 'vite';

function gitApiPlugin(): Plugin {
  return {
    name: 'git-api-plugin',
    configureServer(server) {
      server.middlewares.use(async (req, res, next) => {
        if (!req.url?.startsWith('/api/git')) {
          return next();
        }

        const androidPath = path.resolve(__dirname, 'masrof-manager-android');

        res.setHeader('Content-Type', 'application/json');

        if (req.method === 'GET' && req.url === '/api/git/status') {
          try {
            const branch = execSync('git rev-parse --abbrev-ref HEAD', { cwd: androidPath, encoding: 'utf-8' }).trim();
            const lastCommit = execSync('git log -n 1 --pretty=format:"%h - %s (%cr) <%an>"', { cwd: androidPath, encoding: 'utf-8' }).trim();
            const status = execSync('git status --porcelain', { cwd: androidPath, encoding: 'utf-8' }).trim();
            const remoteUrl = 'https://github.com/mohammedalhzmi-spec/masrof-manager1';

            res.end(JSON.stringify({
              success: true,
              branch,
              lastCommit,
              hasUncommittedChanges: status.length > 0,
              uncommittedDetails: status || 'المستودع نظيف ومتطابق مع الفرع الرئيسي',
              remoteUrl,
            }));
          } catch (err: any) {
            res.end(JSON.stringify({
              success: false,
              error: err.message,
            }));
          }
          return;
        }

        if (req.method === 'POST' && req.url === '/api/git/push') {
          let body = '';
          req.on('data', (chunk) => {
            body += chunk;
          });

          req.on('end', () => {
            try {
              const { token, commitMessage } = JSON.parse(body || '{}');

              if (!token || typeof token !== 'string') {
                res.statusCode = 400;
                res.end(JSON.stringify({
                  success: false,
                  error: 'يرجى إدخال رمز الوصول الشخصي (GitHub Personal Access Token) للتمكن من رفع التعديلات.',
                }));
                return;
              }

              // Check if changes exist
              const status = execSync('git status --porcelain', { cwd: androidPath, encoding: 'utf-8' }).trim();
              if (status.length > 0) {
                execSync('git add -A', { cwd: androidPath });
                const message = commitMessage?.trim() || 'تحديثات نظام مالية صندوق النظافة';
                execSync(`git commit -m "${message.replace(/"/g, '\\"')}"`, { cwd: androidPath });
              }

              // Execute git push using authenticated URL
              const cleanToken = token.trim();
              const pushUrl = `https://${cleanToken}@github.com/mohammedalhzmi-spec/masrof-manager1.git`;
              
              const pushOutput = execSync(`git push ${pushUrl} main`, {
                cwd: androidPath,
                encoding: 'utf-8',
              });

              res.end(JSON.stringify({
                success: true,
                message: 'تم رفع التعديلات بنجاح إلى المستودع على GitHub!',
                output: pushOutput,
              }));
            } catch (err: any) {
              res.statusCode = 500;
              const sanitizedMsg = (err.stderr || err.message || '').replace(/https:\/\/[^@]+@/g, 'https://***@');
              res.end(JSON.stringify({
                success: false,
                error: sanitizedMsg || 'حدث خطأ أثناء محاولة الرفع إلى GitHub. تأكد من صحة التوكن والصلاحيات.',
              }));
            }
          });
          return;
        }

        if (req.method === 'GET' && req.url === '/api/git/download') {
          try {
            const archiveFile = '/tmp/masrof-manager1-source.tar.gz';
            execSync(`tar -czf ${archiveFile} -C . masrof-manager-android`);
            const fs = await import('fs');
            const data = fs.readFileSync(archiveFile);
            res.setHeader('Content-Type', 'application/gzip');
            res.setHeader('Content-Disposition', 'attachment; filename="masrof-manager1-source.tar.gz"');
            res.end(data);
          } catch (err: any) {
            res.statusCode = 500;
            res.end(JSON.stringify({ success: false, error: err.message }));
          }
          return;
        }

        if (req.method === 'POST' && req.url === '/api/git/pull') {
          try {
            const pullOutput = execSync('git pull origin main', { cwd: androidPath, encoding: 'utf-8' });
            res.end(JSON.stringify({ success: true, message: 'تم سحب آخر التحديثات بنجاح', output: pullOutput }));
          } catch (err: any) {
            res.statusCode = 500;
            res.end(JSON.stringify({ success: false, error: err.message }));
          }
          return;
        }

        next();
      });
    },
  };
}

export default defineConfig(() => {
  return {
    plugins: [react(), tailwindcss(), gitApiPlugin()],
    resolve: {
      alias: {
        '@': path.resolve(__dirname, '.'),
      },
    },
    server: {
      hmr: process.env.DISABLE_HMR !== 'true',
      watch: process.env.DISABLE_HMR === 'true' ? null : {},
    },
  };
});
