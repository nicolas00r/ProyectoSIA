package model;

import java.io.*;

/**
 * La clase Cliente representa a un cliente que utiliza los servicios de la veterinaria.
 * Cada cliente tiene datos personales como nombre, RUT, dirección, número de teléfono y correo electrónico.
 * Además, un cliente puede tener asociada una o más mascotas que serán gestionadas mediante un objeto de la clase {@link MascotasControl}.
 * 
 * Esta clase proporciona métodos para registrar, listar, obtener y eliminar mascotas asociadas a un cliente.
 * También sobrescribe el método `toString` para proporcionar una representación textual del cliente.
 */
public class Cliente {

    /** Nombre del cliente */
    private String nombre;
    
    /** RUT del cliente (identificación única en el sistema) */
    private String rut;
    
    /** Dirección física del cliente */
    private String direccion;
    
    /** Número de teléfono del cliente */
    private String numeroTelefono;
    
    /** Correo electrónico del cliente */
    private String correoElectronico;
    
    /** Controlador de las mascotas asociadas al cliente */
    private MascotasControl mascotas;

    /**
     * Constructor que crea un nuevo cliente con los datos proporcionados.
     * También inicializa el controlador de mascotas del cliente.
     * 
     * @param nombre El nombre del cliente
     * @param rut El RUT del cliente
     * @param direccion La dirección del cliente
     * @param numeroTelefono El número de teléfono del cliente
     * @param correoElectronico El correo electrónico del cliente
     */
    public Cliente(String nombre, String rut, String direccion, String numeroTelefono, String correoElectronico) {
        this.nombre = nombre;
        this.rut = rut;
        this.numeroTelefono = numeroTelefono;
        this.direccion = direccion;
        this.correoElectronico = correoElectronico;
        this.mascotas = new MascotasControl();  // Inicialización del controlador de mascotas
    }

    /**
     * Constructor vacío que inicializa el controlador de mascotas del cliente.
     * Los otros atributos del cliente deberán ser asignados posteriormente mediante setters.
     */
    public Cliente() {
        mascotas = new MascotasControl();
    }

    // Métodos Getters y Setters

    /**
     * Obtiene el nombre del cliente.
     * 
     * @return El nombre del cliente
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del cliente.
     * 
     * @param nombre El nombre del cliente
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el RUT del cliente.
     * 
     * @return El RUT del cliente
     */
    public String getRut() {
        return rut;
    }

    /**
     * Establece el RUT del cliente.
     * 
     * @param rut El RUT del cliente
     */
    public void setRut(String rut) {
        this.rut = rut;
    }

    /**
     * Obtiene la dirección del cliente.
     * 
     * @return La dirección del cliente
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Establece la dirección del cliente.
     * 
     * @param direccion La dirección del cliente
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * Obtiene el número de teléfono del cliente.
     * 
     * @return El número de teléfono del cliente
     */
    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    /**
     * Establece el número de teléfono del cliente.
     * 
     * @param numeroTelefono El número de teléfono del cliente
     */
    public void setNumeroTelefono(String numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }

    /**
     * Obtiene el correo electrónico del cliente.
     * 
     * @return El correo electrónico del cliente
     */
    public String getCorreoElectronico() {
        return correoElectronico;
    }

    /**
     * Establece el correo electrónico del cliente.
     * 
     * @param correoElectronico El correo electrónico del cliente
     */
    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    // Métodos para gestionar las mascotas del cliente

    /**
     * Registra una nueva mascota en el sistema para el cliente actual.
     * 
     * @param m La mascota a registrar
     */
    public void registrarMascota(Mascota m) {
        mascotas.agregarMascota(m);
    }

    /**
     * Obtiene un listado de las mascotas registradas para el cliente actual.
     * 
     * @return Una cadena de texto con la información de todas las mascotas del cliente
     */
    public String listarMascotas() {
        return mascotas.listarMascotas();
    }

    /**
     * Obtiene una mascota asociada al cliente por su ID.
     * 
     * @param idMascota El ID de la mascota
     * @return La mascota correspondiente al ID dado, o null si no existe
     */
    public Mascota obtenerMascota(int idMascota) {
        return mascotas.obtenerMascotaPorId(idMascota);
    }

    /**
     * Obtiene una mascota asociada al cliente por su posición en la lista.
     * 
     * @param j La posición de la mascota en la lista
     * @return La mascota en la posición especificada
     */
    public Mascota obtenerMascotaPos(int j) {
        return mascotas.obtenerMascotaPorUbicacion(j);
    }

    /**
     * Elimina una mascota del sistema para el cliente actual.
     * 
     * @param m La mascota a eliminar
     */
    public void eliminarMascota(Mascota m) {
        mascotas.eliminarMascota(m);
    }

    /**
     * Devuelve el número total de mascotas registradas para el cliente actual.
     * 
     * @return El número total de mascotas
     */
    public int totalMascotas() {
        return mascotas.totalMascotas();
    }

    /**
     * Devuelve una representación en formato texto del cliente, con los datos principales
     * separados por "¿¿¿".
     * 
     * @return Una cadena con el nombre, RUT, teléfono, dirección y correo electrónico del cliente
     */
    @Override
    public String toString() {
        return nombre + "¿¿¿" + rut + "¿¿¿" + numeroTelefono + "¿¿¿" + direccion + "¿¿¿" + correoElectronico + "\n";
    }
}
