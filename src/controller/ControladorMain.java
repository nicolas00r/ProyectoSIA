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
        
        if(registrarCliente != null && ae.getSource() == registrarCliente.getButtonAgregar()){
            Cliente c = new Cliente();
            c.setNombre(registrarCliente.getTextNombre().getText());
            c.setRut(registrarCliente.getTextRut().getText());
            c.setNumeroTelefono(registrarCliente.getTextTelefono().getText());
            c.setDireccion(registrarCliente.getTextDireccion().getText());
            c.setCorreoElectronico(registrarCliente.getTextCorreo().getText());
            
            sistema.registrarCliente(c);
            JOptionPane.showMessageDialog(null, "Cliente registrado correctamente.");
            registrarCliente.dispose();
            return;
        }
        
        if(ae.getSource() == main.getModificarCliente()){
            modificarCliente = new VentanaModificarCliente(sistema.entregarListadoClientes());
            modificarCliente.getButtonModificarCliente().addActionListener(this);       
            modificarCliente.setVisible(true);   
            return;
        }
        
        if(modificarCliente != null && ae.getSource() == modificarCliente.getButtonModificarCliente()){
            String clienteSeleccionado = modificarCliente.getClienteSeleccionado();
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
            c.setNombre(subModificarCliente.getTextNombre().getText());
            c.setRut(subModificarCliente.getTextRut().getText());
            c.setNumeroTelefono(subModificarCliente.getTextTelefono().getText());
            c.setDireccion(subModificarCliente.getTextDireccion().getText());
            c.setCorreoElectronico(subModificarCliente.getTextCorreo().getText());
            modificarCliente.actualizarFila(c.toString());
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
            registrarMascota = new VentanaRegistrarMascota();
       
            registrarMascota.setVisible(true);      
            return;
        }
        
        if(ae.getSource() == main.getModificarMascota()){
            modificarMascota = new VentanaModificarMascota();
       
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
            mostrarMascotas = new VentanaMostrarMascotas();
       
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
