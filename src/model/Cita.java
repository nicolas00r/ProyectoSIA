package model;

import java.util.ArrayList;
//Variables

public class Cita{
    // Variables de instancia
    private Cliente cliente;
    private Mascota mascota;
    private String tipoDeServicio;
    private String fecha;
    private String hora;
    private String descripcion;
    private int id;
    private static int contadorIdC = 0;

    // Constructor 
    public Cita(Cliente dueñoMascota, Mascota mascota){
        id = ++contadorIdC;
        cliente = dueñoMascota;
        this.mascota = mascota;
    }
    
    // Setters y Getters
    public Cliente getCliente(){ return cliente;}

    public void setCliente(Cliente cliente){ this.cliente = cliente;}

    public Mascota getMascota(){ return mascota;}

    public void setMascota(Mascota mascota){ this.mascota = mascota;}

    public String getTipoDeServicio(){ return tipoDeServicio;}
    
    public void setTipoDeServicio(String t){ tipoDeServicio = t;}
    
    public String getFecha() { return fecha;}

    public void setFecha(String fecha) { this.fecha = fecha;}

    public String getHora() { return hora;}

    public void setHora(String hora) { this.hora = hora;}
    
    public String getDescripcion(){ return descripcion;}
    
    public void setDescripcion(String d){ descripcion = d;}
    
    public int getId(){
        return id;
    }
    
    @Override
    public String toString(){
        return tipoDeServicio+"¿¿¿"+fecha+"¿¿¿"+hora+"¿¿¿"+descripcion+"¿¿¿"+id;
    }
}
