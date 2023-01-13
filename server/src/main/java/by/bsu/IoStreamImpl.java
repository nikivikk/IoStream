package by.bsu;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class IoStreamImpl implements IoStream {

    @Override
    public String read(String filename) {
        StringBuilder text = new StringBuilder();
        try (FileReader reader = new FileReader(filename)) {
            int c;
            while ((c = reader.read()) != -1) {
                text.append((char) c);
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return text.toString();
    }

    @Override
    public void write(String filename, String text) {
        try (FileWriter writer = new FileWriter(filename, false)) {
            // запись всей строки
            writer.write(text);
            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}


