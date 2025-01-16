/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author christianrieigl
 */
public abstract class Vehiculo {
    protected String tipo;
    protected String matricula;
    protected String color;

    public Vehiculo(String matricula, String color) {
        this.matricula = matricula;
        this.color = color;
    }

    public String getTipo() {
        return tipo;
    }

    public String getMatricula() {
        return matricula;
    }

    public abstract double calcularTarifa();
}