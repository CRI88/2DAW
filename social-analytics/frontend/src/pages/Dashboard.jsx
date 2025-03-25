import Navbar from "../components/Navbar";
import Footer from "../components/Footer";

const Dashboard = () => {
  return (
    <>
      <Navbar />
      <div className="container mx-auto p-6">
        <h2 className="text-2xl font-bold mb-4">Dashboard</h2>
        <p>Aquí verás las estadísticas de tus redes sociales.</p>
      </div>
      <Footer />
    </>
  );
};

export default Dashboard;
