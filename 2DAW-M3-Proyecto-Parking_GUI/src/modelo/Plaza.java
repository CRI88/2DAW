package modelo;

/**
 * Clase que representa una plaza de parking
 */
public class Plaza {
    
    private int numero;
    private boolean ocupada;
    private Vehiculo vehiculo;
    
    /**
     * Constructor de la plaza
     * @param numero Número de la plaza
     */
    public Plaza(int numero) {
        this.numero = numero;
        this.ocupada = false;
        this.vehiculo = null;
    }
    
    /**
     * Obtiene el número de la plaza
     * @return Número de la plaza
     */
    public int getNumero() {
        return numero;
    }
    
    /**
     * Comprueba si la plaza está ocupada
     * @return true si está ocupada, false en caso contrario
     */
    public boolean isOcupada() {
        return ocupada;
    }
    
    /**
     * Obtiene el vehículo aparcado en la plaza
     * @return Vehículo aparcado o null si no hay ninguno
     */
    public Vehiculo getVehiculo() {
        return vehiculo;
    }
    
    /**
     * Ocupa la plaza con un vehículo
     * @param vehiculo Vehículo a aparcar
     */
    public void ocupar(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
        this.ocupada = true;
    }
    
    /**
     * Libera la plaza
     */
    public void liberar() {
        this.vehiculo = null;
        this.ocupada = false;
    }
}

