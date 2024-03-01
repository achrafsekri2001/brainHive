package edu.esprit.controllers;

import edu.esprit.entities.Produit;
import edu.esprit.services.ServiceProduit;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

public class AfficherProduit implements Initializable {
    @FXML
    private AnchorPane container;


    @FXML
    private VBox vcontainer;

    private final ServiceProduit sp = new ServiceProduit();


    public void initialize(URL location, ResourceBundle resources) {
        try {
            List<Produit> produits = sp.getAll();

            for (Produit produit : produits) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Item.fxml"));
                try {
                   // AnchorPane produitItem = loader.load();
                    vcontainer.getChildren().add(loader.load());
                    ItemController controller = loader.getController();
                    controller.setData(produit, container); // Passer le conteneur ici
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
