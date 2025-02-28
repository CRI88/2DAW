package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import modelo.Plaza;
import modelo.Parking;
import modelo.Vehiculo;
import excepciones.PlazaOcupadaException;
import excepciones.VehiculoNoEncontradoException;

/**
 * Interfaz gráfica principal para la gestión del parking
 */
public class ParkingGUI extends JFrame {
    
    private Parking parking;
    private JPanel panelPlazas;
    private JLabel lblEstado;
    private JButton btnEntrada;
    private JButton btnSalida;
    private JButton btnEstadisticas;
    private ArrayList<JPanel> panelesPlazas;
    
    /**
     * Constructor de la interfaz gráfica del parking
     * @param parking Objeto Parking que se va a gestionar
     */
    public ParkingGUI(Parking parking) {
        this.parking = parking;
        this.panelesPlazas = new ArrayList<>();
        
        // Configuración básica del JFrame
        setTitle("Gestión de Parking");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        
        // Inicializar componentes
        inicializarComponentes();
        
        // Actualizar la visualización del parking
        actualizarVisualizacionParking();
    }
    
    /**
     * Inicializa todos los componentes de la interfaz
     */
    private void inicializarComponentes() {
        // Panel principal con BorderLayout
        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        // Panel superior con información del estado
        JPanel panelSuperior = new JPanel(new BorderLayout());
        JLabel lblTitulo = new JLabel("Sistema de Gestión de Parking");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setHorizontalAlignment(JLabel.CENTER);
        
        lblEstado = new JLabel("Plazas disponibles: " + parking.getPlazasDisponibles() + " / " + parking.getNumeroTotalPlazas());
        lblEstado.setFont(new Font("Arial", Font.PLAIN, 14));
        
        panelSuperior.add(lblTitulo, BorderLayout.NORTH);
        panelSuperior.add(lblEstado, BorderLayout.SOUTH);
        
        // Panel central con la visualización de plazas
        panelPlazas = new JPanel();
        int filas = (int) Math.ceil(Math.sqrt(parking.getNumeroTotalPlazas()));
        int columnas = (int) Math.ceil((double) parking.getNumeroTotalPlazas() / filas);
        panelPlazas.setLayout(new GridLayout(filas, columnas, 5, 5));
        
        // Panel inferior con botones de acción
        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        
        btnEntrada = new JButton("Registrar Entrada");
        btnSalida = new JButton("Registrar Salida");
        btnEstadisticas = new JButton("Estadísticas");
        
        // Configurar acciones de los botones
        btnEntrada.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarVentanaEntrada();
            }
        });
        
        btnSalida.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarVentanaSalida();
            }
        });
        
        btnEstadisticas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarEstadisticas();
            }
        });
        
        panelInferior.add(btnEntrada);
        panelInferior.add(btnSalida);
        panelInferior.add(btnEstadisticas);
        
        // Añadir todos los paneles al panel principal
        panelPrincipal.add(panelSuperior, BorderLayout.NORTH);
        panelPrincipal.add(panelPlazas, BorderLayout.CENTER);
        panelPrincipal.add(panelInferior, BorderLayout.SOUTH);
        
        // Añadir el panel principal al JFrame
        setContentPane(panelPrincipal);
    }
    
    /**
     * Actualiza la visualización gráfica del estado del parking
     */
    public void actualizarVisualizacionParking() {
        // Limpiar el panel de plazas y la lista de paneles
        panelPlazas.removeAll();
        panelesPlazas.clear();
        
        // Crear un panel para cada plaza
        for (int i = 0; i < parking.getNumeroTotalPlazas(); i++) {
            Plaza plaza = parking.getPlaza(i);
            JPanel panelPlaza = crearPanelPlaza(plaza);
            panelesPlazas.add(panelPlaza);
            panelPlazas.add(panelPlaza);
        }
        
        // Actualizar la etiqueta de estado
        lblEstado.setText("Plazas disponibles: " + parking.getPlazasDisponibles() + " / " + parking.getNumeroTotalPlazas());
        
        // Refrescar la visualización
        panelPlazas.revalidate();
        panelPlazas.repaint();
    }
    
    /**
     * Crea un panel visual para representar una plaza de parking
     * @param plaza Plaza a representar
     * @return Panel configurado para la plaza
     */
    private JPanel crearPanelPlaza(Plaza plaza) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setPreferredSize(new Dimension(100, 80));
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
        // Etiqueta con el número de plaza
        JLabel lblNumero = new JLabel("Plaza " + plaza.getNumero());
        lblNumero.setHorizontalAlignment(JLabel.CENTER);
        
        // Etiqueta con información del vehículo (si hay)
        JLabel lblInfo = new JLabel();
        lblInfo.setHorizontalAlignment(JLabel.CENTER);
        
        if (plaza.isOcupada()) {
            panel.setBackground(Color.RED);
            Vehiculo vehiculo = plaza.getVehiculo();
            lblInfo.setText(vehiculo.getMatricula());
        } else {
            panel.setBackground(Color.GREEN);
            lblInfo.setText("Libre");
        }
        
        panel.add(lblNumero, BorderLayout.NORTH);
        panel.add(lblInfo, BorderLayout.CENTER);
        
        // Añadir listener para mostrar detalles al hacer clic
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mostrarDetallesPlaza(plaza);
            }
        });
        
        return panel;
    }
    
    /**
     * Muestra los detalles de una plaza al hacer clic en ella
     * @param plaza Plaza seleccionada
     */
    private void mostrarDetallesPlaza(Plaza plaza) {
        StringBuilder mensaje = new StringBuilder();
        mensaje.append("Información de la Plaza ").append(plaza.getNumero()).append("\n\n");
        
        if (plaza.isOcupada()) {
            Vehiculo vehiculo = plaza.getVehiculo();
            mensaje.append("Estado: OCUPADA\n");
            mensaje.append("Matrícula: ").append(vehiculo.getMatricula()).append("\n");
            mensaje.append("Tipo: ").append(vehiculo.getTipo()).append("\n");
            mensaje.append("Hora de entrada: ").append(vehiculo.getHoraEntrada()).append("\n");
        } else {
            mensaje.append("Estado: LIBRE\n");
        }
        
        JOptionPane.showMessageDialog(this, mensaje.toString(), "Detalles de Plaza", JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * Muestra la ventana para registrar la entrada de un vehículo
     */
    private void mostrarVentanaEntrada() {
        GestionVehiculoGUI ventanaEntrada = new GestionVehiculoGUI(this, parking, true);
        ventanaEntrada.setVisible(true);
    }
    
    /**
     * Muestra la ventana para registrar la salida de un vehículo
     */
    private void mostrarVentanaSalida() {
        GestionVehiculoGUI ventanaSalida = new GestionVehiculoGUI(this, parking, false);
        ventanaSalida.setVisible(true);
    }
    
    /**
     * Muestra estadísticas del parking
     */
    private void mostrarEstadisticas() {
        StringBuilder mensaje = new StringBuilder();
        mensaje.append("Estadísticas del Parking\n\n");
        mensaje.append("Total de plazas: ").append(parking.getNumeroTotalPlazas()).append("\n");
        mensaje.append("Plazas ocupadas: ").append(parking.getNumeroTotalPlazas() - parking.getPlazasDisponibles()).append("\n");
        mensaje.append("Plazas disponibles: ").append(parking.getPlazasDisponibles()).append("\n");
        mensaje.append("Porcentaje de ocupación: ").append(
                String.format("%.2f", (double)(parking.getNumeroTotalPlazas() - parking.getPlazasDisponibles()) / 
                        parking.getNumeroTotalPlazas() * 100)).append("%\n");
        
        JOptionPane.showMessageDialog(this, mensaje.toString(), "Estadísticas", JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * Método principal para probar la interfaz
     */
    public static void main(String[] args) {
        // Crear un parking de prueba con 20 plazas
        Parking parking = new Parking(20);
        
        // Lanzar la interfaz gráfica
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ParkingGUI gui = new ParkingGUI(parking);
                gui.setVisible(true);
            }
        });
    }
}

