package com.example.project_3;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class FavoriteMoviesController implements Initializable{

    @FXML
    private TextField movieNameField;

    @FXML
    private ChoiceBox<String> genreBox;

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
    private ListView<String> moviesListView;

    @FXML
    private ListView<String> tempMovieListView;

    @FXML
    private Button saveMoviesListButton;

    @FXML
    private Button loadMoviesListButton;

    @FXML
    private Button deleteMovie;

    private final ArrayList<Movie> movieArrayList = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        genreBox.getItems().addAll("romance", "thriller", "comedy", "action", "fantasy/sci-fi");
    }

    @FXML
    protected void onAddMovieButtonClick() {
        String movieName = movieNameField.getText();
        String movieGenre = genreBox.getSelectionModel().getSelectedItem();
        Movie movie = new Movie(movieName, movieGenre);

        movieArrayList.add(new Movie(movieName, movieGenre));
        tempMovieListView.getItems().add(movie + "\n");

        movieNameField.clear();
    }

    @FXML
    protected void onSaveMoviesListButtonClick(){
        // create file
        File file = new File("movies.txt");

        // write to file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))){
            for (Movie movie : movieArrayList){
                writer.write(movie.toString());
                writer.newLine();
            }
            writer.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        tempMovieListView.getItems().clear();
    }

    @FXML
    protected void onDeleteMovieButtonClick(){
    }

    @FXML
    protected void onLoadMoviesListButtonClick(){

    }

}