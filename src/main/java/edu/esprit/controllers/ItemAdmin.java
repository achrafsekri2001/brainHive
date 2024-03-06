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
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

public class ItemAdmin {
    @FXML
    private Label parascolaireMatiere;

    @FXML
    private ImageView parascolaireImage;

    @FXML
    private Label parascolaireNom;

    @FXML
    private Label parascolaireNote;
    ServiceProduit sp = new ServiceProduit();
    private Produit produit;
    private GridPane container;
    private int produitId;
    public void setDataAdmin(Produit produit, GridPane container) {
        this.produit = produit;
        this.container = container;

        // Assigner l'ID du produit
        this.produitId = produit.getId_produit();

        parascolaireNom.setText(produit.getNom());
        parascolaireMatiere.setText(produit.getMatiere());
        // Charger l'image depuis le chemin du fichier
        parascolaireImage.setImage(new Image(new File(produit.getImage()).toURI().toString()));

    }

    @FXML
    void modifierAction(ActionEvent event) {

            try {
                // Charger le fichier FXML de la fenêtre de modification du produit
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/ModifierProduit.fxml"));
                Parent root = loader.load();

                // Récupérer le contrôleur associé à la fenêtre de modification
                ModifierProduit modifierProduitController = loader.getController();

                // Passer les données du produit à modifier au contrôleur de modification
                modifierProduitController.initData(produit);

                // Créer une nouvelle scène avec la racine chargée depuis le fichier FXML
                Scene scene = new Scene(root);

                // Créer une nouvelle fenêtre (stage) pour la scène
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.initModality(Modality.APPLICATION_MODAL); // Définir la modalité de la fenêtre
                stage.showAndWait(); // Afficher la fenêtre et attendre qu'elle soit fermée
            } catch (IOException e) {
                e.printStackTrace();
            }

    }
    private AfficherProduitAdmin parentController; // Référence vers le contrôleur AfficherProduit

    public void setParentController(AfficherProduitAdmin parentController) {
        this.parentController = parentController;
    }
    @FXML
    void supprimerAction(ActionEvent event) {
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
    @FXML
    void avisAction(ActionEvent event) {

    }


}
