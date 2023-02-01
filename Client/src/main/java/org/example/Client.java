package org.example;

import org.example.avdotchenkov.Phone;

import java.io.*;


public class Client {
    public static void main(String[] args) {
        try (Phone phone = new Phone("192.168.1.23",8000)) {
//        Socket socket=new Socket("192.168.1.23",8000) ;
//        BufferedWriter writer =
//                new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
//        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))){
            System.out.println("Connected to server");
            String request = "Moscow";
            System.out.println("Request: " + request);

//            writer.write(request);
//            writer.newLine();
//            writer.flush();
            phone.writeLine(request);
            String response = phone.readLine();
            System.out.println("Response: " + response);


        } catch(IOException e){
            throw new RuntimeException(e);
        }
    }
}
