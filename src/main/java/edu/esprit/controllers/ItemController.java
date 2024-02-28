package edu.esprit.controllers;

import edu.esprit.entities.Produit;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;

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

    private Produit produit;

    public void setData(Produit produit) {
        this.produit = produit;
        parascolaireNom.setText(produit.getNom());
        parascolaireDescription.setText(produit.getDescription());
        parascolaireNote.setText(String.valueOf(produit.getNote())); // Supposant que la note est un double
        // Charger l'image depuis le chemin du fichier
       //parascolaireImage.setImage(new Image(new File(produit.getImage()).toURI().toString()));
    }

    @FXML
    private void evaluerAction() {
        // Action à effectuer lorsqu'on clique sur le bouton Évaluer
        // Par exemple, ouvrir une nouvelle fenêtre pour l'évaluation
    }
}
