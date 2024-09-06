package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import Clases.PetServiceManagement;

public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
        PetServiceManagement gestor = new PetServiceManagement();
        int opcion;
    
        do{
            gestor.mostrarMenu();
            opcion = Integer.parseInt(lector.readLine());
    
            switch (opcion) {
                case 1:
                    gestor.registrarCliente();
                    break;
    
                case 2:
                    gestor.agregarMascota();
                    break;
    
                case 3:
                    gestor.gestionarCitas();
                    break;
    
                case 4:
                    gestor.mostrarClientes();
                    break;
    
                case 5:
                    gestor.detallesMascotas();
                    break;
    
                case 6:
                    System.out.println("Saliendo del sistema...");
                    break;
                
                default: 
                    System.out.println("Opción no valida, intente nuevamente");
                    break;
            }
            gestor.presioneEnter();
            gestor.limpiarPantalla();
    
        } while(opcion != 6);
    }
}

