/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import java.awt.event.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import model.*;
import view.*;

/**
 *
 * @author 12212
 */
public class ControladorMain implements ActionListener{
    private PetServiceManagement sistema;
    private VentanaPrincipal main;
    private VentanaRegistrarCliente registrarCliente;
    private VentanaModificarCliente modificarCliente;
    private VentanaEliminarCliente eliminarCliente;
    private VentanaRegistrarMascota registrarMascota;
    private VentanaModificarMascota modificarMascota;
    private VentanaEliminarMascota eliminarMascota;
    private VentanaRealizarCita realizarCita;
    private VentanaModificarCita modificarCita;
    private VentanaEliminarCita eliminarCita;
    private VentanaConfirmarCita confirmarCita;
    private VentanaMostrarClientes mostrarClientes;
    private VentanaMostrarMascotas mostrarMascotas;
    private VentanaMostrarHistorialServicios mostrarServicios;
    private SubVentanaModificarCliente subModificarCliente;
    
    public void iniciar(){
        sistema = new PetServiceManagement();
        
        main = new VentanaPrincipal();
        
        main.getRegistrarCliente().addActionListener(this);
        main.getModificarCliente().addActionListener(this);
        main.getEliminarCliente().addActionListener(this);
        main.getRegistrarMascota().addActionListener(this);
        main.getModificarMascota().addActionListener(this);
        main.getEliminarMascota().addActionListener(this);
        main.getRealizarCita().addActionListener(this);
        main.getModificarCita().addActionListener(this);
        main.getEliminarCita().addActionListener(this);
        main.getConfirmarCita().addActionListener(this);
        main.getMostrarClientes().addActionListener(this);
        main.getMostrarMascotas().addActionListener(this);
        main.getMostrarHistorialServicios().addActionListener(this);
        
        main.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        main.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == main.getRegistrarCliente()){
            registrarCliente = new VentanaRegistrarCliente();
            registrarCliente.getButtonAgregar().addActionListener(this);
            registrarCliente.setAlwaysOnTop(true);
            registrarCliente.setVisible(true);
            return;
        }
        
        if (registrarCliente != null && ae.getSource() == registrarCliente.getButtonAgregar()) {
    try {
        Cliente c = new Cliente();
        
        // Verificamos que los campos no estén vacíos
        String nombre = registrarCliente.getTextNombre().getText();
        String rut = registrarCliente.getTextRut().getText();
        String telefono = registrarCliente.getTextTelefono().getText();
        String direccion = registrarCliente.getTextDireccion().getText();
        String correo = registrarCliente.getTextCorreo().getText();
        
        if (nombre.isEmpty() || rut.isEmpty() || telefono.isEmpty() || direccion.isEmpty() || correo.isEmpty()) {
            throw new IllegalArgumentException("Todos los campos son obligatorios.");
        }
        
        // Validar el formato del RUT o del número de teléfono según tus requisitos
        if (!rut.matches("[0-9]+-[0-9Kk]")) {
            throw new IllegalArgumentException("Formato de RUT inválido.");
        }

        // Asignamos los valores si todo está en orden
        c.setNombre(nombre);
        c.setRut(rut);
        c.setNumeroTelefono(telefono);
        c.setDireccion(direccion);
        c.setCorreoElectronico(correo);

        // Registrar al cliente en el sistema
        sistema.registrarCliente(c);
        JOptionPane.showMessageDialog(null, "Cliente registrado correctamente.");
        registrarCliente.dispose();

    } catch (IllegalArgumentException e) {
        // Manejo de errores específicos
        JOptionPane.showMessageDialog(null, "Error al registrar cliente: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    } catch (Exception e) {
        // Cualquier otro error inesperado
        JOptionPane.showMessageDialog(null, "Ocurrió un error inesperado: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}
        
        if(ae.getSource() == main.getModificarCliente()){
            modificarCliente = new VentanaModificarCliente(sistema.entregarListadoClientes());
            modificarCliente.getButtonModificarCliente().addActionListener(this);       
            modificarCliente.setVisible(true);   
            return;
        }
        
        if(modificarCliente != null && ae.getSource() == modificarCliente.getButtonModificarCliente()){
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
        
        if(subModificarCliente != null && ae.getSource() == subModificarCliente.getButtonConfirmar()){
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
        
        if(ae.getSource() == main.getEliminarCliente()){
            eliminarCliente = new VentanaEliminarCliente(sistema.entregarListadoClientes());
            eliminarCliente.getButtonEliminarCliente().addActionListener(this);
            eliminarCliente.setVisible(true);   
            return;
        }
        
        if(eliminarCliente != null && ae.getSource() == eliminarCliente.getButtonEliminarCliente()){
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
        
        if(ae.getSource() == main.getRegistrarMascota()){
            registrarMascota = new VentanaRegistrarMascota(sistema.entregarListadoClientes());
       
            registrarMascota.setVisible(true);      
            return;
        }
        
        if(ae.getSource() == main.getModificarMascota()){
            modificarMascota = new VentanaModificarMascota(sistema.entregarListadoClientes());
       
            modificarMascota.setVisible(true);    
            return;
        }
        
        if(ae.getSource() == main.getEliminarMascota()){
            eliminarMascota = new VentanaEliminarMascota();
       
            eliminarMascota.setVisible(true);  
            return;
        }
        
        if(ae.getSource() == main.getRealizarCita()){
            realizarCita = new VentanaRealizarCita();
       
            realizarCita.setVisible(true);  
            return;
        }
        
        if(ae.getSource() == main.getModificarCita()){
            modificarCita = new VentanaModificarCita();
       
            modificarCita.setVisible(true);  
            return;
        }
        
        if(ae.getSource() == main.getEliminarCita()){
            eliminarCita = new VentanaEliminarCita();
       
            eliminarCita.setVisible(true);  
            return;
        }
        
        if(ae.getSource() == main.getConfirmarCita()){
            confirmarCita = new VentanaConfirmarCita();
       
            confirmarCita.setVisible(true);  
            return;
        }
        
        if(ae.getSource() == main.getMostrarClientes()){
            mostrarClientes = new VentanaMostrarClientes(sistema.entregarListadoClientes());
       
            mostrarClientes.setVisible(true);  
            return;
        }
        
        if(ae.getSource() == main.getMostrarMascotas()){
            mostrarMascotas = new VentanaMostrarMascotas(sistema.entregarListadoMascotasTotal());
       
            mostrarMascotas.setVisible(true);  
            return;
        }
        
        if(ae.getSource() == main.getMostrarHistorialServicios()){
            mostrarServicios = new VentanaMostrarHistorialServicios();
       
            mostrarServicios.setVisible(true);  
            return;
        }
        
    }
}
