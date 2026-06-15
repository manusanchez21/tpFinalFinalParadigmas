import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Clase Articulo.
 * Representa un artículo de noticias publicado por un autor.
 * Contiene el título, detalle, fecha, autor y comentarios asociados.
 * 
 * @author Jano Chiambretto y Manuel Sanchez Fossa
 * @version 1.0.0
 */
public class Articulo implements Guardable {
    private static Integer idCounter = 0;
    private Integer idArticulo;
    private Autor autor;
    private String titulo;
    private String detalle;
    private LocalDate fecha;
    private ArrayList<Comentario> comentarios;
    private Categoria categoria;

    /**
     * Constructor de Articulo (nuevo).
     * Crea un artículo con la fecha actual.
     * 
     * @param autor el autor del artículo
     * @param titulo el título del artículo
     * @param detalle el contenido del artículo
     * @param categoria la categoría del artículo
     */
    Articulo(Autor autor, String titulo, String detalle, Categoria categoria){
        idArticulo = idCounter;
        idCounter++;
        this.autor = autor;
        this.titulo = titulo;
        this.detalle = detalle;
        fecha = LocalDate.now();
        comentarios = new ArrayList<Comentario>();
        this.categoria = categoria; 
    }

    /**
     * Constructor de Articulo (cargado).
     * Crea un artículo desde datos almacenados con ID, comentarios y fecha especía.
     * 
     * @param idArticulo el ID del artículo
     * @param autor el autor del artículo
     * @param titulo el título del artículo
     * @param detalle el contenido del artículo
     * @param fecha la fecha en formato string (yyyy-MM-dd)
     * @param comentarios lista de comentarios asociados
     * @param categoria la categoría del artículo
     */
    Articulo(Integer idArticulo, Autor autor, String titulo, String detalle, String fecha, ArrayList<Comentario> comentarios, Categoria categoria){
        if (idArticulo >= idCounter) {
            idCounter = idArticulo + 1;
        }
        this.idArticulo = idArticulo;
        this.autor = autor;
        this.titulo = titulo;
        this.detalle = detalle;
        String[] fechaPartes = fecha.split("-");
        this.fecha = LocalDate.of(Integer.parseInt(fechaPartes[0]), Integer.parseInt(fechaPartes[1]), Integer.parseInt(fechaPartes[2]));
        this.comentarios = comentarios;
        this.categoria = categoria; 
    }

    /**
     * Agrega un comentario al artículo.
     * 
     * @param comentario el comentario a agregar
     */
    public void agregarComentario(Comentario comentario){
        comentarios.add(comentario);
    }

    /**
     * Obtiene el título del artículo.
     * 
     * @return el título
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Obtiene la fecha de publicación del artículo.
     * 
     * @return la fecha del artículo
     */
    public LocalDate getFecha() {
        return fecha;
    }

    /**
     * Obtiene el ID del artículo.
     * 
     * @return el ID
     */
    public Integer getIdArticulo() {
        return idArticulo;
    }

    /**
     * Obtiene la lista de comentarios del artículo.
     * 
     * @return ArrayList con los comentarios
     */
    public ArrayList<Comentario> getComentarios() {
        return comentarios;
    }

    /**
     * Obtiene el autor del artículo.
     * 
     * @return el autor
     */
    public Autor getAutor() {
        return autor;
    }
    
    /**
     * Convierte los datos del artículo a formato CSV.
     * 
     * @return la representación CSV del artículo
     */
    public String toCsv() {
        return idArticulo + "," + autor.getDNI() + "," + titulo + "," + detalle + "," + fecha + "," + categoria;
    }

    /**
     * Guarda el artículo en el archivo de artículos.
     * 
     * @throws IOException si ocurre un error al guardar
     */
    public void guardarEnArchivo() throws IOException {
        try {
            HandlerArchivos.escribirArchivo(this.toCsv(), "articulos.txt");
        } catch (IOException e) {
            throw new IOException("Error al guardar el articulo: " + e.getMessage());
        }
    }

    /**
     * Retorna una representación textual del artículo.
     * 
     * @return cadena con categoría, título, detalle, fecha y autor
     */
    public String toString(){
        return "\n" + categoria + "\n" + titulo + ": " + detalle + "\n" + fecha.toString() + " - " + autor.getNombre() + "\n";
    }
}