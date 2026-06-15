import java.io.IOException;

public abstract class Usuario implements Guardable {
    protected Integer DNI;
    protected String nombre;
    protected Integer edad;

    public Usuario(Integer DNI, String nombre, Integer edad) {
        this.DNI = DNI;
        this.nombre = nombre;
        this.edad = edad;
    }
    
    public Comentario comentar(Articulo articulo, String texto) {
        Comentario comentario = new Comentario(this, articulo.getIdArticulo(), texto);
        articulo.agregarComentario(comentario);
        return comentario;
        // Handler
    }

    public Integer getDNI() {
        return DNI;
    }

    public Integer getEdad() {
        return edad;
    }

    public String getNombre() {
        return nombre;
    }

    public void guardarEnArchivo() throws IOException {
        try {
            HandlerArchivos.escribirArchivo(this.toCsv(), "usuarios.txt");
        } catch (IOException e) {
            throw new IOException("Error al guardar el usuario: " + e.getMessage());
        }
    }

    public abstract String toString();
    public abstract String toCsv();
}