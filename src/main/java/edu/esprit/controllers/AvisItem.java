package edu.esprit.controllers;

import com.gluonhq.charm.glisten.control.Avatar;
import edu.esprit.entities.AvisProduit;
import edu.esprit.entities.Produit;
import edu.esprit.services.ServiceAvis;
import edu.esprit.services.ServiceProduit;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;

import java.io.File;

public class AvisItem {
    @FXML
    private Label contenuavis;

    @FXML
    private Avatar userimg;

    @FXML
    private Label username;
    ServiceAvis sp = new ServiceAvis();
    private AvisProduit avisProduit;
    private AnchorPane containerAvis;
    public void setAvisData(AvisProduit avisProduit, AnchorPane containerAvis) {
        this.avisProduit = avisProduit;
        this.containerAvis= containerAvis;

        contenuavis.setText(avisProduit.getContenu());
        username.setText(avisProduit.getUser_name());
        userimg.setImage(new Image(new File(avisProduit.getUser_img()).toURI().toString()));
    }
}
