package excepciones;

/**
 * Excepción que se lanza cuando se intenta ocupar una plaza ya ocupada
 */
public class PlazaOcupadaException extends Exception {
    
    /**
     * Constructor con mensaje
     * @param mensaje Mensaje de error
     */
    public PlazaOcupadaException(String mensaje) {
        super(mensaje);
    }
}

