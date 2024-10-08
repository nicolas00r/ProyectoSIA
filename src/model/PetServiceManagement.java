package model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.*;

/**
 * La clase PetServiceManagement gestiona el sistema de clientes y mascotas para un servicio veterinario.
 * Proporciona métodos para registrar clientes, agregar mascotas, realizar citas y gestionar las listas de clientes, mascotas y citas.
 * También incluye funciones para cargar y guardar datos en archivos CSV utilizando la clase {@link Persistencia}.
 * 
 * @see Cliente
 * @see Mascota
 * @see Cita
 * @see Persistencia
 */
public class PetServiceManagement {

    /** Controlador de clientes que gestiona la lista de clientes registrados */
    private static ClientesControl clientes;

    /** Instancia de Persistencia utilizada para cargar y guardar datos */
    private Persistencia persistencia;

    /** Lista de clientes registrada en el sistema */
    private List<Cliente> listaClientes;

    /** Lista de mascotas registrada en el sistema */
    private List<Mascota> listaMascotas;

    /**
     * Constructor que inicializa el sistema de gestión de servicios para mascotas.
     * Crea el controlador de clientes, la instancia de persistencia y carga los datos iniciales desde archivos CSV.
     */
    public PetServiceManagement() {
        clientes = new ClientesControl();
        persistencia = new Persistencia(); // Instancia de Persistencia
        listaClientes = new ArrayList<>();
        listaMascotas = new ArrayList<>();
        cargarDatos(); // Carga los datos desde archivos CSV
    }

    // Métodos de gestión de clientes y mascotas

    /**
     * Registra un cliente en el sistema.
     * 
     * @param c El cliente a registrar
     * @throws ClienteDuplicadoException Si ya existe un cliente con el mismo RUT en el sistema
     */
    public void registrarCliente(Cliente c) throws ClienteDuplicadoException {
        clientes.agregarCliente(c);
    }

    /**
     * Obtiene un cliente por su RUT.
     * 
     * @param rut El RUT del cliente
     * @return El cliente correspondiente al RUT dado, o {@code null} si no se encuentra
     */
    public Cliente obtenerCliente(String rut) {
        return clientes.obtenerCliente(rut);
    }

    /**
     * Elimina un cliente del sistema por su RUT.
     * 
     * @param rut El RUT del cliente a eliminar
     */
    public void eliminarCliente(String rut) {
        clientes.eliminarCliente(rut);
    }

    /**
     * Devuelve una lista en formato texto de todos los clientes registrados en el sistema.
     * 
     * @return Una cadena con la información de todos los clientes
     */
    public String entregarListadoClientes() {
        return clientes.listarClientes();
    }

    /**
     * Agrega una mascota a un cliente registrado en el sistema.
     * 
     * @param c El cliente dueño de la mascota
     * @param m La mascota a agregar
     */
    public void agregarMascota(Cliente c, Mascota m) {
        c.registrarMascota(m);
    }

    /**
     * Devuelve una lista en formato texto de todas las mascotas registradas en el sistema.
     * 
     * @return Una cadena con la información de todas las mascotas
     */
    public String entregarListadoMascotasTotal() {
        String ret = "";
        for (int i = 0; i < clientes.totalClientes(); i++) {
            Cliente c = clientes.obtenerCliente(i);
            ret += c.listarMascotas();
        }
        return ret;
    }

    /**
     * Obtiene una mascota por su ID para un cliente específico.
     * 
     * @param c El cliente dueño de la mascota
     * @param id El ID de la mascota
     * @return La mascota correspondiente al ID dado
     */
    public Mascota obtenerMascota(Cliente c, int id) {
        return c.obtenerMascota(id);
    }

    /**
     * Elimina una mascota de un cliente específico.
     * 
     * @param c El cliente dueño de la mascota
     * @param m La mascota a eliminar
     */
    public void eliminarMascota(Cliente c, Mascota m) {
        c.eliminarMascota(m);
    }

    /**
     * Realiza una cita para una mascota específica.
     * 
     * @param m La mascota para la cual se realiza la cita
     * @param c La cita a realizar
     */
    public void realizarCita(Mascota m, Cita c) {
        m.realizarCita(c);
    }

    /**
     * Elimina una cita de una mascota específica.
     * 
     * @param m La mascota de la cual se eliminará la cita
     * @param d La cita a eliminar
     */
    public void eliminarCita(Mascota m, Cita d) {
        m.eliminarCita(d);
    }

    /**
     * Obtiene una cita específica asociada a una mascota.
     * 
     * @param m La mascota de la cual se obtiene la cita
     * @param id El ID de la cita
     * @return La cita correspondiente al ID dado, o {@code null} si no se encuentra
     */
    public Cita obtenerCita(Mascota m, String id) {
        return m.obtenerCita(id);
    }

    /**
     * Devuelve una lista en formato texto de todas las citas registradas en el sistema.
     * 
     * @return Una cadena con la información de todas las citas
     */
    public String entregarListadoCitas() {
        String ret = "";
        for (int i = 0; i < clientes.totalClientes(); i++) {
            Cliente c = clientes.obtenerCliente(i);
            for (int j = 0; j < c.totalMascotas(); j++) {
                Mascota m = c.obtenerMascotaPos(j);
                ret += m.listarCitas();
            }
        }
        return ret;
    }

    /**
     * Devuelve una lista en formato texto de las citas registradas en una fecha específica.
     * 
     * @param fecha La fecha en la que se desea buscar citas
     * @return Una cadena con la información de las citas en la fecha indicada
     */
    public String entregarListadoCitas(String fecha) {
        String ret = "";
        for (int i = 0; i < clientes.totalClientes(); i++) {
            Cliente c = clientes.obtenerCliente(i);
            for (int j = 0; j < c.totalMascotas(); j++) {
                Mascota m = c.obtenerMascotaPos(j);
                ret += m.listarCitas(fecha);
            }
        }
        return ret;
    }

    /**
     * Carga los datos de clientes y mascotas desde archivos CSV.
     */
    private void cargarDatos() {
        try {
            persistencia.cargarCsvClientes(listaClientes);
            persistencia.cargarCsvMascotas(this); // Pasamos el sistema para asociar las mascotas

            for (Cliente cliente : listaClientes) {
                try {
                    clientes.agregarCliente(cliente);
                } catch (ClienteDuplicadoException e) {
                    System.err.println("Error: El cliente con RUT " + cliente.getRut() + " ya se encuentra registrado.");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Guarda los datos de clientes y mascotas en archivos CSV.
     */
    public void guardarDatos() {
        try {
            persistencia.guardarCsvClientes(listaClientes);
            persistencia.guardarCsvMascotas(listaMascotas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Obtiene un cliente por su nombre.
     * 
     * @param nombreDueño El nombre del cliente a buscar
     * @return El cliente correspondiente al nombre dado, o {@code null} si no se encuentra
     */
    public Cliente obtenerClientePorNombre(String nombreDueño) {
        for (Cliente cliente : listaClientes) {
            if (cliente.getNombre().equalsIgnoreCase(nombreDueño)) {
                return cliente;
            }
        }
        return null;
    }

    /**
     * Exporta la información de los clientes a un archivo de texto.
     * 
     * @param clientesData La información de los clientes en formato texto
     * @param fileName El nombre del archivo donde se guardará la información
     */
    
    public void exportClientesToFile(String clientesData, String fileName){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))){
            //Separar los clientes por "\n"
            String[] clientes = clientesData.split("\n");
            
            for(String cliente : clientes){
                String[] datosCliente = cliente.split("¿¿¿");
                
                for(String dato : datosCliente){
                    writer.write(dato);
                    writer.newLine();
                }
                
                // Separar cada cliente con una linea en blanco
                writer.newLine();
            }
        } catch(IOException e){
            e.printStackTrace(); // Para propósitos de depuración            
        }
    }
    
    // Llenado de datos obsoleto, se mantiene por punto de SIA1.4
    /*
    private void llenadoDatos(){
        
        Cliente cliente1 = new Cliente("Laura", "12.345.678-9", "Av. Siempre Viva 123, Santiago", "912345678", "laura@example.com");
        clientes.agregarCliente(cliente1);
    
        Mascota C1mascota1 = new Mascota("Rocky", "Laura", "Perro", "5");
        Servicio C1mascota1Servicio1 = new Servicio("Vacunación", "2024-08-01", "Vacuna contra la rabia");
        C1mascota1.agregarServicio(C1mascota1Servicio1);
        
        Servicio C1mascota1CitaServicio = new Servicio("Corte de pelo", "2024-09-10", "Corte de cuerpo completo");
        Cita C1mascota1Cita1 = new Cita(cliente1, C1mascota1, C1mascota1CitaServicio, "2024-09-10", "10:00", 0);
        C1mascota1.agregarCita(C1mascota1Cita1, 0);
        cliente1.registrarMascota(C1mascota1);
    
        Cliente cliente2 = new Cliente("Carlos", "23.456.789-0", "Calle Falsa 456, Valparaíso", "923456789", "carlos@example.com");
        clientes.agregarCliente(cliente2);

    
        Mascota C2mascota1 = new Mascota("Bella", "Carlos", "Perro", "4");
        Servicio C2mascota1Servicio1 = new Servicio("Consulta general", "2024-08-10", "Chequeo de rutina");
        C2mascota1.agregarServicio(C2mascota1Servicio1);
        
        Mascota C2mascota2 = new Mascota("Oliver", "Carlos", "Gato", "1");
        Servicio C2mascota2Servicio1 = new Servicio("Vacunación", "2024-09-01", "Vacuna contra el moquillo");
        C2mascota2.agregarServicio(C2mascota2Servicio1);
        
        Servicio C2mascota2CitaServicio = new Servicio("Chequeo ocular", "2024-09-15", "Chequeo de ojos y visión");
        Cita C2mascota2Cita1 = new Cita(cliente2, C2mascota2, C2mascota2CitaServicio, "2024-09-15", "11:00", 0);
        C2mascota2.agregarCita(C2mascota2Cita1, 0);
    
        cliente2.registrarMascota(C2mascota1);
        cliente2.registrarMascota(C2mascota2);
    
        Cliente cliente3 = new Cliente("Marta", "34.567.890-1", "Ruta 5, Concepción", "934567890", "marta@example.com");
        clientes.agregarCliente(cliente3);
    
        Mascota C3mascota1 = new Mascota("Coco", "Marta", "Perro", "6");
        Servicio C3mascota1Servicio1 = new Servicio("Esterilización", "2024-07-25", "Esterilización para prevenir futuras camadas");
        C3mascota1.agregarServicio(C3mascota1Servicio1);
        
        Servicio C3mascota1CitaServicio = new Servicio("Consulta dermatológica", "2024-09-20", "Chequeo de problemas de piel");
        Cita C3mascota1Cita1 = new Cita(cliente3, C3mascota1, C3mascota1CitaServicio, "2024-09-20", "09:00", 0);
        C3mascota1.agregarCita(C3mascota1Cita1, 0);
        cliente3.registrarMascota(C3mascota1);
    
        Cliente cliente4 = new Cliente("Juan", "45.678.901-2", "Pje. Del Sol 789, Temuco", "945678901", "juan@example.com");
        clientes.agregarCliente(cliente4);
    
        Mascota C4mascota1 = new Mascota("Toby", "Juan", "Gato", "5");
        Servicio C4mascota1Servicio1 = new Servicio("Limpieza dental", "2024-08-20", "Limpieza profunda de dientes");
        C4mascota1.agregarServicio(C4mascota1Servicio1);
        
        Servicio C4mascota1CitaServicio = new Servicio("Vacunación anual", "2024-09-25", "Vacuna contra múltiples enfermedades");
        Cita C4mascota1Cita1 = new Cita(cliente4, C4mascota1, C4mascota1CitaServicio, "2024-09-25", "14:00", 0);
        C4mascota1.agregarCita(C4mascota1Cita1, 0);
        cliente4.registrarMascota(C4mascota1);
    
        Cliente cliente5 = new Cliente("Ana", "56.789.012-3", "Av. Central 1010, La Serena", "956789012", "ana@example.com");
        clientes.agregarCliente(cliente5);
    
        Mascota C5mascota1 = new Mascota("Zeus", "Ana", "Perro", "7");
        Servicio C5mascota1Servicio1 = new Servicio("Consulta general", "2024-08-05", "Chequeo de salud general");
        C5mascota1.agregarServicio(C5mascota1Servicio1);
    
        Mascota C5mascota2 = new Mascota("Daisy", "Ana", "Gato", "4");
        Servicio C5mascota2Servicio1 = new Servicio("Vacunación", "2024-08-30", "Vacuna contra leucemia felina");
        C5mascota2.agregarServicio(C5mascota2Servicio1);
        
        Servicio C5mascota2CitaServicio = new Servicio("Chequeo de vacunas", "2024-09-05", "Chequeo de vacunas y refuerzos");
        Cita C5mascota2Cita1 = new Cita(cliente5, C5mascota2, C5mascota2CitaServicio, "2024-09-05", "16:00", 0);
        C5mascota2.agregarCita(C5mascota2Cita1, 0);
    
        cliente5.registrarMascota(C5mascota1);
        cliente5.registrarMascota(C5mascota2);
    } 
    */
}
