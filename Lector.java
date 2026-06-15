/**
 * Clase Lector.
 * Representa un lector que puede comentar artículos en el sistema de noticias.
 * Extiende Usuario.
 * 
 * @author Jano Chiambretto y Manuel Sanchez Fossa
 * @version 1.0.0
 */
public class Lector extends Usuario {
    /**
     * Constructor de Lector.
     * 
     * @param DNI el DNI del lector
     * @param nombre el nombre del lector
     * @param edad la edad del lector
     */
    public Lector(Integer DNI, String nombre, Integer edad) {
        super(DNI, nombre, edad);
    }    

    /**
     * Convierte los datos del lector a formato CSV.
     * El último valor (1) indica que es un lector.
     * 
     * @return la representación CSV del lector
     */
    @Override
    public String toCsv() {
        return this.DNI + "," + nombre + "," + edad + ",1";
    }

    /**
     * Retorna una representación textual del lector.
     * 
     * @return cadena con los datos del lector
     */
    @Override
    public String toString() {
        return "El dni del lector es " + DNI + " su nombre es " + nombre + " y tiene " + edad;
    }
}