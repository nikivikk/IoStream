package by.bsu;

public interface IoStream {
    String read(String filename);

    void write(String filename, String text);
}
