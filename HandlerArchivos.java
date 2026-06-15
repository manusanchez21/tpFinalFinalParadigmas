import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Clase HandlerArchivos.
 * Gestiona la lectura y escritura de datos en archivos de texto (CSV).
 * Maneja la persistencia de usuarios, artículos y comentarios.
 * 
 * @author Jano Chiambretto y Manuel Sanchez Fossa
 * @version 1.0.0
 */
public class HandlerArchivos {
    /**
     * Constructor de HandlerArchivos.
     */
    public HandlerArchivos() {
    }

    /**
     * Escribe una línea de texto CSV en un archivo.
     * Si el archivo no existe, lo crea.
     * 
     * @param textoCsv el texto CSV a escribir
     * @param path la ruta del archivo
     * @throws IOException si ocurre un error al escribir
     */
    public static void escribirArchivo(String textoCsv, String path) throws IOException {
        FileWriter fw = new FileWriter(path, true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(textoCsv);
        bw.newLine();
        bw.close();
    }

    /**
     * Carga todos los usuarios del archivo.
     * Distingue entre Autores (tipo 0) y Lectores (tipo 1).
     * 
     * @return HashMap con los usuarios cargados, indexed por DNI
     * @throws IOException si ocurre un error al leer el archivo
     */
    public HashMap<Integer, Usuario> cargarUsuarios() throws IOException {
        HashMap<Integer, Usuario> usuarios = new HashMap<Integer, Usuario>();

        BufferedReader reader = new BufferedReader(new FileReader("usuarios.txt"));

        String usuario = reader.readLine();
        while (usuario != null) {
            String[] datos = usuario.split(",");
            int dni = Integer.parseInt(datos[0]);
            String nombre = datos[1];
            int edad = Integer.parseInt(datos[2]);
            int tipo = Integer.parseInt(datos[3]);
            if (tipo == 0) {
                Autor autor = new Autor(dni, nombre, edad);
                usuarios.put(dni, autor);
            } else {
                Lector lector = new Lector(dni, nombre, edad);
                usuarios.put(dni, lector);
            }

            usuario = reader.readLine();
        }

        reader.close();
        return usuarios;

    }

    /**
     * Carga todos los artículos del archivo.
     * Asocia comentarios a cada artículo.
     * 
     * @param usuarios HashMap con los usuarios cargados previamente
     * @return HashMap con los artículos cargados, indexed por ID
     * @throws IOException si ocurre un error al leer el archivo
     */
    public HashMap<Integer, Articulo> cargarArticulos(HashMap<Integer, Usuario> usuarios) throws IOException {
        HashMap<Integer, Articulo> articulos = new HashMap<Integer, Articulo>();

        BufferedReader reader = new BufferedReader(new FileReader("articulos.txt"));

        String articuloCsv = reader.readLine();

        HashMap<Integer, ArrayList<Comentario>> comentarios = cargarComentarios(usuarios);

        while (articuloCsv != null) {
            String[] datos = articuloCsv.split(",");
            Integer idArticulo = Integer.parseInt(datos[0]);
            Usuario usuario = usuarios.get(Integer.parseInt(datos[1]));
            if(usuario == null || !(usuario instanceof Autor)){
                reader.close();
                throw new IllegalArgumentException("Existe inconsistencia entre los datos, un articulo fue escrito por un autor inexistente");
            }
            Autor autor = (Autor) usuario;
            datos[5] = datos[5].trim();
            Categoria categoria = Categoria.valueOf(datos[5]);
            if (comentarios.get(idArticulo) != null) {
                Articulo articulo = new Articulo(idArticulo, autor, datos[2], datos[3], datos[4], comentarios.get(idArticulo), categoria);
                articulos.put(idArticulo, articulo);

            } else {
                Articulo articulo = new Articulo(idArticulo, autor, datos[2], datos[3], datos[4], new ArrayList<Comentario>(), categoria);
                articulos.put(idArticulo, articulo);
            }
            articuloCsv = reader.readLine();
        }

        reader.close();
        return articulos;
    }

    /**
     * Carga todos los comentarios del archivo.
     * Agrupa comentarios por ID de artículo.
     * 
     * @param usuarios HashMap con los usuarios cargados previamente
     * @return HashMap con ArrayList de comentarios, indexed por ID de artículo
     * @throws IOException si ocurre un error al leer el archivo
     */
    private HashMap<Integer, ArrayList<Comentario>> cargarComentarios(HashMap<Integer, Usuario> usuarios) throws IOException {
        HashMap<Integer, ArrayList<Comentario>> comentarios = new HashMap<Integer, ArrayList<Comentario>>();

        BufferedReader reader = new BufferedReader(new FileReader("comentarios.txt"));

        String comentarioCsv = reader.readLine();

        while (comentarioCsv != null) {
            String[] datos = comentarioCsv.split(",");
            Integer dniUsuario = Integer.parseInt(datos[0]);
            Integer idArticulo = Integer.parseInt(datos[1]);
            Comentario comentario = new Comentario(usuarios.get(dniUsuario), idArticulo, datos[2]);
            ArrayList<Comentario> comentariosTemp = comentarios.get(idArticulo);
            if (comentariosTemp == null) {
                comentariosTemp = new ArrayList<Comentario>();
            }
            comentariosTemp.add(comentario);
            comentarios.remove(idArticulo);
            comentarios.put(idArticulo, comentariosTemp);
            comentarioCsv = reader.readLine();
        }

        reader.close();
        return comentarios;
    }

}