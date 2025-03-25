import { useState } from "react";
import Navbar from "../components/Navbar";
import Footer from "../components/Footer";

const Register = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  
  const handleSubmit = async (e) => {
    e.preventDefault();
    const response = await fetch("http://localhost:8000/api/register", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ email, password }),
    });
    const data = await response.json();
    console.log(data);
  };

  return (
    <>
      <Navbar />
      <div className="flex justify-center items-center h-screen">
        <form onSubmit={handleSubmit} className="bg-white p-6 rounded-lg shadow-lg w-96">
          <h2 className="text-2xl font-bold mb-4">Registro</h2>
          <input type="email" className="border p-2 w-full mb-4" placeholder="Correo"
            value={email} onChange={(e) => setEmail(e.target.value)} required />
          <input type="password" className="border p-2 w-full mb-4" placeholder="Contraseña"
            value={password} onChange={(e) => setPassword(e.target.value)} required />
          <button type="submit" className="bg-green-600 text-white p-2 w-full rounded">Registrarse</button>
        </form>
      </div>
      <Footer />
    </>
  );
};

export default Register;
