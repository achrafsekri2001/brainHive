package edu.esprit.controllers;

import edu.esprit.entities.AvisProduit;
import edu.esprit.entities.Produit;
import edu.esprit.entities.Utilisateur;
import edu.esprit.services.ServiceAvis;

import edu.esprit.services.ServiceProduit;
import edu.esprit.services.UserCRUD;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class NoteEtAvis implements Initializable {

    @FXML
    private TextField avisTF;

    @FXML
    private AnchorPane containerAvis;

    @FXML
    private ImageView imgpara;

    @FXML
    private Label usercompte;

    @FXML
    private VBox vcontainer;

    Utilisateur user = new Utilisateur(1,"rima","sdfghj");
    private final UserCRUD su=new UserCRUD();
    private final ServiceAvis sa = new ServiceAvis();
    private final int id_Produit;
    private final ServiceProduit serviceProduit = new ServiceProduit();
    public NoteEtAvis(int id_Produit) {
        this.id_Produit = id_Produit;
    }
    public void initialize(URL location, ResourceBundle resources) {
       /* try {
            Produit produitAEvaluer = serviceProduit.getOneById(id_Produit);

            List<AvisProduit> avis = sa.getAll();
            for (AvisProduit avisProduit : avis) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/AvisItem.fxml"));
                try {
                    vcontainer.getChildren().add(loader.load());
                    AvisItem controller = loader.getController();
                    controller.setAvisData(avisProduit, containerAvis);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }

    @FXML
    void ajouteravisAction(ActionEvent event) throws SQLException {
        if (isValidInput()) {
            AvisProduit avis = new AvisProduit();
            avis.setContenu(avisTF.getText());
            avis.setUser_id(user.getId()); // Définissez l'utilisateur actuel comme le "user" de la réclamation
            avis.setUser_img(user.getImgUser()); // Définissez l'ID de l'utilisateur actuel comme l'ID de l'utilisateur de la réclamation
            avis.setUser_name(user.getUserName());
            sa.ajouter(avis);


            // Récupérer le parascolaire nouvellement ajouté depuis la base de données
           // AvisProduit nouveauAvis = sa.getOneByID(avis.getId_commentaire()); // Ici, supposez que vous avez une méthode dans votre service produit pour récupérer un parascolaire par son ID

            // Mettre à jour la liste d'affichage dans la page d'affichage avec le nouveau parascolaire
            // AfficherProduit.getInstance().ajouterParascolaire(nouveauParascolaire);

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
}
