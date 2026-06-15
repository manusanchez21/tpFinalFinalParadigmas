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

    public void escribirArchivo(String textoCsv, String path) throws IOException {
        try (FileWriter fw = new FileWriter(path);
                BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(textoCsv);
        }
    }

    public ArrayList<Usuario> cargarUsuarios() throws IOException {
        ArrayList<Usuario> usuarios = new ArrayList<Usuario>();

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
                usuarios.add(autor);
            } else {
                Lector lector = new Lector(dni, nombre, edad);
                usuarios.add(lector);
            }

            usuario = reader.readLine();
        }

        reader.close();
        return usuarios;

    }

    public ArrayList<Articulo> cargarArticulos() throws IOException {
        ArrayList<Articulo> articulos = new ArrayList<Articulo>();

        BufferedReader reader = new BufferedReader(new FileReader("articulos.txt"));

        String articuloCsv = reader.readLine();

        HashMap<Integer, ArrayList<Comentario>> comentarios = cargarComentarios();

        if (articuloCsv != null) {
            String[] datos = articuloCsv.split(",");
            Integer idArticulo = Integer.parseInt(datos[0]);
            Integer dniAutor = Integer.parseInt(datos[1]);
            datos[6] = datos[6].trim();
            Categoria categoria = Categoria.valueOf(datos[6]); 
            if (comentarios.get(idArticulo) != null) {
                Articulo articulo = new Articulo(idArticulo, dniAutor, datos[2], datos[3], datos[4],
                        comentarios.get(idArticulo), categoria);
                articulos.add(articulo);

            } else {
                Articulo articulo = new Articulo(idArticulo, dniAutor, datos[2], datos[3], datos[4],
                        new ArrayList<Comentario>(), categoria);
                articulos.add(articulo);
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