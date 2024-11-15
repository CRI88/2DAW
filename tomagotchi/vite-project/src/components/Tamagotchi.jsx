//src/components/Tamagotchi.jsx
import React from 'react';
import { useState, useEffect } from 'react';

export function Tamagotchi() {
    const [hunger, setHunger] = useState(() => parseInt(localStorage.getItem('hunger')) || 50);
    const [happiness, setHappiness] = useState(() => parseInt(localStorage.getItem('happiness')) || 50);
    const [health, setHealth] = useState(() => parseInt(localStorage.getItem('health')) || 100);
    const [clean, setClean] = useState(() => parseInt(localStorage.getItem('clean')) || 70);
    const [energy, setEnergy] = useState(() => parseInt(localStorage.getItem('energy')) || 60);
    const [level, setLevel] = useState(() => parseInt(localStorage.getItem('level')) || 1);
    const [points, setPoints] = useState(() => parseInt(localStorage.getItem('points')) || 0);
    const [fish, setFish] = useState(() => parseInt(localStorage.getItem('fish')) || 0);
    const [toy, setToy] = useState(() => parseInt(localStorage.getItem('toy')) || 0);
    const [brush, setBrush] = useState(() => parseInt(localStorage.getItem('brush')) || 0);
    const [drink, setDrink] = useState(() => parseInt(localStorage.getItem('drink')) || 0);
    const [isGameOver, setIsGameOver] = useState(() => localStorage.getItem('isGameOver') === 'true' || false);
    const [isLevelUpModalVisible, setIsLevelUpModalVisible] = useState(false);

    useEffect(() => {
        localStorage.setItem('hunger', hunger);
    }, [hunger]);

    useEffect(() => {
        localStorage.setItem('happiness', happiness);
    }, [happiness]);

    useEffect(() => {
        localStorage.setItem('health', health);
    }, [health]);

    useEffect(() => {
        localStorage.setItem('clean', clean);
    }, [clean]);

    useEffect(() => {
        localStorage.setItem('energy', energy);
    }, [energy]);

    useEffect(() => {
        localStorage.setItem('level', level);
    }, [level]);

    useEffect(() => {
        localStorage.setItem('points', points);
    }, [points]);

    useEffect(() => {
        localStorage.setItem('fish', fish);
    }, [fish]);

    useEffect(() => {
        localStorage.setItem('toy', toy);
    }, [toy]);

    useEffect(() => {
        localStorage.setItem('brush', brush);
    }, [brush]);

    useEffect(() => {
        localStorage.setItem('drink', drink);
    }, [drink]);

    useEffect(() => {
        localStorage.setItem('isGameOver', isGameOver);
    }, [isGameOver]);

    const feed = () => {
        setHunger((prev) => Math.min(prev + 20, 100));
        setHealth((prev) => Math.min(prev + 5, 100));
        setClean((prev) => Math.max(prev - 10, 0));
        setEnergy((prev) => Math.max(prev - 7, 0));
        setPoints(points + 10);
    };
    const play = () => {
        setHappiness((prev) => Math.min(prev + 20, 100));
        setHunger((prev) => Math.max(prev - 5, 0));
        setHealth((prev) => Math.max(prev - 5, 0));
        setClean((prev) => Math.max(prev - 15, 0));
        setEnergy((prev) => Math.max(prev - 10, 0));
        setPoints(points + 15);
    };
    const sleep = () => {
        setHealth((prev) => Math.min(prev + 10, 100));
        setHappiness((prev) => Math.max(prev - 5, 0));
        setEnergy((prev) => Math.min(prev + 15, 100));
        setPoints(points + 10);
    };
    const cleaning = () => {
        setClean((prev) => Math.min(prev + 15, 100));
        setHealth((prev) => Math.min(prev + 10, 100));
        setEnergy((prev) => Math.max(prev - 5, 0));
        setPoints(points + 20);
    }

    useEffect(() => {

        const lastLevelUpPoints = parseInt(localStorage.getItem('lastLevelUpPoints')) || 0;

        if (points >= lastLevelUpPoints + 500) {
            setLevel(level + 1);
            setFish(fish + 1);
            setToy(toy + 1);
            setBrush(brush + 1);
            setDrink(drink + 1);
            setIsLevelUpModalVisible(true);

            localStorage.setItem('lastLevelUpPoints', lastLevelUpPoints + 500);

            setTimeout(() => {
                setIsLevelUpModalVisible(false);
            }, 3000);
        }
    }, [points]);

    useEffect(() => {
        const decremento = level;
        const timer = setInterval(() => {
            setHunger((prev) => Math.max(prev - decremento, 0));
            setHappiness((prev) => Math.max(prev - decremento, 0));
            setHealth((prev) => Math.max(prev - decremento, 0));
            setClean((prev) => Math.max(prev - decremento, 0));
            setEnergy((prev) => Math.max(prev - decremento, 0));
        }, 3000);
        return () => clearInterval(timer);
    }, [level]);

    useEffect(() => {
        const pointsTimer = setInterval(() => {
            setPoints((points) => points + 100);
        }, 60000);
        return () => clearInterval(pointsTimer);
    }, []);

    const getProgressColor = (value) => {
        if (value > 60) return "bg-green-500";
        if (value > 20) return "bg-yellow-500";
        return "bg-red-500";
    };
    // Función para mostrar el mensaje de estado del Tamagotchi
    const getStatusMessage = () => {
        if (hunger < 20) return "¡Tengo hambre! 😟😟";
        if (happiness < 20) return "Estoy triste 😢😢";
        if (health < 20) return "No me siento bien 😷😷";
        if (clean < 20) return "Necesito limpiarme 😖😖";
        if (energy < 20) return "¡Llévame a dormir! 😴😴";
        return "¡Estoy feliz! 😊😊";
    };

    const handleUseFish = () => {
        if (fish > 0) {
            setFish(fish - 1);
            setHunger((prev) => Math.min(prev + 100, 100));
            setHappiness((prev) => Math.min(prev + 100, 100));
            setEnergy((prev) => Math.min(prev + 100, 100));
        } else {
            alert("No tienes más peces");
        }
    };

    const handleBuyFish = () => {
        if (points > 1000) {
            setFish(fish + 1);
            setPoints(points - 1000);
        } else {
            alert("Necesitas 1000 puntos para comprar 1 pez");
        }
    };

    const handleUseToy = () => {
        if (toy > 0) {
            setToy(toy - 1);
            setHappiness((prev) => Math.min(prev + 70, 100));
        } else {
            alert("No tienes más juguetes");
        }
    };

    const handleBuyToy = () => {
        if (points > 200) {
            setToy(toy + 1);
            setPoints(points - 200);
        } else {
            alert("Necesitas 200 puntos para comprar 1 juguete");
        }
    };

    const handleUseBrush = () => {
        if (brush > 0) {
            setBrush(brush - 1);
            setHealth((prev) => Math.min(prev + 40, 100));
            setClean((prev) => Math.min(prev + 60, 100));
        } else {
            alert("No tienes más cepillos");
        }
    };

    const handleBuyBrush = () => {
        if (points > 350) {
            setBrush(brush + 1);
            setPoints(points - 350);
        } else {
            alert("Necesitas 350 puntos para comprar 1 cepillo");
        }
    };

    const handleUseDrink = () => {
        if (drink > 0) {
            setDrink(drink - 1);
            setHappiness((prev) => Math.min(prev + 60, 100));
            setHappiness((prev) => Math.min(prev + 50, 100));
        } else {
            alert("No tienes más bebidas");
        }
    };

    const handleBuyDrink = () => {
        if (points > 150) {
            setDrink(drink + 1);
            setPoints(points - 150);
        } else {
            alert("Necesitas 150 puntos para comprar 1 bebida");
        }
    };

    const resetGame = () => {
        setHunger(50);
        setHappiness(50);
        setHealth(100);
        setClean(70);
        setEnergy(60);
        setLevel(1);
        setPoints(0);
        setFish(0);
        setToy(0);
        setBrush(0);
        setDrink(0);
        setIsGameOver(false);
        localStorage.clear();
    };

    useEffect(() => {
        if (hunger === 0 || happiness === 0 || health === 0 || clean === 0 || energy === 0) {
            setIsGameOver(true);
        }
    }, [hunger, happiness, health, clean, energy]);

    return (
        <div className="p-4 bg-white rounded-lg shadow-md w-80">

            <div className="space-y-4 mt-4">
                <h1 className="text-2xl font-bold text-center mb-4">💎Estadísticas💎</h1>
                {/* Primer objeto */}
            <div className="flex items-center space-x-4 p-4 border rounded-md shadow-md">
                <span className="text-lg font-semibold">Nivel:</span>
                <span className="text-lg font-semibold">{level}</span>
                <span className="text-lg font-semibold">Puntos:</span>
                <span className="text-lg font-semibold">{points}</span>
            </div>
            </div>

            <h1 className="text-2xl font-bold text-center mt-2">🐱Tamagotchi🐱</h1>
            <img
                src="../public/gato.gif"
                alt="Gif de Tamagotchi"
                className="block mx-auto mt-4 w-32 h-32"
                />
            {/* Mensaje de estado */}
            <p className="text-center text-lg font-semibold mt-1">{getStatusMessage()}</p>
            {/* Barra de Progreso para Hambre */}
            <div className="mb-4">
                <label className="block font-medium mb-1">Hambre: {hunger}</label>
                <div className="bg-gray-300 h-4 rounded overflow-hidden">
                    <div className={`${getProgressColor(hunger)} h-full`} style={{
                        width: `${hunger}%`
                    }} />
                </div>
            </div>
            {/* Barra de Progreso para Felicidad */}
            <div className="mb-4">
                <label className="block font-medium mb-1">Felicidad: {happiness}</label>
                <div className="bg-gray-300 h-4 rounded overflow-hidden">
                    <div className={`${getProgressColor(happiness)} h-full`}
                        style={{ width: `${happiness}%` }} />
                </div>
            </div>
            {/* Barra de Progreso para Salud */}
            <div className="mb-4">
                <label className="block font-medium mb-1">Salud: {health}</label>
                <div className="bg-gray-300 h-4 rounded overflow-hidden">
                    <div className={`${getProgressColor(health)} h-full`} style={{
                        width: `${health}%`
                    }} />
                </div>
            </div>
            {/* Barra de Progreso para Limpieza */}
            <div className="mb-4">
                <label className="block font-medium mb-1">Limpieza: {clean}</label>
                <div className="bg-gray-300 h-4 rounded overflow-hidden">
                    <div className={`${getProgressColor(clean)} h-full`} style={{
                        width: `${clean}%`
                    }} />
                </div>
            </div>
            {/* Barra de Progreso para Energia */}
            <div className="mb-4">
                <label className="block font-medium mb-1">Energia: {energy}</label>
                <div className="bg-gray-300 h-4 rounded overflow-hidden">
                    <div className={`${getProgressColor(energy)} h-full`} style={{
                        width: `${energy}%`
                    }} />
                </div>
            </div>
            {/* Botones de interacción */}
            <div className="flex space-x-2 mt-4">
                <button onClick={feed} className="px-4 py-2 bg-blue-500 text-white rounded hover:bg-blue-600 transition">Feed</button>
                <button onClick={play} className="px-4 py-2 bg-green-500 text-white rounded hover:bg-green-600 transition">Play</button>
                <button onClick={sleep} className="px-4 py-2 bg-purple-500 text-white rounded hover:bg-purple-600 transition">Sleep</button>
                <button onClick={cleaning} className="px-4 py-2 bg-red-500 text-white rounded hover:bg-red-600 transition">Clean</button>
            </div>
            <div className="space-y-4 mt-4">
                <h1 className="text-2xl font-bold text-center mb-4">📦Objetos📦</h1>
                {/* Primer objeto */}
            <div className="flex items-center space-x-4 p-4 border rounded-md shadow-md">
                <span className="text-2xl">🐟</span>
                <span className="text-lg font-semibold">{fish}</span>
                <button onClick={handleUseFish} className="bg-yellow-500 text-white px-3 py-1 rounded hover:bg-yellow-600">
                    Usar
                </button>
                <button onClick={handleBuyFish} className="bg-pink-500 text-white px-3 py-1 rounded hover:bg-pink-600">
                    Comprar
                </button>
            </div>

            {/* Segundo objeto */}
            <div className="flex items-center space-x-4 p-4 border rounded-md shadow-md">
                <span className="text-2xl">🪀</span>
                <span className="text-lg font-semibold">{toy}</span>
                <button onClick={handleUseToy} className="bg-yellow-500 text-white px-3 py-1 rounded hover:bg-yellow-600">
                    Usar
                </button>
                <button onClick={handleBuyToy} className="bg-pink-500 text-white px-3 py-1 rounded hover:bg-pink-600">
                    Comprar
                </button>
            </div>

            {/* Tercer objeto */}
            <div className="flex items-center space-x-4 p-4 border rounded-md shadow-md">
                <span className="text-2xl">🪥</span>
                <span className="text-lg font-semibold">{brush}</span>
                <button onClick={handleUseBrush} className="bg-yellow-500 text-white px-3 py-1 rounded hover:bg-yellow-600">
                    Usar
                </button>
                <button onClick={handleBuyBrush} className="bg-pink-500 text-white px-3 py-1 rounded hover:bg-pink-600">
                    Comprar
                </button>
            </div>

            {/* Cuarto objeto */}
            <div className="flex items-center space-x-4 p-4 border rounded-md shadow-md">
                <span className="text-2xl">🥤</span>
                <span className="text-lg font-semibold">{drink}</span>
                <button onClick={handleUseDrink} className="bg-yellow-500 text-white px-3 py-1 rounded hover:bg-yellow-600">
                    Usar
                </button>
                <button onClick={handleBuyDrink} className="bg-pink-500 text-white px-3 py-1 rounded hover:bg-pink-600">
                    Comprar
                </button>
            </div>
            </div>
            {isGameOver && (
    <div className="fixed inset-0 flex items-center justify-center bg-black bg-opacity-75">
        <div className="bg-white p-8 rounded-lg shadow-lg text-center max-w-sm">
            <h2 className="text-4xl font-bold text-red-600 mb-4">Game Over</h2>
            <p className="text-lg mb-6">¡Has perdido! Inténtalo de nuevo.</p>
            <button
                onClick={resetGame}
                className="bg-blue-500 text-white px-6 py-2 rounded-md hover:bg-blue-600"
            >
                Reiniciar Juego
            </button>
        </div>
    </div>
    )}
    {isLevelUpModalVisible && (
    <div className="fixed top-0 left-0 w-full h-full flex items-center justify-center bg-black bg-opacity-50 z-50">
        <div className="bg-white rounded-lg shadow-lg p-6 text-center">
            <h2 className="text-2xl font-bold text-green-600">¡Subiste de nivel!</h2>
            <p className="text-lg">Ahora estás en el nivel {level}.</p>
        </div>
    </div>
    )}
        </div>
    );
}
