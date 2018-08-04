package com.siyann;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Hello world!
 */
public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Main.fxml"));
        Scene scene = new Scene(root, 1500, 1000);
        scene.getStylesheets().add(getClass().getClassLoader().getResource("main.css").toExternalForm());

        primaryStage.setTitle("Simple JavaFX");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
