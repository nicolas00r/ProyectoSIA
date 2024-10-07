package model;

/**
 * La clase MascotaDeRiesgo extiende la clase {@link Mascota} y representa una mascota que requiere cuidados especiales
 * debido a una condición particular. Además de los atributos heredados de `Mascota`, esta clase añade información sobre
 * la condición de la mascota y los cuidados especiales necesarios.
 * 
 * Esta clase se utiliza para mascotas que, por su condición de riesgo, requieren un tratamiento y atención más detallada.
 * 
 * @see Mascota
 */
public class MascotaDeRiesgo extends Mascota {

    /** Condición de la mascota que la clasifica como de riesgo */
    private String condicion;

    /** Cuidados especiales necesarios para la mascota */
    private String cuidadosEspeciales;

    /**
     * Constructor que inicializa una mascota de riesgo con todos los datos proporcionados.
     * 
     * @param nombreMascota El nombre de la mascota
     * @param nombreDueño El nombre del dueño de la mascota
     * @param especie La especie de la mascota
     * @param edad La edad de la mascota en formato String
     * @param condicion La condición médica o especial de la mascota
     * @param cuidadosEspeciales Los cuidados especiales necesarios para la mascota
     */
    public MascotaDeRiesgo(String nombreMascota, String nombreDueño, String especie, String edad, String condicion, String cuidadosEspeciales) {
        super(nombreMascota, nombreDueño, especie, edad);  // Llama al constructor de la clase base
        setCondicion(condicion);
        setCuidadosEspeciales(cuidadosEspeciales);
    }

    /**
     * Constructor vacío que crea una mascota de riesgo sin inicializar atributos.
     * Los atributos deberán ser establecidos posteriormente mediante los setters.
     */
    public MascotaDeRiesgo() {
        super();
    }

    // Métodos Getters y Setters

    /**
     * Establece la condición de la mascota que la clasifica como de riesgo.
     * 
     * @param condicion La condición médica o especial de la mascota
     */
    public void setCondicion(String condicion) {
        this.condicion = condicion;
    }

    /**
     * Obtiene la condición de la mascota.
     * 
     * @return La condición médica o especial de la mascota
     */
    public String getCondicion() {
        return condicion;
    }

    /**
     * Establece los cuidados especiales necesarios para la mascota.
     * 
     * @param cuidadosEspeciales Descripción de los cuidados especiales necesarios
     */
    public void setCuidadosEspeciales(String cuidadosEspeciales) {
        this.cuidadosEspeciales = cuidadosEspeciales;
    }

    /**
     * Obtiene los cuidados especiales necesarios para la mascota.
     * 
     * @return Descripción de los cuidados especiales de la mascota
     */
    public String getCuidadosEspeciales() {
        return cuidadosEspeciales;
    }

    // Métodos adicionales

    /**
     * Devuelve una representación textual de la mascota de riesgo, incluyendo su nombre, especie,
     * condición de riesgo, edad, nombre del dueño y su ID único.
     * 
     * @return Una cadena con los datos principales de la mascota de riesgo
     */
    @Override
    public String toString() {
        return nombreMascota + ", " + especie + ", " + condicion + ", " + edad + ", " + nombreDueño + ", " + id + "\n";
    }

    /**
     * Muestra los datos detallados de la mascota de riesgo en la consola, incluyendo su ID, especie,
     * edad, condición de riesgo y descripción de cuidados especiales.
     */
    public void mostrarDatosMascota() {
        System.out.println("------------------------");
        System.out.println("Datos de la mascota:");
        System.out.println("ID de la mascota: " + getId());
        System.out.println("Especie: " + getEspecie());
        System.out.println("Edad: " + getEdad());
        System.out.println("Esta es una mascota de riesgo, su condicion es: " + getCondicion());
        System.out.println("Se entrega la siguiente descripción para los cuidados especiales de la mascota: " + getCuidadosEspeciales());
        System.out.println("------------------------");
    }
}
