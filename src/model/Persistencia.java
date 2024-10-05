
package model;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;


public class Persistencia {

    public void guardarClientesCSV(List<Cliente> clientes) throws IOException {
        File file = new File("clientes.csv");
        FileOutputStream fos = new FileOutputStream(file);
        OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
        BufferedWriter bw = new BufferedWriter(osw);
        PrintWriter pw = new PrintWriter(bw);

        for (Cliente cliente : clientes) {
            String linea = cliente.getNombre() + "," +
                           cliente.getRut() + "," +
                           cliente.getDireccion() + "," +
                           cliente.getNumeroTelefono() + "," +
                           cliente.getCorreoElectronico();
            pw.write(linea + "\n");
        }

        pw.close();
        bw.close();
        osw.close();
        fos.close();
    }

    public void guardarMascotasCSV(List<Mascota> mascotas) throws IOException {
        File file = new File("mascotas.csv");
        FileOutputStream fos = new FileOutputStream(file);
        OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
        BufferedWriter bw = new BufferedWriter(osw);
        PrintWriter pw = new PrintWriter(bw);

        for (Mascota mascota : mascotas) {
            String linea = mascota.getNombreMascota() + "," +
                           mascota.getNombreDueño() + "," +
                           mascota.getEspecie() + "," +
                           mascota.getEdad() + "," +
                           mascota.getId();
            pw.write(linea + "\n");
        }

        pw.close();
        bw.close();
        osw.close();
        fos.close();
    }

    public void guardarServiciosCSV(List<Servicio> servicios) throws IOException {
        File file = new File("servicios.csv");
        FileOutputStream fos = new FileOutputStream(file);
        OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
        BufferedWriter bw = new BufferedWriter(osw);
        PrintWriter pw = new PrintWriter(bw);

        for (Servicio servicio : servicios) {
            String linea = servicio.getTipo() + "," +
                           servicio.getFecha() + "," +
                           servicio.getDescripcion();
            pw.write(linea + "\n");
        }

        pw.close();
        bw.close();
        osw.close();
        fos.close();
    }

    public void cargarClientesCSV(List<Cliente> clientes) throws IOException {
        BufferedReader lectorCsv = new BufferedReader(new InputStreamReader(new FileInputStream("clientes.csv"), StandardCharsets.UTF_8));
        String linea;

        while((linea = lectorCsv.readLine()) != null) {
            String[] datos = linea.split(",");
            String nombre = datos[0];
            String rut = datos[1];
            String direccion = datos[2];
            String numeroTelefono = datos[3];
            String correoElectronico = datos[4];

            Cliente cliente = new Cliente(nombre, rut, direccion, numeroTelefono, correoElectronico);
            clientes.add(cliente);
        }

        lectorCsv.close();
    }

    public void cargarMascotasCSV(List<Mascota> mascotas) throws IOException {
        BufferedReader lectorCsv = new BufferedReader(new InputStreamReader(new FileInputStream("mascotas.csv"), StandardCharsets.UTF_8));
        String linea;

        while((linea = lectorCsv.readLine()) != null) {
            String[] datos = linea.split(",");
            String nombreMascota = datos[0];
            String nombreDueño = datos[1];
            String especie = datos[2];
            int edad = Integer.parseInt(datos[3].trim());

            Mascota mascota = new Mascota(nombreMascota, nombreDueño, especie, edad);
            mascotas.add(mascota);
        }

        lectorCsv.close();
    }
    
    public void cargarServiciosCSV(List<Servicio> servicios) throws IOException {
        BufferedReader lectorCsv = new BufferedReader(new InputStreamReader(new FileInputStream("servicios.csv"), StandardCharsets.UTF_8));
        String linea;

        while((linea = lectorCsv.readLine()) != null) {
            String[] datos = linea.split(",");
            String tipo = datos[0];
            String fecha = datos[1];
            String descripcion = datos[2];

            Servicio servicio = new Servicio(tipo, fecha, descripcion);
            servicios.add(servicio);
        }

        lectorCsv.close();
    }
}
