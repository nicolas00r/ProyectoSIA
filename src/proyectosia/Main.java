/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyectosia;
import java.io.*;
import java.util.List;
import Clases.Helper;
import Clases.PetServiceManagement;
import Clases.Persistencia;
import Clases.Cita;

public class Main {
    public static void main(String[] args)throws IOException {
        List<Cita> citas = Persistencia.cargarCitasCSV();
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
        PetServiceManagement gestor = new PetServiceManagement();
        int opcion;
    
        do{
            Helper.mostrarMenuPrincipal();
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
                    Persistencia.guardarCitasCSV(citas);
                    System.out.println("Saliendo del sistema...");
                    break;
                
                default: 
                    System.out.println("Opción no valida, intente nuevamente");
                    break;
            }
            Helper.presioneEnter();
            Helper.limpiarPantalla();
    
        } while(opcion != 6);
    }
}


