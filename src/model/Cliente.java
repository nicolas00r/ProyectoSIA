package model;

import java.io.*;

public class Cliente{
    // Variables de instancia
    private String nombre;
    private String rut;
    private String direccion;
    private String numeroTelefono;
    private String correoElectronico;
    private MascotasControl mascotas;


    // Constructor
    public Cliente(String nombre, String rut, String direccion, String numeroTelefono, String correoElectronico){
        this.nombre = nombre;
        this.rut = rut;
        this.numeroTelefono = numeroTelefono;
        this.direccion = direccion;
        this.correoElectronico = correoElectronico;
        mascotas = new MascotasControl();
    }
    
    public Cliente(){
        mascotas = new MascotasControl();
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
    public String toString(){ return nombre+"¿¿¿"+rut+"¿¿¿"+numeroTelefono+"¿¿¿"+direccion+"¿¿¿"+correoElectronico+"\n";}
    
    public void registrarMascota(Mascota m){
        mascotas.agregarMascota(m);
    }
    
    public String listarMascotas(){
        return mascotas.listarMascotas();
    }
    
    public Mascota obtenerMascota(int idMascota){ return mascotas.obtenerMascotaPorId(idMascota);}
    
    public Mascota obtenerMascotaPos(int j){ return mascotas.obtenerMascotaPorUbicacion(j);}
    
    public void eliminarMascota(Mascota m){
        mascotas.eliminarMascota(m);
    }
    
    public int totalMascotas(){
        return mascotas.totalMascotas();
    }
}
