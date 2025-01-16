/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author christianrieigl
 */
public class Camion extends Vehiculo {
    public Camion(String matricula, String color) {
        super(matricula, color);
        this.tipo = "camion";
    }

    @Override
    public double calcularTarifa() {
        return 0.20; // Tarifa por minuto
    }
}
