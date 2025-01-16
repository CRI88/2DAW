/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author christianrieigl
 */
public class Plaza {
    private boolean disponible;
    private String numeroPlaza;
    private String tipoPermitido;
    private Vehiculo vehiculo;

    public Plaza(String numeroPlaza) {
        this.numeroPlaza = numeroPlaza;
        this.disponible = true;
        this.vehiculo = null;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void asignarVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
        this.disponible = false;
    }

    public void eliminarVehiculo() {
        this.vehiculo = null;
        this.disponible = true;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public String getNumeroPlaza() {
        return numeroPlaza;
    }

    public String getTipoPermitido() {
        return tipoPermitido;
    }

    public void setTipoPermitido(String tipoPermitido) {
        this.tipoPermitido = tipoPermitido;
    }

    public boolean esPermitido(String tipoVehiculo) {
        return tipoVehiculo.equalsIgnoreCase(tipoPermitido);
    }
}
