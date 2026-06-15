public class Lector extends Usuario {
    public Lector(Integer DNI, String nombre, Integer edad) {
        super(DNI, nombre, edad);
    }    

    @Override
    public String toCsv() {
        return this.DNI + "," + nombre + "," + edad + ",";
    }

    @Override
    public String toString() {
        return "El dni del lector es " + DNI + " su nombre es " + nombre + " y tiene " + edad;
    }
}