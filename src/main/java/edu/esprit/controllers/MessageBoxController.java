package edu.esprit.controllers;

import com.gluonhq.charm.glisten.control.Avatar;
import edu.esprit.entities.Messagerie;
import edu.esprit.entities.User;
import edu.esprit.services.MessagerieService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.ByteArrayInputStream;
import java.util.Base64;
import java.util.Date;

public class MessageBoxController {
    @FXML
    private Label datetf;

    @FXML
    private AnchorPane container;

    @FXML
    private Label msgTf1;

    @FXML
    private Label nonUser1;

    @FXML
    private VBox vboxMsg1;

    @FXML
    private ImageView imgUser1;

    private Messagerie messagerie;
    private AnchorPane Anchorpane;
    //    private MessagerieService messagerieService;
    MessagerieService sm =  new MessagerieService();



    public void setMessageData(Messagerie messagerie, AnchorPane Anchorpane) {
        this.messagerie = messagerie;
        this.Anchorpane = Anchorpane;
        if (messagerie.getReceiver_message() != null) {
            nonUser1.setText(messagerie.getReceiver_message().getNom());
        } else {
            // Gérer le cas où le destinataire est null
            nonUser1.setText(messagerie.getSender_message().getNom());
        }
     //   nonUser1.setText(messagerie.getReceiver_message().getUserName());
        msgTf1.setText(messagerie.getContenu());
        datetf.setText(String.valueOf(messagerie.getDate()));
//        // Convertir la chaîne de caractères base64 en un tableau d'octets
//        String imageString = messagerie.getSender_message().getImgUser();
//        byte[] imageData = Base64.getDecoder().decode(imageString);
//
//        // Créer une image à partir du tableau d'octets et l'assigner à votre élément d'interface utilisateur (supposons qu'il s'appelle "imageView")
//        Image image = new Image(new ByteArrayInputStream(imageData));
//        imgUser1.setImage(image);
//        //    imgUser1.setImage(Utilisateur.getImgUser());



    }

    @FXML
    void ModifierMsgAction(ActionEvent event) {
        // Action à effectuer lorsqu'on souhaite modifier un message
        // À implémenter selon les besoins de votre application
    }

    @FXML
    void SupprimerMsgAction(ActionEvent event) {
        // Action à effectuer lorsqu'on souhaite supprimer un message
        // À implémenter selon les besoins de votre application
        if (messagerie != null) {
            // Supprimer le message de la base de données
            sm.supprimer(messagerie.getIdMessage());
            // Supprimer également l'affichage du message de l'interface utilisateur
            container.getChildren().remove(vboxMsg1);
        }
    }
}