/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

public class Verificar {

    public static void verificarNumero(String numeroInput){
        try {

            if (numeroInput == null || numeroInput.trim().isEmpty()) {
                throw new IllegalArgumentException("Este campo no puede estar vacío.");
            }

            int numero = Integer.parseInt(numeroInput);
            if (numero < 0) {
                throw new IllegalArgumentException("Este campo no puede ser negativo.");
            }

        } catch (NumberFormatException e){
            System.out.println("Error: La entrada debe ser un número válido.");
            
        } catch (IllegalArgumentException e){
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    public static void verificarCadena(String nombre){
        try {
   
            if (nombre == null || nombre.trim().isEmpty()){
                throw new IllegalArgumentException("Este campo no puede estar vacío.");
            }

            if (nombre.matches(".*\\d.*")){
                throw new IllegalArgumentException("Este campo no puede contener números.");
            }

        } catch (IllegalArgumentException e){
            System.out.println("Error: " + e.getMessage());
        }
    }
}

