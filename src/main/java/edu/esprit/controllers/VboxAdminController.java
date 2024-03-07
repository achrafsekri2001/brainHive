package edu.esprit.controllers;

import com.gluonhq.charm.glisten.control.Avatar;
import edu.esprit.entities.Reclamation;
import edu.esprit.services.ReclamationService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class VboxAdminController {
    @FXML
    private    VBox boxeReclamation1;

    @FXML
    private Label dateRec;

    @FXML
    private Avatar imageuser1;

    @FXML
    private Label objet1;

    @FXML
    private AnchorPane pane;

    @FXML
    private Label probleme1;

    @FXML
    private Label username;
    ReclamationService serviceReclamation = new ReclamationService();

    // Reference to the Reclamation object
    private Reclamation reclamation;

    // Reference to the container (parent) HBox
    private AnchorPane container;  private void navigateTo(String fxmlFilePath) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxmlFilePath));
            username.getScene().setRoot(root);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Navigation Error");
            alert.setContentText("An error occurred while navigating to the next page.");
            alert.showAndWait();
        }
    }


    @FXML
    void supprimerAction(ActionEvent event) {
        if (reclamation != null) {
            // Supprimer le message de la base de données
            serviceReclamation.supprimer(reclamation.getIdReclamation());
            // Supprimer également l'affichage du message de l'interface utilisateur
            container.getChildren().remove(boxeReclamation1);
            navigateTo("/Fxml/AdminRec.fxml");
        }

    }
    public void setReclamationData(Reclamation reclamation, AnchorPane container) {
        this.reclamation = reclamation;
        this.container = container;

        // Set data to UI elements
        username.setText(reclamation.getUtilisateur().getNom());

        probleme1.setText(reclamation.getContenu());
        objet1.setText(reclamation.getObjet());
        dateRec.setText(String.valueOf(reclamation.getDate()));
    }


}
