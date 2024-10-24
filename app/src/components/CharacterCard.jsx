import React from 'react'; 
function CharacterCard({ name, image, description }) {
 return ( 
 <div className="character-card">
 <img src={image} alt={name} className="character-card-image" />
 <h3>{name}</h3>
 <p>{description}</p> {/* Mostramos la descripción del personaje 
*/}
 </div>
 );
}
    
 export default CharacterCard; 