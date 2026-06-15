import java.io.IOException;

public class Comentario implements Guardable {
    private Usuario usuario;
    private Integer idArticulo;
    private String texto;
    
    Comentario(Usuario usuario, Integer idArticulo, String texto){
        this.usuario = usuario;
        this.idArticulo = idArticulo;
        this.texto = texto;
    }

    public String toCsv() {
        return this.usuario.getNombre() + "," + idArticulo + "," + texto;
    }

    public String toString() {
        return "El nombre del usuario es " + usuario.getNombre() + " y el comentario es " + this.texto;
    }

    public void guardarEnArchivo() throws IOException {
        try {
            HandlerArchivos.escribirArchivo(this.toCsv(), "comentarios.txt");
        } catch (IOException e) {
            System.out.println("Error al guardar el comentario: " + e.getMessage());
        }
    }
}