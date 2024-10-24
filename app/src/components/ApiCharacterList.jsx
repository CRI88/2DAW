import React, { useEffect, useState } from 'react'; 
import CharacterCard from './CharacterCard'; 
function ApiCharacterList() {
 const [characters, setCharacters] = useState([]);
 
 // useEffect para cargar los personajes desde la API cuando el componente se monta
 useEffect(() => { 
 const fetchCharactersFromAPI = async () => { 
 try { 
 const response = await
fetch('https://ghibliapi.vercel.app/people'); // Hacemos la petición a la API
 const data = await response.json();
// Formateamos los datos recibidos para que se ajusten a nuestro 
componente
 const formattedCharacters = data.map(character => ({
 name: character.name, 
 image: 'https://via.placeholder.com/200', // No tenemos imágenes en la API, usamos placeholder
 description: character.gender || 'Sin descripción' // Usamos el género como descripción si existe
 }));
 setCharacters(formattedCharacters); // Actualizamos el estado con los datos de la API
 } catch (error) {
 console.error('Error al obtener los personajes de la API:', 
error);
 } 
 };
 fetchCharactersFromAPI(); // Llamamos a la función para obtener los 
datos
 }, []);
 return ( 
 <div className="characters-grid">
 {characters.map((character) => (
 <CharacterCard
 key={character.name}
 name={character.name}
 image={character.image}
 description={character.description}
 />
 ))}
 </div>
 );
} 
export default ApiCharacterList;
