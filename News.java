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
            if (articulo.getFecha().isAfter(actualMenosUnAnio)) {
                articulosFiltrados.add(articulo);
            }
        }
        return articulosFiltrados;
    }

    public ArrayList<Articulo> listarArticulosUltimoMes() {
        ArrayList<Articulo> articulosFiltrados = new ArrayList<Articulo>();
        LocalDate actualMenosUnMes = LocalDate.now().minusMonths(1);
        for (Articulo articulo : this.articulos) {
            if (articulo.getFecha().isAfter(actualMenosUnMes));
        }
        return articulosFiltrados;
    }

    public ArrayList<Articulo> todosLosArticulos() {
        return this.articulos;
    }

    public ArrayList<Comentario> mostrarComentariosDeUnArticulo(Integer idArticulo) {
        for (Articulo articulo : articulos) {
            if (articulo.getIdArticulo() == idArticulo) {
                return articulo.getComentarios();
            }
        }
        ArrayList<Comentario> comentarios = null;
        return comentarios;
    }

    public ArrayList<Articulo> mostrarArticlosPorAutor(Integer dniAutor) {
        ArrayList<Articulo> articulosDeAutor = new ArrayList<Articulo>();

        for (Articulo articulo : this.articulos) {
            if (articulo.getDniAutor() == dniAutor) {
                articulosDeAutor.add(articulo);
            }
        }
        return articulosDeAutor;
    }

    public void agregarAutor() {

    }

    public void agregarLector() {

    }

    public void agregarArticulo(Integer dniAutor, String titulo, String detalle, String categoria) throws IOException {
        if (dniAutor < 0 || dniAutor == null) {
            throw new IllegalArgumentException();
        }
        if (titulo == null || detalle == null) {
            throw new IllegalArgumentException();
        }
        if (titulo.equals("") || detalle.equals("")) {
            throw new IllegalArgumentException();
        }

        Categoria categoriaC = verificarYCrearCategoria(categoria);


        Articulo articulo = new Articulo(dniAutor, titulo, detalle, categoriaC);
        this.articulos.add(articulo);
        articulo.guardarEnArchivo();
    }


    private Categoria verificarYCrearCategoria(String categoria) throws IllegalArgumentException {
        if (categoria == null) {
            throw new IllegalArgumentException();
        }

        String categoriaLimpia = categoria.trim();

        for (Categoria c : Categoria.values()) {
            if (c.name().equalsIgnoreCase(categoriaLimpia)) {
                return c;
            }
        }
        throw new IllegalArgumentException("La categoria no es valida");
    }
}