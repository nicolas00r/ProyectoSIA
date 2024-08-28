package Clases;

public class Mascota{
    //VARIABLES
    private String nombreMascota;
    private String nombreDueño;
    private String especie;
    private int edad;
    private int id;

    private static int contadorId = 0;

    //CONSTRUCTOR
    public Mascota(String nombreMascota, String especie, int edad){
        this.id = ++contadorId;
        setNombreMascota(nombreMascota);
        setNombreDueño(nombreDueño);
        setEspecie(especie);
        setEdad(edad);
    }

    //METODOS
    public void setNombreMascota(String nombreMascota){this.nombreMascota = nombreMascota;}
    public String getNombreMascota(){return nombreMascota;}

    public void setNombreDueño(String nombreDueño){this.nombreDueño = nombreDueño;}
    public String getNombreDueño(){return nombreDueño;}

    public void setEspecie(String especie){this.especie = especie;}
    public String getEspecie(){return especie;}

    public int getId(){return id;}

    public void setEdad(int edad){this.edad = edad;}
    public int getEdad(){return edad;}

    public void mostrarDatosMascota(){
        System.out.println("Datos de la mascota");
        System.out.println("Nombre de la mascota: " + nombreMascota);
        System.out.println("Nombre del dueño: " + nombreDueño);
        System.out.println("Id de la mascota: " + id);
        System.out.println("Especie: " + especie);
        System.out.println("Edad: " + edad);
    }
} 