import express from 'express';
import cors from 'cors';
import { createServer as createViteServer } from 'vite';

const app = express();
app.use(cors());
app.use(express.json());

app.get('/api/health', (_req, res) => {
  res.json({ status: 'healthy', app: 'Masrof Manager Web', auth: 'Firebase Authentication', storage: 'Cloud Firestore' });
});

async function startServer() {
  const vite = await createViteServer({ server: { middlewareMode: true }, appType: 'spa' });
  app.use(vite.middlewares);
  const port = Number(process.env.PORT || 3000);
  app.listen(port, '0.0.0.0', () => console.log(`Web server listening on ${port}`));
}

startServer();
