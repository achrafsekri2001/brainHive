package edu.esprit.controllers;

import edu.esprit.entities.Messagerie;
import edu.esprit.services.MessagerieService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class VboxDiscussionController implements Initializable {
    @FXML
    private Label contenuMsg1;

    @FXML
    private ImageView imageuser11;

    @FXML
    private Label nomEmetteur1;
    MessagerieService messagerieService= new MessagerieService();
    private  Messagerie messagerie;
    private AnchorPane container;
     public void setDiscussionData(Messagerie messagerie, AnchorPane container){
         this.messagerie = messagerie;
         this.container = container;


         nomEmetteur1.setText(messagerie.getReceiver_message().getNom());
         contenuMsg1.setText(messagerie.getContenu());

     }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
