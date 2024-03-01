package edu.esprit.controllers;

import edu.esprit.entities.Produit;
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
    private Produit produit;
    private File selectedFile;
    private String[] matiere = {"Arabe","Anglais","Allemand","Economie","Espagnole","Français","Gestion","Histoire et géographie","Informatique","Italien","Mathématique","Philosophie","Physique et chimie","Sciences de la vie et de la terre","Technique"};

    public void initialize(URL arg0, ResourceBundle arg1){
        matierepara.getItems().addAll(matiere);
        matierepara.setOnAction(this::getmatiere);
    }

    private void getmatiere(ActionEvent event) {
        String maMatiere = matierepara.getValue();
    }
    @FXML
    private TextField cheminImageTextField;

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

            // Afficher le chemin de l'image dans le champ de texte
            cheminImageTextField.setText(selectedFile.getAbsolutePath());
        }
    }
    public void initData(Produit produit) {
        this.produit = produit;

        // Pré-remplir les champs avec les données du produit à modifier
        titreTF.setText(produit.getNom());
        descriptionTF.setText(produit.getDescription());
        cheminImageTextField.setText(produit.getImage());
    }
    @FXML
    void modifierParascolaireAction(ActionEvent event) {

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
    void navigatetoAffichageAction(ActionEvent event) {
        navigateTo("/AfficherProduitAdmin.fxml");

    }


}
