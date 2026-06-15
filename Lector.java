public class Lector extends Usuario {
    public Lector(Integer DNI, String nombre, Integer edad) {
        super(Integer DNI, String nombre, Integer edad);
    }    

    @Override
    public String toCsv() {
        return dni "," nombre "," edad ",";
    }

    @Override
    public String toString() {
        return "El dni del lector es " + dni " su nombre es" + nombre + " y tiene " + edad;
    }
}