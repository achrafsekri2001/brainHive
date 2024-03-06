/*package edu.esprit.controllers;


import edu.esprit.services.UserCRUD;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.ByteArrayInputStream;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Base64;
import java.util.ResourceBundle;

public class HboxReclamationController implements Initializable {
    @FXML
    private VBox boxeReclamation1;

    @FXML
    private Label dateRec;
    @FXML
    private ImageView imageuser1;

    @FXML
    private Label objet1;

    @FXML
    private AnchorPane pane;

    @FXML
    private Label probleme1;

    @FXML
    private Label username;
    Utilisateur user = new Utilisateur();
    UserCRUD su =new UserCRUD();

    ReclamationService serviceReclamation = new ReclamationService();

    // Reference to the Reclamation object
    private Reclamation reclamation;

    // Reference to the container (parent) HBox
    private AnchorPane container;

    // Create a method to set data from Reclamation object
    public void setReclamationData(Reclamation reclamation, AnchorPane container) throws SQLException {
        this.reclamation = reclamation;
        this.container = container;


        // Set data to UI elements
        reclamation.setUtilisateur(user);
        reclamation.setContenu(probleme1.getText());
        reclamation.setObjet(objet1.getText());
        // probleme1.setText(reclamation.getContenu());
        reclamation.setDate(Timestamp.valueOf(String.valueOf(reclamation.getDate())));
        //dateRec.setText(String.valueOf(reclamation.getDate()));
        ReclamationService reclamationService = new ReclamationService();
        reclamationService.ajouterReclamation(reclamation);



        // Set data to UI elements
//
//       username.setText(reclamation.getUtilisateur().getUserName());
//        probleme1.setText(reclamation.getContenu());
//        objet1.setText(reclamation.getObjet());
//        dateRec.setText(String.valueOf(reclamation.getDate()));
//        String imageString = reclamation.getImgUser();
//
//        // Convertir la chaîne de caractères base64 en un tableau d'octets
//        byte[] imageData = Base64.getDecoder().decode(imageString);
//
//        // Créer une image à partir du tableau d'octets et l'assigner à votre élément d'interface utilisateur (supposons qu'il s'appelle "imageView")
//        Image image = new Image(new ByteArrayInputStream(imageData));
//        imageuser1.setImage(image);
    }

    // Create a method to set data from Reclamation object


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
*/