package Clases;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.IIOException;

public class GestionCitas{
    private Map<String , cita> citas;

    public GestionCitas(){
        citas = new HashMap<>();
    }

    public void mostrarMenu() throws IOException{
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
        int opcion;

        do {
            System.out.println("=== Menú de Gestión de Citas ===");
            System.out.println("1. Agregar una cita");
            System.out.println("2. Eliminar una cita");
            System.out.println("3. Modificar una cita");
            System.out.println("4. Confirmar una cita");
            System.out.print("Seleccione una opción: ");
            opcion = Integer.parseInt(lector.readLine());

            switch(opcion){
                case 1:
                    agregarCita();
                    break;
                case 2:
                    eliminarCita();
                    break;
                case 3:
                    modificarCita();
                    break;
                case 4:
                    confirmarCita();
                case 5:
                    System.out.println("Saliendo del menú...");
                    break;
                default:
                    System.out.println("Opcion no válida. Intente nuefvamente.");
            }
        } while (opcion != 5);
    }

    public void agregarCita() throws IOException{
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Ingrese el ID de la cita");
        String idCita = lector.readLine();

        System.out.println("Ingrese el nombre del cliente: ");
        String nombreCliente = lector.readLine();
        Cliente cliente = clientes.get(nombreCliente);
        if(cliente == null){
            System.out.println("Cliente no encontrado.");
            return;
        }

        System.out.println("Ingrese el nombre de la mascota: ");
        String nombreMascota = lector.readLine();
        Mascota mascota = cliente.getMascota(nombreMascota);
        if(mascota == null){
            System.out.println("Mascota no encontrada.");
            return;
        }

        System.out.println("Ingrese el tipo de servicio: ");
        String tipoServicio = lector.readLine();
        System.out.println("Ingrese la fecha del servicio: ");
        String fechaServicio = lector.readLine();
        System.out.println("Ingrese la descripcion del servicio: ");
        String descripcionServicio = lector.readLine();
        System.out.println("Ingrese la fecha y hora de la cita: ");
        String fechaHora = lector.readLine();

        Servicio servicio = new Servicio(tipoServicio, fechaServicio, descripcionServicio);
        Cita cita = new Cita(cliente, mascota , servicio , fechaHora);
        citas.put(idCita, cita);


        System.out.println("Cita agregada exitosamente.");
    }

    public void eliminarCita() throws IOException{
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Ingrese el ID de la cita a eliminar");
        String idCita = lector.readLine();

        if (citas.remove(idCita) != null){
            System.out.println("Cita eliminada exitosamente");
        }else{
            System.out.println("Cita no encontrada.");
        }
    }

    public void modificarCita() throws IOException{
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Ingrese el ID de la cita a modificar: ");
        String idCita = lector.readLine();
        Cita cita = citas.get(idCita);

        if(cita != null){
            System.out.println("Ingrese una nueva fecha y hora de la cita: ");
            String nuevaFechaHora = lector.readLine();
            cita.setFechaHora(nuevaFechaHora);
            citas.put(idCita, cita);
            System.out.println("Cita modificada exitosamente.");
        }else{
            System.out.println("Cita no encontrada.");
        }
    }

    public void confirmarCita() throws IOException{
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Ingrese el ID de la cita a confirmar: ");
        String idCita = lector.readLine();

        Cita cita = citas.get(idCita);
        if(cita != null){
            System.out.println("Detalles de la cita");
            System.out.println("Cliente: " + cita.getCliente().getNombre());
            System.out.println("Mascota: " + cita.getMascota().getNombreMascota());
            System.out.println("Servicio: " + cita.getServicio().getTipo());
            System.out.println("Fecha del servicio: " + cita.getServicio().getFecha());
            System.out.println("Descripcion: " + cita.getServicio().getDescripcion());
            System.out.println("Fecha y hora de la cita: " + cita.getFechaHora());

            System.out.println("¿Desea confirmar esta cita? (si/no): ");
            String respuesta = lector.readLine().trim().toLowerCase();

            if(respuesta.equals("si")){
                System.out.println("Cita confirmada exitosamente.");
            }else if(respuesta.equals("no")){
                System.out.println("Cita no confirmada");
            }else{
                System.out.println("Respuesta no valida.");
            }
        }else{
            System.out.println("Cita no encontrada con el ID proporcionado");
        }
    }
}
