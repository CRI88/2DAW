package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;
import modelo.Parking;
import modelo.Plaza;
import modelo.Vehiculo;
import excepciones.PlazaOcupadaException;
import excepciones.VehiculoNoEncontradoException;
import java.text.ParseException;

/**
 * Ventana para gestionar la entrada y salida de vehículos
 */
public class GestionVehiculoGUI extends JDialog {
    
    private Parking parking;
    private ParkingGUI ventanaPrincipal;
    private boolean modoEntrada;
    
    private JTextField txtMatricula;
    private JRadioButton rbCoche;
    private JRadioButton rbMoto;
    private JRadioButton rbFurgoneta;
    private JTextField txtNumeroPlaza;
    private JButton btnAceptar;
    private JButton btnCancelar;
    
    /**
     * Constructor de la ventana de gestión de vehículos
     * @param parent Ventana principal
     * @param parking Objeto Parking a gestionar
     * @param modoEntrada true para entrada, false para salida
     */
    public GestionVehiculoGUI(ParkingGUI parent, Parking parking, boolean modoEntrada) {
        super(parent, modoEntrada ? "Registrar Entrada de Vehículo" : "Registrar Salida de Vehículo", true);
        this.parking = parking;
        this.ventanaPrincipal = parent;
        this.modoEntrada = modoEntrada;
        
        // Configuración básica del JDialog
        setSize(400, 300);
        setLocationRelativeTo(parent);
        setResizable(false);
        
        // Inicializar componentes
        inicializarComponentes();
    }
    
    /**
     * Inicializa los componentes de la interfaz
     */
    private void inicializarComponentes() {
        // Panel principal con BorderLayout
        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        // Panel de formulario con GridLayout
        JPanel panelFormulario = new JPanel(new GridLayout(0, 2, 5, 10));
        panelFormulario.setBorder(BorderFactory.createTitledBorder(
                modoEntrada ? "Datos de Entrada" : "Datos de Salida"));
        
        // Componentes del formulario
        JLabel lblMatricula = new JLabel("Matrícula:");
        
        try {
            MaskFormatter formatoMatricula = new MaskFormatter("####AAA");
            formatoMatricula.setPlaceholderCharacter('_');
            txtMatricula = new JFormattedTextField(formatoMatricula);
        } catch (ParseException e) {
            // Si hay error con el formato, usar un JTextField normal
            txtMatricula = new JTextField();
        }
        
        panelFormulario.add(lblMatricula);
        panelFormulario.add(txtMatricula);
        
        // Si es modo entrada, mostrar opciones de tipo de vehículo
        if (modoEntrada) {
            JLabel lblTipo = new JLabel("Tipo de vehículo:");
            JPanel panelTipos = new JPanel(new FlowLayout(FlowLayout.LEFT));
            
            rbCoche = new JRadioButton("Coche");
            rbMoto = new JRadioButton("Moto");
            rbFurgoneta = new JRadioButton("Furgoneta");
            
            ButtonGroup grupoTipos = new ButtonGroup();
            grupoTipos.add(rbCoche);
            grupoTipos.add(rbMoto);
            grupoTipos.add(rbFurgoneta);
            
            rbCoche.setSelected(true);
            
            panelTipos.add(rbCoche);
            panelTipos.add(rbMoto);
            panelTipos.add(rbFurgoneta);
            
            panelFormulario.add(lblTipo);
            panelFormulario.add(panelTipos);
            
            JLabel lblNumeroPlaza = new JLabel("Número de plaza:");
            txtNumeroPlaza = new JTextField();
            
            panelFormulario.add(lblNumeroPlaza);
            panelFormulario.add(txtNumeroPlaza);
        }
        
        // Panel de botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        
        btnAceptar = new JButton(modoEntrada ? "Registrar Entrada" : "Registrar Salida");
        btnCancelar = new JButton("Cancelar");
        
        // Configurar acciones de los botones
        btnAceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (modoEntrada) {
                    registrarEntrada();
                } else {
                    registrarSalida();
                }
            }
        });
        
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        
        panelBotones.add(btnAceptar);
        panelBotones.add(btnCancelar);
        
        // Añadir paneles al panel principal
        panelPrincipal.add(panelFormulario, BorderLayout.CENTER);
        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);
        
        // Añadir el panel principal al JDialog
        setContentPane(panelPrincipal);
    }
    
    /**
     * Registra la entrada de un vehículo
     */
    private void registrarEntrada() {
        // Validar campos
        if (txtMatricula.getText().trim().isEmpty() || 
                (txtNumeroPlaza.getText().trim().isEmpty())) {
            JOptionPane.showMessageDialog(this, 
                    "Todos los campos son obligatorios", 
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Obtener datos del formulario
        String matricula = txtMatricula.getText().trim();
        String tipo = rbCoche.isSelected() ? "Coche" : 
                     (rbMoto.isSelected() ? "Moto" : "Furgoneta");
        
        int numeroPlaza;
        try {
            numeroPlaza = Integer.parseInt(txtNumeroPlaza.getText().trim());
            
            // Validar que el número de plaza sea válido
            if (numeroPlaza < 0 || numeroPlaza >= parking.getNumeroTotalPlazas()) {
                JOptionPane.showMessageDialog(this, 
                        "El número de plaza debe estar entre 0 y " + 
                        (parking.getNumeroTotalPlazas() - 1), 
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, 
                    "El número de plaza debe ser un valor numérico", 
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Crear vehículo
        Vehiculo vehiculo = new Vehiculo(matricula, tipo, LocalDateTime.now());
        
        try {
            // Intentar aparcar el vehículo
            parking.aparcarVehiculo(vehiculo, numeroPlaza);
            
            // Mostrar mensaje de éxito
            JOptionPane.showMessageDialog(this, 
                    "Vehículo registrado correctamente en la plaza " + numeroPlaza, 
                    "Entrada Registrada", JOptionPane.INFORMATION_MESSAGE);
            
            // Actualizar la visualización del parking
            ventanaPrincipal.actualizarVisualizacionParking();
            
            // Cerrar la ventana
            dispose();
            
        } catch (PlazaOcupadaException e) {
            JOptionPane.showMessageDialog(this, 
                    "Error: " + e.getMessage(), 
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Registra la salida de un vehículo
     */
    private void registrarSalida() {
        // Validar campos
        if (txtMatricula.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                    "Debe introducir la matrícula del vehículo", 
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Obtener matrícula
        String matricula = txtMatricula.getText().trim();
        
        try {
            // Buscar el vehículo y calcular el importe
            double importe = parking.calcularImporte(matricula);
            
            // Mostrar el importe y confirmar la salida
            int opcion = JOptionPane.showConfirmDialog(this, 
                    "El importe a pagar es: " + String.format("%.2f", importe) + " €\n" +
                    "¿Desea confirmar la salida del vehículo?", 
                    "Confirmar Salida", JOptionPane.YES_NO_OPTION);
            
            if (opcion == JOptionPane.YES_OPTION) {
                // Registrar la salida
                parking.retirarVehiculo(matricula);
                
                // Mostrar mensaje de éxito
                JOptionPane.showMessageDialog(this, 
                        "Vehículo retirado correctamente.\nImporte pagado: " + 
                        String.format("%.2f", importe) + " €", 
                        "Salida Registrada", JOptionPane.INFORMATION_MESSAGE);
                
                // Actualizar la visualización del parking
                ventanaPrincipal.actualizarVisualizacionParking();
                
                // Cerrar la ventana
                dispose();
            }
            
        } catch (VehiculoNoEncontradoException e) {
            JOptionPane.showMessageDialog(this, 
                    "Error: " + e.getMessage(), 
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

