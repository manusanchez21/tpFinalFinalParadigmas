/**
 * Clase Autor.
 * Representa un autor que puede publicar artículos en el sistema de noticias.
 * Extiende Usuario.
 * 
 * @author Jano Chiambretto y Manuel Sanchez Fossa
 * @version 1.0.0
 */
public class Autor extends Usuario {
    /**
     * Constructor de Autor.
     * 
     * @param DNI el DNI del autor
     * @param nombre el nombre del autor
     * @param edad la edad del autor
     */
    public Autor(Integer DNI, String nombre, Integer edad) {
        super(DNI, nombre, edad);
    } 

    /**
     * Convierte los datos del autor a formato CSV.
     * El último valor (0) indica que es un autor.
     * 
     * @return la representación CSV del autor
     */
    @Override
    public String toCsv() {
        return DNI + "," + nombre + "," + edad + ",0";
    }

    /**
     * Retorna una representación textual del autor.
     * 
     * @return cadena con los datos del autor
     */
    @Override
    public String toString() {
        return "El dni del autor es " + DNI + " su nombre es " + nombre + " y tiene " + edad;
    }
}