package edu.esprit.controllers;

import edu.esprit.entities.Produit;
import edu.esprit.services.ServiceProduit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AfficherProduitAdmin implements Initializable {
    @FXML
    private GridPane container;
    @FXML
    private ComboBox<String> matiereBox;
    @FXML
    private TextField recherche;
    private final ServiceProduit sp = new ServiceProduit();

    List<Produit> produitList = new ArrayList<>();
    public void initialize(URL location, ResourceBundle resources) {
        container.setHgap(10); // Espacement horizontal
        container.setVgap(10); // Espacement vertical

        afficherTousLesProduits();
        // Remplir le ComboBox avec les options de matière
        List<String> matieres = sp.getMatiereList();
        ObservableList<String> matieresObservable = FXCollections.observableArrayList(matieres);
        matiereBox.setItems(matieresObservable);

        // Définir un écouteur pour détecter les changements de sélection dans le ComboBox
        matiereBox.setOnAction(event -> {
            String matiereSelectionnee = matiereBox.getSelectionModel().getSelectedItem();
            if (matiereSelectionnee != null) {
                // Appeler la méthode pour récupérer les produits par matière sélectionnée
                List<Produit> produitsParMatiere = sp.getProduitsByMatiere(matiereSelectionnee);

                // Afficher les produits récupérés
                afficherProduits(produitsParMatiere);
            }
        });
    }
    private void afficherTousLesProduits() {
        List<Produit> tousLesProduits = sp.getAll();
        afficherProduits(tousLesProduits);
    }


    private void afficherProduits(List<Produit> produits) {
        // Effacer les produits précédemment affichés
        container.getChildren().clear();

        // Afficher les nouveaux produits
        int column = 0;
        int row = 0;
        for (Produit produit : produits) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/ItemAdmin.fxml"));
            try {
                AnchorPane item = loader.load();
                ItemAdmin controller = loader.getController();
                controller.setDataAdmin(produit, container);
                container.getChildren().add(item);
                GridPane.setRowIndex(item, row);
                GridPane.setColumnIndex(item, column);
                column++;
                if (column > 2) {
                    column = 0;
                    row++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void refreshData() {
        afficherTousLesProduits(); // Rechargez simplement les produits pour rafraîchir les données
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
        navigateTo("/Fxml/AjouterParascolaire.fxml");

    }
    @FXML
    private void rechercherParNom() {
        String texteRecherche = recherche.getText().trim();
        if (!texteRecherche.isEmpty()) {
            List<Produit> produitsParNom = sp.getProduitsByNom(texteRecherche);
            afficherProduits(produitsParNom);
        } else {
            // Si le champ de recherche est vide, afficher tous les produits
            afficherTousLesProduits();
        }
    }

}
