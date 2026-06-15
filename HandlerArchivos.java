import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class HandlerArchivos {
    public HandlerArchivos() {
    }

    public static void escribirArchivo(String textoCsv, String path) throws IOException {
        FileWriter fw = new FileWriter(path, true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(textoCsv);
        bw.newLine();
        bw.close();
    }

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

    public HashMap<Integer, Articulo> cargarArticulos(HashMap<Integer, Usuario> usuarios) throws IOException {
        HashMap<Integer, Articulo> articulos = new HashMap<Integer, Articulo>();

        BufferedReader reader = new BufferedReader(new FileReader("articulos.txt"));

        String articuloCsv = reader.readLine();

        HashMap<Integer, ArrayList<Comentario>> comentarios = cargarComentarios();

        while (articuloCsv != null) {
            String[] datos = articuloCsv.split(",");
            Integer idArticulo = Integer.parseInt(datos[0]);
            Usuario usuario = usuarios.get(Integer.parseInt(datos[1]));
            if(usuario == null || !(usuario instanceof Autor)){
                reader.close();
                throw new IllegalArgumentException("Existe inconsistencia entre los datos, un articulo fue escrito por un autor inexistente");
            }
            Autor autor = new Autor(usuario.getDNI(), usuario.getNombre(), usuario.getEdad());
            datos[6] = datos[6].trim();
            Categoria categoria = Categoria.valueOf(datos[6]);
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

    private HashMap<Integer, ArrayList<Comentario>> cargarComentarios() throws IOException {
        HashMap<Integer, ArrayList<Comentario>> comentarios = new HashMap<Integer, ArrayList<Comentario>>();

        BufferedReader reader = new BufferedReader(new FileReader("comentarios.txt"));

        String comentarioCsv = reader.readLine();

        while (comentarioCsv != null) {
            String[] datos = comentarioCsv.split(",");
            Integer dniUsuario = Integer.parseInt(datos[0]);
            Integer idArticulo = Integer.parseInt(datos[1]);
            Comentario comentario = new Comentario(dniUsuario, idArticulo, datos[2]);
            ArrayList<Comentario> comentariosTemp = comentarios.get(idArticulo);
            comentariosTemp.add(comentario);
            comentarios.put(idArticulo, comentariosTemp);
            comentarioCsv = reader.readLine();
        }

        reader.close();
        return comentarios;
    }

}