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
    
    public boolean agregarCliente(Cliente c){
        clientesXRut.put(c.getRut(), c);
        return lista.add(c);
    }
    
    public boolean eliminarCliente(String rut)throws ClienteNoEncontradoException{
        Cliente c;
        c = clientesXRut.get(rut);
        if(c == null){
            throw new ClienteNoEncontradoException("El cliente con RUT " + rut + " no se encuentra en el sistema.");
        }
        clientesXRut.remove(c.getRut());
        return lista.remove(c);
    }
    
    public boolean existeCliente(String rut){
        return clientesXRut.containsKey(rut);
    }
    
    public Cliente obtenerCliente(String rut) throws ClienteNoEncontradoException{
        Cliente c = clientesXRut.get(rut);
        if(c == null){
            throw new ClienteNoEncontradoException("El cliente con RUT " + rut + "no se encuentra en el sistema.");
        }
        return c;
    }
    
    public String listarClientes(){
        String ret;
        ret = "";
        
        for(int i = 0; i < lista.size(); i++){
            ret += lista.get(i).toString();
        }
        return ret;
    }
}
