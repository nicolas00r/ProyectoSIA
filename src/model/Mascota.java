package model;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;


public class Mascota{
    // Variables de instancia
    protected String nombreMascota;
    protected String nombreDueño;
    protected String especie;
    protected int edad;
    protected int id;
    protected static int contadorId = 0;
 
    protected static ArrayList<Cita> listaCitas;
    protected ArrayList<Cita> listaHistorialCitas;

    // Constructor
    public Mascota(String nombreMascota, String nombreDueño, String especie, String edad){
        id = ++contadorId;
        listaCitas = new ArrayList<>();
        listaHistorialCitas = new ArrayList<>();
        setNombreMascota(nombreMascota);
        setNombreDueño(nombreDueño);
        setEspecie(especie);
        setEdad(edad);
    }
    
    public Mascota(){
        id = ++contadorId;
        listaCitas = new ArrayList<>();
        listaHistorialCitas = new ArrayList<>();
    }

    // Setters y Getters
    public void setNombreMascota(String nombreMascota){this.nombreMascota = nombreMascota;}

    public String getNombreMascota(){return nombreMascota;}

    public void setNombreDueño(String nombreDueño){this.nombreDueño = nombreDueño;}

    public String getNombreDueño(){return nombreDueño;}

    public void setEspecie(String especie){this.especie = especie;}

    public String getEspecie(){return especie;}

    public int getId(){return id;}
    
    public void setEdad(String edad) {
        this.edad = Integer.parseInt(edad);
    }

    public int getEdad(){return edad;}

    // Métodos
    @Override
    public String toString(){
        return nombreMascota+", "+especie+", "+edad+", "+nombreDueño+", "+id+"\n";
    }

    public void realizarCita(Cita c){
        listaCitas.add(c);
    }
    
    public Cita obtenerCita(String fecha, String hora) {
        for (int i = 0; i < listaHistorialCitas.size(); i++) {
            Cita cita = listaHistorialCitas.get(i);
            if (cita.getFecha().equals(fecha) && cita.getHora().equals(hora)) {
                return cita;
            }
        }
        return null;
    }
    
    public ArrayList<Cita> getListaCitas(){ return listaCitas;}
} 