import java.io.IOException;

/**
 * Clase Comentario.
 * Representa un comentario realizado por un usuario sobre un artículo.
 * 
 * @author Jano Chiambretto y Manuel Sanchez Fossa
 * @version 1.0.0
 */
public class Comentario implements Guardable {
    private Usuario usuario;
    private Integer idArticulo;
    private String texto;
    
    /**
     * Constructor de Comentario.
     * 
     * @param usuario el usuario que realiza el comentario
     * @param idArticulo el ID del artículo comentado
     * @param texto el texto del comentario
     */
    Comentario(Usuario usuario, Integer idArticulo, String texto){
        this.usuario = usuario;
        this.idArticulo = idArticulo;
        this.texto = texto;
    }

    /**
     * Convierte los datos del comentario a formato CSV.
     * 
     * @return la representación CSV del comentario
     */
    public String toCsv() {
        return this.usuario.getDNI() + "," + idArticulo + "," + texto;
    }

    /**
     * Retorna una representación textual del comentario.
     * 
     * @return cadena con el nombre del usuario y el texto del comentario
     */
    public String toString() {
        return usuario.getNombre() + ": " + this.texto;
    }

    /**
     * Guarda el comentario en el archivo de comentarios.
     * 
     * @throws IOException si ocurre un error al guardar
     */
    public void guardarEnArchivo() throws IOException {
        try {
            HandlerArchivos.escribirArchivo(this.toCsv(), "comentarios.txt");
        } catch (IOException e) {
            System.out.println("Error al guardar el comentario: " + e.getMessage());
        }
    }
}