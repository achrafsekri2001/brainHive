package edu.esprit.controllers;

import edu.esprit.entities.Produit;
import edu.esprit.services.ServiceProduit;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AfficherProduitAdmin implements Initializable {
    @FXML
    private AnchorPane container;

    @FXML
    private VBox vcontainer;
    private final ServiceProduit sp = new ServiceProduit();

    List<Produit> produitList = new ArrayList<>();
    public void initialize(URL location, ResourceBundle resources) {
        loadProducts();
    }

    private void loadProducts() {
        try {
            vcontainer.getChildren().clear();
            List<Produit> produits = sp.getAll();

            for (Produit produit : produits) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/ItemAdmin.fxml"));
                try {
                    // AnchorPane produitItem = loader.load();
                    vcontainer.getChildren().add(loader.load());
                    ItemAdmin controller = loader.getController();
                    controller.setDataAdmin(produit, container); // Passer le conteneur ici
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void refreshData() {
        loadProducts(); // Rechargez simplement les produits pour rafraîchir les données
    }
    private void navigateTo(String fxmlFilePath) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxmlFilePath));
            container.getScene().setRoot(root);

        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Navigation Error");
            alert.setContentText("An error occurred while navigating to the next page.");
            alert.showAndWait();
        }}
    @FXML
    void navigatetoAjoutAction(ActionEvent event) {
        navigateTo("/AjouterParascolaire.fxml");

    }

}
