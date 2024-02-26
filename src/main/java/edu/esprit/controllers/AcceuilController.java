package edu.esprit.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.util.List;

public class AcceuilController {


    @FXML
    private ListView<String> subjectsList;

    @FXML
    public void initialize() {

        // populate a FXML listview with the id "subjects" with a list of subjects in french
        List<String> subjects = List.of("Mathématiques", "Physique", "Informatique", "Anglais", "Français", "Histoire", "Géographie", "Philosophie", "SVT", "EPS", "Arts plastiques", "Musique", "Technologie", "Sciences de l'ingénieur", "Langues vivantes", "Latin", "Arabe");
        subjectsList.getItems().addAll(subjects);


    }
}
