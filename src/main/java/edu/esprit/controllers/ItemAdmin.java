package edu.esprit.controllers;

import edu.esprit.entities.Produit;
import edu.esprit.services.ServiceProduit;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

public class ItemAdmin {
    @FXML
    private Label parascolaireDescription;

    @FXML
    private ImageView parascolaireImage;

    @FXML
    private Label parascolaireNom;

    @FXML
    private Label parascolaireNote;
    ServiceProduit sp = new ServiceProduit();
    private Produit produit;
    private AnchorPane container;
    private int produitId;
    public void setDataAdmin(Produit produit, AnchorPane container) {
        this.produit = produit;
        this.container = container;

        // Assigner l'ID du produit
        this.produitId = produit.getId_produit();

        parascolaireNom.setText(produit.getNom());
        parascolaireDescription.setText(produit.getDescription());
        parascolaireNote.setText(String.valueOf(produit.getNote())); // Supposant que la note est un double
        // Charger l'image depuis le chemin du fichier
        parascolaireImage.setImage(new Image(new File(produit.getImage()).toURI().toString()));
    }
    private void refreshData() {
        if (parentController != null) {
            parentController.refreshData();
        }

    }

    @FXML
    void ModifierAction(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierProduit.fxml"));
        Parent root;
        try {
            root = loader.load();
            ModifierProduit controller = loader.getController();
            controller.initData(produit); // Passer les données du produit sélectionné au contrôleur de modification
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private AfficherProduitAdmin parentController; // Référence vers le contrôleur AfficherProduit

    public void setParentController(AfficherProduitAdmin parentController) {
        this.parentController = parentController;
    }
    @FXML
    void SupprimerAction(ActionEvent event) {
        // Afficher une boîte de dialogue de confirmation pour confirmer la suppression
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Supprimer le produit");
        alert.setContentText("Êtes-vous sûr de vouloir supprimer ce produit ?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // Supprimer le produit de la base de données en utilisant le service approprié
            sp.supprimer(produit.getId_produit());

            // Rafraîchir les données de l'interface principale après suppression
            if (parentController != null) {
                parentController.refreshData();
            }
        }
    }
}
