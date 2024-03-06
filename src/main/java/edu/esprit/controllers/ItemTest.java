package edu.esprit.controllers;

import edu.esprit.entities.Produit;
import edu.esprit.services.ServiceAvis;
import edu.esprit.services.ServiceProduit;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class ItemTest{

    @FXML
    private ImageView parascolaireImage;

    @FXML
    private Label parascolaireMatiere;

    @FXML
    private Label parascolaireNom;

    @FXML
    private Label parascolaireNote;


    private Produit produit;
    private GridPane container;

    public void setData(Produit produit, GridPane container) {
        this.produit = produit;
        this.container = container;

        parascolaireNom.setText(produit.getNom());
        parascolaireMatiere.setText(produit.getMatiere());
        parascolaireImage.setImage(new Image(new File(produit.getImage()).toURI().toString()));


        // Calculer la moyenne des notes pour ce produit
        double moyenneNotes = calculerMoyenneNotes(produit.getId_produit());
        parascolaireNote.setText(String.valueOf(moyenneNotes)); // Mettre à jour la moyenne des notes
    }

    private double calculerMoyenneNotes(int idProduit) {
        // Utilisez votre service ServiceAvis pour récupérer la moyenne des notes pour le produit spécifié
        // Je suppose que vous avez une méthode dans ServiceAvis pour récupérer la moyenne des notes par produit
        ServiceAvis serviceAvis = new ServiceAvis();
        return serviceAvis.getMoyenneNotesParProduit(idProduit);
    }


    @FXML
    private void evaluerAction(ActionEvent event){


            try {
                // Charger le fichier FXML de la fenêtre de modification du produit
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/NoteEtAvis.fxml"));
                Parent root = loader.load();

                // Récupérer le contrôleur associé à la fenêtre de modification
                NoteEtAvis NoteEtAvis = loader.getController();

                // Passer les données du produit à évaluer au contrôleur d'avis'
                NoteEtAvis.initDataPara(produit);

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



    private void navigateTo(String fxmlFilePath) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxmlFilePath));
            container.getChildren().setAll(root);

        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Navigation Error");
            alert.setContentText("An error occurred while navigating to the next page.");
            alert.showAndWait();
        }
    }
}
