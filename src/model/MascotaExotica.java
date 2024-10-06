/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author joaqu
 */
public class MascotaExotica extends Mascota{
    
    private String habitat;
    private String nivelPeligrosidad;
    
    public MascotaExotica(String nombreMascota, String nombreDueño, String especie, String edad, String habitat, String nivelPeligrosidad){
        super(nombreMascota, nombreDueño, especie, edad);
        setHabitat(habitat);
        setNivelPeligrosidad(nivelPeligrosidad);
    }
    
    public MascotaExotica(){
        super();
    }
    
    public void setHabitat(String habitat){
        Verificar.verificarCadena(habitat);
        this.habitat = habitat;
    }
    
    public String getHabitat(){return habitat;}
    
    public void setNivelPeligrosidad(String nivelPeligrosidad){
        Verificar.verificarCadena(nivelPeligrosidad);
        this.nivelPeligrosidad = nivelPeligrosidad;
    }
    
    public String getNivelPeligrosidad(){return nivelPeligrosidad;}
    
    @Override
    public String toString(){
        return nombreMascota+", "+especie+", "+habitat+", "+nivelPeligrosidad+", "+edad+", "+nombreDueño+", "+id+"\n";
    }
    
    public void mostrarDatosMascota(){
        System.out.println("------------------------");
        System.out.println("Datos de la mascota:");
        System.out.println("Nombre de la mascota: " + getNombreMascota());
        System.out.println("ID de la mascota: " + getId());
        System.out.println("Especie: " + getEspecie());
        System.out.println("Edad: " + getEdad());
        System.out.println("Esta es una mascota exótica");
        System.out.println("Su nivel de peligrosidad es: " + getNivelPeligrosidad());
        System.out.println("Su habitat es: " + getHabitat());
        System.out.println("------------------------");
    }
}