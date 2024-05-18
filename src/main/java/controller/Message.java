package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.time.LocalTime;


public class Message {

    @FXML
    private Label txtClientMessage;

    @FXML
    private Label txtSetTime;

    @FXML
    private Label txtClientTalking;



    public void SetMessage(String message,String userTalking) {
        txtClientMessage.setText(message);
        txtClientMessage.setWrapText(true);
        LocalTime lt = LocalTime.now();
        txtSetTime.setText(lt.toString());
        txtClientTalking.setText(userTalking);
    }




}
