package edu.esprit.controllers;

import com.gluonhq.charm.glisten.control.Avatar;
import edu.esprit.entities.AvisProduit;
import edu.esprit.services.ServiceAvis;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.Rating;

import java.util.Optional;

public class AvisItemAdmin {
    @FXML
    private Label contenuavis;

    @FXML
    private Rating rate;

    @FXML
    private Avatar userimg;

    @FXML
    private Label username;
    private AvisProduit avisProduit;
    private AnchorPane containerAvis;
    ServiceAvis sa = new ServiceAvis();


    private AvisAdmin parentController;
    @FXML
    void supprimerAvisAction(ActionEvent event) {
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Confirmation de suppression");
        confirmationAlert.setHeaderText("Voulez-vous vraiment supprimer cet avis ?");
        confirmationAlert.setContentText("Cette action est irréversible.");

        Optional<ButtonType> result = confirmationAlert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // Supprimer le produit de la base de données en utilisant le service approprié
            sa.supprimer(avisProduit.getId());

            // Rafraîchir les données de l'interface principale après suppression
            if (parentController != null) {
                parentController.refreshData();
            }
        }
    }
    public void setAvisAdmin(AvisProduit avisProduit, AnchorPane containerAvis) {
        this.avisProduit = avisProduit;
        this.containerAvis = containerAvis;

        // Récupérer les données de l'avisProduit
        contenuavis.setText(avisProduit.getContenu());
        rate.setRating(avisProduit.getNote());


        userimg.setImage(null); // Effacer l'image
        username.setText(avisProduit.getUser().getNom());

    }

    public void setParentController(AvisAdmin parentController) {
        this.parentController = parentController;
    }

}


