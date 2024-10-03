package Clases;

import java.io.*;

public class GestionCitas{
    // Variables de instancia
    private static int creadorIdCita;

    // Constructor
    public GestionCitas(){
        creadorIdCita = 1;
    }

    // Métodos
    public void menu(Cliente cliente) throws IOException{
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
        int opcion;

        do {
            Helper.mostrarMenuCitas();
            opcion = Integer.parseInt(lector.readLine());

            switch(opcion){
                case 1:
                    agregarCita(cliente);
                    break;
                case 2:
                    eliminarCita(cliente);
                    break;
                case 3:
                    modificarCita(cliente);
                    break;
                case 4:
                    confirmarCita(cliente);
                case 5:
                    System.out.println("Saliendo del menú...");
                    break;
                default:
                    System.out.println("Opcion no válida. Intente nuevamente.");
            }
        } while (opcion != 5);
    }

    public void agregarCita(Cliente cliente) throws IOException{
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
        Mascota mascota = Helper.identificarMascota(cliente);
        if(mascota == null) return;
        
        System.out.println("Ingrese el tipo de servicio: ");
        String tipoServicio = lector.readLine();
        System.out.println("Ingrese la descripcion del servicio: ");
        String descripcionServicio = lector.readLine();
        System.out.println("Ingrese la fecha de la cita: ");
        String fecha = lector.readLine();
        System.out.println("Ingrese la hora de la cita: ");
        String hora = lector.readLine();
        
        Servicio servicio = new Servicio(tipoServicio, fecha, descripcionServicio);
        Cita cita = new Cita(cliente, mascota, servicio , fecha, hora, creadorIdCita);

        mascota.agregarCita(cita, creadorIdCita);
        
        System.out.println("Cita agregada exitosamente.");
        System.out.println("El ID de su cita es: " + creadorIdCita);
        creadorIdCita++;
    }

    public void eliminarCita(Cliente cliente) throws IOException{
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
        Mascota mascota = Helper.identificarMascota(cliente);
        if(mascota == null) return;

        if(mascota.citasEstaVacio()){
            System.out.println("Esta mascota no tiene citas reservadas");
            return;
        }

        mascota.mostrarListaCitas();

        System.out.println("Ingrese el ID de la cita a eliminar");
        int idCita = Integer.parseInt(lector.readLine());

        if(mascota.eliminarCita(idCita))
            System.out.println("Cita eliminada correctamente");


    }

    public void modificarCita(Cliente cliente) throws IOException{
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
        Mascota mascota = Helper.identificarMascota(cliente);
        if(mascota == null) return;

        if(mascota.citasEstaVacio()){
            System.out.println("Esta mascota no tiene citas reservadas");
            return;
        }

        mascota.mostrarListaCitas();

        System.out.println("Ingrese el ID de la cita a modificar");
        int idCita = Integer.parseInt(lector.readLine());

        Cita cita = mascota.obtenerCita(idCita);

        if(cita == null){
            System.out.println("Cita no encontrada.");
            return;
        }

        System.out.println("Ingrese una nueva fecha para la cita: ");
        String nuevaFecha = lector.readLine();
        System.out.println("Ingrese la nueva hora de la cita: ");
        String nuevaHora = lector.readLine();
        cita.setFecha(nuevaFecha);
        cita.setHora(nuevaHora);
        System.out.println("Cita modificada exitosamente.");
    }

    public void confirmarCita(Cliente cliente) throws IOException{
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
        Mascota mascota = Helper.identificarMascota(cliente);
        if(mascota == null) return;

        if(mascota.citasEstaVacio()){
            System.out.println("Esta mascota no tiene citas reservadas");
            return;
        }

        mascota.mostrarListaCitas();

        System.out.println("Ingrese el ID de la cita a confirmar: ");
        int idCita = Integer.parseInt(lector.readLine());

        Cita cita = mascota.obtenerCita(idCita);
        
        if (cita == null) {
            System.out.println("Cita no encontrada con el ID proporcionado");
            System.out.println("Ingrese la fecha de la cita: ");
            String fecha = lector.readLine();
            System.out.println("Ingrese la hora de la cita: ");
            String hora = lector.readLine();
            
            cita = mascota.obtenerCita(fecha, hora); 
            
            if (cita == null) {
                System.out.println("Cita no encontrada con la fecha y hora proporcionadas.");
                return;
            }
        }

        System.out.println("Detalles de la cita");
        System.out.println("Cliente: " + cita.getCliente().getNombre());
        System.out.println("Mascota: " + cita.getMascota().getNombreMascota());
        System.out.println("Servicio: " + cita.getServicio().getTipo());
        System.out.println("Descripcion: " + cita.getServicio().getDescripcion());
        System.out.println("Fecha de la cita: " + cita.getFecha());
        System.out.println("Hora de la cita: " + cita.getHora());

        System.out.println("¿Desea confirmar esta cita? (si/no): ");
        String respuesta = lector.readLine().trim().toLowerCase();

        if(respuesta.equals("si")){
            mascota.agregarServicio(cita.getServicio());
            mascota.eliminarCita(cita.getIdCita());
            System.out.println("Cita confirmada exitosamente.");
        }else if(respuesta.equals("no")){
            System.out.println("Cita no confirmada");
        }else{
            System.out.println("Respuesta no valida.");
        }
    }
    
    
}
