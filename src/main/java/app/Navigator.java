package app;

import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class Navigator {
    public final static String communciaton = "clientServerCommunication.fxml";
    public final static String connect = "connectPage.fxml";
    public final static String client = "client.fxml";
    public final static String server = "server.fxml";
    public final static String connectServer = "connectServer.fxml";


    public static void navigate(Event event, String form){
        Node eventNode = (Node) event.getSource();
        Stage stage = (Stage) eventNode.getScene().getWindow();
        stage.setResizable(false);
        navigate(stage, form);
    }

    public static void navigate(Stage stage, String form){
        Pane formPane = loadPane(form);
        Scene newScene = new Scene(formPane);
        stage.setScene(newScene);
        stage.show();
    }

    public static void navigate(Pane pane, String form){
        Pane formPane = loadPane(form);
        pane.getChildren().clear();
        pane.getChildren().add(formPane);
    }

    private static Pane loadPane(String form){
        FXMLLoader loader = new FXMLLoader(
                Navigator.class.getResource(form)
        );
        try {
            return loader.load();
        }catch (IOException ioe){
            return null;
        }
    }
}