package controller;

import Service.PortConnection;
import app.Navigator;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentHashMap;

public class Server implements Initializable {


    @FXML
    private Pane paneServerMessage;


    @FXML
    private int Y=100;

    private static Map<String, String> messageQueue = new ConcurrentHashMap<>();


public static void addMessage(String username, String message) {
    messageQueue.put(username, message);
}



    private void listenForMessages() {
        new Thread(() -> {
            while (true) {
                try {
                    for (Map.Entry<String, String> entry : messageQueue.entrySet()) {
                        String username = entry.getKey();
                        String message = entry.getValue();

                        if (message != null && !message.isEmpty()) {
                            Platform.runLater(() -> {
                                try {
                                    displayMessage(message, username);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            });
                            messageQueue.remove(username);
                        }
                    }
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("4"+ConnectClient.username);
        new Thread(() -> {
            try {
                ClientServer.Server.startServer(PortConnection.getPortNumber());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
        listenForMessages();
    }
        public void displayMessage(String message,String user) throws IOException {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/message.fxml"));
            Pane messagePane = loader.load();
            messagePane.setLayoutY(Y);
            Y += 70;
            Message messageController = loader.getController();
            messageController.SetMessage(message,user);
            paneServerMessage.getChildren().add(messagePane);
            paneServerMessage.setPrefHeight(paneServerMessage.getHeight() + 100);
        }


    public void handleCloseConnection(ActionEvent event) throws IOException {
        Navigator.navigate(event, Navigator.connect);
    }

}
