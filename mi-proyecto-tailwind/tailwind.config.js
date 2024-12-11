/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    "./public/**/*.{html,js}",
    "./src/**/*.{html,js}"
  ],
  theme: {
    extend: {
      colors: {
        'primary': '#FFB703',
        'secondary': '#D9BF77',
        'accent': '#EF476F',
        'dark': '#333333',
        'light': '#F1F1F1',
      },
      fontFamily: {
        sans: ['Inter', 'Helvetica', 'Arial', 'sans-serif'],
      },
      boxShadow: {
        'xl': '0 10px 20px rgba(0, 0, 0, 0.15)',
      }
    },
  },
  plugins: [],
}



