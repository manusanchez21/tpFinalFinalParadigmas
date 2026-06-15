import java.io.IOException;
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
        for (Articulo articulo : this.articulos) {
            if (articulo.getFecha().isAfter())
        }
        return articulosFiltrados;
    }

    public void listarArticulosUltimoMes() {

    }
    
    public void mostrarTodosLosArticulos() {

    }

    public void mostrarComentariosDeUnaNoticia() {

    }

    public void moastrarArticlosPorAutor() {

    }

    public void agregarAutor() {

    }

    public void agregarLector() {

    }

    public void agregarArticulo() {
        
    }

}