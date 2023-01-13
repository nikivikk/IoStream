package by.bsu;

import java.io.File;

class Main {
    public static void main(String[] args) {
        while (true) {
            Server serverGet = new Server();
            serverGet.initServer(9527);
            System.out.println("Please wait");
            String file = serverGet.getFile();
            File f1 = new File(file);
            f1.deleteOnExit();
            serverGet.RPN();

            String trFile = serverGet.transformFile();
            System.out.println("Done!");
            serverGet.serverStop();
            Server serverSend = new Server();
            serverSend.initServer(9528);
            serverSend.sendFile(trFile);
            File f2 = new File(trFile);
            f2.deleteOnExit();
            serverSend.serverStop();
        }
    }
}