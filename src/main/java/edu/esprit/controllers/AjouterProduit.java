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

public class AjouterProduit implements Initializable {
    @FXML
    private TextField descriptionTF;
    @FXML
    private TextField titreTF;
    @FXML
    private ImageView img;
    private File selectedFile;

    @FXML
    private ChoiceBox<String> matierepara;
    private String[] matiere = {"Arabe","Anglais","Allemand","Economie","Espagnole","Français","Gestion","Histoire et géographie","Informatique","Italien","Mathématique","Philosophie","Physique et chimie","Sciences de la vie et de la terre","Technique"};

    public void initialize(URL arg0, ResourceBundle arg1){
        matierepara.getItems().addAll(matiere);
        matierepara.setOnAction(this::getmatiere);
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
        }
    }

    private final ServiceProduit sp = new ServiceProduit();
    @FXML
    void ajouterParascolaireAction(ActionEvent event) {

            try {
                if (isValidInput()) {
                    Produit produit = new Produit(titreTF.getText(), selectedFile.getAbsolutePath(), descriptionTF.getText(), matierepara.getValue());
                    sp.ajouter(produit);


                    // Récupérer le parascolaire nouvellement ajouté depuis la base de données
                    //Produit nouveauParascolaire = sp.getOneByID(produit.getId_produit()); // Ici, supposez que vous avez une méthode dans votre service produit pour récupérer un parascolaire par son ID

                    // Mettre à jour la liste d'affichage dans la page d'affichage avec le nouveau parascolaire
                   // AfficherProduit.getInstance().ajouterParascolaire(nouveauParascolaire);

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success");
                    alert.setContentText("Produit ajouté avec succès !");
                    alert.show();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erreur");
                    alert.setContentText("Veuillez remplir tous les champs.");
                    alert.showAndWait();
                }
            } catch (SQLException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("SQL Exception");
                alert.setContentText("Erreur lors de l'ajout du produit : " + e.getMessage());
                alert.showAndWait();
            }
    }

    private boolean isValidInput() {
        // Vérifier si le champ titre est vide ou trop long (plus de 50 caractères)
        if (titreTF.getText() == null || titreTF.getText().isEmpty() || titreTF.getText().length() > 30) {
            return false;
        }

        // Vérifier si le champ description est vide
        if (descriptionTF.getText() == null || descriptionTF.getText().isEmpty()) {
            return false;

        }

        // Vérifier si une matière est sélectionnée
        if (matierepara.getValue() == null || matierepara.getValue().isEmpty()) {
            return false;
        }

        // Vérifier si une image est sélectionnée
        if (selectedFile == null) {
            return false;
        }

        return true;
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
        }}
    @FXML
    void navigatetoAcceuilAction(ActionEvent event) {
        navigateTo("/AfficherProduitAdmin.fxml");
    }
}
