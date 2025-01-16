/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.time.*;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author christianrieigl
 */
import java.time.LocalDate;
import java.time.LocalTime;

public class Ticket {
    private static int contador = 0;
    private String codigo;
    private LocalDate fechaEntrada;
    private LocalTime horaEntrada;

    public Ticket() {
        contador++;
        this.codigo = "Codigo: " + contador;
        this.fechaEntrada = LocalDate.now();
        this.horaEntrada = LocalTime.now();
    }

    public String getCodigo() {
        return codigo;
    }

    public LocalDate getFechaEntrada() {
        return fechaEntrada;
    }

    public LocalTime getHoraEntrada() {
        return horaEntrada;
    }
}

