import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

interface Guardable {
    public void guardarEnArchivo(String texto, String path);
}