package edu.esprit.controllers;

import edu.esprit.entities.Produit;
import edu.esprit.services.ServiceProduit;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

public class AfficherProduit implements Initializable {

    @FXML
    private GridPane produitGrid;

    private final ServiceProduit sp = new ServiceProduit();
    ;
   // Set<Produit> equipementSet = sp.getAll();
    List<Produit> produitList = new ArrayList<>();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        afficherProduits();
    }

    private void afficherProduits() {
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < produitList.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/Item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemController itemController = fxmlLoader.getController();
                itemController.setData(produitList.get(i));

                if (column == 1) {
                    column = 0;
                    row++;
                }

                produitGrid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                produitGrid.setMinWidth(Region.USE_COMPUTED_SIZE);
                produitGrid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                produitGrid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                produitGrid.setMinHeight(Region.USE_COMPUTED_SIZE);
                produitGrid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                produitGrid.setMaxHeight(Region.USE_PREF_SIZE);

               // GridPane.setMargin(anchorPane, new Insets());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    /*    List<Produit> produits = serviceProduit.getAll();

        int column = 0;
        int row = 0;

        for (Produit produit : produits) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Item.fxml"));
            try {
                AnchorPane produitItem = (AnchorPane) loader.load();
                ItemController itemController = loader.getController();
                itemController.setData(produit);
                produitGrid.add(produitItem, column, row);

                // Incrémentation de la colonne et passage à la ligne suivante si nécessaire
                column++;
                if (column == 3) {
                    column = 0;
                    row++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/
    }
}
