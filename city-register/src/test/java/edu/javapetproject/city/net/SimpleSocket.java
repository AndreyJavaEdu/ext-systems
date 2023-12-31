package edu.javapetproject.city.net;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class SimpleSocket {
@Test
    public void simpleSocket() throws IOException {
    Socket socket = new Socket("localhost", 8080);
    InputStream is = socket.getInputStream(); //входной поток Сокета, по которому мы можем читать байты
    OutputStream os = socket.getOutputStream(); //выходной поток Сокета
    String command = "GET / HTTP/1.1\r\nHost:java-course\r\n\r\n"; // команда для получения данных из файла sitemap
    os.write(command.getBytes()); //Запись байтов
    os.flush();

    int c = 0;
    while ((c = is.read()) != -1) {
        System.out.print((char)c);
    }
    socket.close();
}
}
