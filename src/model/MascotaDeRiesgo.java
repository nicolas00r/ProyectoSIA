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
    private String cuidadosEspeciales;
    public MascotaDeRiesgo(String nombreMascota, String nombreDueño, String especie, String edad, String condicion, String cuidadosEspeciales){
        super(nombreMascota, nombreDueño, especie, edad);
        setCondicion(condicion);
        setCuidadosEspeciales(cuidadosEspeciales);
    }
    
    public void setCondicion(String condicion){
        Verificar.verificarCadena(condicion);
        this.condicion = condicion;
    }
    
    public String getCondicion(){return condicion;}
    
    public void setCuidadosEspeciales(String cuidadosEspeciales){
        Verificar.verificarCadena(cuidadosEspeciales);
        this.cuidadosEspeciales = cuidadosEspeciales;
    }
    
    public String getCuidadosEspeciales(){return cuidadosEspeciales;}
    
    public void mostrarDatosMascota(){
        System.out.println("------------------------");
        System.out.println("Datos de la mascota:");
        System.out.println("Nombre de la mascota: " + getNombreMascota());
        System.out.println("ID de la mascota: " + getId());
        System.out.println("Especie: " + getEspecie());
        System.out.println("Edad: " + getEdad());
        System.out.println("Esta es una mascota de riesgo");
        System.out.println("Su condicion es: " + getCondicion());
        System.out.println("Se entrega la siguiente descripcion para los cuidados especiales de la mascota: " + getCuidadosEspeciales());
        System.out.println("------------------------");
    }
}
