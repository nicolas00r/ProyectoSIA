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
public class ClientesControl {
    private ArrayList<Cliente> lista;
    private HashMap<String, Cliente> clientesXRut;
    
    public ClientesControl(){
       lista = new ArrayList(); 
       clientesXRut = new HashMap();
    }
    
    public boolean agregarCliente(Cliente c)throws ClienteDuplicadoException {
        if(clientesXRut.containsKey(c.getRut())){
            throw new ClienteDuplicadoException("El cliente con RUT " + c.getRut() + " ya esta registrado.");
        }
        clientesXRut.put(c.getRut(), c);
        return lista.add(c);
    }
    
    public boolean eliminarCliente(String rut){
        Cliente c;
        c = clientesXRut.get(rut);
        clientesXRut.remove(c.getRut());
        return lista.remove(c);
    }
    
    public boolean existeCliente(String rut){
        return clientesXRut.containsKey(rut);
    }
    
    public Cliente obtenerCliente(String rut){
       Cliente c = clientesXRut.get(rut);
        return c;
    }
    
    public Cliente obtenerCliente(int i){
        if(i < 0 || i >= lista.size()){
            throw new IndexOutOfBoundsException("Indice figura fuera de rango" + i);
        }
        return lista.get(i);
    }
    
    public String listarClientes(){
        String ret;
        ret = "";
        
        for(int i = 0; i < lista.size(); i++){
            ret += lista.get(i).toString();
        }
        return ret;
    }
    
    public int totalClientes(){
        return lista.size();
    }
}
