package edu.esprit.controllers;

import edu.esprit.entities.Produit;
import edu.esprit.services.ServiceProduit;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

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
        parascolaireNote.setText(String.valueOf(produit.getNote()));
        parascolaireImage.setImage(new Image(new File(produit.getImage()).toURI().toString()));
    }

    @FXML
    private void evaluerAction(ActionEvent event) {
        navigateTo("/NoteEtAvis.fxml");
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
