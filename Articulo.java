import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class Articulo implements Guardable {
    private static Integer idCounter = 0;
    private Integer idArticulo;
    private Autor autor;
    private String titulo;
    private String detalle;
    private LocalDate fecha;
    private ArrayList<Comentario> comentarios;
    private Categoria categoria;

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

    public void agregarComentario(Comentario comentario){
        comentarios.add(comentario);
    }

    public String getTitulo() {
        return titulo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public Integer getIdArticulo() {
        return idArticulo;
    }

    public ArrayList<Comentario> getComentarios() {
        return comentarios;
    }

    public Autor getAutor() {
        return autor;
    }
    
    public String toCsv() {
        return idArticulo + "," + autor.getDNI() + "," + titulo + "," + detalle + "," + fecha + "," + categoria;
    }

    public void guardarEnArchivo() throws IOException {
        try {
            HandlerArchivos.escribirArchivo(this.toCsv(), "articulos.txt");
        } catch (IOException e) {
            throw new IOException("Error al guardar el articulo: " + e.getMessage());
        }
    }

    public String toString(){
        return "\n" + categoria + "\n" + titulo + ": " + detalle + "\n" + fecha.toString() + "\n";
    }
}