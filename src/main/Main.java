/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package main;
import java.io.*;
import java.util.List;
import model.Helper;
import model.PetServiceManagement;
import model.Persistencia;
import model.Cita;
import controller.ControladorMain;
import javax.swing.JFrame;
import view.VentanaPrincipal;

public class Main {
    public static void main(String[] args)throws IOException {
        List<Cita> citas = Persistencia.cargarCitasCSV();
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
        PetServiceManagement gestor = new PetServiceManagement();
        ControladorMain e = new ControladorMain();
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run(){
                VentanaPrincipal v = new VentanaPrincipal();
                v.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                v.setVisible(true);
            }
        });
        /*int opcion;
    
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
    
        } while(opcion != 6);*/
    }
}


