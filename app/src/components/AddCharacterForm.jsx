import { useState } from 'react'; 
function AddCharacterForm({ onAddCharacter }) {
 // Estado local para los inputs del formulario
 const [newCharacter, setNewCharacter] = useState({
 name: '', 
 image: '', 
 description: ''
 });
 // Función para manejar cambios en los inputs
 const handleInputChange = (event) => { 
 const { name, value } = event.target; // Capturamos el nombre del input y su valor
 setNewCharacter({
 ...newCharacter, // Mantenemos los valores previos (spread operator) 
 [name]: value // Actualizamos solo el input correspondiente
 });
 };
 // Función para gestionar el envío del formulario
 const handleSubmit = (event) => { 
 event.preventDefault(); // Prevenimos el comportamiento por defecto del formulario
 onAddCharacter(newCharacter); // Llamamos a la función que viene del padre para añadir el nuevo personaje
 setNewCharacter({ name: '', image: '', description: '' }); // Limpiamos el formulario
 };
 return ( 
 <form onSubmit={handleSubmit}>
 {/* Campo para el nombre del personaje */}
 <input
 type="text"
 name="name"
 placeholder="Nombre del personaje"
 value={newCharacter.name}
 onChange={handleInputChange}
 />
{/* Campo para la URL de la imagen */}
<input
 type="text"
 name="image"
 placeholder="URL de la imagen"
 value={newCharacter.image}
 onChange={handleInputChange}
 />
 {/* Campo para la descripción del personaje */}
 <input
 type="text"
 name="description"
 placeholder="Descripción del personaje"
 value={newCharacter.description}
 onChange={handleInputChange}
 />
 {/* Botón para enviar el formulario */}
 <button type="submit">Añadir personaje</button>
 </form>
 );
} 
export default AddCharacterForm; 