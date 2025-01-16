/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.util.Scanner;

/**
 *
 * @author christianrieigl
 */
import java.util.Scanner;

public class Parking {
    private Plaza[][] plazas; // Array bidimensional de plazas (pisos y plazas por piso)

    public Parking() {
        // Constructor por defecto
    }

    public Parking(int numPisos, int numPlazasPorPiso) {
        plazas = new Plaza[numPisos][numPlazasPorPiso];
        for (int piso = 0; piso < numPisos; piso++) {
            for (int plaza = 0; plaza < numPlazasPorPiso; plaza++) {
                Plaza nuevaPlaza = new Plaza("P" + (piso + 1) + "_" + (plaza + 1));
                if (plaza < 5) {
                    nuevaPlaza.setTipoPermitido("coche");
                } else if (plaza < 8) {
                    nuevaPlaza.setTipoPermitido("moto");
                } else {
                    nuevaPlaza.setTipoPermitido("camion");
                }
                plazas[piso][plaza] = nuevaPlaza;
            }
        }
    }

    public void generarTicket(Scanner s) {
        System.out.println("¡Bienvenido al parking Monlau!");
        System.out.println("Introduce el tipo de vehículo (coche, moto, camion): ");
        String tipo = s.next().trim().toLowerCase();
        System.out.println("Introduce la matrícula del vehículo: ");
        String matricula = s.next().trim();
        System.out.println("Introduce el color del vehículo: ");
        String color = s.next().trim();

        Vehiculo vehiculo;
        switch (tipo) {
            case "coche":
                vehiculo = new Coche(matricula, color);
                break;
            case "moto":
                vehiculo = new Moto(matricula, color);
                break;
            case "camion":
                vehiculo = new Camion(matricula, color);
                break;
            default:
                System.out.println("Tipo de vehículo no válido.");
                return;
        }

        Plaza plazaAsignada = asignarVehiculo(vehiculo);
        if (plazaAsignada != null) {
            Ticket ticket1 = new Ticket();
            System.out.println("          TICKET de ENTRADA");
            System.out.println("----------------------------------------");
            System.out.println("            Parking Monlau");
            System.out.println("         C/ Monlau 6, Barcelona");
            System.out.println("             +34 666 66 66");
            System.out.println("         " + ticket1.getCodigo());
            System.out.println("----------------------------------------");
            System.out.println("   Fecha de Entrada: " + ticket1.getFechaEntrada());
            System.out.println("    Hora de Entrada: " + ticket1.getHoraEntrada());
            System.out.println("Matrícula: " + vehiculo.getMatricula());
            System.out.println("Plaza asignada: " + plazaAsignada.getNumeroPlaza());
        } else {
            System.out.println("No hay plazas disponibles para este tipo de vehículo.");
        }
    }

    public Plaza asignarVehiculo(Vehiculo vehiculo) {
        for (int piso = 0; piso < plazas.length; piso++) {
            for (int plaza = 0; plaza < plazas[piso].length; plaza++) {
                if (plazas[piso][plaza].isDisponible() && plazas[piso][plaza].esPermitido(vehiculo.getTipo())) {
                    plazas[piso][plaza].asignarVehiculo(vehiculo);
                    return plazas[piso][plaza];
                }
            }
        }
        return null;
    }

    public void retirarVehiculo(String numeroPlaza) {
        for (int piso = 0; piso < plazas.length; piso++) {
            for (int plaza = 0; plaza < plazas[piso].length; plaza++) {
                if (plazas[piso][plaza].getNumeroPlaza().equals(numeroPlaza)) {
                    Plaza plazaSeleccionada = plazas[piso][plaza];
                    double tarifa = plazaSeleccionada.getVehiculo().calcularTarifa();
                    plazaSeleccionada.eliminarVehiculo();
                    System.out.println("Vehículo retirado de la plaza " + numeroPlaza + ". Tarifa: " + tarifa + " €");
                    return;
                }
            }
        }
        System.out.println("Plaza no encontrada.");
    }

    public void verPlazasDisponibles() {
        System.out.println("Plazas disponibles:");
        for (int piso = 0; piso < plazas.length; piso++) {
            for (int plaza = 0; plaza < plazas[piso].length; plaza++) {
                if (plazas[piso][plaza].isDisponible()) {
                    System.out.println("Piso " + (piso + 1) + " - Plaza " + plazas[piso][plaza].getNumeroPlaza());
                }
            }
        }
    }

    public void verPlazasDisponiblesPorTipo(String tipoVehiculo) {
        System.out.println("Plazas disponibles para " + tipoVehiculo + ":");
        for (int piso = 0; piso < plazas.length; piso++) {
            for (int plaza = 0; plaza < plazas[piso].length; plaza++) {
                if (plazas[piso][plaza].isDisponible() && plazas[piso][plaza].esPermitido(tipoVehiculo)) {
                    System.out.println("Piso " + (piso + 1) + " - Plaza " + plazas[piso][plaza].getNumeroPlaza());
                }
            }
        }
    }

    public void verPlazasOcupadasPorTipo(String tipoVehiculo) {
        System.out.println("Plazas ocupadas por " + tipoVehiculo + ":");
        for (int piso = 0; piso < plazas.length; piso++) {
            for (int plaza = 0; plaza < plazas[piso].length; plaza++) {
                if (!plazas[piso][plaza].isDisponible() && plazas[piso][plaza].getVehiculo().getTipo().equals(tipoVehiculo)) {
                    System.out.println("Piso " + (piso + 1) + " - Plaza " + plazas[piso][plaza].getNumeroPlaza());
                }
            }
        }
    }
}


