package Clases;

public class Servicio{
    private String tipo;
    private String fecha;
    private String descripcion;

    public Servicio(String tipo, String fecha, String descripcion){
        this.tipo = tipo;
        this.fecha = fecha;
        this.descripcion = descripcion;
    }

    public String getTipo(){
        return tipo;
    }

    public void setTipo(String tipo){
        this.tipo = tipo;
    }

    public String getFecha(){
        return fecha;
    }

    public void setFecha(String fecha){
        this.fecha = fecha;
    }

    public String getDescripcion(){
        return descripcion;
    }

    public void setDescripcion(String descripcion){
        this.descripcion = descripcion;
    }
}