import java.time.LocalDate;
import java.util.ArrayList;

public class Articulo {
    private static Integer idCounter = 0;
    private Integer idArticulo;
    private Integer dniAutor;
    private String titulo;
    private String detalle;
    private LocalDate fecha;
    private ArrayList<Comentario> comentarios;
    private Categoria categoria;

    Articulo(Integer dniAutor, String titulo, String detalle, Categoria categoria){
        idArticulo = idCounter;
        idCounter++;
        this.dniAutor = dniAutor;
        this.titulo = titulo;
        this.detalle = detalle;
        fecha = LocalDate.now();
        comentarios = new ArrayList<Comentario>();
        this.categoria = categoria; 
    }

    Articulo(Integer idArticulo, Integer dniAutor, String titulo, String detalle, String fecha, ArrayList<Comentario> comentarios, Categoria categoria){
        if (idArticulo >= idCounter) {
            idCounter = idArticulo + 1;
        }
        this.idArticulo = idArticulo;
        this.dniAutor = dniAutor;
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

    public LocalDate getFecha() {
        return fecha;
    }

    public Integer getIdArticulo() {
        return idArticulo;
    }

    public ArrayList<Comentario> getComentarios() {
        return comentarios;
    }

    public Integer getDniAutor() {
        return dniAutor;
    }
    
    public String toCsv() {
        return idArticulo + "," + dniAutor + "," + titulo + "," + detalle + "," + fecha + "," + categoria;
    }
}