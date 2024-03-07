package edu.esprit.controllers;

import edu.esprit.entities.AvisProduit;
import edu.esprit.entities.Produit;
import edu.esprit.services.ServiceAvis;
import edu.esprit.services.ServiceProduit;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AvisAdmin implements Initializable {


        @FXML
        private AnchorPane conatinerAvis;

        @FXML
        private AnchorPane container;

        @FXML
        private Label description;

        @FXML
        private ImageView imgpara;

        @FXML
        private Label nom;

        @FXML
        private VBox vcontainer1;
        private Produit produit;
    private ServiceProduit sp;

    public void initialize(URL location, ResourceBundle resources) {
        sp = new ServiceProduit();

    }

    private List<AvisProduit> recupererReclamationsProduit(int idProduit) {
        ServiceAvis serviceAvis = new ServiceAvis();
        try {
            return serviceAvis.recupererReclamationsParProduit(idProduit);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>(); // En cas d'erreur, retournez une liste vide
        }
    }

    public void initDataProduit(Produit produit) {
        // Affichage des détails du produit
        this.produit = produit;
        if (produit.getImage() != null && !produit.getImage().isEmpty()) {
            File imageFile = new File(produit.getImage());
            if (imageFile.exists()) {
                Image image = new Image(imageFile.toURI().toString());
                imgpara.setImage(image);
            }
        }
        nom.setText(produit.getNom());
        description.setText(produit.getDescription());

        // Récupération et affichage des réclamations du produit
        List<AvisProduit> reclamations = recupererReclamationsProduit(produit.getId_produit());
        for (AvisProduit reclamation : reclamations) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/AvisItemAdmin.fxml"));
            try {
                AnchorPane item = loader.load();
                AvisItemAdmin avisitem = loader.getController();
                avisitem.setAvisAdmin(reclamation, conatinerAvis);
                vcontainer1.getChildren().add(item);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public void refreshData() {
        vcontainer1.getChildren().clear(); // Effacer tous les avis actuellement affichés

        // Récupérer à nouveau les réclamations du produit
        List<AvisProduit> reclamations = recupererReclamationsProduit(produit.getId_produit());

        // Afficher les réclamations mises à jour
        for (AvisProduit reclamation : reclamations) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/AvisItem.fxml"));
            try {
                AnchorPane item = loader.load();
                AvisItem avisItem = loader.getController();
                avisItem.setAvisData(reclamation, conatinerAvis);
                vcontainer1.getChildren().add(item);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
