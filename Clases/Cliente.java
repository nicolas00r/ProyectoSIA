package Clases;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Cliente{
    // Variables de instancia
    private String nombre;
    private String rut;
    private String direccion;
    private String numeroTelefono;
    private String correoElectronico;
    private ArrayList<Mascota> listaMascotas;
    private HashMap<String,Mascota> mascotasXNombre;
    private HashMap<Integer, Mascota> mascotasXId;

    // Constructor
    public Cliente(String nombre, String rut, String direccion, String numeroTelefono, String correoElectronico){
        this.nombre = nombre;
        this.rut = rut;
        this.direccion = direccion;
        this.numeroTelefono = numeroTelefono;
        this.correoElectronico = correoElectronico;
        listaMascotas = new ArrayList<>();
        mascotasXNombre = new HashMap<>();
        mascotasXId = new HashMap<>();
    }

    // Setters y Getters
    public String getNombre(){ return nombre;}

    public void setNombre(String nombre){ this.nombre = nombre;}

    public String getRut() { return rut;}

    public void setRut(String rut) { this.rut = rut;}

    public String getDireccion(){ return direccion;}

    public void setDireccion(String direccion){ this.direccion = direccion;}

    public String getNumeroTelefono(){ return numeroTelefono;}

    public void setNumeroTelefono(String numeroTelefono){ this.numeroTelefono = numeroTelefono;}

    public String getCorreoElectronico(){ return correoElectronico;}

    public void setCorreoElectronico(String correoElectronico){ this.correoElectronico = correoElectronico;}


    // Métodos
    public boolean listaEstaVacia(){ return listaMascotas.isEmpty();}

    public boolean existeMascota(String nombreMascota){ return mascotasXNombre.containsKey(nombreMascota.toUpperCase());}

    public Mascota getMascota(String nombreMascota){ return mascotasXNombre.get(nombreMascota.toUpperCase());}

    public Mascota getMascota(int idMascota){ return mascotasXId.get(idMascota);}

    public void registrarMascota()throws IOException{
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Bienvenido al registro de mascotas");
        System.out.println("Ingrese el nombre de su mascota: ");
        String nombreMascota = lector.readLine();
        System.out.println("Ingrese la especie de su mascota (Perro, gato, etc.): ");
        String especie = lector.readLine();
        System.out.println("Ingrese la edad de su mascota: ");
        int edadMascota = Integer.parseInt(lector.readLine());

        Mascota mascota = new Mascota(nombreMascota, nombre, especie, edadMascota);
        if(!existeMascota(mascota.getNombreMascota())){
            listaMascotas.add(mascota);
            mascotasXNombre.put(nombreMascota.toUpperCase(), mascota);
            mascotasXId.put(mascota.getId(), mascota);
            System.out.println("Se ha registrado a " + mascota.getNombreMascota() + " correctamente");
        }
        else{System.out.println("El registro ha fallado debido a que " + mascota.getNombreMascota() + " ya se encontraba en su registro de mascotas");}
    }

    public void mostrarMascotas(){
        if(listaMascotas.size() == 0){System.out.println("No existen mascotas registradas para el usuario");}
        else
            for(int i = 0; i <listaMascotas.size(); i++){
                Mascota mascota = listaMascotas.get(i);
                mascota.mostrarDatosMascota();
            }
    }
}
