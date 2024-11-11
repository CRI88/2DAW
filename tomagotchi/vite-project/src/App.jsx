import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'

//src/App.jsx
import { Tamagotchi } from './components/Tamagotchi';

function App() {
  return (
      <div className="bg-gray-100 min-h-screen flex justify-center items-center">
        <Tamagotchi />
      </div>
  )
}

export default App
