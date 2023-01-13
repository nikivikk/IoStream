package by.bsu.decorators;

import by.bsu.IoStream;

public class DataDecorator implements IoStream {
    protected IoStream wrapper;

    protected DataDecorator(IoStream data) {
        wrapper = data;
    }

    @Override
    public String read(String filename) {
        return wrapper.read(filename);
    }

    @Override
    public void write(String filename, String text) {
        wrapper.write(filename, text);
    }
}
