import java.io.IOException;

public class HandlerArchivos {
    public HandlerArchivos() {
    }

    public void escribirArchivo(String textoCsv, String path) throws IOException {
        try (FileWriter fw = new FileWriter(path);
        BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(texto);
        }
    }
}