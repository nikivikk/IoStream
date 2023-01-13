package com.client;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Client {
    private InputStream inputStream = null;
    private OutputStream outputStream = null;
    private Socket client;

    public void connectSocketServer(int port) {
        try {
            ServerSocket server = new ServerSocket(port);
            client = server.accept();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            inputStream = client.getInputStream();
            outputStream = client.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clientStop() {
        try {
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Отправить файл
    public void sendFile(File f, String flag) {
        try {
            PrintWriter printWriter = new PrintWriter(outputStream, true);
            printWriter.println(f.getName());
            printWriter.println(flag);
        } catch (Exception e) {
            e.printStackTrace();
        }

        byte[] b = new byte[1];
        //File f = new File(filename);
        try {// Поток вывода данных
            OutputStream dataOutputStream = new DataOutputStream(new BufferedOutputStream(outputStream)); // Поток чтения файла
            InputStream ins = new FileInputStream(f);
            int n = ins.read(b);
            while (n != -1) {// Запись данных в сеть
                dataOutputStream.write(b); // Отправить содержимое файла
                dataOutputStream.flush(); // снова прочитать n байтов
                n = ins.read(b);
            } // Закрыть поток
            ins.close();
            dataOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getFile() {
        byte[] b = new byte[1024];
        try {// Определим входной поток,
            InputStream in = inputStream;
            DataInputStream din = new DataInputStream(new BufferedInputStream(in)); // Создать файл для сохранения
            String filename = din.readLine();
            File f = new File(filename);
            RandomAccessFile fw = new RandomAccessFile(f, "rw");

            int num = din.read(b);
            while (num != -1) {// Записать 0 ~ num байтов в файл
                fw.write(b, 0, num); // Пропустить num байтов и снова записать в файл
                fw.skipBytes(num); // Чтение num байтов
                num = din.read(b);
            } // Закрыть входной и выходной потоки
            din.close();
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
