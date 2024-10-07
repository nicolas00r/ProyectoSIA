package model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * La clase MascotasControl gestiona una colección de objetos {@link Mascota}, proporcionando métodos
 * para agregar, eliminar, y buscar mascotas por nombre o por ID. También permite listar todas las mascotas
 * y verificar si un cliente tiene mascotas registradas.
 * 
 * Esta clase utiliza una lista y dos mapas: uno para asociar mascotas por su nombre y otro por su ID.
 * 
 * @see Mascota
 */
public class MascotasControl {

    /** Lista que almacena todas las mascotas registradas */
    private ArrayList<Mascota> lista;

    /** Mapa que asocia el nombre de la mascota con su objeto correspondiente */
    private HashMap<String, Mascota> mascotasXNombre;

    /** Mapa que asocia el ID de la mascota con su objeto correspondiente */
    private HashMap<Integer, Mascota> mascotasXId;

    /**
     * Constructor que inicializa las estructuras de datos para gestionar las mascotas.
     * Se crea una lista para almacenar las mascotas, y dos mapas para buscar por nombre e ID.
     */
    public MascotasControl() {
        lista = new ArrayList<>();
        mascotasXNombre = new HashMap<>();
        mascotasXId = new HashMap<>();
    }

    /**
     * Devuelve una lista en formato texto con los detalles de todas las mascotas registradas.
     * 
     * @return Una cadena de texto con la información de todas las mascotas
     */
    public String listarMascotas() {
        StringBuilder ret = new StringBuilder();
        for (int i = 0; i < lista.size(); i++) {
            ret.append(lista.get(i).toString());
        }
        return ret.toString();
    }

    /**
     * Agrega una nueva mascota al sistema. La mascota se añade a la lista y a los mapas indexados por nombre e ID.
     * 
     * @param m La mascota que se desea agregar
     * @return {@code true} si la mascota fue añadida correctamente
     */
    public boolean agregarMascota(Mascota m) {
        mascotasXNombre.put(m.getNombreMascota(), m);
        mascotasXId.put(m.getId(), m);
        return lista.add(m);
    }

    /**
     * Verifica si el cliente tiene alguna mascota registrada.
     * 
     * @return {@code true} si el cliente no tiene mascotas, {@code false} si tiene una o más mascotas
     */
    public boolean tieneMascotas() {
        return !lista.isEmpty();
    }

    /**
     * Obtiene una mascota por su nombre.
     * 
     * @param nombre El nombre de la mascota a buscar
     * @return La mascota correspondiente al nombre, o {@code null} si no se encuentra
     */
    public Mascota obtenerMascotaPorNombre(String nombre) {
        return mascotasXNombre.get(nombre);
    }

    /**
     * Obtiene una mascota por su ID.
     * 
     * @param id El ID de la mascota a buscar
     * @return La mascota correspondiente al ID, o {@code null} si no se encuentra
     */
    public Mascota obtenerMascotaPorId(int id) {
        return mascotasXId.get(id);
    }

    /**
     * Obtiene una mascota por su posición en la lista.
     * 
     * @param i La posición de la mascota en la lista
     * @return La mascota correspondiente a la posición indicada
     */
    public Mascota obtenerMascotaPorUbicacion(int i) {
        return lista.get(i);
    }

    /**
     * Elimina una mascota del sistema, removiéndola de la lista y de los mapas indexados por nombre e ID.
     * 
     * @param m La mascota que se desea eliminar
     */
    public void eliminarMascota(Mascota m) {
        String nombre = m.getNombreMascota();
        int id = m.getId();
        
        lista.remove(m);
        mascotasXNombre.remove(nombre);
        mascotasXId.remove(id);
    }

    /**
     * Devuelve el número total de mascotas registradas en el sistema.
     * 
     * @return El número total de mascotas
     */
    public int totalMascotas() {
        return lista.size();
    }
}
