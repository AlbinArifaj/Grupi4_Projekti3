package app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Navigator.navigate(stage,Navigator.connectServer);
    }
}
