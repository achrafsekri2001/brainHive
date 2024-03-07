package edu.esprit.controllers;

import com.gluonhq.charm.glisten.control.Avatar;
import edu.esprit.entities.AvisProduit;
import edu.esprit.services.ServiceAvis;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.Rating;

public class AvisItem {

    @FXML
    private Label contenuavis;

    @FXML
    private Rating rate;

    @FXML
    private Avatar userimg;

    @FXML
    private Label username;
    ServiceAvis sp = new ServiceAvis();
    private AvisProduit avisProduit;
    private AnchorPane containerAvis;


    public void setAvisData(AvisProduit avisProduit, AnchorPane containerAvis) {
        this.avisProduit = avisProduit;
        this.containerAvis = containerAvis;

        // Récupérer les données de l'avisProduit
        contenuavis.setText(avisProduit.getContenu());
        rate.setRating(avisProduit.getNote());

        userimg.setImage(null); // Effacer l'image
        username.setText(avisProduit.getUser().getNom());
        //  }
    }
}
