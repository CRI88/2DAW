package modelo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Clase que representa un vehículo en el parking
 */
public class Vehiculo {
    
    private String matricula;
    private String tipo;
    private LocalDateTime horaEntrada;
    
    /**
     * Constructor del vehículo
     * @param matricula Matrícula del vehículo
     * @param tipo Tipo de vehículo (Coche, Moto, Furgoneta)
     * @param horaEntrada Hora de entrada al parking
     */
    public Vehiculo(String matricula, String tipo, LocalDateTime horaEntrada) {
        this.matricula = matricula;
        this.tipo = tipo;
        this.horaEntrada = horaEntrada;
    }
    
    /**
     * Obtiene la matrícula del vehículo
     * @return Matrícula
     */
    public String getMatricula() {
        return matricula;
    }
    
    /**
     * Obtiene el tipo de vehículo
     * @return Tipo de vehículo
     */
    public String getTipo() {
        return tipo;
    }
    
    /**
     * Obtiene la hora de entrada
     * @return Hora de entrada
     */
    public LocalDateTime getHoraEntrada() {
        return horaEntrada;
    }
    
    /**
     * Obtiene la hora de entrada formateada
     * @return Hora de entrada formateada
     */
    public String getHoraEntradaFormateada() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return horaEntrada.format(formatter);
    }
}

