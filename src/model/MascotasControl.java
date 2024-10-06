/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author 12212
 */
public class MascotasControl {
    private ArrayList<Mascota> lista;
    private HashMap<String,Mascota> mascotasXNombre;
    private HashMap<Integer, Mascota> mascotasXId;
    
    public MascotasControl(){
        lista = new ArrayList();
        mascotasXNombre = new HashMap();
        mascotasXId = new HashMap();
    }
    
    public String listarMascotas(){
        String ret;
        ret = "";
        
        for(int i = 0; i < lista.size(); i++){
            ret += lista.get(i).toString();
        }
        return ret;
    }
    
    public boolean agregarMascota(Mascota m){
        mascotasXNombre.put(m.getNombreMascota(), m);
        mascotasXId.put(m.getId(), m);
        return lista.add(m);
    }
    
    public boolean tieneMascotas(){
        return lista.isEmpty();
    }
    
    public Mascota obtenerMascotaPorNombre(String nombre){
        Mascota mascota = mascotasXNombre.get(nombre);
        return mascota;
    }

    public Mascota obtenerMascotaPorId(int id){
        Mascota mascota = mascotasXId.get(id);
        return mascota;
    }
}
