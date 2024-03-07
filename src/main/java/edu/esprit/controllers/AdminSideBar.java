package edu.esprit.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Hyperlink;

import java.io.IOException;

public class AdminSideBar {

    @FXML
    private Hyperlink gestionPost = new Hyperlink();


    @FXML
    private Hyperlink gestionUtilisateur = new Hyperlink();


    @FXML
    public void initialize() {
        gestionPost.setOnAction(this::Navigate);
        gestionUtilisateur.setOnAction(this::Navigate);
    }


    @FXML
    void Navigate(ActionEvent event) {

        if (event.getSource() == gestionPost) {
            navigateTo("/Fxml/AdminAccueil.fxml");
        }
        if (event.getSource() == gestionUtilisateur) {
            navigateTo("/Fxml/AdminUtilisateur.fxml");
        }


    }


    private void navigateTo(String fxmlFilePath) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxmlFilePath));
            gestionPost.getScene().setRoot(root);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Navigation Error");
            alert.setContentText("An error occurred while navigating to the next page.");
            alert.showAndWait();
        }
    }


}
