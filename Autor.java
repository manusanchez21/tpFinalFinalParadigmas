public class Autor extends Usuario {
    public Autor(Integer DNI, String nombre, Integer edad) {
        super(DNI, nombre, edad);
    } 

    @Override
    public String toCsv() {
        return DNI + "," + nombre + "," + edad + ",";
    }

    @Override
    public String toString() {
        return "El dni del autor es " + DNI + " su nombre es " + nombre + " y tiene " + edad;
    }
}