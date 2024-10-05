package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class PetServiceManagement{

    // Variables de instancia
    private static ClientesControl clientes;
    private static GestionCitas gestorCitas;

    // Constructor
    public PetServiceManagement(){
        clientes = new ClientesControl();
        gestorCitas = new GestionCitas();
        llenadoDatos(); // FUNCIÓN TEMPORAL PARA RELLENAR DATOS
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

    public void agregarMascota()throws IOException{
        Helper.limpiarPantalla();
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Ingrese su rut");
        String rutCliente = lector.readLine();

        if(clientes.existeCliente(rutCliente)){
            Cliente cliente = clientes.obtenerCliente(rutCliente);
            cliente.registrarMascota();
        } else{
            System.out.println("No existe un cliente registrado con ese RUT");
            System.out.println("Registrese e intentelo nuevamente");
        }
    }

    public void detallesMascotas()throws IOException{
        Helper.limpiarPantalla();
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Ingrese su RUT:");
        String rutCLiente = lector.readLine();

        if(!clientes.eliminarCliente(rutCLiente)){
            System.out.println("El usuario no se encuentra registrado en el sistema, por favor registrese a través de la opción número 1");
            return;

        }
        Cliente cliente = clientes.obtenerCliente(rutCLiente);

        if(cliente.listaEstaVacia()){
            System.out.println("El cliente no posee mascotas registradas a su nombre");
            return;
        }

        System.out.println("Mostrando detalle de mascotas");
        cliente.mostrarMascotas();

        System.out.println("¿Desea ver el historial de servicios de una de las mascotas? Si/No");
        String respuesta = lector.readLine();
        if(respuesta.toUpperCase().equals("SI")){
            Mascota mascota = Helper.identificarMascota(cliente);

            mascota.mostrarHistorialServicios();
        }
    }

    public void gestionarCitas() throws IOException{
        Helper.limpiarPantalla();
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(("Ingrese su RUT:"));
        String rutCLiente = lector.readLine();

        if(!clientes.existeCliente(rutCLiente)){
            System.out.println("El usuario no se encuentra registrado en el sistema, por favor registrese a través de la opción número 1");
            return;
        }
        Cliente cliente = clientes.obtenerCliente(rutCLiente);

        gestorCitas.menu(cliente);
    }
    
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
}
