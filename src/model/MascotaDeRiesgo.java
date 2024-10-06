/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author joaqu
 */
public class MascotaDeRiesgo extends Mascota{
    
    private String condicion;
    public MascotaDeRiesgo(String nombreMascota, String nombreDueño, String especie, String edad, String condicion){
        super(nombreMascota, nombreDueño, especie, edad);
        setCondicion(condicion);
    }
    
    public void setCondicion(String condicion){
        Verificar.verificarCadena(condicion);
        this.condicion = condicion;
    }
    
    public String getCondicion(){return condicion;}
    
    public void mostrarDatosMascota(){
        System.out.println("------------------------");
        System.out.println("Datos de la mascota:");
        System.out.println("Nombre de la mascota: " + getNombreMascota());
        System.out.println("ID de la mascota: " + getId());
        System.out.println("Especie: " + getEspecie());
        System.out.println("Edad: " + getEdad());
        System.out.println("Esta es una mascota de riesgo, su condicion es: " + getCondicion());
        System.out.println("------------------------");
    }
}
