package edu.esprit.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.util.List;

public class SideBarController {
    @FXML
    private ListView<String> matiereList;

    @FXML
    private Hyperlink navigateAcceuil = new Hyperlink();


    @FXML
    private Hyperlink navigatePopulaire = new Hyperlink();

    @FXML
    private Hyperlink navigateSauveguardee = new Hyperlink();

    @FXML
    private Hyperlink navigateMessagerie = new Hyperlink();

    @FXML
    private Hyperlink navigateParascolaire = new Hyperlink();

    @FXML
    private Hyperlink navigateReclamation = new Hyperlink();


    @FXML
    public void initialize() {

        List<String> listeMatiere = List.of("Mathématiques", "Physique", "Informatique", "Anglais", "Français", "Histoire", "Géographie", "Philosophie", "SVT", "EPS", "Arts plastiques", "Musique", "Technologie", "Sciences de l'ingénieur", "Langues vivantes", "Latin", "Arabe");
        matiereList.getItems().addAll(listeMatiere);

        // when a subject is selected, navigate to a page called "subjects"
        matiereList.setOnMouseClicked(event -> {
            navigateWithMatiere("/Fxml/postMatiere.fxml", matiereList.getSelectionModel().getSelectedItem());
        });
        // navigation
        navigateAcceuil.setOnAction(this::Navigate);
        navigatePopulaire.setOnAction(this::Navigate);
        navigateSauveguardee.setOnAction(this::Navigate);
        navigateMessagerie.setOnAction(this::Navigate);
        navigateParascolaire.setOnAction(this::Navigate);
        navigateReclamation.setOnAction(this::Navigate);


    }


    @FXML
    void Navigate(ActionEvent event) {
        if (event.getSource() == navigateAcceuil) {
            navigateTo("/Fxml/pageAcceuil.fxml");
        }
        if (event.getSource() == navigatePopulaire) {
            navigateTo("/Fxml/poppulaire.fxml");
        }
        if (event.getSource() == navigateSauveguardee) {
            navigateTo("/Fxml/pageSauveguardee.fxml");
        }
        if (event.getSource() == navigateMessagerie) {
            navigateTo("/Fxml/pageMessagerie.fxml");
        }
        if (event.getSource() == navigateParascolaire) {
            navigateTo("/Fxml/pageParascolaire.fxml");
        }
        if (event.getSource() == navigateReclamation) {
            navigateTo("/Fxml/pageReclamation.fxml");
        }


    }


    private void navigateTo(String fxmlFilePath) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxmlFilePath));
            navigateAcceuil.getScene().setRoot(root);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Navigation Error");
            alert.setContentText("An error occurred while navigating to the next page.");
            alert.showAndWait();
        }
    }

    private void navigateWithMatiere(String fxmlFilePath, String matiere) {
        try {
            // load global holder
            GlobalHolder globalHolder = (GlobalHolder) matiereList.getScene().getWindow().getUserData();
            globalHolder.setCurrentMatiere(matiere);
            Parent root = FXMLLoader.load(getClass().getResource(fxmlFilePath));
            // get the stage
            matiereList.getScene().setRoot(root);

        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Navigation Error");
            alert.setContentText("An error occurred while navigating to the next page.");
            alert.showAndWait();
        }
    }
}
