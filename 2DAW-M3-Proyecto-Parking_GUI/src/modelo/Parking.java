package modelo;

import excepciones.PlazaOcupadaException;
import excepciones.VehiculoNoEncontradoException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Clase que representa un parking con múltiples plazas
 */
public class Parking {
    
    private ArrayList<Plaza> plazas;
    private int numeroTotalPlazas;
    private int plazasDisponibles;
    
    // Tarifas por tipo de vehículo (€ por minuto)
    private static final double TARIFA_COCHE = 0.12;      // 7,20€ por hora
    private static final double TARIFA_MOTO = 0.08;       // 4,80€ por hora
    private static final double TARIFA_FURGONETA = 0.15;  // 9,00€ por hora
    
    /**
     * Constructor del parking
     * @param numeroPlazas Número total de plazas
     */
    public Parking(int numeroPlazas) {
        this.numeroTotalPlazas = numeroPlazas;
        this.plazasDisponibles = numeroPlazas;
        this.plazas = new ArrayList<>(numeroPlazas);
        
        // Inicializar todas las plazas
        for (int i = 0; i < numeroPlazas; i++) {
            plazas.add(new Plaza(i));
        }
    }
    
    /**
     * Obtiene el número total de plazas
     * @return Número total de plazas
     */
    public int getNumeroTotalPlazas() {
        return numeroTotalPlazas;
    }
    
    /**
     * Obtiene el número de plazas disponibles
     * @return Número de plazas disponibles
     */
    public int getPlazasDisponibles() {
        return plazasDisponibles;
    }
    
    /**
     * Obtiene una plaza específica por su número
     * @param numero Número de plaza
     * @return Plaza correspondiente
     */
    public Plaza getPlaza(int numero) {
        if (numero >= 0 && numero < plazas.size()) {
            return plazas.get(numero);
        }
        return null;
    }
    
    /**
     * Aparca un vehículo en una plaza específica
     * @param vehiculo Vehículo a aparcar
     * @param numeroPlaza Número de plaza donde aparcar
     * @throws PlazaOcupadaException Si la plaza ya está ocupada
     */
    public void aparcarVehiculo(Vehiculo vehiculo, int numeroPlaza) throws PlazaOcupadaException {
        if (numeroPlaza < 0 || numeroPlaza >= plazas.size()) {
            throw new IllegalArgumentException("Número de plaza no válido");
        }
        
        Plaza plaza = plazas.get(numeroPlaza);
        
        if (plaza.isOcupada()) {
            throw new PlazaOcupadaException("La plaza " + numeroPlaza + " ya está ocupada");
        }
        
        plaza.ocupar(vehiculo);
        plazasDisponibles--;
    }
    
    /**
     * Retira un vehículo del parking
     * @param matricula Matrícula del vehículo a retirar
     * @throws VehiculoNoEncontradoException Si no se encuentra el vehículo
     */
    public void retirarVehiculo(String matricula) throws VehiculoNoEncontradoException {
        for (Plaza plaza : plazas) {
            if (plaza.isOcupada() && plaza.getVehiculo().getMatricula().equals(matricula)) {
                plaza.liberar();
                plazasDisponibles++;
                return;
            }
        }
        
        throw new VehiculoNoEncontradoException("No se encontró ningún vehículo con matrícula " + matricula);
    }
    
    /**
     * Calcula el importe a pagar por un vehículo
     * @param matricula Matrícula del vehículo
     * @return Importe a pagar
     * @throws VehiculoNoEncontradoException Si no se encuentra el vehículo
     */
    public double calcularImporte(String matricula) throws VehiculoNoEncontradoException {
        for (Plaza plaza : plazas) {
            if (plaza.isOcupada() && plaza.getVehiculo().getMatricula().equals(matricula)) {
                Vehiculo vehiculo = plaza.getVehiculo();
                
                // Calcular tiempo de estancia en minutos
                LocalDateTime horaEntrada = vehiculo.getHoraEntrada();
                LocalDateTime horaSalida = LocalDateTime.now();
                long minutosEstancia = Duration.between(horaEntrada, horaSalida).toMinutes();
                
                // Aplicar tarifa según tipo de vehículo
                double tarifa;
                switch (vehiculo.getTipo()) {
                    case "Coche":
                        tarifa = TARIFA_COCHE;
                        break;
                    case "Moto":
                        tarifa = TARIFA_MOTO;
                        break;
                    case "Furgoneta":
                        tarifa = TARIFA_FURGONETA;
                        break;
                    default:
                        tarifa = TARIFA_COCHE; // Por defecto
                }
                
                // Calcular importe (mínimo 1 minuto)
                return Math.max(1, minutosEstancia) * tarifa;
            }
        }
        
        throw new VehiculoNoEncontradoException("No se encontró ningún vehículo con matrícula " + matricula);
    }
    
    /**
     * Busca un vehículo por su matrícula
     * @param matricula Matrícula a buscar
     * @return Vehículo encontrado o null si no existe
     */
    public Vehiculo buscarVehiculo(String matricula) {
        for (Plaza plaza : plazas) {
            if (plaza.isOcupada() && plaza.getVehiculo().getMatricula().equals(matricula)) {
                return plaza.getVehiculo();
            }
        }
        return null;
    }
    
    /**
     * Busca la plaza donde está aparcado un vehículo
     * @param matricula Matrícula del vehículo
     * @return Número de plaza o -1 si no se encuentra
     */
    public int buscarPlaza(String matricula) {
        for (int i = 0; i < plazas.size(); i++) {
            Plaza plaza = plazas.get(i);
            if (plaza.isOcupada() && plaza.getVehiculo().getMatricula().equals(matricula)) {
                return i;
            }
        }
        return -1;
    }
}

