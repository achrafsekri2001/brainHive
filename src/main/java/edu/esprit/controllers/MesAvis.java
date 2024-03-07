package edu.esprit.controllers;

import edu.esprit.entities.AvisProduit;
import edu.esprit.services.ServiceAvis;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.List;

public class MesAvis {

    @FXML
    private VBox container;
    @FXML
    private AnchorPane containeravis;

    private ServiceAvis serviceAvis;

    public void initialize() {
        // Initialisez votre service de gestion des avis
        serviceAvis = new ServiceAvis();

        // Récupérez tous les avis de l'utilisateur actuel
        List<AvisProduit> mesAvis = serviceAvis.recupererAvisParUtilisateur(GlobalHolder.getcurrentUser().getId()); // Remplacez CurrentUser.getId() par l'ID de votre utilisateur actuel

        // Pour chaque avis récupéré, créez une instance de MesAvisItem et ajoutez-la à un conteneur VBox
        for (AvisProduit avis : mesAvis) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/MesAvisItem.fxml"));
            try {
                AnchorPane item = loader.load();
                MesAvisItem mesavisItem = loader.getController();
                mesavisItem.setAvisProduit(avis, containeravis);
                container.getChildren().add(item);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
