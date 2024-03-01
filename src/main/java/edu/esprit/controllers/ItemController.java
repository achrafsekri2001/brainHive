package edu.esprit.controllers;

import edu.esprit.entities.AvisProduit;
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

import java.io.File;
import java.io.IOException;

public class ItemController {

    @FXML
    private ImageView parascolaireImage;

    @FXML
    private Label parascolaireNom;

    @FXML
    private Label parascolaireDescription;

    @FXML
    private Label parascolaireNote;

    @FXML
    private Button evaluerButton;
    ServiceProduit sp = new ServiceProduit();
    private Produit produit;
    private AnchorPane container;
    private int produitId;
    public void setData(Produit produit, AnchorPane container) {
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
    private void navigateTo(String fxmlFilePath) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxmlFilePath));
            parascolaireNom.getScene().setRoot(root);

        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Navigation Error");
            alert.setContentText("An error occurred while navigating to the next page.");
            alert.showAndWait();
        }}
    @FXML
    private void evaluerAction(ActionEvent event) {
        navigateTo("/NoteEtAvis.fxml");

       /* try {
            // Charger l'interface NoteEtAvis.fxml avec le contrôleur associé
            FXMLLoader loader = new FXMLLoader(getClass().getResource("NoteEtAvis.fxml"));
            NoteEtAvis controller = new NoteEtAvis(produit.getId_produit());
            loader.setController(controller);
            AnchorPane noteEtAvisPane = loader.load();

            // Afficher l'interface NoteEtAvis dans le conteneur parent
            container.getChildren().setAll(noteEtAvisPane);
        } catch (IOException e) {
            e.printStackTrace();
        }*/

    }




}
