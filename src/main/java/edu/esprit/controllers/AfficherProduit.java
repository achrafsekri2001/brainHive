package edu.esprit.controllers;

import edu.esprit.entities.Produit;
import edu.esprit.services.ServiceProduit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AfficherProduit implements Initializable {
    @FXML
    private GridPane container;
    @FXML
    private ComboBox<String> matiereBox;
    @FXML
    private TextField recherche;
    private final ServiceProduit sp = new ServiceProduit();

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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Item.fxml"));
            try {
                AnchorPane item = loader.load();
                ItemTest controller = loader.getController();
                controller.setData(produit, container);
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
