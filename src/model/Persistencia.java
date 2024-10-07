package model;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class Persistencia {

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

   
    public void cargarCsvMascotas(List<Mascota> listaMascotas) throws IOException {
        BufferedReader lectorCsv = new BufferedReader(new InputStreamReader(new FileInputStream("src/datos/mascotas.csv"), "UTF-8"));
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