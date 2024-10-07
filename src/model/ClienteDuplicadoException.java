package model;

/**
 * La clase ClienteDuplicadoException es una excepción personalizada que se lanza cuando 
 * se intenta registrar un cliente que ya existe en el sistema.
 * 
 * Esta excepción extiende la clase {@link Exception}, y puede ser utilizada para manejar 
 * situaciones en las que se detecte un duplicado de cliente, proporcionando un mensaje
 * detallado sobre el error.
 * 
 * @author benja
 */
public class ClienteDuplicadoException extends Exception {

    /**
     * Constructor que recibe un mensaje detallado sobre la causa de la excepción.
     * 
     * @param mensaje El mensaje de error que se mostrará cuando se lance la excepción.
     */
    public ClienteDuplicadoException(String mensaje) {
        super(mensaje);
    }
}
