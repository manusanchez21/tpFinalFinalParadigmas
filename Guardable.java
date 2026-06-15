import java.io.IOException;

/**
 * Interfaz Guardable.
 * Define el contrato para la persistencia de datos en archivos.
 * 
 * @author Jano Chiambretto y Manuel Sanchez Fossa
 * @version 1.0.0
 */
interface Guardable {
    /**
     * Guarda los datos del objeto en un archivo de texto.
     * 
     * @throws IOException si ocurre un error al escribir el archivo
     */
    public void guardarEnArchivo() throws IOException;
}