import { Link } from "react-router-dom";

const Navbar = () => {
  return (
    <nav className="bg-blue-600 p-4 text-white flex justify-between">
      <h1 className="text-xl font-bold">Social Analytics</h1>
      <div>
        <Link className="mr-4" to="/">Inicio</Link>
        <Link className="mr-4" to="/dashboard">Dashboard</Link>
        <Link className="mr-4" to="/login">Iniciar Sesión</Link>
        <Link to="/register">Registro</Link>
      </div>
    </nav>
  );
};

export default Navbar;
