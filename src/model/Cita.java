package model;

import java.util.ArrayList;

/**
 * La clase Cita representa una cita entre un cliente, su mascota y un servicio que se realiza en una fecha y hora específicas.
 * Cada cita tiene un ID único generado automáticamente, y almacena detalles sobre el cliente, la mascota, el tipo de servicio, 
 * la fecha, la hora y una descripción adicional del servicio.
 * 
 * Esta clase facilita el manejo de citas en el sistema de gestión de servicios para mascotas.
 * 
 */
public class Cita {

    /** Cliente que solicita el servicio */
    private Cliente cliente;
    
    /** Mascota a la que se le realizará el servicio */
    private Mascota mascota;
    
    /** Tipo de servicio que se va a realizar (ej. "Consulta", "Vacunación") */
    private String tipoDeServicio;
    
    /** Fecha de la cita en formato String (ej. "2024-10-07") */
    private String fecha;
    
    /** Hora de la cita en formato String (ej. "14:30") */
    private String hora;
    
    /** Descripción adicional de la cita o del servicio */
    private String descripcion;
    
    /** ID único de la cita */
    private int id;
    
    /** Contador estático para generar IDs únicos para las citas */
    private static int contadorIdC = 0;

    /**
     * Constructor que crea una nueva cita con un cliente y una mascota.
     * El ID de la cita se genera automáticamente de manera incremental.
     * 
     * @param dueñoMascota El cliente que posee la mascota
     * @param mascota La mascota para la cual se programa la cita
     */
    public Cita(Cliente dueñoMascota, Mascota mascota) {
        id = ++contadorIdC;  // Genera un ID único para la cita
        this.cliente = dueñoMascota;
        this.mascota = mascota;
    }

    // Métodos Getters y Setters

    /**
     * Obtiene el cliente asociado a la cita.
     * 
     * @return El cliente que solicitó la cita
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * Establece un nuevo cliente para la cita.
     * 
     * @param cliente El cliente que se asociará a la cita
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * Obtiene la mascota asociada a la cita.
     * 
     * @return La mascota que recibirá el servicio
     */
    public Mascota getMascota() {
        return mascota;
    }

    /**
     * Establece una nueva mascota para la cita.
     * 
     * @param mascota La nueva mascota que se asociará a la cita
     */
    public void setMascota(Mascota mascota) {
        this.mascota = mascota;
    }

    /**
     * Obtiene el tipo de servicio que se realizará en la cita.
     * 
     * @return El tipo de servicio programado
     */
    public String getTipoDeServicio() {
        return tipoDeServicio;
    }

    /**
     * Establece el tipo de servicio que se realizará en la cita.
     * 
     * @param t El tipo de servicio a programar
     */
    public void setTipoDeServicio(String t) {
        this.tipoDeServicio = t;
    }

    /**
     * Obtiene la fecha de la cita.
     * 
     * @return La fecha de la cita en formato String
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * Establece la fecha de la cita.
     * 
     * @param fecha La nueva fecha de la cita en formato String
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    /**
     * Obtiene la hora de la cita.
     * 
     * @return La hora de la cita en formato String
     */
    public String getHora() {
        return hora;
    }

    /**
     * Establece la hora de la cita.
     * 
     * @param hora La nueva hora de la cita en formato String
     */
    public void setHora(String hora) {
        this.hora = hora;
    }

    /**
     * Obtiene la descripción adicional de la cita.
     * 
     * @return La descripción de la cita o del servicio
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Establece una descripción adicional para la cita.
     * 
     * @param d La nueva descripción de la cita
     */
    public void setDescripcion(String d) {
        this.descripcion = d;
    }

    /**
     * Obtiene el ID único de la cita.
     * 
     * @return El ID de la cita
     */
    public int getId() {
        return id;
    }

    /**
     * Devuelve una representación en String de la cita, con los detalles de la mascota, cliente, servicio, fecha y hora.
     * 
     * El formato devuelto utiliza "¿¿¿" como separador entre los diferentes campos de información.
     * 
     * @return Una cadena de texto con la información completa de la cita
     */
    @Override
    public String toString() {
        return mascota.getNombreMascota() + "¿¿¿" + mascota.getNombreDueño() + "¿¿¿" + tipoDeServicio 
                + "¿¿¿" + fecha + "¿¿¿" + hora + "¿¿¿" + descripcion + "¿¿¿" + id + "\n";
    }
}
