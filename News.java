import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class News {
    HashMap<Integer, Usuario> usuarios;
    HashMap<Integer, Articulo> articulos;
    HandlerArchivos handlerArchivos;

    // hacer constructor para cuando se inicializa el programa cargando arraylists

    public News() {
        this.handlerArchivos = new HandlerArchivos();
        try {
            this.usuarios = handlerArchivos.cargarUsuarios();
            this.articulos = handlerArchivos.cargarArticulos(usuarios);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public ArrayList<String> listarArticulosUltimoAnio() {
        ArrayList<String> articulosFiltradosTxt = new ArrayList<String>();
        LocalDate actualMenosUnAnio = LocalDate.now().minusYears(1);
        for (Articulo articulo : this.articulos.values()) {
            if (articulo.getFecha().isAfter(actualMenosUnAnio)) {
                articulosFiltradosTxt.add(articulo.toString() + articulo.getAutor().getNombre());
            }
        }
        return articulosFiltradosTxt;
    }

    public ArrayList<Articulo> listarArticulosUltimoMes() {
        ArrayList<Articulo> articulosFiltrados = new ArrayList<Articulo>();
        LocalDate actualMenosUnMes = LocalDate.now().minusMonths(1);
        for (Articulo articulo : this.articulos.values()) {
            if (articulo.getFecha().isAfter(actualMenosUnMes));
        }
        return articulosFiltrados;
    }

    public Collection<Articulo> todosLosArticulos() {
        return this.articulos.values();
    }

    public ArrayList<Comentario> mostrarComentariosDeUnArticulo(Integer idArticulo) {
        for (Articulo articulo : articulos.values()) {
            if (articulo.getIdArticulo() == idArticulo) {
                return articulo.getComentarios();
            }
        }
        ArrayList<Comentario> comentarios = null;
        return comentarios;
    }

    public ArrayList<Articulo> mostrarArticlosPorAutor(Integer dniAutor) {
        ArrayList<Articulo> articulosDeAutor = new ArrayList<Articulo>();

        for (Articulo articulo : this.articulos.values()) {
            if (articulo.getAutor().getDNI() == dniAutor) {
                articulosDeAutor.add(articulo);
            }
        }
        return articulosDeAutor;
    }

    public void agregarAutor(Integer DNI, String nombre, Integer edad) throws IOException {
        if(nombre == null || nombre.equals("")){
            throw new IllegalArgumentException("No se puede guardar un nombre vacio");
        }
        if (edad < 0) {
            throw new IllegalArgumentException("No se puede generar una edad menor a 0");
        }
        for (Usuario usuario : usuarios.values()) {
            if (usuario.getDNI() == DNI) {
                throw new IllegalArgumentException("Ya existe un usuario con este DNI");
            }
        }
        Autor autor = new Autor(DNI, nombre, edad);
        usuarios.put(DNI,autor);
        autor.guardarEnArchivo();
    }

    public void agregarLector(Integer DNI, String nombre, Integer edad) throws IOException {
        if(nombre == null || nombre.equals("")){
            throw new IllegalArgumentException("No se puede guardar un nombre vacio");
        }
        if (edad < 0) {
            throw new IllegalArgumentException("No se puede generar una edad menor a 0");
        }
        for (Usuario usuario : usuarios.values()) {
            if (usuario.getDNI() == DNI) {
                throw new IllegalArgumentException("Ya existe un usuario con este DNI");
            }
        }
        Lector lector = new Lector(DNI, nombre, edad);
        usuarios.put(DNI,lector);
        lector.guardarEnArchivo();
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

    
        if (!(usuarios.containsKey(dniAutor) && usuarios.get(dniAutor) instanceof Autor)) {
            throw new IllegalArgumentException("No se pudo encontrar el autor con ese dni");
        }
        Autor autor = new Autor(dniAutor, usuarios.get(dniAutor).getNombre(), usuarios.get(dniAutor).getEdad());

        Articulo articulo = new Articulo(autor, titulo, detalle, categoriaC);
        this.articulos.put(articulo.getIdArticulo(), articulo);
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