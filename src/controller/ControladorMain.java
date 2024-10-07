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
    private VentanaSeleccionarCliente modificarMascota;
    private VentanaSeleccionarCliente eliminarMascota;
    private VentanaSeleccionarCliente realizarCita;
    private VentanaSeleccionarCliente eliminarCita;
    private VentanaMostrarClientes mostrarClientes;
    private VentanaMostrarMascotas mostrarMascotas;
    private VentanaMostrarHistorialServicios mostrarServicios;
    private SubVentanaModificarCliente subModificarCliente;
    private SubVentanaRegistrarMascota subRegistrarMascota;
    private VentanaSeleccionarMascota subModificarMascota;
    private SubVentanaModificarMascota2 subModificarMascota2;
    private VentanaSeleccionarMascota subEliminarMascota;
    private VentanaSeleccionarMascota subRealizarCita;
    private SubVentanaRealizarCita subRealizarCita2;
    private VentanaSeleccionarMascota subEliminarCita;
    private VentanaEliminarCita subEliminarCita2;
    
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
        main.getEliminarCita().addActionListener(this);
        main.getMostrarClientes().addActionListener(this);
        main.getMostrarMascotas().addActionListener(this);
        main.getMostrarHistorialServicios().addActionListener(this);
        
        main.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        main.setVisible(true);
        
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
        try {
        sistema.guardarDatos();  // sistema debe ser una instancia de PetServiceManagement
        } catch (Exception e) {
            e.printStackTrace();  // Captura cualquier error durante el guardado
        }
        }));
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
        
        String nombre = registrarCliente.getTextNombre().getText();
        String rut = registrarCliente.getTextRut().getText();
        String telefono = registrarCliente.getTextTelefono().getText();
        String direccion = registrarCliente.getTextDireccion().getText();
        String correo = registrarCliente.getTextCorreo().getText();
        
        if (nombre.isEmpty() || rut.isEmpty() || telefono.isEmpty() || direccion.isEmpty() || correo.isEmpty()) {
            throw new IllegalArgumentException("Todos los campos son obligatorios.");
        }
        
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
        
        if (ae.getSource() == main.getRegistrarMascota()) {
            registrarMascota = new VentanaRegistrarMascota(sistema.entregarListadoClientes());
            registrarMascota.getButtonSeleccionarCliente().addActionListener(this);
            registrarMascota.setVisible(true);
            return;
        }

        if (registrarMascota != null && ae.getSource() == registrarMascota.getButtonSeleccionarCliente()) {
            String clienteSeleccionado = registrarMascota.getTextRut().getText();

            Cliente c = sistema.obtenerCliente(clienteSeleccionado);
            String nombreDueño = c.getNombre();
            String rutDueño = c.getRut();

            if (clienteSeleccionado != null) {
                registrarMascota.dispose();
                subRegistrarMascota = new SubVentanaRegistrarMascota(nombreDueño, rutDueño);
                subRegistrarMascota.getButtonRegistrar().addActionListener(this);
                subRegistrarMascota.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Seleccione un cliente válido.");
            }
            return;
        }

        if (subRegistrarMascota != null && ae.getSource() == subRegistrarMascota.getButtonRegistrar()) {
            try {
                Cliente c = sistema.obtenerCliente(subRegistrarMascota.getRutDueño());

                String nombreMascota = subRegistrarMascota.getTextNombre().getText();
                String especie = subRegistrarMascota.getTextEspecie().getText();
                String edadTexto = subRegistrarMascota.getTextEdad().getText();

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

        
        if(ae.getSource() == main.getModificarMascota()){
            modificarMascota = new VentanaSeleccionarCliente(sistema.entregarListadoClientes());
            modificarMascota.getButtonSeleccionarCliente().addActionListener(this);       
            modificarMascota.setVisible(true);    
            return;
        }
        
        if(modificarMascota != null && ae.getSource() == modificarMascota.getButtonSeleccionarCliente()){
            String clienteSeleccionado = modificarMascota.getTextRut().getText();
            
            Cliente c = sistema.obtenerCliente(clienteSeleccionado);
            
            if (clienteSeleccionado != null) {
                modificarMascota.dispose();
                subModificarMascota = new VentanaSeleccionarMascota(c.listarMascotas(), c.getRut());
                if(subModificarMascota.getRutDueño().equals("-1"))
                    return;
                subModificarMascota.getButtonSeleccionar().addActionListener(this);
                subModificarMascota.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Seleccione un cliente para válido.");
            }
            return;
        }
        
        if(subModificarMascota != null && ae.getSource() == subModificarMascota.getButtonSeleccionar()){
            Cliente c = sistema.obtenerCliente(subModificarMascota.getRutDueño());
            int idMascota = Integer.parseInt(subModificarMascota.getMascotaSeleccionada());
            Mascota m = sistema.obtenerMascota(c, idMascota);
            String rutDueño = c.getRut();
            
            if (m != null) {
                subModificarMascota2 = new SubVentanaModificarMascota2(m.toString(), rutDueño);
                subModificarMascota.dispose();
                subModificarMascota2.getButtonConfirmar().addActionListener(this);
                subModificarMascota2.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Seleccione una mascota para modificar.");
            }
            return;
        }
    
        if(subModificarMascota2 != null && ae.getSource() == subModificarMascota2.getButtonConfirmar()){
            Cliente c = sistema.obtenerCliente(subModificarMascota2.getRutDueño());
            Mascota m = sistema.obtenerMascota(c, subModificarMascota2.getIdMascota());
            
            if(subModificarMascota2.getCheckRiesgo().isSelected()){
                sistema.eliminarMascota(c, m);
                MascotaDeRiesgo a = new MascotaDeRiesgo();
                a.setNombreDueño(c.getNombre());
                a.setNombreMascota(subModificarMascota2.getTextNombre().getText());
                a.setEspecie(subModificarMascota2.getTextEspecie().getText());
                a.setEdad(subModificarMascota2.getTextEdad().getText());
                a.setCondicion(subModificarMascota2.getTextCondicion().getText());
                c.registrarMascota(a);
            } else if(subModificarMascota2.getCheckExotica().isSelected()){
                sistema.eliminarMascota(c, m);
                MascotaExotica b = new MascotaExotica();
                b.setNombreDueño(c.getNombre());
                b.setNombreMascota(subModificarMascota2.getTextNombre().getText());
                b.setEspecie(subModificarMascota2.getTextEspecie().getText());
                b.setEdad(subModificarMascota2.getTextEdad().getText());
                b.setHabitat(subModificarMascota2.getTextHabitat().getText());
                b.setNivelPeligrosidad(subModificarMascota2.getTextPeligrosidad().getText());
                c.registrarMascota(b);
            } else{
                sistema.eliminarMascota(c, m);
                Mascota d = new Mascota();
                d.setNombreDueño(c.getNombre());
                d.setNombreMascota(subModificarMascota2.getTextNombre().getText());
                d.setEspecie(subModificarMascota2.getTextEspecie().getText());
                d.setEdad(subModificarMascota2.getTextEdad().getText());
                c.registrarMascota(d);
            }
           
            JOptionPane.showMessageDialog(null, "Mascota modificado correctamente.");
            subModificarMascota2.dispose();
        }
                
        if(ae.getSource() == main.getEliminarMascota()){
            eliminarMascota = new VentanaSeleccionarCliente(sistema.entregarListadoClientes());
            eliminarMascota.getButtonSeleccionarCliente().addActionListener(this);       
            eliminarMascota.setVisible(true);    
            return;
        }
        
        if(eliminarMascota != null && ae.getSource() == eliminarMascota.getButtonSeleccionarCliente()){
            String clienteSeleccionado = eliminarMascota.getTextRut().getText();
            
            Cliente c = sistema.obtenerCliente(clienteSeleccionado);
            
            if (clienteSeleccionado != null) {
                eliminarMascota.dispose();
                subEliminarMascota = new VentanaSeleccionarMascota(c.listarMascotas(), c.getRut());
                if(subEliminarMascota.getRutDueño().equals("-1"))
                    return;
                subEliminarMascota.getButtonSeleccionar().addActionListener(this);
                subEliminarMascota.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Seleccione un cliente para válido.");
            }
            return;
        }

        if(subEliminarMascota != null && ae.getSource() == subEliminarMascota.getButtonSeleccionar()){
            Cliente c = sistema.obtenerCliente(subEliminarMascota.getRutDueño());
            int idMascota = Integer.parseInt(subEliminarMascota.getMascotaSeleccionada());
            Mascota m = sistema.obtenerMascota(c, idMascota);
            
            if (m != null) {
                sistema.eliminarMascota(c, m);
                JOptionPane.showMessageDialog(null, "Mascota eliminada correctamente.");
                subEliminarMascota.dispose();
                
            } else {
                JOptionPane.showMessageDialog(null, "Seleccione una mascota para eliminar.");
            }
            return;
        }
        
        if(ae.getSource() == main.getRealizarCita()){
            realizarCita = new VentanaSeleccionarCliente(sistema.entregarListadoClientes());
            realizarCita.getButtonSeleccionarCliente().addActionListener(this);       
            realizarCita.setVisible(true);    
            return;
        }
        
        if(realizarCita != null && ae.getSource() == realizarCita.getButtonSeleccionarCliente()){
            String clienteSeleccionado = realizarCita.getTextRut().getText();
            
            Cliente c = sistema.obtenerCliente(clienteSeleccionado);
            
            if (clienteSeleccionado != null) {
                realizarCita.dispose();
                subRealizarCita = new VentanaSeleccionarMascota(c.listarMascotas(), c.getRut());
                if(subRealizarCita.getRutDueño().equals("-1"))
                    return;
                subRealizarCita.getButtonSeleccionar().addActionListener(this);
                subRealizarCita.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Seleccione un cliente para válido.");
            }
            return;
        }
        
        if(subRealizarCita != null && ae.getSource() == subRealizarCita.getButtonSeleccionar()){
            Cliente c = sistema.obtenerCliente(subRealizarCita.getRutDueño());
            int idMascota = Integer.parseInt(subRealizarCita.getMascotaSeleccionada());
            Mascota m = sistema.obtenerMascota(c, idMascota);
            
            if (m != null) {
                subRealizarCita.dispose();
                subRealizarCita2 = new SubVentanaRealizarCita(c.getRut(), idMascota);
                subRealizarCita2.getButtonRealizar().addActionListener(this);
                subRealizarCita2.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Seleccione una mascota para eliminar.");
            }
            return;
        }
        
        if(subRealizarCita2 != null && ae.getSource() == subRealizarCita2.getButtonRealizar()){
            Cliente c = sistema.obtenerCliente(subRealizarCita2.getRut());
            Mascota m = sistema.obtenerMascota(c, subRealizarCita2.getId());
            Cita cita = new Cita(c,m);
            cita.setTipoDeServicio(subRealizarCita2.getTextTipoServicio().getText());
            cita.setFecha(subRealizarCita2.getTextFecha().getText());
            cita.setHora(subRealizarCita2.getTextHora().getText());
            cita.setDescripcion(subRealizarCita2.getTextDescripcion().getText());
            
            sistema.realizarCita(m, cita);
            JOptionPane.showMessageDialog(null, "Cita realizada");
            subRealizarCita2.dispose();

        }
        
        if(ae.getSource() == main.getEliminarCita()){
            eliminarCita = new VentanaSeleccionarCliente(sistema.entregarListadoClientes());
            eliminarCita.getButtonSeleccionarCliente().addActionListener(this);       
            eliminarCita.setVisible(true);    
            return;
        }
        
        if(eliminarCita != null && ae.getSource() == eliminarCita.getButtonSeleccionarCliente()){
            String clienteSeleccionado = eliminarCita.getTextRut().getText();
            
            Cliente c = sistema.obtenerCliente(clienteSeleccionado);
            
            if (clienteSeleccionado != null) {
                eliminarCita.dispose();
                subEliminarCita = new VentanaSeleccionarMascota(c.listarMascotas(), c.getRut());
                if(subEliminarCita.getRutDueño().equals("-1"))
                    return;
                subEliminarCita.getButtonSeleccionar().addActionListener(this);
                subEliminarCita.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Seleccione un cliente para válido.");
            }
            return;
        }
        
        if(subEliminarCita != null && ae.getSource() == subEliminarCita.getButtonSeleccionar()){
            Cliente c = sistema.obtenerCliente(subEliminarCita.getRutDueño());
            int idMascota = Integer.parseInt(subEliminarCita.getMascotaSeleccionada());
            Mascota m = sistema.obtenerMascota(c, idMascota);
            
            if (m != null) {
                subEliminarCita.dispose();
                subEliminarCita2 = new VentanaEliminarCita(m.listarCitas(), c.getRut(), idMascota);
                subEliminarCita2.getButtonEliminar().addActionListener(this);
                subEliminarCita2.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Seleccione una mascota.");
            }
            return;
        }
        
        if(subEliminarCita2 != null && ae.getSource() == subEliminarCita2.getButtonEliminar()){
            if(subEliminarCita2.getId() == -1){
                subEliminarCita2.dispose();
               return;
            }
            Cliente c = sistema.obtenerCliente(subEliminarCita2.getRutDueño());
            Mascota m = sistema.obtenerMascota(c, subEliminarCita2.getId());
            Cita cita = sistema.obtenerCita(m, subEliminarCita2.getCitaSeleccionada());
            
            if (cita != null) {
                sistema.eliminarCita(m, cita);
                subEliminarCita2.eliminarFilaSeleccionada();
                JOptionPane.showMessageDialog(null, "Cita eliminado correctamente.");
            } else {
                JOptionPane.showMessageDialog(null, "Seleccione una cita para eliminar.");
            }
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
            mostrarServicios = new VentanaMostrarHistorialServicios(sistema.listarCitasTotales());
            mostrarServicios.setVisible(true);  
            return;
        }
        
    }
}
