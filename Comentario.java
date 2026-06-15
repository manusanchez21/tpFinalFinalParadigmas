public class Comentario {
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
}