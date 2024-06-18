/** @type {import('tailwindcss').Config} */
module.exports = {
  content: ['./src/main/resources/templates/**/*.html'],
  darkMode: 'media',
  theme: {
    extend: {},
  },
  plugins: [require('@tailwindcss/forms')],
}
