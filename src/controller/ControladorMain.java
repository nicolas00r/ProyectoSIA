package controller;

import java.awt.event.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import model.*;
import view.*;

/**
 * Clase ControladorMain es responsable de coordinar las interacciones entre el 
 * sistema de gestión de servicios para mascotas y las diferentes ventanas de la interfaz gráfica (GUI).
 * Implementa la interfaz {@link java.awt.event.ActionListener} para manejar los eventos de acción.
 * 
 * La clase permite gestionar la creación, modificación y eliminación de clientes, mascotas y citas,
 * así como mostrar información de clientes, mascotas y citas registradas. 
 * También facilita la persistencia de datos y la exportación de información a archivos de texto.
 * 
 * @author 12212
 */
public class ControladorMain implements ActionListener {

    /** Sistema de gestión de servicios para mascotas */
    private PetServiceManagement sistema;

    /** Ventana principal de la aplicación */
    private VentanaPrincipal main;

    /** Ventanas relacionadas con la gestión de clientes */
    private VentanaRegistrarCliente registrarCliente;
    private VentanaModificarCliente modificarCliente;
    private VentanaEliminarCliente eliminarCliente;

    /** Ventanas relacionadas con la gestión de mascotas */
    private VentanaRegistrarMascota registrarMascota;
    private VentanaSeleccionarCliente modificarMascota;
    private VentanaSeleccionarCliente eliminarMascota;

    /** Ventanas relacionadas con la gestión de citas */
    private VentanaSeleccionarCliente realizarCita;
    private VentanaSeleccionarCliente eliminarCita;

    /** Ventanas de visualización de información */
    private VentanaMostrarClientes mostrarClientes;
    private VentanaMostrarMascotas mostrarMascotas;
    private VentanaMostrarCitas mostrarServicios;

    /** Subventanas para operaciones específicas en clientes, mascotas y citas */
    private SubVentanaModificarCliente subModificarCliente;
    private SubVentanaRegistrarMascota subRegistrarMascota;
    private VentanaSeleccionarMascota subModificarMascota;
    private SubVentanaModificarMascota2 subModificarMascota2;
    private VentanaSeleccionarMascota subEliminarMascota;
    private VentanaSeleccionarMascota subRealizarCita;
    private SubVentanaRealizarCita subRealizarCita2;
    private VentanaSeleccionarMascota subEliminarCita;
    private VentanaEliminarCita subEliminarCita2;
    private VentanaBuscarCitasFechas buscarCitasFechas;
    private VentanaMostrarCitas subBuscarCitasFechas;

    /**
     * Método principal para iniciar la aplicación. Inicializa el sistema de gestión de servicios para mascotas,
     * configura la ventana principal y registra los ActionListener para manejar las interacciones de la GUI.
     * También añade un shutdown hook para guardar los datos al cerrar la aplicación.
     */
    public void iniciar() {
        sistema = new PetServiceManagement();
        main = new VentanaPrincipal();

        // Asignación de ActionListeners para cada botón de la ventana principal
        main.getRegistrarCliente().addActionListener(this);
        main.getModificarCliente().addActionListener(this);
        main.getEliminarCliente().addActionListener(this);
        main.getRegistrarMascota().addActionListener(this);
        main.getModificarMascota().addActionListener(this);
        main.getEliminarMascota().addActionListener(this);
        main.getRealizarCita().addActionListener(this);
        main.getEliminarCita().addActionListener(this);
        main.getMostrarClientes().addActionListener(this);
        main.getMostrarMascotas().addActionListener(this);
        main.getMostrarHistorialServicios().addActionListener(this);
        main.getBuscarCitasFechas().addActionListener(this);
        main.getSalirGuadar().addActionListener(this);

        main.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        main.setVisible(true);

        // Hook para guardar los datos del sistema al cerrar la aplicación
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                sistema.guardarDatos();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }));
    }

    /**
     * Método que maneja todos los eventos de acción generados por los componentes de la interfaz gráfica.
     * Realiza la acción correspondiente según el botón presionado.
     * 
     * @param ae El evento de acción que fue generado.
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        // Registrar cliente
        if (ae.getSource() == main.getRegistrarCliente()) {
            registrarCliente = new VentanaRegistrarCliente();
            registrarCliente.getButtonAgregar().addActionListener(this);
            registrarCliente.setAlwaysOnTop(true);
            registrarCliente.setVisible(true);
            return;
        }

        // Acciones para registrar un cliente en el sistema
        if (registrarCliente != null && ae.getSource() == registrarCliente.getButtonAgregar()) {
            try {
                Cliente c = new Cliente();

                // Captura de datos de la ventana de registro de cliente
                String nombre = registrarCliente.getTextNombre().getText();
                String rut = registrarCliente.getTextRut().getText();
                String telefono = registrarCliente.getTextTelefono().getText();
                String direccion = registrarCliente.getTextDireccion().getText();
                String correo = registrarCliente.getTextCorreo().getText();

                // Validaciones
                if (nombre.isEmpty() || rut.isEmpty() || telefono.isEmpty() || direccion.isEmpty() || correo.isEmpty()) {
                    throw new IllegalArgumentException("Todos los campos son obligatorios.");
                }

                if (!rut.matches("[0-9]+-[0-9Kk]")) {
                    throw new IllegalArgumentException("Formato de RUT inválido.");
                }

                // Asignación de valores al cliente
                c.setNombre(nombre);
                c.setRut(rut);
                c.setNumeroTelefono(telefono);
                c.setDireccion(direccion);
                c.setCorreoElectronico(correo);

                // Registro del cliente en el sistema
                sistema.registrarCliente(c);
                JOptionPane.showMessageDialog(null, "Cliente registrado correctamente.");
                registrarCliente.dispose();

            } catch (IllegalArgumentException e) {
                // Manejo de errores de validación
                JOptionPane.showMessageDialog(null, "Error al registrar cliente: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception e) {
                // Manejo de otros errores no previstos
                JOptionPane.showMessageDialog(null, "Ocurrió un error inesperado: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        // Modificar cliente
        if (ae.getSource() == main.getModificarCliente()) {
            modificarCliente = new VentanaModificarCliente(sistema.entregarListadoClientes());
            modificarCliente.getButtonModificarCliente().addActionListener(this);
            modificarCliente.setVisible(true);
            return;
        }

        // Confirmar modificación de cliente
        if (modificarCliente != null && ae.getSource() == modificarCliente.getButtonModificarCliente()) {
            String clienteSeleccionado = modificarCliente.getTextRutModificar().getText();
            Cliente c = sistema.obtenerCliente(clienteSeleccionado);

            if (clienteSeleccionado != null) {
                subModificarCliente = new SubVentanaModificarCliente(c.toString());
                subModificarCliente.getButtonConfirmar().addActionListener(this);
                subModificarCliente.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Seleccione un cliente para modificar.");
            }
            return;
        }

        // Confirmar modificación final del cliente
        if (subModificarCliente != null && ae.getSource() == subModificarCliente.getButtonConfirmar()) {
            Cliente c = sistema.obtenerCliente(subModificarCliente.getClienteOriginal());
            String rutOriginal = c.getRut();
            c.setNombre(subModificarCliente.getTextNombre().getText());
            c.setRut(subModificarCliente.getTextRut().getText());
            c.setNumeroTelefono(subModificarCliente.getTextTelefono().getText());
            c.setDireccion(subModificarCliente.getTextDireccion().getText());
            c.setCorreoElectronico(subModificarCliente.getTextCorreo().getText());
            modificarCliente.actualizarFila(c.toString(), rutOriginal);
            JOptionPane.showMessageDialog(null, "Cliente modificado correctamente.");
            subModificarCliente.dispose();
        }

        // Eliminar cliente
        if (ae.getSource() == main.getEliminarCliente()) {
            eliminarCliente = new VentanaEliminarCliente(sistema.entregarListadoClientes());
            eliminarCliente.getButtonEliminarCliente().addActionListener(this);
            eliminarCliente.setVisible(true);
            return;
        }

        // Confirmar eliminación de cliente
        if (eliminarCliente != null && ae.getSource() == eliminarCliente.getButtonEliminarCliente()) {
            String clienteSeleccionado = eliminarCliente.getClienteSeleccionado();

            if (clienteSeleccionado != null) {
                sistema.eliminarCliente(clienteSeleccionado);
                eliminarCliente.eliminarFilaSeleccionada();
                JOptionPane.showMessageDialog(null, "Cliente eliminado correctamente.");
            } else {
                JOptionPane.showMessageDialog(null, "Seleccione un cliente para eliminar.");
            }
            return;
        }

        // Registrar mascota
        if (ae.getSource() == main.getRegistrarMascota()) {
            registrarMascota = new VentanaRegistrarMascota(sistema.entregarListadoClientes());
            registrarMascota.getButtonSeleccionarCliente().addActionListener(this);
            registrarMascota.setVisible(true);
            return;
        }

        // Acciones para seleccionar cliente en la ventana de registro de mascotas
        if (registrarMascota != null && ae.getSource() == registrarMascota.getButtonSeleccionarCliente()) {
            String clienteSeleccionado = registrarMascota.getTextRut().getText();
            Cliente c = sistema.obtenerCliente(clienteSeleccionado);

            if (clienteSeleccionado != null) {
                registrarMascota.dispose();
                subRegistrarMascota = new SubVentanaRegistrarMascota(c.getNombre(), c.getRut());
                subRegistrarMascota.getButtonRegistrar().addActionListener(this);
                subRegistrarMascota.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Seleccione un cliente válido.");
            }
            return;
        }

        // Acciones para registrar una mascota en el sistema
        if (subRegistrarMascota != null && ae.getSource() == subRegistrarMascota.getButtonRegistrar()) {
            try {
                Cliente c = sistema.obtenerCliente(subRegistrarMascota.getRutDueño());
                String nombreMascota = subRegistrarMascota.getTextNombre().getText();
                String especie = subRegistrarMascota.getTextEspecie().getText();
                String edadTexto = subRegistrarMascota.getTextEdad().getText();

                // Validaciones
                if (nombreMascota.isEmpty() || especie.isEmpty() || edadTexto.isEmpty()) {
                    throw new IllegalArgumentException("Todos los campos son obligatorios.");
                }

                int edad = Integer.parseInt(edadTexto);
                if (edad < 0) {
                    throw new IllegalArgumentException("La edad no puede ser negativa.");
                }

                if (subRegistrarMascota.getCheckRiesgo().isSelected() && subRegistrarMascota.getCheckExotica().isSelected()) {
                    throw new IllegalArgumentException("No puedes seleccionar 'Riesgo' y 'Exótica' al mismo tiempo.");
                }

                // Registro de la mascota en el sistema
                if (subRegistrarMascota.getCheckRiesgo().isSelected()) {
                    MascotaDeRiesgo m = new MascotaDeRiesgo();
                    m.setNombreDueño(subRegistrarMascota.getNombreDueño());
                    m.setNombreMascota(nombreMascota);
                    m.setEspecie(especie);
                    m.setEdad(edadTexto);
                    m.setCondicion(subRegistrarMascota.getTextCondicion().getText());
                    sistema.agregarMascota(c, m);
                } else if (subRegistrarMascota.getCheckExotica().isSelected()) {
                    MascotaExotica m = new MascotaExotica();
                    m.setNombreDueño(subRegistrarMascota.getNombreDueño());
                    m.setNombreMascota(nombreMascota);
                    m.setEspecie(especie);
                    m.setEdad(edadTexto);
                    m.setHabitat(subRegistrarMascota.getTextHabitat().getText());
                    m.setNivelPeligrosidad(subRegistrarMascota.getTextPeligrosidad().getText());
                    sistema.agregarMascota(c, m);
                } else {
                    Mascota m = new Mascota();
                    m.setNombreDueño(subRegistrarMascota.getNombreDueño());
                    m.setNombreMascota(nombreMascota);
                    m.setEspecie(especie);
                    m.setEdad(edadTexto);
                    sistema.agregarMascota(c, m);
                }

                JOptionPane.showMessageDialog(null, "Mascota registrada correctamente.");
                subRegistrarMascota.dispose();

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "La edad debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(null, "Error al registrar mascota: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Ocurrió un error inesperado: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
            return;
        }

        // Otras acciones relacionadas con modificación y eliminación de mascotas, gestión de citas, entre otras, irían aquí...

        // Exportar datos a archivo de texto
        if (ae.getSource() == main.getSalirGuadar()) {
            exportarTXT();
            sistema.guardarDatos();
            System.exit(0);
        }
    }

    /**
     * Método que exporta la lista de clientes a un archivo de texto.
     * El archivo generado contiene información detallada de todos los clientes registrados.
     */
    public void exportarTXT() {
        sistema.exportClientesToFile(sistema.entregarListadoClientes(), "Reporte.txt");
    }
}
