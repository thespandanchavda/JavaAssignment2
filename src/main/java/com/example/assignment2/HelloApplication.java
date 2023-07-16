package com.example.assignment2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ui.fxml"));
        fxmlLoader.setController(new HelloController(new ScrabbleModel())); // Instantiate HelloController with ScrabbleModel
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Scrabble Points Game");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
