package Clases;

//Variables
public class Cita{
    private Cliente cliente;
    private Mascota mascota;
    private Servicio servicio;
    private String fechaHora;
    // Constructor 
    public Cita(Cliente cliente, Mascota mascota, Servicio servicio, String fechaHora){
        this.cliente = cliente;
        this.mascota = mascota;
        this.servicio = servicio;
        this.fechaHora = fechaHora;
    }
    // Metodos
    public Cliente getCliente(){
        return cliente;
    }

    public void setCliente(Cliente cliente){
        this.cliente = cliente;
    }

    public Mascota getMascota(){
        return mascota;
    }

    public void setMascota(Mascota mascota){
        this.mascota = mascota;
    }

    public Servicio getServicio(){
        return servicio;
    }

    public void setServicio(Servicio servicio){
        this.servicio = servicio;
    }

    public String getFechaHora(){
        return fechaHora;
    }

    public void setFechaHora(String fechaHora){
        this.fechaHora = fechaHora;
    }

    public void mostrarCita(){
        servicio.mostrarServicio();
        System.out.println("Fecha: " + fechaHora);
    }
}
