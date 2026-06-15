import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

interface Guardable {
    public void guardarEnArchivo(String texto, String path);
    // public void guardarEnArchivo(String texto, String path) {
    //     try (FileWriter fw = new FileWriter(path);
    //     BufferedWriter bw = new BufferedWriter(fw)) {
    //         bw.write(texto);
    //     }
    // };
}