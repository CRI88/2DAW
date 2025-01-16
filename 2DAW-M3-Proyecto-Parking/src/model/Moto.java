/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author christianrieigl
 */
public class Moto extends Vehiculo {
    public Moto(String matricula, String color) {
        super(matricula, color);
        this.tipo = "moto";
    }

    @Override
    public double calcularTarifa() {
        return 0.10; // Tarifa por minuto
    }
}
