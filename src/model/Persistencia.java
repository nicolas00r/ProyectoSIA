/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.io.*;
import java.util.List;
import java.util.ArrayList;

public class Persistencia {
    
    public static void guardarCitasCSV(List<Cita> citas) {
        List<Cita> citasGuardadas = cargarCitasCSV(); // Cargar citas existentes

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("citas.csv",false))) {
            for (Cita cita : citas) {
                boolean existe = false;
                for (Cita citaGuardada : citasGuardadas) {
                // Compara por ID de cita o cualquier otro criterio que definas
                    if (cita.getIdCita() == citaGuardada.getIdCita()) {
                        existe = true;
                        break;
                    }
                }
                if (!existe) { // Solo guarda si no existe
                    String linea = cita.getIdCita() + "," +
                                   cita.getCliente().getNombre() + "," +
                                   cita.getCliente().getRut() + "," +
                                   cita.getCliente().getDireccion() + "," +
                                   cita.getCliente().getNumeroTelefono() + "," +
                                   cita.getCliente().getCorreoElectronico() + "," +
                                   cita.getMascota().getNombreMascota() + "," +
                                   cita.getMascota().getEspecie() + "," +
                                   cita.getMascota().getEdad() + "," +
                                   cita.getMascota().getId() + "," + // ID de la mascota
                                   cita.getServicio().getTipo() + "," +
                                   cita.getServicio().getFecha() + "," + // Fecha del servicio
                                   cita.getServicio().getDescripcion() + "," + // Descripción del servicio
                                   cita.getFecha() + "," + // Fecha de la cita
                                   cita.getHora(); // Hora de la cita
                    writer.write(linea);
                    writer.newLine();
                }
            }
            System.out.println("Citas guardadas en el archivo CSV.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // Método para cargar citas desde un archivo CSV
    public static List<Cita> cargarCitasCSV() {
        List<Cita> citas = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("citas.csv"))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] data = linea.split(",");

                // Crear el objeto Cliente
                Cliente cliente = new Cliente(
                    data[1], // Nombre
                    data[2], // Rut
                    data[3], // Dirección
                    data[4], // Número de teléfono
                    data[5]  // Correo electrónico
                );

                // Crear el objeto Mascota
                Mascota mascota = new Mascota(data[6], cliente.getNombre(), data[7], Integer.parseInt(data[8]));
                
                // Crear el objeto Servicio
                Servicio servicio = new Servicio(data[10], data[11], data[12]);

                // Crear el objeto Cita
                Cita cita = new Cita(cliente, mascota, servicio, data[13], data[14], Integer.parseInt(data[0]));
                citas.add(cita);
            }
            System.out.println("Citas cargadas desde el archivo CSV.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return citas;
    }
}

