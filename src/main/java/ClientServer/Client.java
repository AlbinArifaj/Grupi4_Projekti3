package ClientServer;

import EncryptDecrypt.EncryptDecrypt;
import app.Navigator;
import controller.ConnectClient;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Base64;
import java.util.Scanner;


public class Client extends Application {
    public static Socket socket;
    private static PrintWriter out;


    public static void sendMessage(String message) throws Exception {

        System.out.println("Write a message to Server:");
        String senMsg = message;
        byte[] messageEncrypted = EncryptDecrypt.encrypt(senMsg);
        String encryptedMessageBase64 = Base64.getEncoder().encodeToString(messageEncrypted);
        System.out.println(encryptedMessageBase64);
        out.println(ConnectClient.username);
        out.println(new String(encryptedMessageBase64));

    }

    public  static void connectClient(int connectionPort) throws IOException {
         socket = new Socket("localhost", connectionPort);
         out = new PrintWriter(socket.getOutputStream(), true);
        System.out.println("Client connected");

    }

    public static void closeClient() {
        try {
            System.out.println("Closing Client");
            socket.close();
        }catch (Exception e){

        }
    }



    @Override
    public void start(Stage stage) throws Exception {

        Navigator.navigate(stage,Navigator.connect);
    }

    public static void main(String[] args) {
        launch(args);
    }
}

