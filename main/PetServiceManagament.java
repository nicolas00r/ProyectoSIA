package main;

import Clases.Cliente;
import Clases.GestionCitas;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class PetServiceManagament{

    private static ArrayList listaClientes = new ArrayList<>();
    private static HashMap clientesXRut = new HashMap<>();
    private static GestionCitas gestorCitas = new GestionCitas();

    public static void main(String[] args) throws IOException{
        runSystem();
    }

    public static void runSystem()throws IOException{
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
        int opcion;

        do{
            mostrarMenu();
            opcion = Integer.parseInt(lector.readLine());

            switch (opcion) {
                case 1:
                    registrarCliente();
                    break;

                case 2:
                    agregarMascota();
                    break;

                case 3:
                    gestionarCitas();
                    break;

                case 4:
                    mostrarClientes();
                    break;

                case 5:
                    detallesMascotas();
                    break;

                case 6:
                    System.out.println("Saliendo del sistema...");
                    break;
            
                default: 
                    System.out.println("Opción no valida, intente nuevamente");
                    break;
            }
            presioneEnter();
            limpiarPantalla();

        } while(opcion != 6);


    }

    public static void presioneEnter(){
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Presione Enter para continuar...");
        try {
            lector.readLine();  // Espera a que el usuario presione Enter.
        } catch (IOException e) {
            System.out.println("Error al leer la entrada del usuario.");
            e.printStackTrace();
        }

    }

    public static void limpiarPantalla() {
        // ANSI escape code para limpiar la consola
        System.out.print("\033[H\033[2J");  
        System.out.flush();
    }

    public static void mostrarMenu(){
        System.out.println("Bienvenido al menú");
        System.out.println("¿Qué opción deseas realizar?");
        System.out.println("1. Registrar un cliente");
        System.out.println("2. Agregar una mascota");
        System.out.println("3. Gestionar una cita");
        System.out.println("4. Mostrar clientes");
        System.out.println("5. Ver detalles de mascotas");
        System.out.println("6. Salir");
    }

    public static void registrarCliente()throws IOException{
        limpiarPantalla();
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

    public static void mostrarClientes(){
        limpiarPantalla();
        for(int i = 0; i < listaClientes.size(); i++){
            Cliente cliente = (Cliente)listaClientes.get(i);
            System.out.println("Información cliente:");
            System.out.println("Nombre: " + cliente.getNombre());
            System.err.println("Rut: " + cliente.getRut());
            System.out.println("Dirección: " + cliente.getDireccion());
            System.out.println("Número de telefono: " + cliente.getNumeroTelefono());
            System.out.println("Correo electronico: " + cliente.getCorreoElectronico());
            System.out.println("");
        }
    }

    public static void agregarMascota()throws IOException{
        limpiarPantalla();
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Ingrese su rut");
        String rutCliente = lector.readLine();

        if(clientesXRut.containsKey(rutCliente)){
            Cliente cliente = (Cliente)clientesXRut.get(rutCliente);
            cliente.registrarMascota();
        } else{
            System.out.println("No existe un cliente registrado con ese RUT");
            System.out.println("Registrese e intentelo nuevamente");
        }
    }

    public static void detallesMascotas()throws IOException{
        limpiarPantalla();
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Ingrese su RUT:");
        String rutCLiente = lector.readLine();

        if(!clientesXRut.containsKey(rutCLiente)){
            System.out.println("El usuario no se encuentra registrado en el sistema, por favor registrese a través de la opción número 1");
            return;

        }
        Cliente cliente = (Cliente)clientesXRut.get(rutCLiente);
        System.out.println("Mostrando detalle de mascotas");
        cliente.mostrarMascotas();

    }

    public static void gestionarCitas() throws IOException{
        limpiarPantalla();
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(("Ingrese su RUT:"));
        String rutCLiente = lector.readLine();

        if(!clientesXRut.containsKey(rutCLiente)){
            System.out.println("El usuario no se encuentra registrado en el sistema, por favor registrese a través de la opción número 1");
        }
        Cliente cliente = (Cliente)clientesXRut.get(rutCLiente);

        gestorCitas.mostrarMenu(cliente);
    }
}
