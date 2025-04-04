package com.example.project_3;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class FavoriteMoviesController implements Initializable{

    @FXML
    public Button addMovieButton;
    public Label titleText;

    @FXML
    private TextField movieNameField;

    @FXML
    private ChoiceBox<String> genreBox;

    @FXML
    private CheckBox action;

    @FXML
    private CheckBox thriller;

    @FXML
    private CheckBox romance;

    @FXML
    private CheckBox comedy;

    @FXML
    private CheckBox fantasy;

    @FXML
    private ListView<String> permMoviesListView;

    @FXML
    private ListView<String> tempMovieListView;

    @FXML
    private Button saveMoviesListButton;

    @FXML
    private Button loadMoviesListButton;

    @FXML
    private Button deleteMovie;

    private final ArrayList<Movie> tempMovieArrayList = new ArrayList<>();
    private final ArrayList<Movie> permMovieArrayList = new ArrayList<>();
    private final ArrayList<CheckBox> checkBoxes = new ArrayList<>();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        genreBox.getItems().addAll("romance", "thriller", "comedy", "action", "fantasy");
        tempMovieListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        checkBoxes.add(action);
        checkBoxes.add(thriller);
        checkBoxes.add(romance);
        checkBoxes.add(comedy);
        checkBoxes.add(fantasy);
    }

    @FXML
    protected void onAddMovieButtonClick() {
        String movieName = movieNameField.getText().toLowerCase().trim();
        String movieGenre = genreBox.getSelectionModel().getSelectedItem();
        Movie movie = new Movie(movieName, movieGenre);

        tempMovieArrayList.add(movie);
        updateMovieViewList(tempMovieListView, tempMovieArrayList);

        movieNameField.clear();
    }

    @FXML
    protected void onSaveMoviesListButtonClick(){
        // create file
        File file = new File("movies.txt");

        // write to file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))){
            for (Movie movie : tempMovieArrayList){
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
    protected void onDeleteMovieButtonClick() {
        ObservableList<String> items = tempMovieListView.getSelectionModel().getSelectedItems();
        for (String item : items) {
            String[] parts = item.split(",");
            String name = parts[0];

            // syntax recommended by the ide
            // remove movie from list if the names match
            tempMovieArrayList.removeIf(movie -> Objects.equals(name, movie.getName()));
        }
        updateMovieViewList(tempMovieListView, tempMovieArrayList);
    }

    @FXML
    protected void onLoadMoviesListButtonClick(){
        permMoviesListView.getItems().clear();
        permMovieArrayList.clear();
        String filePath = "movies.txt";
        String boxGenre;
        if (isFileEmpty(filePath)){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("The file is empty, please add movies before loading!");
            alert.showAndWait();
            return;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String name = parts[0];
                String genre = parts[1];
                Movie movie = new Movie(name, genre);
                permMovieArrayList.add(movie);
                for (CheckBox box : checkBoxes){
                    if (isChecked(box)){
                        boxGenre = box.getText();
                        System.out.println(boxGenre);
                        if (genre.equalsIgnoreCase(boxGenre)){
                            System.out.println(genre);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        updateMovieViewList(permMoviesListView, permMovieArrayList);
    }

    protected void updateMovieViewList(ListView<String> listView, ArrayList<Movie> arrayList){
        listView.getItems().clear();
        for (Movie movie : arrayList){
            if (!listView.getItems().contains(movie + "\n")){
                listView.getItems().add(movie.toString());
            }
        }
    }

    // generated by google search
    protected boolean isFileEmpty(String filepath){
        File file = new File(filepath);
        return file.length() == 0;
    }

    protected boolean isChecked(CheckBox checkBox) {
        return checkBox.isSelected();
    }
}