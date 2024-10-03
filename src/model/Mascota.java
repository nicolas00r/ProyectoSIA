package model;
import model.Cita;
import java.util.ArrayList;
import java.util.HashMap;

public class Mascota{
    // Variables de instancia
    private String nombreMascota;
    private String nombreDueño;
    private String especie;
    private int edad;
    private int id;
    private static int contadorId = 0;
 
    private static HashMap<Integer,Cita> mapaCitas;
    private static ArrayList<Cita> listaCitas;

    private ArrayList<Servicio> listaServicios;

    // Constructor
    public Mascota(String nombreMascota, String nombreDueño, String especie, int edad){
        id = ++contadorId;
        listaServicios = new ArrayList<>();
        mapaCitas = new HashMap<>();
        listaCitas = new ArrayList<>();
        setNombreMascota(nombreMascota);
        setNombreDueño(nombreDueño);
        setEspecie(especie);
        setEdad(edad);
    }

    // Setters y Getters
    public void setNombreMascota(String nombreMascota){this.nombreMascota = nombreMascota;}

    public String getNombreMascota(){return nombreMascota;}

    public void setNombreDueño(String nombreDueño){this.nombreDueño = nombreDueño;}

    public String getNombreDueño(){return nombreDueño;}

    public void setEspecie(String especie){this.especie = especie;}

    public String getEspecie(){return especie;}

    public int getId(){return id;}

    public void setEdad(int edad){this.edad = edad;}

    public int getEdad(){return edad;}

    // Métodos
    public void agregarCita(Cita cita, int idCita){
        mapaCitas.put(idCita, cita);
        listaCitas.add(cita);
    }

    public boolean eliminarCita(int idCita){
        if(!mapaCitas.containsKey(idCita)){
            System.out.println("Cita no existe");
            return false;
        }

        Cita cita = mapaCitas.get(idCita);
        listaCitas.remove(cita);
        mapaCitas.remove(idCita);
        return true;
    }

    public Cita obtenerCita(int idCita){ return mapaCitas.get(idCita);}

    public Cita obtenerCita(String fecha, String hora) {
        for (int i = 0; i < listaCitas.size(); i++) {
            Cita cita = listaCitas.get(i);
            if (cita.getFecha().equals(fecha) && cita.getHora().equals(hora)) {
                return cita;
            }
        }
        return null;
    }

    public boolean citasEstaVacio(){ return listaCitas.isEmpty();}

    public void mostrarListaCitas(){
        for(int i = 0; i < listaCitas.size(); i++){
            Cita cita = listaCitas.get(i);
            cita.mostrarCita();
        }
    }

    public void agregarServicio(Servicio servicio){ listaServicios.add(servicio);}

    public void mostrarDatosMascota(){
        System.out.println("------------------------");
        System.out.println("Datos de la mascota:");
        System.out.println("Nombre de la mascota: " + nombreMascota);
        System.out.println("ID de la mascota: " + id);
        System.out.println("Especie: " + especie);
        System.out.println("Edad: " + edad);
        System.out.println("------------------------");
    }

    public void mostrarHistorialServicios(){
        if(listaServicios.size() == 0){
            System.out.println("No existen servicios registrados en esta mascota");
            return;
        }

        System.out.println("Lista de servicios realizados a " + nombreMascota + ":");
        for(int i = 0; i < listaServicios.size(); i++){
            Servicio servicio = listaServicios.get(i);
            System.out.println("------------------------");
            System.out.println("Servicio número " + (i + 1));
            System.out.println("Tipo de servicio: " + servicio.getTipo());
            System.out.println("Fecha del procedimiento: " + servicio.getFecha());
            System.out.println("Descripción: " + servicio.getDescripcion());
            System.out.println("------------------------");
        }
    }
} 