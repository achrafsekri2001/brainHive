package edu.esprit.controllers;

import com.gluonhq.charm.glisten.control.Avatar;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MessagerieController  implements Initializable {


    @FXML
    private Label contenuMsg;

    @FXML
    private Label date;

    @FXML
    private VBox msgBox;

    @FXML
    private Avatar userImage;

    @FXML
    private Label userName;

    private void navigateTo(String fxmlFilePath) {

        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxmlFilePath));

            userImage.getScene().setRoot(root);


        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Navigation Error");
            alert.setContentText("An error occurred while navigating to the next page.");
            alert.showAndWait();
        }}
    @FXML
    void NavigateToAcceuil(ActionEvent event) {
        navigateTo("/Acceuil.fxml");

    }

    @FXML
    void NavigateToFavoris(ActionEvent event) {
        navigateTo("/Favoris.fxml");

    }

    @FXML
    void NavigateToMessages(ActionEvent event) {
        navigateTo("/Messagerie.fxml");

    }

    @FXML
    void NavigateToProduit(ActionEvent event) {
        navigateTo("/Produit.fxml");

    }

    @FXML
    void NavigateToReclamation(ActionEvent event) {
        navigateTo("/Reclamation.fxml");

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
