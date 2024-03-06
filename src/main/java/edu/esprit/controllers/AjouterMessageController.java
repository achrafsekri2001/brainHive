package edu.esprit.controllers;

import edu.esprit.entities.Messagerie;

import edu.esprit.entities.Utilisateur;
import edu.esprit.services.MessagerieService;
import edu.esprit.services.UserCRUD;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;



import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AjouterMessageController implements Initializable {


    @FXML
    private ImageView imageuser;

    @FXML
    private TextField message;

    @FXML
    private TextField nomReceiver;

    Utilisateur user= new Utilisateur(5,"feriel","assets/417957442_1478499912738648_2882908279475441643_n.jpg");
    Utilisateur user1= new Utilisateur(4,"rima","assets/417957442_1478499912738648_2882908279475441643_n.jpg");
    private final MessagerieController ms=new MessagerieController();
    private final UserCRUD su=new UserCRUD();
    Utilisateur userSender = su.getOneByID(user.getId());
    Utilisateur userReciver = su.getOneByID(user1.getId());

    @FXML
    void AjouterDiscussionAction(ActionEvent event) {

        if (isValidInput()) {
            String receiverName = nomReceiver.getText();

            // Récupérer l'utilisateur destinataire à partir du nom
            userReciver = su.getByUsername(receiverName);

            // Vérifier si l'utilisateur destinataire existe
            if (userReciver == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setContentText("Utilisateur destinataire introuvable.");
                alert.showAndWait();
                return;
            }

            Messagerie messagerie = new Messagerie();
            messagerie.setContenu(message.getText());
            messagerie.setSender_message(userSender);
            messagerie.setReceiver_message(userReciver);

            MessagerieService messagerieService = new MessagerieService();


                // Ajouter le message en utilisant le service Messagerie
                messagerieService.ajouter(messagerie);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setContentText("Messagerie ajoutée avec succès !");
                alert.show();

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText("Veuillez remplir tous les champs.");
            alert.showAndWait();
        }

    }

// Méthodes de validation de l'entrée

private boolean isValidInput() {
    return (nomReceiver.getText() != null && !nomReceiver.getText().isEmpty() && countWords(nomReceiver.getText()) <= 10) &&
            (message.getText() != null && !message.getText().isEmpty() && countLines(message.getText()) <= 10);
}

private int countWords(String text) {
    String[] words = text.split("\\s+");
    return words.length;
}

private int countLines(String text) {
    String[] lines = text.split("\\r?\\n");
    return lines.length;
}

// Méthode de navigation

private void navigateTo(String fxmlFilePath) {
    try {
        Parent root = FXMLLoader.load(getClass().getResource(fxmlFilePath));
        imageuser.getScene().setRoot(root);
    } catch (IOException e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Navigation Error");
        alert.setContentText("An error occurred while navigating to the next page.");
        alert.showAndWait();
    }
}


public void initialize(URL url, ResourceBundle resourceBundle) {
    // Initialiser les champs objettf et problemetf si nécessaire
}

    @FXML
    void NavigateToAcceuil(ActionEvent event) {

    }

    @FXML
    void NavigateToBackAction(ActionEvent event) {
        navigateTo("/Fxml/Messagerie.fxml");

    }

    @FXML
    void NavigateToFavoris(ActionEvent event) {

    }

    @FXML
    void NavigateToMessages(ActionEvent event) {

    }

    @FXML
    void NavigateToProduit(ActionEvent event) {

    }

    @FXML
    void NavigateToReclamation(ActionEvent event) {

    }


}