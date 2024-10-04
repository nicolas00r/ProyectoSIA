/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import view.VentanaPrincipal;

/**
 *
 * @author 12212
 */
public class ControladorMain {
    private VentanaPrincipal main;
    
    public void iniciar(){
        main = new VentanaPrincipal();
        main.setVisible(true);
    }
}
