import viteReact from '@vitejs/plugin-react';
import path from 'path';
import { defineConfig } from 'vite';

export default defineConfig({
  resolve: {
    alias: {
      '@': path.resolve(__dirname, './src'),
    },
  },
  plugins: [viteReact()],
  server: {
    port: 3000,
    host: 'localhost',
  },
  preview: {
    host: true,
    strictPort: true,
    port: 3000,
  },
});
