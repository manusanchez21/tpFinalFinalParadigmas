import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class News {
    ArrayList<Usuario> usuarios;
    ArrayList<Articulo> articulos;
    HandlerArchivos handlerArchivos;
    
    // hacer constructor para cuando se inicializa el programa cargando arraylists

    public News() {
        this.handlerArchivos = new HandlerArchivos();
        try {
            this.articulos = handlerArchivos.cargarArticulos();
            this.usuarios = handlerArchivos.cargarUsuarios();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public ArrayList<Articulo> listarArticulosUltimoAnio() {
        ArrayList<Articulo> articulosFiltrados = new ArrayList<Articulo>();
        LocalDate actualMenosUnAnio = LocalDate.now().minusYears(1);
        for (Articulo articulo : this.articulos) {
            if (articulo.getFecha().isAfter(actualMenosUnAnio));
        }
        return articulosFiltrados;
    }

    public ArrayList<Articulo> listarArticulosUltimoMes() {

    }
    
    public ArrayList<Articulo> todosLosArticulos() {
        return this.articulos;
    }

    public ArrayList<Comentario> mostrarComentariosDeUnArticulo(Integer articuloId) {

    }

    public ArrayList<Articulo> moastrarArticlosPorAutor() {

    }

    public void agregarAutor() {

    }

    public void agregarLector() {

    }

    public void agregarArticulo() {
        
    }

}