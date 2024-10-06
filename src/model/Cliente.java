package model;

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
    public Cliente(String nombre, String rut, String numeroTelefono, String direccion, String correoElectronico){
        this.nombre = nombre;
        this.rut = rut;
        this.numeroTelefono = numeroTelefono;
        this.direccion = direccion;
        this.correoElectronico = correoElectronico;
        listaMascotas = new ArrayList<>();
        mascotasXNombre = new HashMap<>();
        mascotasXId = new HashMap<>();
    }
    
    public Cliente(){
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
    @Override
    public String toString(){ return nombre+", "+rut+", "+numeroTelefono+", "+direccion+", "+correoElectronico+"\n";}
    
    public boolean listaEstaVacia(){ return listaMascotas.isEmpty();}

    public boolean existeMascota(String nombreMascota){ return mascotasXNombre.containsKey(nombreMascota.toUpperCase());}

    public Mascota getMascota(String nombreMascota){ return mascotasXNombre.get(nombreMascota.toUpperCase());}

    public Mascota getMascota(int idMascota){ return mascotasXId.get(idMascota);}

    public void registrarMascota() throws IOException {
    BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));

    System.out.println("Bienvenido al registro de mascotas");
    System.out.println("Seleccione el tipo de mascota que desea registrar:");
    System.out.println("1. Mascota normal");
    System.out.println("2. Mascota de riesgo");
    System.out.println("3. Mascota exótica");
    int tipoMascota = Integer.parseInt(lector.readLine());

    // Datos comunes para todas las mascotas
    System.out.println("Ingrese el nombre de su mascota: ");
    String nombreMascota = lector.readLine();
    System.out.println("Ingrese la especie de su mascota (Perro, gato, etc.): ");
    String especie = lector.readLine();
    System.out.println("Ingrese la edad de su mascota: ");
    String edadMascota = lector.readLine();

    Mascota mascota = null;

    switch (tipoMascota) {
        case 1:
            // Mascota normal
            mascota = new Mascota(nombreMascota, nombre, especie, edadMascota);
            break;
        case 2:
            // Mascota de riesgo
            System.out.println("Ingrese la condición de riesgo de su mascota: ");
            String condicionRiesgo = lector.readLine();
            System.out.println("Ingrese detalles para los cuidados especiales de su mascota: ");
            String cuidadosEspeciales = lector.readLine();
            mascota = new MascotaDeRiesgo(nombreMascota, nombre, especie, edadMascota, condicionRiesgo, cuidadosEspeciales);
            break;
        case 3:
            // Mascota exótica
            System.out.println("Ingrese el hábitat natural de su mascota: ");
            String habitat = lector.readLine();
            System.out.println("Ingrese el nivel de peligrosidad de su mascota (Alto, Medio o Bajo): ");
            String nivelPeligrosidad = lector.readLine();
            mascota = new MascotaExotica(nombreMascota, nombre, especie, edadMascota, habitat, nivelPeligrosidad);
            break;
        default:
            System.out.println("Opción inválida. Registro cancelado.");
            return;
    }

    // Verifimos si la mascota ya existe antes de agregarla
    if (!existeMascota(mascota.getNombreMascota())) {
        listaMascotas.add(mascota);
        mascotasXNombre.put(nombreMascota.toUpperCase(), mascota);
        mascotasXId.put(mascota.getId(), mascota);
        System.out.println("Se ha registrado a " + mascota.getNombreMascota() + " correctamente.");
    } else {
        System.out.println("El registro ha fallado debido a que " + mascota.getNombreMascota() + " ya se encontraba en su registro de mascotas.");
    }
}


    public void registrarMascota(Mascota mascota){
        listaMascotas.add(mascota);
        mascotasXNombre.put(mascota.getNombreMascota().toUpperCase(), mascota);
        mascotasXId.put(mascota.getId(), mascota);
    }
    
    public void modificarMascota() throws IOException {
    BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
    
    System.out.println("Bienvenido a la modificación de mascotas");
    System.out.print("Ingrese el id de su mascota: ");
    int idMascota = Integer.parseInt(lector.readLine());
    
    Mascota mascotaMod = getMascota(idMascota);
    if (mascotaMod == null) {
        System.out.println("No se encontró una mascota con ese ID.");
        return; // Salir si no se encuentra la mascota
    }
    
    System.out.println("Ingrese el nombre de su mascota: ");
    mascotaMod.setNombreMascota(lector.readLine());
    
    System.out.println("Ingrese la edad de su mascota:");
    mascotaMod.setEdad(lector.readLine());
    
    System.out.println("Ingrese la especie de su mascota");
    mascotaMod.setEspecie(lector.readLine());
    
    System.out.println("Los datos de la mascota se han modificado con éxito");
}
    
    public void eliminarMascota() throws IOException {
    BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
    
    System.out.println("Bienvenido a la eliminación de mascotas");
    System.out.print("Ingrese el nombre de su mascota: ");
    String nombreMascota = lector.readLine();
    
    String nombreMascotaUpper = nombreMascota.toUpperCase();
    if (!existeMascota(nombreMascotaUpper)) {
        System.out.println("No se encontró una mascota con ese nombre.");
        return;
    }

    Mascota mascotaAEliminar = getMascota(nombreMascotaUpper);
    
    listaMascotas.remove(mascotaAEliminar);
    mascotasXNombre.remove(nombreMascotaUpper);
    mascotasXId.remove(mascotaAEliminar.getId());

    System.out.println("Se ha eliminado a " + mascotaAEliminar.getNombreMascota() + " del registro correctamente.");
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
