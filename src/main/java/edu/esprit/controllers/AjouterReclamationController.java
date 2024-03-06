package edu.esprit.controllers;


import com.gluonhq.charm.glisten.control.Avatar;
import edu.esprit.entities.Reclamation;
import edu.esprit.entities.Utilisateur;
import edu.esprit.services.ReclamationService;
import edu.esprit.services.UserCRUD;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class AjouterReclamationController  implements Initializable {


    @FXML
    private Avatar imageuser;

    @FXML
    private TextField TF;

    @FXML
    private TextField TFp;
    Utilisateur user= new Utilisateur(5,"feriel","assets/417957442_1478499912738648_2882908279475441643_n.jpg");
    private final ReclamationService rec=new ReclamationService();
    private final UserCRUD su=new UserCRUD();


    @FXML
    void NavigateToAcceuil(ActionEvent event) {
        navigateTo("/Acceuil.fxml");
    }

    @FXML
    void NavigateToBackAction(ActionEvent event) {
        navigateTo("/Reclamation.fxml");
    }

    @FXML
    void NavigateToFavoris(ActionEvent event) {
        navigateTo("/Favoris.fxml");
    }

    @FXML
    void NavigateToMessages(ActionEvent event) {
        navigateTo("/Messagerie.fxml");
    }

    @FXML
    void NavigateToProduit(ActionEvent event) {
        navigateTo("/Produit.fxml");
    }


    @FXML
    void NavigateToReclamation(ActionEvent event) {
        navigateTo("/Reclamation.fxml");
    }

    private void navigateTo(String fxmlFilePath) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxmlFilePath));
            TF.getScene().setRoot(root);

        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Navigation Error");
            alert.setContentText("An error occurred while navigating to the next page.");
            alert.showAndWait();
        }}

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Initialiser les champs objettf et problemetf si nécessaire



    }
    @FXML
    public void AjouterReclamationAction(ActionEvent actionEvent) throws SQLException {

        if (isValidInput()) {
            // Utilisateur user = Utilisateur.getCurrent_User();

            Reclamation reclamation = new Reclamation();
            reclamation.setObjet(TF.getText());
            reclamation.setContenu(TFp.getText());
           reclamation.setUser(user.getCurrent_User().getUserName()); // Définissez l'utilisateur actuel comme le "user" de la réclamation
            reclamation.setIdUser(user.getId()); // Définissez l'ID de l'utilisateur actuel comme l'ID de l'utilisateur de la réclamation
            //reclamation.setImgUser(user.getImgUser());
            rec.ajouterReclamation(reclamation);


            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Reclamation ajouté avec succès !");
            alert.show();


        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText("Veuillez remplir tous les champs.");
            alert.showAndWait();
        }


    }
    private boolean isValidInput() {
        return (TF.getText() != null && !TF.getText().isEmpty()&& countWords(TF.getText()) <= 10) && (TFp.getText() != null && !TFp.getText().isEmpty()& countLines(TFp.getText()) <= 10);


    }
    private int countWords(String text) {
        String[] words = text.split("\\s+");
        return words.length;
    }

    // Méthode pour compter le nombre de lignes dans une chaîne de caractères
    private int countLines(String text) {
        String[] lines = text.split("\\r?\\n");
        return lines.length;
    }
}