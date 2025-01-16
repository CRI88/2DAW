/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author christianrieigl
 */
public class Coche extends Vehiculo {
    public Coche(String matricula, String color) {
        super(matricula, color);
        this.tipo = "coche";
    }

    @Override
    public double calcularTarifa() {
        return 0.15; // Tarifa por minuto
    }
}
