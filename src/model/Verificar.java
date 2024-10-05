/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

public class Verificar {

    public static void verificarEdad(String edadInput){
        try {

            if (edadInput == null || edadInput.trim().isEmpty()) {
                throw new IllegalArgumentException("La edad no puede estar vacía.");
            }

            int edad = Integer.parseInt(edadInput);
            if (edad < 0) {
                throw new IllegalArgumentException("La edad de la mascota no puede ser negativa.");
            }

        } catch (NumberFormatException e){
            System.out.println("Error: La entrada debe ser un número válido.");
            
        } catch (IllegalArgumentException e){
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    public static void verificarNombre(String nombre){
        try {
   
            if (nombre == null || nombre.trim().isEmpty()){
                throw new IllegalArgumentException("El nombre no puede estar vacío.");
            }

            if (nombre.matches(".*\\d.*")){
                throw new IllegalArgumentException("El nombre no puede contener números.");
            }

        } catch (IllegalArgumentException e){
            System.out.println("Error: " + e.getMessage());
        }
    }
}

