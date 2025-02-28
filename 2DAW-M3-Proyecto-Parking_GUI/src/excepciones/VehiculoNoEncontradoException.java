package excepciones;

/**
 * Excepción que se lanza cuando no se encuentra un vehículo en el parking
 */
public class VehiculoNoEncontradoException extends Exception {
    
    /**
     * Constructor con mensaje
     * @param mensaje Mensaje de error
     */
    public VehiculoNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}

