import java.io.IOException;

/**
 * Clase abstracta Usuario.
 * Representa a un usuario del sistema de noticias. Puede ser Autor o Lector.
 * 
 * @author Jano Chiambretto y Manuel Sanchez Fossa
 * @version 1.0.0
 */
public abstract class Usuario implements Guardable {
    protected Integer DNI;
    protected String nombre;
    protected Integer edad;

    /**
     * Constructor de Usuario.
     * 
     * @param DNI el DNI del usuario
     * @param nombre el nombre del usuario
     * @param edad la edad del usuario
     */
    public Usuario(Integer DNI, String nombre, Integer edad) {
        this.DNI = DNI;
        this.nombre = nombre;
        this.edad = edad;
    }
    
    /**
     * Permite al usuario comentar un artículo.
     * 
     * @param articulo el artículo a comentar
     * @param texto el texto del comentario
     * @return el comentario creado
     */
    public Comentario comentar(Articulo articulo, String texto) {
        Comentario comentario = new Comentario(this, articulo.getIdArticulo(), texto);
        articulo.agregarComentario(comentario);
        return comentario;
        // Handler
    }

    /**
     * Obtiene el DNI del usuario.
     * 
     * @return el DNI
     */
    public Integer getDNI() {
        return DNI;
    }

    /**
     * Obtiene la edad del usuario.
     * 
     * @return la edad
     */
    public Integer getEdad() {
        return edad;
    }

    /**
     * Obtiene el nombre del usuario.
     * 
     * @return el nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Guarda el usuario en el archivo de usuarios.
     * 
     * @throws IOException si ocurre un error al guardar
     */
    public void guardarEnArchivo() throws IOException {
        try {
            HandlerArchivos.escribirArchivo(this.toCsv(), "usuarios.txt");
        } catch (IOException e) {
            throw new IOException("Error al guardar el usuario: " + e.getMessage());
        }
    }

    public abstract String toString();
    public abstract String toCsv();
}