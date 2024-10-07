package model;


import java.util.ArrayList;

public class GestionCitas{
    // Variables de instancia
    private static int creadorIdCita;

    // Constructor
    public GestionCitas(){
        creadorIdCita = 1;
    }

    // Método para buscar citas por fecha y tipo de servicio
    public ArrayList<Cita> buscarCitasPorFechaYServicio(ArrayList<Mascota> listaMascotas, String fecha, String tipoServicio) {
        ArrayList<Cita> citasFiltradas = new ArrayList<>();
        
        for (Mascota mascota : listaMascotas) {
            // Iterar sobre la lista de citas de cada mascota
            for (Cita cita : mascota.getListaCitas()) {
                // Filtrar por fecha y servicio
                if (cita.getFecha().equals(fecha) && cita.getServicio().getTipo().equalsIgnoreCase(tipoServicio)) {
                    citasFiltradas.add(cita);
                }
            }
        }
        // Retornar las citas filtradas
        return citasFiltradas;
    }
}


