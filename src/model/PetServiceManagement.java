package model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class PetServiceManagement{

    // Variables de instancia
    private static ClientesControl clientes;
    private static GestionCitas gestorCitas;
    private Persistencia persistencia;
    private List<Cliente> listaClientes;
    private List<Mascota> listaMascotas;
    private List<Servicio> listaServicios;

    // Constructor
    public PetServiceManagement(){
        clientes = new ClientesControl();
        gestorCitas = new GestionCitas();
        persistencia = new Persistencia(); // Instancia de Persistencia
        listaClientes = new ArrayList<>();
        listaMascotas = new ArrayList<>();
        listaServicios = new ArrayList<>();
        cargarDatos(); // <--- Aquí se llama al método cargarDatos        
    }

    // Métodos    
    public void registrarCliente(Cliente c){
        clientes.agregarCliente(c);
    }
    
    public Cliente obtenerCliente(String rut){
        return clientes.obtenerCliente(rut);
    }
    
    public void eliminarCliente(String rut){
        clientes.eliminarCliente(rut);
    }
    
    public String entregarListadoClientes(){
        return clientes.listarClientes();
    }

    public void agregarMascota(Cliente c, Mascota m){
        c.registrarMascota(m);
    }
    
    public String entregarListadoMascotasTotal(){
        String ret;
        ret = "";
        
        for(int i = 0; i < clientes.totalClientes(); i++){
            Cliente c = clientes.obtenerCliente(i);
            ret += c.listarMascotas();
        }
        
        return ret;
    }

    public Mascota obtenerMascota(Cliente c, int id){
        return c.getMascota(id);
    }
    
    public void eliminarMascota(Cliente c, Mascota m){
        c.eliminarMascota(m);
    }
    
    private void cargarDatos() {
        try {
            persistencia.cargarCsvClientes(listaClientes);
            persistencia.cargarCsvMascotas(listaMascotas);
            persistencia.cargarCsvServicios(listaServicios);

            // Procesar los clientes cargados y registrarlos en el sistema
            for (Cliente cliente : listaClientes) {
                clientes.agregarCliente(cliente);
            }
        } catch (IOException e) {
            // Manejar el error de carga sin mostrar mensajes, según tu preferencia
            e.printStackTrace(); // Para propósitos de depuración
        }
    }
    
    public void guardarDatos() {
        try {
            persistencia.guardarCsvClientes(listaClientes);
            persistencia.guardarCsvMascotas(listaMascotas);
            persistencia.guardarCsvServicios(listaServicios);
        } catch (IOException e) {
            // Manejar el error de guardado sin mostrar mensajes, según tu preferencia
            e.printStackTrace(); // Para propósitos de depuración
        }
    }
}
