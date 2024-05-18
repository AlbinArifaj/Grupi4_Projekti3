package controller;

import ClientServer.AvailablePort;
import ClientServer.Client;
import Service.PortConnection;
import app.Navigator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.security.Provider;

public class ConnectClient {

    @FXML
    private TextField txtPort;
    @FXML
    private TextField txtName;
    @FXML
    public static String username;

    public void handleUpdate(ActionEvent event) throws Exception {
        username =txtName.getText();
        System.out.println("1" + username);
        PortConnection.setPortNumber(Integer.parseInt(txtPort.getText()));
        ClientServer.Client.connectClient(Integer.parseInt(txtPort.getText()));
        Navigator.navigate(event, Navigator.client);
    }



}
