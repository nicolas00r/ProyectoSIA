package model;

/**
 * La clase MascotaExotica extiende la clase {@link Mascota} y representa una mascota exótica, 
 * que incluye información adicional como el hábitat y el nivel de peligrosidad.
 * Esta clase se utiliza para mascotas que no son comunes y requieren condiciones de vida específicas o tienen un grado de peligrosidad.
 * 
 * @see Mascota
 * @see MascotaDeRiesgo
 * 
 * 
 */
public class MascotaExotica extends Mascota {

    /** Hábitat natural o necesario para la mascota exótica */
    private String habitat;

    /** Nivel de peligrosidad de la mascota exótica */
    private String nivelPeligrosidad;

    /**
     * Constructor que inicializa una mascota exótica con los datos proporcionados.
     * 
     * @param nombreMascota El nombre de la mascota
     * @param nombreDueño El nombre del dueño de la mascota
     * @param especie La especie de la mascota
     * @param edad La edad de la mascota en formato String
     * @param habitat El hábitat natural de la mascota exótica
     * @param nivelPeligrosidad El nivel de peligrosidad de la mascota
     */
    public MascotaExotica(String nombreMascota, String nombreDueño, String especie, String edad, String habitat, String nivelPeligrosidad) {
        super(nombreMascota, nombreDueño, especie, edad);  // Llama al constructor de la clase base
        setHabitat(habitat);
        setNivelPeligrosidad(nivelPeligrosidad);
    }

    /**
     * Constructor vacío que crea una mascota exótica sin inicializar atributos.
     * Los atributos deberán ser establecidos posteriormente mediante los setters.
     */
    public MascotaExotica() {
        super();
    }

    // Métodos Getters y Setters

    /**
     * Establece el hábitat natural o necesario para la mascota exótica.
     * 
     * @param habitat El hábitat natural de la mascota
     */
    public void setHabitat(String habitat) {
        this.habitat = habitat;
    }

    /**
     * Obtiene el hábitat de la mascota exótica.
     * 
     * @return El hábitat natural de la mascota
     */
    public String getHabitat() {
        return habitat;
    }

    /**
     * Establece el nivel de peligrosidad de la mascota exótica.
     * 
     * @param nivelPeligrosidad El nivel de peligrosidad de la mascota
     */
    public void setNivelPeligrosidad(String nivelPeligrosidad) {
        this.nivelPeligrosidad = nivelPeligrosidad;
    }

    /**
     * Obtiene el nivel de peligrosidad de la mascota exótica.
     * 
     * @return El nivel de peligrosidad de la mascota
     */
    public String getNivelPeligrosidad() {
        return nivelPeligrosidad;
    }

    // Métodos adicionales

    /**
     * Devuelve una representación textual de la mascota exótica, que incluye su nombre, especie,
     * hábitat, nivel de peligrosidad, edad, nombre del dueño y su ID único.
     * 
     * @return Una cadena con los datos principales de la mascota exótica
     */
    @Override
    public String toString() {
        return nombreMascota + ", " + especie + ", " + habitat + ", " + nivelPeligrosidad + ", " + edad + ", " + nombreDueño + ", " + id + "\n";
    }

    /**
     * Muestra los datos detallados de la mascota exótica en la consola, incluyendo su ID, especie,
     * edad, nivel de peligrosidad y hábitat.
     */
    public void mostrarDatosMascota() {
        System.out.println("------------------------");
        System.out.println("Datos de la mascota:");
        System.out.println("Nombre de la mascota: " + getNombreMascota());
        System.out.println("ID de la mascota: " + getId());
        System.out.println("Especie: " + getEspecie());
        System.out.println("Edad: " + getEdad());
        System.out.println("Esta es una mascota exótica");
        System.out.println("Su nivel de peligrosidad es: " + getNivelPeligrosidad());
        System.out.println("Su hábitat es: " + getHabitat());
        System.out.println("------------------------");
    }
}
