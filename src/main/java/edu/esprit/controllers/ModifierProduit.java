package edu.esprit.controllers;

import edu.esprit.entities.Produit;
import edu.esprit.services.ServiceProduit;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ModifierProduit implements Initializable {

    @FXML
    private TextField descriptionTF;

    @FXML
    private ImageView img;

    @FXML
    private ChoiceBox<String> matierepara;

    @FXML
    private TextField titreTF;

    private ServiceProduit sp;

    private Produit produit;
    private File selectedFile;
    private String[] matiere = {"Arabe","Anglais","Allemand","Economie","Espagnole","Français","Gestion","Histoire et géographie","Informatique","Italien","Mathématique","Philosophie","Physique et chimie","Sciences de la vie et de la terre","Technique"};

    public void initialize(URL arg0, ResourceBundle arg1){
        matierepara.getItems().addAll(matiere);
        matierepara.setOnAction(this::getmatiere);

        sp = new ServiceProduit();
    }

    private void getmatiere(ActionEvent event) {
        String maMatiere = matierepara.getValue();
    }



    @FXML
    void uploadimgAction(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select an Image");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("PNG Files", "*.png"),
                new FileChooser.ExtensionFilter("JPEG Files", "*.jpg", "*.jpeg"),
                new FileChooser.ExtensionFilter("GIF Files", "*.gif")
        );

        selectedFile = fileChooser.showOpenDialog(new Stage());
        if (selectedFile != null) {
            System.out.println("Selected image: " + selectedFile.getAbsolutePath());
            Image image = new Image(selectedFile.toURI().toString());
            img.setImage(image);

            // Assurez-vous que l'objet produit n'est pas nul avant d'essayer de le mettre à jour
            if (produit != null) {
                // Mettre à jour le chemin de l'image dans l'objet produit
                produit.setImage(selectedFile.getAbsolutePath());
            }
        }
    }
    public void initData(Produit produit) {

        this.produit = produit;

        // Pré-remplir les champs avec les données du produit à modifier
        titreTF.setText(produit.getNom());
        descriptionTF.setText(produit.getDescription());

        // Sélectionner la matière dans le choix de la matière
        matierepara.getSelectionModel().select(produit.getMatiere());

        // Afficher l'image du produit
        if (produit.getImage() != null && !produit.getImage().isEmpty()) {
            File imageFile = new File(produit.getImage());
            if (imageFile.exists()) {
                Image image = new Image(imageFile.toURI().toString());
                img.setImage(image);
            }
        }
    }
    @FXML
    void modifierParascolaireAction(ActionEvent event) {
        // Récupérer les données saisies par l'utilisateur
        String titre = titreTF.getText().trim();
        String description = descriptionTF.getText().trim();
        String matiere = matierepara.getValue();

        // Vérifier si les champs obligatoires sont renseignés
        if (titre.isEmpty() || description.isEmpty() || matiere == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Champs requis non renseignés");
            alert.setContentText("Veuillez remplir tous les champs obligatoires.");
            alert.showAndWait();
            return;
        }

        // Créer un objet Produit avec les nouvelles données
        Produit nouveauProduit = new Produit(produit.getId_produit(), produit.getImage(), titre, description, matiere);

        // Appeler la méthode de service pour modifier le produit
        try {
            sp.modifier(nouveauProduit);
            // Afficher un message de succès
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succès");
            alert.setHeaderText(null);
            alert.setContentText("Le parascolaire a été modifié avec succès.");
            alert.showAndWait();

            // Naviguer vers la page d'affichage des produits administrateur après la modification
            navigatetoAffichageAction(event);
        } catch (SQLException ex) {
            // En cas d'erreur lors de la modification, afficher un message d'erreur
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur lors de la modification");
            alert.setContentText("Une erreur est survenue lors de la modification du parascolaire. Veuillez réessayer.");
            alert.showAndWait();
        }
    }

    private void navigateTo(String fxmlFilePath) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxmlFilePath));
            titreTF.getScene().setRoot(root);

        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Navigation Error");
            alert.setContentText("An error occurred while navigating to the next page.");
            alert.showAndWait();
        }
    }

    @FXML
    void navigatetoAffichageAction(ActionEvent event) {
        navigateTo("/AfficherProduitAdmin.fxml");

    }
}
