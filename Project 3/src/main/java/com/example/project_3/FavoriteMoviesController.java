package com.example.project_3;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class FavoriteMoviesController {
    @FXML
    private Label welcomeText;

    @FXML
    private Label titleText;

    @FXML
    private TextField movieNameField;

    @FXML
    private ChoiceBox genreBox;

    @FXML
    private Button addMovieButton;

    @FXML
    private CheckBox actionCheckbox;

    @FXML
    private CheckBox thrillerCheckbox;

    @FXML
    private CheckBox romanceCheckbox;

    @FXML
    private CheckBox comedyCheckbox;

    @FXML
    private CheckBox fantasyCheckbox;

    @FXML
    private ListView moviesListView;

    @FXML
    private Button saveMoviesListButton;

    @FXML
    private Button loadMoviesListButton;

    @FXML
    protected void onAddMovieButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}