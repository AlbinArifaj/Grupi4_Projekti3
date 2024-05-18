package controller;

import ClientServer.ClientHandler;
import Service.PortConnection;
import app.Navigator;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Client implements Initializable {

    @FXML
    private TextField txtInputText;

    @FXML
    private Label txtPort;

    @FXML
    private Button btnCloseConnection;

    public void sendMessage() throws Exception {
        String messageToSend = txtInputText.getText();
        ClientServer.Client.sendMessage(messageToSend);
        txtInputText.clear();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("3" + ConnectClient.username);
        txtPort.setText(String.valueOf(PortConnection.getPortNumber()));
    }

    public void handleInputEnter(KeyEvent keyEvent) throws Exception {
        if(keyEvent.getCode() == KeyCode.ENTER) {
            sendMessage();
        }
    }

    public void handleInputText(ActionEvent event) throws Exception {
        sendMessage();
    }


    public void handleCloseConnection(ActionEvent event) {
        ClientServer.Client.closeClient();
        Navigator.navigate(event,Navigator.connect);
    }
}
