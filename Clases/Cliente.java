package Clases;

public class Cliente{
    private String nombre;
    private String direccion;
    private String numeroTelefono;
    private String correoElectronico;

    public Cliente(String nombre, String direccion, String numeroTelefono, String correoElectronico){
        this.nombre = nombre;
        this.direccion = direccion;
        this.numeroTelefono = numeroTelefono;
        this.correoElectronico = correoElectronico;
    }

    public String getNombre(){
        return nombre;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public String getDireccion(){
        return direccion;
    }

    public void setDireccion(String direccion){
        this.direccion = direccion;
    }

    public String getNumeroTelefono(){
        return numeroTelefono;
    }

    public void setNumeroTelefono(String numeroTelefono){
        this.numeroTelefono = numeroTelefono;
    }

    public String getCorreoElectronico(){
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico){
        this.correoElectronico = correoElectronico;
    }
}
