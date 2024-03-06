package edu.esprit.controllers;

import edu.esprit.entities.AvisProduit;
import edu.esprit.entities.Produit;
import edu.esprit.entities.Utilisateur;
import edu.esprit.services.ServiceAvis;

import edu.esprit.services.ServiceProduit;
import edu.esprit.services.UserCRUD;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import org.controlsfx.control.Rating;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class NoteEtAvis implements Initializable {
    @FXML
    private Rating rating;

    @FXML
    private TextField avisTF;

    @FXML
    private AnchorPane conatinerAvis;

    @FXML
    private ImageView imgpara;
    @FXML
    private Label nom;

    @FXML
    private Label description;

    @FXML
    private VBox vcontainer;
    private ServiceProduit sp;

    private Produit produit;
    Utilisateur user= new Utilisateur(1,"rima","assets/417957442_1478499912738648_2882908279475441643_n.jpg");
    Utilisateur user1= new Utilisateur(2,"feriel","assets/417957442_1478499912738648_2882908279475441643_n.jpg");
    Utilisateur user2= new Utilisateur(3,"achref","assets/417957442_1478499912738648_2882908279475441643_n.jpg");


   /* Utilisateur user = new Utilisateur(1,"rima","sdfghj");
    private final UserCRUD su=new UserCRUD();
    private final ServiceAvis sa = new ServiceAvis();
    private final int id_Produit;
    private final ServiceProduit serviceProduit = new ServiceProduit();
    public NoteEtAvis(int id_Produit) {
        this.id_Produit = id_Produit;
    }*/

    public void initialize(URL location, ResourceBundle resources) {
        sp = new ServiceProduit();

    }

    @FXML
    void ajouteravisAction(ActionEvent event) throws SQLException {
        if (isValidInput()) {
            AvisProduit avis = new AvisProduit();
            avis.setContenu(avisTF.getText());
            avis.setIdProduit(produit.getId_produit()); // Supposons que votre produit ait une méthode getId() qui retourne son identifiant
            avis.setIdUser(user1.getId()); // Supposons que votre utilisateur ait une méthode getId() qui retourne son identifiant

            double note = rating.getRating();
            avis.setNote((int) note);

            // Ajouter l'avis à la base de données en utilisant le service approprié
            ServiceAvis serviceAvis = new ServiceAvis();
            serviceAvis.ajouter(avis);

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

    }

    private boolean isValidInput() {
        if (avisTF.getText() == null || avisTF.getText().isEmpty()) {
            return false;
        }
        return true;
    }

    private List<AvisProduit> recupererReclamationsProduit(int idProduit) {
        ServiceAvis serviceAvis = new ServiceAvis();
        try {
            return serviceAvis.recupererReclamationsParProduit(idProduit);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>(); // En cas d'erreur, retournez une liste vide
        }
    }

    public void initDataPara(Produit produit) {
        // Affichage des détails du produit
        this.produit = produit;
        if (produit.getImage() != null && !produit.getImage().isEmpty()) {
            File imageFile = new File(produit.getImage());
            if (imageFile.exists()) {
                Image image = new Image(imageFile.toURI().toString());
                imgpara.setImage(image);
            }
        }
        nom.setText(produit.getNom());
        description.setText(produit.getDescription());

        // Récupération et affichage des réclamations du produit
        List<AvisProduit> reclamations = recupererReclamationsProduit(produit.getId_produit());
        for (AvisProduit reclamation : reclamations) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AvisItem.fxml"));
            try {
                AnchorPane item = loader.load();
                AvisItem avisItem = loader.getController();
                avisItem.setAvisData(reclamation, conatinerAvis);
                vcontainer.getChildren().add(item);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
