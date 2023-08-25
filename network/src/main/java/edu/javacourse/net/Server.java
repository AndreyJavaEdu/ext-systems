package edu.javacourse.net;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket socket = new ServerSocket(25225); //Серверный сокет и ему передаем порт который он слушает

        System.out.println("Server started");
        while (true) {
            Socket client = socket.accept();//повисаем и ждем изменений
            handleRequest(client);

        }
    }

    private static void handleRequest(Socket client) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
        StringBuilder sb = new StringBuilder("Hello, ");
        String userName = br.readLine();
        System.out.println("Server got string: "+ userName);


        sb.append(userName);
        bw.write(sb.toString());
        bw.newLine();
        bw.flush();

        br.close();
        bw.close();

        client.close(); //поработали, сетевое соединение - прочитали, отправили и закрыли.
    }
}
