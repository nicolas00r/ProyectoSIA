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
    
    public String listarCitasN(){
        String ret;
        ret = "";
        
        for(int i = 0; i < listaCitas.size(); i++){
            ret += listaCitas.get(i).toString();
        }
        return ret;
    }
    
    public Cita obtenerCita(String idCita) {
        int aux = Integer.parseInt(idCita);
        for (int i = 0; i < listaCitas.size(); i++) {
            Cita cita = listaCitas.get(i);
            int idC = cita.getId();
            if (aux == idC) {
                return cita;
            }
        }
        return null;
    }
    
    public void eliminarCita(Cita d){
        listaCitas.remove(d);
    }
    
    public ArrayList<Cita> getListaCitas(){ return listaCitas;}
} 