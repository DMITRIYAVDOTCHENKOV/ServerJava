package org.example;

import org.example.avdotchenkov.Phone;

import java.io.IOException;
import java.net.ServerSocket;

public class Server {
    public static void main(String[] args) {
//создаем сервев который слушает порт 8000 ( можно по сути использовать любой  кроме 1-1024)
        //блягодоря try с ресурсами, нам не до в ручную закрывать соеденения, закрытие идет автоматом

        try (ServerSocket server = new ServerSocket(8000)) {

            System.out.println("Server started!");

            while (true) {

//                        Socket socket = server.accept();// подлючаемся через сокет
//                        BufferedWriter writer =
//                                new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
//                        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))
                Phone phone = new Phone(server);
                new Thread(() -> {
                    String request = phone.readLine();
                    System.out.println("Request: " + request);
                    String response = (int) (Math.random() * 30 - 10) + "";//сообщение серверу
                    try {
                        Thread.sleep(4000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    phone.writeLine(response);
                    System.out.println("Response: " + response);
                    try {
                        phone.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }).start();
//                    writer.newLine();//переход на новую строку
//                    writer.flush();// надо выполнить команду, так как сообщение может быть даже не отправлено
            }
        } catch (NullPointerException | IOException e) {
            e.printStackTrace();
        }
    }
}

