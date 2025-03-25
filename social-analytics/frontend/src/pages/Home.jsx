import Navbar from "../components/Navbar";
import Footer from "../components/Footer";

const Home = () => {
  return (
    <>
      <Navbar />
      <div className="text-center py-10">
        <h2 className="text-3xl font-bold">Bienvenido a Social Analytics</h2>
        <p className="mt-4 text-gray-600">Analiza tus redes sociales con facilidad.</p>
      </div>
      <Footer />
    </>
  );
};

export default Home;
