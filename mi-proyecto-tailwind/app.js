const express = require('express');
const path = require('path');
const app = express();

// Sirve archivos estáticos desde 'public' para HTML
app.use(express.static(path.join(__dirname, 'public')));

// Sirve archivos estáticos desde 'dist' para CSS
app.use('/dist', express.static(path.join(__dirname, 'dist')));

// Sirve archivos estáticos desde 'src' para imágenes
app.use('/src', express.static(path.join(__dirname, 'src')));

app.get('/', (req, res) => {
  res.sendFile(path.join(__dirname, 'public', 'home.html'));
});

const PORT = 8080;
app.listen(PORT, () => {
  console.log(`Servidor corriendo en http://127.0.0.1:${PORT}`);
});
