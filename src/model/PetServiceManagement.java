package model;

import model.GestionCitas;
import model.Servicio;
import model.Mascota;
import model.Cliente;
import model.Cita;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class PetServiceManagement{

    // Variables de instancia
    private static ArrayList<Cliente> listaClientes;
    private static HashMap<String,Cliente> clientesXRut;
    private static GestionCitas gestorCitas;

    // Constructor
    public PetServiceManagement(){
        listaClientes = new ArrayList<>();
        clientesXRut = new HashMap<>();
        gestorCitas = new GestionCitas();
        llenadoDatos(); // FUNCIÓN TEMPORAL PARA RELLENAR DATOS
    }

    // Métodos
    public void registrarCliente()throws IOException{
        Helper.limpiarPantalla();
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Bienvenido al registro de clientes");
        System.out.println("Ingrese su nombre");
        String nombreCliente = lector.readLine();
        System.out.println("Ingrese su rut");
        String rutCliente = lector.readLine();
        System.out.println("Ingrese su dirección");
        String direccionCliente = lector.readLine();
        System.out.println("Ingrese su numero de telefono");
        String numeroTelefonoCliente = lector.readLine();
        System.out.println("Ingrese su correo electronico");
        String correoElectronicoCliente = lector.readLine();

        Cliente nuevoCliente = new Cliente(nombreCliente, rutCliente, direccionCliente, numeroTelefonoCliente, correoElectronicoCliente);
        if(!clientesXRut.containsKey(nuevoCliente.getRut())){
            listaClientes.add(nuevoCliente);
            clientesXRut.put(nuevoCliente.getRut(), nuevoCliente);
            System.out.println("El cliente " + nuevoCliente.getNombre() + " ha sido registrado correctamente...");
        } else{
            System.out.println("El registro de " + nuevoCliente.getNombre() + "ha fallado debido a que ya esta registrado ese nombre en el sitema");
        }
    }

    public void mostrarClientes(){
        Helper.limpiarPantalla();
        for(int i = 0; i < listaClientes.size(); i++){
            Cliente cliente = listaClientes.get(i);
            System.out.println("Información cliente:");
            System.out.println("Nombre: " + cliente.getNombre());
            System.err.println("Rut: " + cliente.getRut());
            System.out.println("Dirección: " + cliente.getDireccion());
            System.out.println("Número de telefono: " + cliente.getNumeroTelefono());
            System.out.println("Correo electronico: " + cliente.getCorreoElectronico());
            System.out.println("");
        }
    }

    public void agregarMascota()throws IOException{
        Helper.limpiarPantalla();
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Ingrese su rut");
        String rutCliente = lector.readLine();

        if(clientesXRut.containsKey(rutCliente)){
            Cliente cliente = clientesXRut.get(rutCliente);
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

        if(!clientesXRut.containsKey(rutCLiente)){
            System.out.println("El usuario no se encuentra registrado en el sistema, por favor registrese a través de la opción número 1");
            return;

        }
        Cliente cliente = clientesXRut.get(rutCLiente);

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
        } else return;

    }

    public void gestionarCitas() throws IOException{
        Helper.limpiarPantalla();
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(("Ingrese su RUT:"));
        String rutCLiente = lector.readLine();

        if(!clientesXRut.containsKey(rutCLiente)){
            System.out.println("El usuario no se encuentra registrado en el sistema, por favor registrese a través de la opción número 1");
            return;
        }
        Cliente cliente = clientesXRut.get(rutCLiente);

        gestorCitas.menu(cliente);
    }

    private void llenadoDatos(){
        // Cliente 1
        Cliente cliente1 = new Cliente("Laura", "12.345.678-9", "Av. Siempre Viva 123, Santiago", "912345678", "laura@example.com");
        listaClientes.add(cliente1);
        clientesXRut.put(cliente1.getRut(), cliente1);
    
        // Mascota 1 de Laura
        Mascota C1mascota1 = new Mascota("Rocky", "Laura", "Perro", 5);
        Servicio C1mascota1Servicio1 = new Servicio("Vacunación", "2024-08-01", "Vacuna contra la rabia");
        C1mascota1.agregarServicio(C1mascota1Servicio1);
        
        Servicio C1mascota1CitaServicio = new Servicio("Corte de pelo", "2024-09-10", "Corte de cuerpo completo");
        Cita C1mascota1Cita1 = new Cita(cliente1, C1mascota1, C1mascota1CitaServicio, "2024-09-10", "10:00", 0);
        C1mascota1.agregarCita(C1mascota1Cita1, 0);
        cliente1.registrarMascota(C1mascota1);
    
        // Cliente 2
        Cliente cliente2 = new Cliente("Carlos", "23.456.789-0", "Calle Falsa 456, Valparaíso", "923456789", "carlos@example.com");
        listaClientes.add(cliente2);
        clientesXRut.put(cliente2.getRut(), cliente2);
    
        // Mascota 1 de Carlos
        Mascota C2mascota1 = new Mascota("Bella", "Carlos", "Perro", 4);
        Servicio C2mascota1Servicio1 = new Servicio("Consulta general", "2024-08-10", "Chequeo de rutina");
        C2mascota1.agregarServicio(C2mascota1Servicio1);
        
        // Mascota 2 de Carlos
        Mascota C2mascota2 = new Mascota("Oliver", "Carlos", "Gato", 1);
        Servicio C2mascota2Servicio1 = new Servicio("Vacunación", "2024-09-01", "Vacuna contra el moquillo");
        C2mascota2.agregarServicio(C2mascota2Servicio1);
        
        Servicio C2mascota2CitaServicio = new Servicio("Chequeo ocular", "2024-09-15", "Chequeo de ojos y visión");
        Cita C2mascota2Cita1 = new Cita(cliente2, C2mascota2, C2mascota2CitaServicio, "2024-09-15", "11:00", 0);
        C2mascota2.agregarCita(C2mascota2Cita1, 0);
    
        cliente2.registrarMascota(C2mascota1);
        cliente2.registrarMascota(C2mascota2);
    
        // Cliente 3
        Cliente cliente3 = new Cliente("Marta", "34.567.890-1", "Ruta 5, Concepción", "934567890", "marta@example.com");
        listaClientes.add(cliente3);
        clientesXRut.put(cliente3.getRut(), cliente3);
    
        // Mascota 1 de Marta
        Mascota C3mascota1 = new Mascota("Coco", "Marta", "Perro", 6);
        Servicio C3mascota1Servicio1 = new Servicio("Esterilización", "2024-07-25", "Esterilización para prevenir futuras camadas");
        C3mascota1.agregarServicio(C3mascota1Servicio1);
        
        Servicio C3mascota1CitaServicio = new Servicio("Consulta dermatológica", "2024-09-20", "Chequeo de problemas de piel");
        Cita C3mascota1Cita1 = new Cita(cliente3, C3mascota1, C3mascota1CitaServicio, "2024-09-20", "09:00", 0);
        C3mascota1.agregarCita(C3mascota1Cita1, 0);
        cliente3.registrarMascota(C3mascota1);
    
        // Cliente 4
        Cliente cliente4 = new Cliente("Juan", "45.678.901-2", "Pje. Del Sol 789, Temuco", "945678901", "juan@example.com");
        listaClientes.add(cliente4);
        clientesXRut.put(cliente4.getRut(), cliente4);
    
        // Mascota 1 de Juan
        Mascota C4mascota1 = new Mascota("Toby", "Juan", "Gato", 5);
        Servicio C4mascota1Servicio1 = new Servicio("Limpieza dental", "2024-08-20", "Limpieza profunda de dientes");
        C4mascota1.agregarServicio(C4mascota1Servicio1);
        
        Servicio C4mascota1CitaServicio = new Servicio("Vacunación anual", "2024-09-25", "Vacuna contra múltiples enfermedades");
        Cita C4mascota1Cita1 = new Cita(cliente4, C4mascota1, C4mascota1CitaServicio, "2024-09-25", "14:00", 0);
        C4mascota1.agregarCita(C4mascota1Cita1, 0);
        cliente4.registrarMascota(C4mascota1);
    
        // Cliente 5
        Cliente cliente5 = new Cliente("Ana", "56.789.012-3", "Av. Central 1010, La Serena", "956789012", "ana@example.com");
        listaClientes.add(cliente5);
        clientesXRut.put(cliente5.getRut(), cliente5);
    
        // Mascota 1 de Ana
        Mascota C5mascota1 = new Mascota("Zeus", "Ana", "Perro", 7);
        Servicio C5mascota1Servicio1 = new Servicio("Consulta general", "2024-08-05", "Chequeo de salud general");
        C5mascota1.agregarServicio(C5mascota1Servicio1);
    
        // Mascota 2 de Ana
        Mascota C5mascota2 = new Mascota("Daisy", "Ana", "Gato", 4);
        Servicio C5mascota2Servicio1 = new Servicio("Vacunación", "2024-08-30", "Vacuna contra leucemia felina");
        C5mascota2.agregarServicio(C5mascota2Servicio1);
        
        Servicio C5mascota2CitaServicio = new Servicio("Chequeo de vacunas", "2024-09-05", "Chequeo de vacunas y refuerzos");
        Cita C5mascota2Cita1 = new Cita(cliente5, C5mascota2, C5mascota2CitaServicio, "2024-09-05", "16:00", 0);
        C5mascota2.agregarCita(C5mascota2Cita1, 0);
    
        cliente5.registrarMascota(C5mascota1);
        cliente5.registrarMascota(C5mascota2);
    } 
}
