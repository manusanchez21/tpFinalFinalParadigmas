import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

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

    public ArrayList<Articulo> cargarArticulos() {
        ArrayList<Articulo> articulos = new ArrayList<Articulo>();

        

        return articulos;
    }
}