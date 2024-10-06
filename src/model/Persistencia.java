package model;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class Persistencia {

    private static final String RUTA_CLIENTES = "src/datos/clientes.csv";
    private static final String RUTA_MASCOTAS = "src/datos/mascotas.csv";
    private static final String RUTA_SERVICIOS = "src/datos/servicios.csv";

    public void cargarCsvClientes(List<Cliente> listaClientes) throws IOException {
        BufferedReader lectorCsv = new BufferedReader(new InputStreamReader(new FileInputStream(RUTA_CLIENTES), StandardCharsets.UTF_8));
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

   
    public void cargarCsvMascotas(List<Mascota> listaMascotas) throws IOException {
        BufferedReader lectorCsv = new BufferedReader(new InputStreamReader(new FileInputStream(RUTA_MASCOTAS), StandardCharsets.UTF_8));
        String linea;

        while ((linea = lectorCsv.readLine()) != null) {
            String[] datosMascota = linea.split(",");
            String nombreMascota = datosMascota[0];
            String nombreDueño = datosMascota[1];
            String especie = datosMascota[2];
            String edad = datosMascota[3].trim();

            Mascota mascota = new Mascota(nombreMascota, nombreDueño, especie, edad);
            listaMascotas.add(mascota);
        }
        lectorCsv.close();
    }

    
    public void cargarCsvServicios(List<Servicio> listaServicios) throws IOException {
        BufferedReader lectorCsv = new BufferedReader(new InputStreamReader(new FileInputStream(RUTA_SERVICIOS), StandardCharsets.UTF_8));
        String linea;

        while ((linea = lectorCsv.readLine()) != null) {
            String[] datosServicio = linea.split(",");
            String tipo = datosServicio[0];
            String fecha = datosServicio[1];
            String descripcion = datosServicio[2];

            Servicio servicio = new Servicio(tipo, fecha, descripcion);
            listaServicios.add(servicio);
        }
        lectorCsv.close();
    }

    
    public void guardarCsvClientes(List<Cliente> listaClientes) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(RUTA_CLIENTES), StandardCharsets.UTF_8));
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
    }

  
     
    public void guardarCsvMascotas(List<Mascota> listaMascotas) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(RUTA_MASCOTAS), StandardCharsets.UTF_8));
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
    }

    
    public void guardarCsvServicios(List<Servicio> listaServicios) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(RUTA_SERVICIOS), StandardCharsets.UTF_8));
        PrintWriter pw = new PrintWriter(bw);

        for (Servicio servicio : listaServicios) {
            String linea = servicio.getTipo() + "," +
                           servicio.getFecha() + "," +
                           servicio.getDescripcion();
            pw.println(linea);
        }

        pw.close();
        bw.close();
    }
}