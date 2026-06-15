import java.io.IOException;

public class Comentario implements Guardable {
    private Integer dniUsuario;
    private Integer idArticulo;
    private String texto;
    
    Comentario(Integer dniUsuario, Integer idArticulo, String texto){
        this.dniUsuario = dniUsuario;
        this.idArticulo = idArticulo;
        this.texto = texto;
    }

    public String toCsv() {
        return dniUsuario + "," + idArticulo + "," + texto;
    }

    public void guardarEnArchivo() throws IOException {
        try {
            HandlerArchivos.escribirArchivo(this.toCsv(), "comentarios.txt");
        } catch (IOException e) {
            System.out.println("Error al guardar el comentario: " + e.getMessage());
        }
    }
}