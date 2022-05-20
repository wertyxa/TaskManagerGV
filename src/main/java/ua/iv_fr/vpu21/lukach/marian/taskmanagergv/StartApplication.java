package ua.iv_fr.vpu21.lukach.marian.taskmanagergv;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class StartApplication extends Application {

    public static final Image icon = new Image(StartApplication.class.getResource("img/task.png").toString());
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StartApplication.class.getResource("layouts/start.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Щоденник справ");
        stage.getIcons().add(icon);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}