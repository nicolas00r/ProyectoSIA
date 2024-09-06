package Clases;
import java.util.ArrayList;
import java.util.HashMap;

public class Mascota{
    //VARIABLES
    private String nombreMascota;
    private String nombreDueño;
    private String especie;
    private int edad;
    private int id;
    private static int contadorId = 0;
 
    private static HashMap mapaCitas;
    private static ArrayList listaCitas;

    private ArrayList listaServicios;

    //CONSTRUCTOR
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

    //METODOS
    public void setNombreMascota(String nombreMascota){this.nombreMascota = nombreMascota;}
    public String getNombreMascota(){return nombreMascota;}

    public void setNombreDueño(String nombreDueño){this.nombreDueño = nombreDueño;}
    public String getNombreDueño(){return nombreDueño;}

    public void setEspecie(String especie){this.especie = especie;}
    public String getEspecie(){return especie;}

    public int getId(){return id;}

    public void setEdad(int edad){this.edad = edad;}
    public int getEdad(){return edad;}

    public void agregarCita(Cita cita, int idCita){
        mapaCitas.put(cita, idCita);
        listaCitas.add(cita);
    }

    public boolean citasEstaVacio(){
        return listaCitas.isEmpty();
    }

    public void mostrarListaCitas(){
        Cita cita;

        for(int i = 0; i < listaCitas.size(); i++){
            cita = (Cita)listaCitas.get(i);
            cita.mostrarCita();
        }
    }

    public boolean eliminarCita(String idCita){
        if(!mapaCitas.containsKey(idCita)){
            System.out.println("Cita no existe");
            return false;
        }

        Cita cita = (Cita)mapaCitas.get(idCita);
        listaCitas.remove(cita);
        mapaCitas.remove(idCita);
        return true;
    }

    public Cita obtenerCita(String idCita){
        return (Cita)mapaCitas.get(idCita);
    }

    public void agregarServicio(Servicio servicio){listaServicios.add(servicio);}

    public void mostrarDatosMascota(){
        System.out.println("------------------------");
        System.out.println("Datos de la mascota:");
        System.out.println("Nombre de la mascota: " + nombreMascota);
        System.out.println("ID de la mascota: " + id);
        System.out.println("Especie: " + especie);
        System.out.println("Edad: " + edad);
        System.out.println("------------------------");
    }

    public void mostrarListaServicios(){
        System.out.println("Lista de servicios realizados a " + nombreMascota + ":");
        for(int i = 0; i < listaServicios.size(); i++){
            Servicio servicio = (Servicio)listaServicios.get(i);
            System.out.println("------------------------");
            System.out.println("Servicio número " + (i + 1));
            System.out.println("Tipo de servicio: " + servicio.getTipo());
            System.out.println("Fecha del procedimiento: " + servicio.getFecha());
            System.out.println("Descripción: " + servicio.getDescripcion());
            System.out.println("------------------------");
        }
    }
} 