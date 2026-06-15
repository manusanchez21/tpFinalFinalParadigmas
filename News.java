import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;

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
                articulosFiltradosTxt.add(articulo.toString());
            }
        }
        return articulosFiltradosTxt;
    }

    public ArrayList<String> listarArticulosUltimoMes() {
        ArrayList<String> articulosFiltradosTxt = new ArrayList<String>();
        LocalDate actualMenosUnMes = LocalDate.now().minusMonths(1);
        for (Articulo articulo : this.articulos.values()) {
            if (articulo.getFecha().isAfter(actualMenosUnMes)){
                articulosFiltradosTxt.add(articulo.toString());
            }
        }
        return articulosFiltradosTxt;
    }

    public ArrayList<String> todosLosArticulos() {
        ArrayList<String> todosLosArticulos = new ArrayList<String>();
        for (Articulo articulo : articulos.values()) {
            todosLosArticulos.add(articulo.toString());
        }
        return todosLosArticulos;
    }

    public ArrayList<String> mostrarComentariosDeUnArticulo(Integer idArticulo) {
        Articulo articulo = articulos.get(idArticulo);

        if (articulo == null) {
            throw new IllegalArgumentException("El articulo no existe");
        } 

        ArrayList<String> comentarios = new ArrayList<String>();

        for (Comentario comentario: articulo.getComentarios()) {
            String comentarioString = comentario.toString();
            comentarios.add(comentarioString);
        }

        return comentarios;
    }

    public ArrayList<String> mostrarArticulosPorAutor(Integer dniAutor) throws InputMismatchException {
        if(!(validarDNI(dniAutor) || !(usuarios.get(dniAutor) instanceof Autor))){
            throw new IllegalArgumentException("El DNI ingresado no es de un autor en el sistema");
        }
        ArrayList<String> articulosDeAutor = new ArrayList<>();

        for (Articulo articulo : this.articulos.values()) {
            if (articulo.getAutor().getDNI() == dniAutor) {
                articulosDeAutor.add(articulo.toString());
            }
        }
        return articulosDeAutor;
    }

    public void agregarAutor(Integer DNI, String nombre, Integer edad) throws IOException {
        if (nombre == null || nombre.equals("")) {
            throw new IllegalArgumentException("No se puede guardar un nombre vacio");
        }
        if (edad < 0) {
            throw new IllegalArgumentException("No se puede generar una edad menor a 0");
        }
        if (DNI == null || DNI <= 0 || usuarios.containsKey(DNI)) {
            throw new IllegalArgumentException("DNI no valido");
        }
        Autor autor = new Autor(DNI, nombre, edad);
        usuarios.put(DNI,autor);
        autor.guardarEnArchivo();
    }

    public void agregarLector(Integer DNI, String nombre, Integer edad) throws IOException {
        if (nombre == null || nombre.equals("")) {
            throw new IllegalArgumentException("No se puede guardar un nombre vacio");
        }
        if (edad < 0) {
            throw new IllegalArgumentException("No se puede generar una edad menor a 0");
        }
        if (DNI <= 0) {
            throw new IllegalArgumentException("DNI no valido");
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
        validarDNI(dniAutor);
        if (titulo == null || detalle == null) {
            throw new IllegalArgumentException();
        }

        if (titulo.equals("") || detalle.equals("")) {
            throw new IllegalArgumentException();
        }
        Categoria categoriaC = verificarYCrearCategoria(categoria);

    
        if (usuarios.get(dniAutor) instanceof Autor) {
            throw new IllegalArgumentException("No se pudo encontrar el autor con ese dni");
        }
        Autor autor = (Autor) usuarios.get(dniAutor);

        Articulo articulo = new Articulo(autor, titulo, detalle, categoriaC);
        this.articulos.put(articulo.getIdArticulo(), articulo);
        articulo.guardarEnArchivo();
    }

    public void agregarComentario(Integer dniUsuario, Integer idArticulo, String text) throws IOException {
        if (idArticulo == null || idArticulo < 0) {
            throw new IllegalArgumentException("El dni no puede ser null o negativo");
        }
        if (text == null) {
            throw new IllegalArgumentException("El texto no puede ser null");
        }
        if (text.equals("")) {
            throw new IllegalArgumentException("El texto no puede estar vacio");
        }

        validarDNI(dniUsuario);

        if (!articulos.containsKey(idArticulo)) {
            throw new IllegalArgumentException("El articulo no existe");
        }
        Comentario comentario = usuarios.get(dniUsuario).comentar(articulos.get(idArticulo), text);
        this.articulos.get(idArticulo).agregarComentario(comentario);
        comentario.guardarEnArchivo();
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

    private boolean validarDNI(Integer dniUsuario) {
        if (dniUsuario == null || dniUsuario <= 0) {
            throw new IllegalArgumentException("El DNI " + dniUsuario + " es invalido");
        }else if (this.usuarios.containsKey(dniUsuario)) {
            throw new IllegalArgumentException("No existe el autor con dni" + dniUsuario);
        }
        return true;
    }
}