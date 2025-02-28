package main;

import gui.ParkingGUI;
import modelo.Parking;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Clase principal para iniciar la aplicación
 */
public class Main {
    
    /**
     * Método principal
     * @param args Argumentos de línea de comandos
     */
    public static void main(String[] args) {
        // Intentar establecer el look and feel del sistema
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | 
                IllegalAccessException | UnsupportedLookAndFeelException e) {
            System.out.println("Error al establecer el look and feel: " + e.getMessage());
        }
        
        // Crear un parking con 20 plazas
        final Parking parking = new Parking(20);
        
        // Iniciar la interfaz gráfica
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ParkingGUI gui = new ParkingGUI(parking);
                gui.setVisible(true);
            }
        });
    }
}

