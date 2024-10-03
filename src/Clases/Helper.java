package Clases;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Helper {

        public static Mascota identificarMascota(Cliente cliente)throws IOException{
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Ingrese el ID de su mascota: ");
        System.out.println("En caso de no recordar el ID de su mascota ingrese la palabra " + "No");
        String cadenaAux = lector.readLine();

        Mascota mascota;

        if(cadenaAux.toUpperCase().equals("NO")){
            System.out.println("Ingrese el nombre de su mascota");
            mascota = cliente.getMascota(lector.readLine());
            if(mascota == null){
                System.out.println("Mascota no encontrada.");
                return null;
            }
        }
        else{
            mascota = cliente.getMascota(Integer.parseInt(cadenaAux)); 
            if(mascota == null){
                System.out.println("Mascota no encontrada.");
                return null;
            }
        }
        return mascota;
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

    public static void mostrarMenuPrincipal(){
        System.out.println("Bienvenido al menú");
        System.out.println("¿Qué opción deseas realizar?");
        System.out.println("1. Registrar un cliente");
        System.out.println("2. Agregar una mascota");
        System.out.println("3. Gestionar una cita");
        System.out.println("4. Mostrar clientes");
        System.out.println("5. Ver detalles de mascotas");
        System.out.println("6. Salir");
    }

    public static void mostrarMenuCitas(){
        System.out.println("=== Menú de Gestión de Citas ===");
        System.out.println("1. Agregar una cita");
        System.out.println("2. Eliminar una cita");
        System.out.println("3. Modificar una cita");
        System.out.println("4. Confirmar una cita");
        System.out.println("5. Salir del menú");
        System.out.print("Seleccione una opción: ");
    }
}
