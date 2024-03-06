package edu.esprit.controllers;

import com.gluonhq.charm.glisten.control.Avatar;
import edu.esprit.entities.AvisProduit;
import edu.esprit.entities.Produit;
import edu.esprit.entities.Utilisateur;
import edu.esprit.services.ServiceAvis;
import edu.esprit.services.ServiceProduit;
import edu.esprit.services.UserCRUD;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.Rating;

import java.io.File;
import java.sql.SQLException;

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
    Utilisateur user= new Utilisateur(1,"rima","assets/417957442_1478499912738648_2882908279475441643_n.jpg");
    Utilisateur user1= new Utilisateur(2,"feriel","assets/417957442_1478499912738648_2882908279475441643_n.jpg");
    Utilisateur user2= new Utilisateur(3,"achref","assets/417957442_1478499912738648_2882908279475441643_n.jpg");


    public void setAvisData(AvisProduit avisProduit, AnchorPane containerAvis)  {
        this.avisProduit = avisProduit;
        this.containerAvis = containerAvis;

        // Récupérer les données de l'avisProduit
        contenuavis.setText(avisProduit.getContenu());
        rate.setRating(avisProduit.getNote());

        // Récupérer l'utilisateur associé à l'avisProduit
        UserCRUD userCRUD = new UserCRUD();
        Utilisateur utilisateur = userCRUD.getOneByID(avisProduit.getIdUser());

        // Vérifier si l'utilisateur existe
        if (utilisateur != null) {
            // Mettre à jour l'image et le nom de l'utilisateur dans l'interface utilisateur
            userimg.setImage(new Image(new File(utilisateur.getImgUser()).toURI().toString()));
            username.setText(utilisateur.getUserName());
        } else {
            // Gérer le cas où l'utilisateur n'est pas trouvé
            userimg.setImage(null); // Effacer l'image
            username.setText("Utilisateur inconnu");
        }
    }
}
