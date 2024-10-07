package model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * La clase ClientesControl es responsable de gestionar una lista de clientes y un mapa de clientes
 * indexado por su RUT (Rol Único Tributario). Proporciona métodos para agregar, eliminar, buscar y listar clientes,
 * además de controlar posibles duplicados a través de excepciones personalizadas.
 * 
 * Esta clase es utilizada para administrar los datos de clientes en el sistema de gestión de citas y servicios para mascotas.
 * 
 * @see Cliente
 * @see ClienteDuplicadoException
 * @author 12212
 */
public class ClientesControl {

    /** Lista que contiene todos los clientes registrados */
    private ArrayList<Cliente> lista;

    /** Mapa que asocia el RUT de cada cliente con su objeto Cliente */
    private HashMap<String, Cliente> clientesXRut;

    /**
     * Constructor que inicializa la lista de clientes y el mapa de clientes por RUT.
     */
    public ClientesControl() {
        lista = new ArrayList<>();
        clientesXRut = new HashMap<>();
    }

    /**
     * Agrega un cliente al sistema, controlando que no exista previamente.
     * 
     * @param c El cliente a agregar
     * @return {@code true} si el cliente fue agregado exitosamente, {@code false} en caso contrario
     * @throws ClienteDuplicadoException Si ya existe un cliente con el mismo RUT en el sistema
     */
    public boolean agregarCliente(Cliente c) throws ClienteDuplicadoException {
        if (clientesXRut.containsKey(c.getRut())) {
            throw new ClienteDuplicadoException("El cliente con RUT " + c.getRut() + " ya está registrado.");
        }
        clientesXRut.put(c.getRut(), c);
        return lista.add(c);
    }

    /**
     * Elimina un cliente del sistema utilizando su RUT.
     * 
     * @param rut El RUT del cliente a eliminar
     * @return {@code true} si el cliente fue eliminado exitosamente, {@code false} si no se encontró el cliente
     */
    public boolean eliminarCliente(String rut) {
        Cliente c = clientesXRut.get(rut);
        if (c != null) {
            clientesXRut.remove(c.getRut());
            return lista.remove(c);
        }
        return false;
    }

    /**
     * Verifica si un cliente con un RUT específico está registrado en el sistema.
     * 
     * @param rut El RUT del cliente a verificar
     * @return {@code true} si el cliente existe, {@code false} en caso contrario
     */
    public boolean existeCliente(String rut) {
        return clientesXRut.containsKey(rut);
    }

    /**
     * Obtiene un cliente utilizando su RUT.
     * 
     * @param rut El RUT del cliente
     * @return El cliente correspondiente al RUT, o {@code null} si no se encuentra
     */
    public Cliente obtenerCliente(String rut) {
        return clientesXRut.get(rut);
    }

    /**
     * Obtiene un cliente según su índice en la lista de clientes.
     * 
     * @param i El índice del cliente en la lista
     * @return El cliente en la posición indicada
     * @throws IndexOutOfBoundsException Si el índice está fuera de los límites de la lista
     */
    public Cliente obtenerCliente(int i) {
        if (i < 0 || i >= lista.size()) {
            throw new IndexOutOfBoundsException("Índice fuera de rango: " + i);
        }
        return lista.get(i);
    }

    /**
     * Devuelve una representación en cadena de texto de todos los clientes registrados en el sistema.
     * Cada cliente se representará utilizando su método `toString`.
     * 
     * @return Una cadena de texto con los detalles de todos los clientes
     */
    public String listarClientes() {
        StringBuilder ret = new StringBuilder();

        for (Cliente cliente : lista) {
            ret.append(cliente.toString());
        }
        return ret.toString();
    }

    /**
     * Devuelve el número total de clientes registrados en el sistema.
     * 
     * @return El número total de clientes
     */
    public int totalClientes() {
        return lista.size();
    }
}
