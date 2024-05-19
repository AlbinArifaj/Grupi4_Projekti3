package controller;

import ClientServer.AvailablePort;
import Service.PortConnection;
import app.Navigator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class ConnectServer {

    @FXML
    private TextField txtPort;


    public void handleUpdate(ActionEvent event) throws Exception {
        if (AvailablePort.available(Integer.parseInt(txtPort.getText()))) {
            PortConnection.setPortNumber(Integer.parseInt(txtPort.getText()));
            Navigator.navigate(event, Navigator.server);
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Port is not available: Port should be between 1 and 65535 or try another port");
            alert.showAndWait();
        }
    }
}
