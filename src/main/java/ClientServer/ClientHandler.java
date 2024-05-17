package ClientServer;


import EncryptDecrypt.EncryptDecrypt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private String username;
    private String message;
    private static Socket clientSocket;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    @Override
    public void run() {
        try {
            readMessage();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public  void readMessage()  {
        try {

        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        username = in.readLine();
        String encryptedMessageBase64;
    while ((encryptedMessageBase64 = in.readLine()) != null) {

        message = encryptedMessageBase64;
        System.out.println("Received message from " + username + ": " + message);

        controller.Server.addMessage(username,message);
    }
    }catch (Exception e){

}
    }
}
