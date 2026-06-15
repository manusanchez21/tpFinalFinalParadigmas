public abstract class Usuario {
    private Integer DNI;
    private String nombre;
    private Integer edad;

    public Usuario(Integer DNI, String nombre, Integer edad) {
        this.DNI = DNI;
        this.nombre = nombre;
        this.edad = edad;
    }
    
    public void comentar(Articulo articulo, String texto) {
        Comentario comentario = new Comentario(this.DNI, texto);
        articulo.agregarComentario(comentario);
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

    public abstract String toString();
    public abstract String toCsv();
}