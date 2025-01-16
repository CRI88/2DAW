/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.util.Scanner;
import model.Camion;
import model.Coche;
import model.Moto;
import model.Parking;
import model.Plaza;
import model.Ticket;
import model.Vehiculo;

/**
 *
 * @author christianrieigl
 */
import java.util.Scanner;

public class Main {
    static Scanner s = new Scanner(System.in);
    static Parking parking = new Parking(5, 10);

    public static void main(String[] args) {
        s.useDelimiter("\n");
        int optionMain;
        do {
            System.out.println("Menu:");
            System.out.println("[1] - Generar ticket");
            System.out.println("[2] - Pagar y retirar vehículo");
            System.out.println("[3] - Lista plazas disponibles");
            System.out.println("[4] - Ver plazas disponibles por tipo");
            System.out.println("[5] - Ver plazas ocupadas por tipo");
            System.out.println("[0] - Exit");

            System.out.print("Choose an option: ");
            optionMain = s.nextInt();
            switch (optionMain) {
                case 1:
                    generarTicketNuevo();
                    break;
                case 2:
                    pagarYRetirarVehiculo();
                    break;
                case 3:
                    verPlazasDisponibles();
                    break;
                case 4:
                    verPlazasDisponiblesPorTipo();
                    break;
                case 5:
                    verPlazasOcupadasPorTipo();
                    break;
                case 0:
                    System.out.println("End Program");
                    break;
                default:
                    System.out.println("Incorrect option. Try again");
            }
        } while (optionMain != 0);
    }

    static void generarTicketNuevo() {
        parking.generarTicket(s);
    }

    static void pagarYRetirarVehiculo() {
        System.out.print("Introduce el número de la plaza: ");
        String numeroPlaza = s.next();
        parking.retirarVehiculo(numeroPlaza);
    }

    static void verPlazasDisponibles() {
        parking.verPlazasDisponibles();
    }

    static void verPlazasDisponiblesPorTipo() {
        System.out.print("Introduce el tipo de vehículo (coche, moto, camion): ");
        String tipo = s.next();
        parking.verPlazasDisponiblesPorTipo(tipo);
    }

    static void verPlazasOcupadasPorTipo() {
        System.out.print("Introduce el tipo de vehículo (coche, moto, camion): ");
        String tipo = s.next();
        parking.verPlazasOcupadasPorTipo(tipo);
    }
}

