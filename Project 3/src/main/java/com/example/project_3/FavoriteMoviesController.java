package com.example.project_3;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class FavoriteMoviesController {
    @FXML
    private Label welcomeText;

    @FXML
    private Label titleText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}