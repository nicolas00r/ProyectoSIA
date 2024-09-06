package Clases;

//Variables
public class Cita{
    // Variables de instancia
    private Cliente cliente;
    private Mascota mascota;
    private Servicio servicio;
    private String fechaHora;
    private int idCita;

    // Constructor 
    public Cita(Cliente cliente, Mascota mascota, Servicio servicio, String fechaHora, int idCita){
        this.cliente = cliente;
        this.mascota = mascota;
        this.servicio = servicio;
        this.fechaHora = fechaHora;
        this.idCita = idCita;
    }
    
    // Setters y Getters
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

    public int getIdCita() {
        return idCita;
    }

    public void setIdCita(int idCita) {
        this.idCita = idCita;
    }

    // Métodos
    public void mostrarCita(){
        servicio.mostrarServicio();
        System.out.println("Fecha: " + fechaHora);
        System.out.println("Id de la cita: " + idCita);
    }
}
