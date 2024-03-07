package edu.esprit.controllers;

import com.gluonhq.charm.glisten.control.Avatar;
import edu.esprit.entities.AvisProduit;
import edu.esprit.services.ServiceAvis;
import edu.esprit.services.ServiceProduit;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.Rating;

import java.io.File;

public class MesAvisItem extends javafx.scene.control.Control {
    @FXML
    private Label avis;

    @FXML
    private ImageView imgpara;

    @FXML
    private Label nompara;

    @FXML
    private Rating rate;
    private AnchorPane containerAvis;

    @FXML
    void supprimerMonAvis(ActionEvent event) {
        ServiceAvis serviceAvis = new ServiceAvis();
        serviceAvis.supprimer(avisProduit.getId());// Assurez-vous que getId() retourne l'ID de l'avis

        // Mettre à jour l'interface utilisateur pour refléter la suppression
        containerAvis.getChildren().remove(containerAvis); // Supprimer cet élément de l'interface utilisateur
    }
    private AvisProduit avisProduit;

    public void setAvisProduit(AvisProduit avisProduit, AnchorPane containerAvis) {
        this.avisProduit = avisProduit;
        this.containerAvis = containerAvis;

        ServiceProduit serviceProduit = new ServiceProduit();
        // Afficher les données de l'avis de l'utilisateur
        avis.setText(avisProduit.getContenu());
        rate.setRating(avisProduit.getNote());
        nompara.setText(serviceProduit.getNomProduitById(avisProduit.getIdProduit()));

        // Récupérer le chemin de l'image du produit associé à partir de l'ID du produit

        String cheminImageProduit = serviceProduit.getCheminImageProduitById(avisProduit.getIdProduit());

        // Charger l'image du produit dans imgpara
        if (cheminImageProduit != null && !cheminImageProduit.isEmpty()) {
            File imageFile = new File(cheminImageProduit);
            if (imageFile.exists()) {
                Image image = new Image(imageFile.toURI().toString());
                imgpara.setImage(image);
            }
        }
    }
}
