package Clases;

import java.io.*;

public class GestionCitas{

    private static int creadorIdCita;

    public GestionCitas(){
        creadorIdCita = 1;
    }

    public void mostrarMenu(Cliente cliente) throws IOException{
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
        int opcion;

        do {
            System.out.println("=== Menú de Gestión de Citas ===");
            System.out.println("1. Agregar una cita");
            System.out.println("2. Eliminar una cita");
            System.out.println("3. Modificar una cita");
            System.out.println("4. Confirmar una cita");
            System.out.println("5. Salir del menú");
            System.out.print("Seleccione una opción: ");
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

    public Mascota identificarMascota(Cliente cliente)throws IOException{
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Ingrese el ID de su mascota: ");
        System.out.println("En caso de no recordar el ID de su mascota ingrese la palabra " + "no");
        String cadenaAux = lector.readLine();

        Mascota mascota;

        if(cadenaAux.equals("no")){
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

    public void agregarCita(Cliente cliente) throws IOException{
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
        Mascota mascota = identificarMascota(cliente);
        if(mascota == null) return;
        
        System.out.println("Ingrese el tipo de servicio: ");
        String tipoServicio = lector.readLine();
        System.out.println("Ingrese la fecha del servicio: ");
        String fechaServicio = lector.readLine();
        System.out.println("Ingrese la descripcion del servicio: ");
        String descripcionServicio = lector.readLine();
        System.out.println("Ingrese la fecha y hora de la cita: ");
        String fechaHora = lector.readLine();
        System.out.println("El ID de su cita es: " + creadorIdCita);
        
        Servicio servicio = new Servicio(tipoServicio, fechaServicio, descripcionServicio);
        Cita cita = new Cita(cliente, mascota, servicio , fechaHora);

        mascota.agregarCita(cita, creadorIdCita);
        
        System.out.println("Cita agregada exitosamente.");
        creadorIdCita++;
    }

    public void eliminarCita(Cliente cliente) throws IOException{
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
        Mascota mascota = identificarMascota(cliente);
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
        Mascota mascota = identificarMascota(cliente);
        if(mascota == null) return;

        if(mascota.citasEstaVacio()){
            System.out.println("Esta mascota no tiene citas reservadas");
            return;
        }

        mascota.mostrarListaCitas();

        System.out.println("Ingrese el ID de la cita a modificar");
        int idCita = Integer.parseInt(lector.readLine());

        Cita cita = mascota.obtenerCita(idCita);

        if(cita != null){
            System.out.println("Ingrese una nueva fecha y hora de la cita: ");
            String nuevaFechaHora = lector.readLine();
            cita.setFechaHora(nuevaFechaHora);
            System.out.println("Cita modificada exitosamente.");
        }else{
            System.out.println("Cita no encontrada.");
        }
    }

    public void confirmarCita(Cliente cliente) throws IOException{
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
        Mascota mascota = identificarMascota(cliente);
        if(mascota == null) return;

        if(mascota.citasEstaVacio()){
            System.out.println("Esta mascota no tiene citas reservadas");
            return;
        }

        mascota.mostrarListaCitas();

        System.out.println("Ingrese el ID de la cita a confirmar: ");
        int idCita = Integer.parseInt(lector.readLine());

        Cita cita = mascota.obtenerCita(idCita);
        
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
                mascota.agregarServicio(cita.getServicio());
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
