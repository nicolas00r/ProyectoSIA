package Clases;

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
        Cliente cliente1 = new Cliente("Pedro", "16.111.111-1", "PUCV, Valparaiso", "912345678", "correo@test.cl");
        listaClientes.add(cliente1);
        clientesXRut.put(cliente1.getRut(), cliente1);

        Cliente cliente2 = new Cliente("Juan", "17.111.111-1", "PUCV, Curauma", "987654321", "test@test.cl");
        listaClientes.add(cliente2);
        clientesXRut.put(cliente2.getRut(), cliente2);

        Cliente cliente3 = new Cliente("Alberto", "18.111.111-1", "PUCV, Viña", "987651234", "test@correo.cl");
        listaClientes.add(cliente3);
        clientesXRut.put(cliente3.getRut(), cliente3);
    }
}
