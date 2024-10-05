package model;

import java.util.ArrayList;
//Variables

public class Cita{
    // Variables de instancia
    private Cliente cliente;
    private Mascota mascota;
    private Servicio servicio;
    private String fecha;
    private String hora;
    private int idCita;
    private ArrayList<Servicio> listaServicios;

    // Constructor 
    public Cita(Cliente cliente, Mascota mascota, Servicio servicio, String fecha, String hora, int idCita){
        this.cliente = cliente;
        this.mascota = mascota;
        this.servicio = servicio;
        this.fecha = fecha;
        this.hora = hora;
        this.idCita = idCita;
    }
    
    // Setters y Getters
    public Cliente getCliente(){ return cliente;}

    public void setCliente(Cliente cliente){ this.cliente = cliente;}

    public Mascota getMascota(){ return mascota;}

    public void setMascota(Mascota mascota){ this.mascota = mascota;}

    public Servicio getServicio(){ return servicio;}

    public void setServicio(Servicio servicio){ this.servicio = servicio;}

    public String getFecha() { return fecha;}

    public void setFecha(String fecha) { this.fecha = fecha;}

    public String getHora() { return hora;}

    public void setHora(String hora) { this.hora = hora;}

    public int getIdCita() { return idCita;}

    public void setIdCita(int idCita) { this.idCita = idCita;}
    
    public ArrayList<Servicio> getServicios(){
        return listaServicios;
    }
    
    

    // Métodos
    public void mostrarCita(){
        servicio.mostrarServicio();
        System.out.println("Fecha: " + fecha);
        System.out.println("Hora: " + hora);
        System.out.println("Id de la cita: " + idCita);
    }
}
