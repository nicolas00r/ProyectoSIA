package main;

import Clases.Cliente;
import Clases.Mascota;
import Clases.Servicio;
import Clases.Cita;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class PetServiceManagament{

    private static ArrayList listaClientes = new ArrayList<>();

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
                    // Lógica de registrar cliente
                    System.out.println("Opción en desarrollo");
                    registrarCliente();
                    break;

                case 2:
                    // Lógica de agregar mascota
                    System.out.println("Opción en desarrollo");
                    break;

                case 3:
                    // Lógica de gestionar cita
                    System.out.println("Opción en desarrollo");
                    break;

                case 4:
                    // Lógica de mostrar cliente
                    System.out.println("Opción en desarrollo");
                    break;

                case 5:
                    // Lógica de ver detalle de mascotas
                    System.out.println("Opción en desarrollo");
                    break;

                case 6:
                    System.out.println("Saliendo del sistema...");
                    break;
            
                default: 
                    System.out.println("Opción no valida, intente nuevamente");
                    break;
            }

        } while(opcion != 6);


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

    
    private static boolean existeCliente(String nombreCliente){
        for(int i = 0; i < listaClientes.size(); i++){
            Cliente cliente = (Cliente)listaClientes.get(i);
            if (cliente.getNombre().toUpperCase().equals(nombreCliente.toUpperCase())) return true;
        }
        return false;
    }

    public static void registrarCliente()throws IOException{
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Bienvenido al registro de clientes");
        System.out.println("Ingrese su nombre");
        String nombreCliente = lector.readLine();
        System.out.println("Ingrese su dirección");
        String direccionCliente = lector.readLine();
        System.out.println("Ingrese su numero de telefono");
        String numeroTelefonoCliente = lector.readLine();
        System.out.println("Ingrese su correo electronico");
        String correoElectronicoCliente = lector.readLine();

        Cliente nuevoCliente = new Cliente(nombreCliente, direccionCliente, numeroTelefonoCliente, correoElectronicoCliente);
        if(!existeCliente(nuevoCliente.getNombre())){
            listaClientes.add(nuevoCliente);
            System.out.println("El cliente " + nuevoCliente.getNombre() + " ha sido registrado correctamente...");
        } else{
            System.out.println("El registro de " + nuevoCliente.getNombre() + "ha fallado debido a que ya esta registrado ese nombre en el sitema");
        }

    }
}
