package Clases;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Cliente{
    private String nombre;
    private String rut;
    private String direccion;
    private String numeroTelefono;
    private String correoElectronico;
    private ArrayList listaMascotas;


    public Cliente(String nombre, String rut, String direccion, String numeroTelefono, String correoElectronico){
        this.nombre = nombre;
        this.rut = rut;
        this.direccion = direccion;
        this.numeroTelefono = numeroTelefono;
        this.correoElectronico = correoElectronico;
        this.listaMascotas = new ArrayList<>();
    }

    public String getNombre(){
        return nombre;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getDireccion(){
        return direccion;
    }

    public void setDireccion(String direccion){
        this.direccion = direccion;
    }

    public String getNumeroTelefono(){
        return numeroTelefono;
    }

    public void setNumeroTelefono(String numeroTelefono){
        this.numeroTelefono = numeroTelefono;
    }

    public String getCorreoElectronico(){
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico){
        this.correoElectronico = correoElectronico;
    }


    public boolean existeMascota(String nombreMascota){
        for(int i = 0; i < listaMascotas.size(); i++){
            Mascota mascota = (Mascota)listaMascotas.get(i);
            if (mascota.getNombreMascota().toUpperCase().equals(nombreMascota.toUpperCase())) return true;
        }
        return false;
    }

    public Mascota getMascota(String nombreMascota){
        for(int i = 0; i < listaMascotas.size(); i++){
            Mascota mascota = (Mascota)listaMascotas.get(i);
            if (mascota.getNombreMascota().toUpperCase().equals(nombreMascota.toUpperCase())) return mascota;
        }
        return null;
    }

    public Mascota getMascota(int idMascota){
        for(int i = 0; i < listaMascotas.size(); i++){
            Mascota mascota = (Mascota)listaMascotas.get(i);
            if (mascota.getId() == idMascota) return mascota;
        }
        return null;
    }

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
            System.out.println("Se ha registrado a " + mascota.getNombreMascota() + " correctamente");
        }
        else{System.out.println("El registro ha fallado debido a que " + mascota.getNombreMascota() + " ya se encontraba en su registro de mascotas");}
    }

    public void mostrarMascotas(){
        if(listaMascotas.size() == 0){System.out.println("No existen mascotas registradas para el usuario");}
        else
            for(int i = 0; i <listaMascotas.size(); i++){
                Mascota mascota = (Mascota)listaMascotas.get(i);
                mascota.mostrarDatosMascota();
            }
    }
}
