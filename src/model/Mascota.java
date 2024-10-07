package model;

import java.util.ArrayList;

/**
 * La clase Mascota representa a una mascota dentro del sistema de gestión veterinaria.
 * Cada mascota tiene un nombre, especie, edad, y está asociada a un dueño. Además, 
 * cada mascota puede tener múltiples citas, las cuales se gestionan mediante una lista.
 * 
 * Esta clase proporciona métodos para gestionar los datos de la mascota, así como registrar, 
 * listar, obtener y eliminar citas relacionadas con la misma.
 * 
 * @see Cita
 */
public class Mascota {

    /** Nombre de la mascota */
    protected String nombreMascota;

    /** Nombre del dueño de la mascota */
    protected String nombreDueño;

    /** Especie de la mascota (ej. "Perro", "Gato") */
    protected String especie;

    /** Edad de la mascota en años */
    protected int edad;

    /** Identificador único de la mascota */
    protected int id;

    /** Contador estático para generar IDs únicos para las mascotas */
    protected static int contadorId = 0;

    /** Lista de citas asociadas a la mascota */
    protected ArrayList<Cita> listaCitas;

    /**
     * Constructor que inicializa una nueva mascota con los datos proporcionados y genera un ID único para ella.
     * También inicializa la lista de citas.
     * 
     * @param nombreMascota El nombre de la mascota
     * @param nombreDueño El nombre del dueño de la mascota
     * @param especie La especie de la mascota
     * @param edad La edad de la mascota (en formato String, que será convertida a entero)
     */
    public Mascota(String nombreMascota, String nombreDueño, String especie, String edad) {
        id = ++contadorId;  // Genera un ID único
        listaCitas = new ArrayList<>();
        setNombreMascota(nombreMascota);
        setNombreDueño(nombreDueño);
        setEspecie(especie);
        setEdad(edad);
    }

    /**
     * Constructor vacío que genera un ID único para la mascota e inicializa la lista de citas.
     * Los otros atributos deberán ser asignados posteriormente.
     */
    public Mascota() {
        id = ++contadorId;
        listaCitas = new ArrayList<>();
    }

    // Métodos Getters y Setters

    /**
     * Establece el nombre de la mascota.
     * 
     * @param nombreMascota El nuevo nombre de la mascota
     */
    public void setNombreMascota(String nombreMascota) {
        this.nombreMascota = nombreMascota;
    }

    /**
     * Obtiene el nombre de la mascota.
     * 
     * @return El nombre de la mascota
     */
    public String getNombreMascota() {
        return nombreMascota;
    }

    /**
     * Establece el nombre del dueño de la mascota.
     * 
     * @param nombreDueño El nombre del dueño de la mascota
     */
    public void setNombreDueño(String nombreDueño) {
        this.nombreDueño = nombreDueño;
    }

    /**
     * Obtiene el nombre del dueño de la mascota.
     * 
     * @return El nombre del dueño de la mascota
     */
    public String getNombreDueño() {
        return nombreDueño;
    }

    /**
     * Establece la especie de la mascota.
     * 
     * @param especie La especie de la mascota (ej. "Perro", "Gato")
     */
    public void setEspecie(String especie) {
        this.especie = especie;
    }

    /**
     * Obtiene la especie de la mascota.
     * 
     * @return La especie de la mascota
     */
    public String getEspecie() {
        return especie;
    }

    /**
     * Obtiene el identificador único de la mascota.
     * 
     * @return El ID de la mascota
     */
    public int getId() {
        return id;
    }

    /**
     * Establece la edad de la mascota.
     * La edad es proporcionada como una cadena de texto y se convierte a un entero.
     * 
     * @param edad La edad de la mascota en formato String
     */
    public void setEdad(String edad) {
        this.edad = Integer.parseInt(edad);
    }

    /**
     * Obtiene la edad de la mascota.
     * 
     * @return La edad de la mascota en años
     */
    public int getEdad() {
        return edad;
    }

    // Métodos adicionales

    /**
     * Devuelve una representación textual de la mascota, que incluye su nombre, especie, edad,
     * nombre del dueño y su ID único, separados por comas.
     * 
     * @return Una cadena que representa la información de la mascota
     */
    @Override
    public String toString() {
        return nombreMascota + ", " + especie + ", " + edad + ", " + nombreDueño + ", " + id + "\n";
    }

    /**
     * Registra una nueva cita para la mascota.
     * 
     * @param c La cita a registrar
     */
    public void realizarCita(Cita c) {
        listaCitas.add(c);
    }

    /**
     * Devuelve una lista de todas las citas asociadas a la mascota.
     * 
     * @return Una cadena que contiene la información de todas las citas de la mascota
     */
    public String listarCitas() {
        StringBuilder ret = new StringBuilder();
        for (Cita cita : listaCitas) {
            ret.append(cita.toString());
        }
        return ret.toString();
    }

    /**
     * Devuelve una lista de las citas de la mascota filtradas por una fecha específica.
     * 
     * @param fecha La fecha en formato String (ej. "2024-10-07")
     * @return Una cadena con la información de las citas en la fecha proporcionada
     */
    public String listarCitas(String fecha) {
        StringBuilder ret = new StringBuilder();
        for (Cita cita : listaCitas) {
            if (cita.getFecha().equals(fecha)) {
                ret.append(cita.toString());
            }
        }
        return ret.toString();
    }

    /**
     * Obtiene una cita específica asociada a la mascota, buscando por el ID de la cita.
     * 
     * @param idCita El ID de la cita en formato String
     * @return La cita correspondiente al ID dado, o {@code null} si no se encuentra
     */
    public Cita obtenerCita(String idCita) {
        int aux = Integer.parseInt(idCita);
        for (Cita cita : listaCitas) {
            if (aux == cita.getId()) {
                return cita;
            }
        }
        return null;
    }

    /**
     * Elimina una cita de la lista de citas de la mascota.
     * 
     * @param d La cita a eliminar
     */
    public void eliminarCita(Cita d) {
        listaCitas.remove(d);
    }

    /**
     * Obtiene la lista completa de citas asociadas a la mascota.
     * 
     * @return Una lista de objetos {@link Cita} correspondientes a la mascota
     */
    public ArrayList<Cita> getListaCitas() {
        return listaCitas;
    }
}
