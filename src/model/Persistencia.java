package model;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * La clase Persistencia maneja la carga y almacenamiento de datos desde y hacia archivos CSV.
 * Permite gestionar listas de clientes y mascotas, facilitando su persistencia en archivos externos.
 * 
 * Proporciona métodos para cargar datos desde archivos CSV y guardarlos nuevamente, manteniendo la integridad
 * de los datos del sistema.
 * 
 * @see Cliente
 * @see Mascota
 */
public class Persistencia {

    /**
     * Carga una lista de clientes desde un archivo CSV y los agrega a la lista proporcionada.
     * Cada línea del archivo CSV se convierte en un objeto {@link Cliente} que se añade a la lista.
     * 
     * @param listaClientes La lista de clientes donde se cargarán los datos.
     * @throws IOException Si ocurre un error de entrada/salida al leer el archivo.
     */
    public void cargarCsvClientes(List<Cliente> listaClientes) throws IOException {
        BufferedReader lectorCsv = new BufferedReader(new InputStreamReader(new FileInputStream("src/datos/clientes.csv"), "UTF-8"));
        String linea;

        while ((linea = lectorCsv.readLine()) != null) {
            String[] datosCliente = linea.split(",");
            String nombre = datosCliente[0];
            String rut = datosCliente[1];
            String direccion = datosCliente[2];
            String telefono = datosCliente[3];
            String correo = datosCliente[4];

            Cliente cliente = new Cliente(nombre, rut, direccion, telefono, correo);
            listaClientes.add(cliente);
        }
        lectorCsv.close();
    }

    /**
     * Carga una lista de mascotas desde un archivo CSV y las asocia a sus respectivos dueños.
     * Cada línea del archivo CSV se convierte en un objeto {@link Mascota} que se asocia al {@link Cliente} correspondiente.
     * 
     * @param sistema El sistema que gestiona los clientes y mascotas, para poder buscar el dueño por su nombre.
     * @throws IOException Si ocurre un error de entrada/salida al leer el archivo.
     */
    public void cargarCsvMascotas(PetServiceManagement sistema) throws IOException {
        BufferedReader lectorCsv = new BufferedReader(new InputStreamReader(new FileInputStream("src/datos/mascotas.csv"), "UTF-8"));
        String linea;

        while ((linea = lectorCsv.readLine()) != null) {
            String[] datosMascota = linea.split(", ");
        
            String nombreMascota = datosMascota[0];
            String nombreDueño = datosMascota[1];
            String especie = datosMascota[2];
            String edad = datosMascota[3].trim();

            Mascota mascota = new Mascota(nombreMascota, nombreDueño, especie, edad);
        
            Cliente cliente = sistema.obtenerClientePorNombre(nombreDueño);
        
            if (cliente != null) {
                cliente.registrarMascota(mascota);
            }
        }
        lectorCsv.close();
    }

    /**
     * Guarda una lista de clientes en un archivo CSV. Cada cliente se guarda como una línea en el archivo, 
     * con sus datos separados por comas.
     * 
     * @param listaClientes La lista de clientes que se desea guardar.
     * @throws IOException Si ocurre un error de entrada/salida al escribir en el archivo.
     */
    public void guardarCsvClientes(List<Cliente> listaClientes) throws IOException {
        File file = new File("src/datos/clientes2.csv");
        FileOutputStream fos = new FileOutputStream(file, false);
        OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
        BufferedWriter bw = new BufferedWriter(osw);
        PrintWriter pw = new PrintWriter(bw);

        for (Cliente cliente : listaClientes) {
            String linea = cliente.getNombre() + "," +
                           cliente.getRut() + "," +
                           cliente.getDireccion() + "," +
                           cliente.getNumeroTelefono() + "," +
                           cliente.getCorreoElectronico();
            pw.println(linea);
        }
        pw.close();
        bw.close();
        osw.close();
        fos.close();
    }

    /**
     * Guarda una lista de mascotas en un archivo CSV. Cada mascota se guarda como una línea en el archivo,
     * con sus datos separados por comas.
     * 
     * @param listaMascotas La lista de mascotas que se desea guardar.
     * @throws IOException Si ocurre un error de entrada/salida al escribir en el archivo.
     */
    public void guardarCsvMascotas(List<Mascota> listaMascotas) throws IOException {
        File file = new File("src/datos/mascotas2.csv");
        FileOutputStream fos = new FileOutputStream(file, false);
        OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
        BufferedWriter bw = new BufferedWriter(osw);
        PrintWriter pw = new PrintWriter(bw);

        for (Mascota mascota : listaMascotas) {
            String linea = mascota.getNombreMascota() + "," +
                           mascota.getNombreDueño() + "," +
                           mascota.getEspecie() + "," +
                           mascota.getEdad() + "," +
                           mascota.getId();
            pw.println(linea);
        }
        pw.close();
        bw.close();
        osw.close();
        fos.close();
    }
}
