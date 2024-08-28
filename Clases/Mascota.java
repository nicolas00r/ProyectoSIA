public class Mascota{
    //VARIABLES
    private String nombre;
    private String especie;
    private int edad;

    //CONSTRUCTOR
    public Mascota(String nombre, String especie, int edad){
        setNombre(nombre);
        setEspecie(especie);
        setEdad(edad);
    }

    //METODOS
    public void setNombre(String nombre){this.nombre = nombre;}
    public String getNombre(){return nombre;}

    public void setEspecie(String especie){this.especie = especie;}
    public String getEspecie(){return especie;}

    public void setEdad(int edad){this.edad = edad;}
    public int getEdad(){return edad;}

    public void mostrarDatosMascota(){
        System.out.println("Datos de la mascota");
        System.out.println("Nombre: " + nombre);
        System.out.println("Especie: " + especie);
        System.out.println("Edad: " + edad);
    }
} 