package edu.esprit.controllers;

import com.gluonhq.charm.glisten.control.Avatar;
import edu.esprit.entities.Reclamation;
import edu.esprit.services.ReclamationService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class ReclamtionController implements Initializable {
    @FXML
    private VBox boxeReclamation1;
    @FXML
    private TextField recherche;

    @FXML
    private Avatar imageUser;

    @FXML
    private AnchorPane container;

    private final ReclamationService serviceReclamation = new ReclamationService();

    @FXML
    private Label titre;


    private void navigateTo(String fxmlFilePath) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxmlFilePath));
            titre.getScene().setRoot(root);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Navigation Error");
            alert.setContentText("An error occurred while navigating to the next page.");
            alert.showAndWait();
        }
    }


    public void afficherToutesLesReclamations() {

        try {
            List<Reclamation> reclamations = serviceReclamation.getAll();

            // Load and add ReclamationItemComponent for each Reclamation
            for (Reclamation reclamation : reclamations) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/hboxReclamation.fxml"));
                try {
                    boxeReclamation1.getChildren().add(loader.load());
                    HboxReclamationController controller = loader.getController();
                    controller.setReclamationData(reclamation, container);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception error) {
            error.printStackTrace();
        }


    }
    @FXML
    void MessagerieAction(ActionEvent event) {
        navigateTo("/Message.fxml");
    }

    @FXML
    void MesReclamationAction(ActionEvent event) {
        navigateTo("/MesReclamations.fxml");

    }

    @FXML
    private void rechercherParNom() {
        String texteRecherche = recherche.getText().trim();
        if (!texteRecherche.isEmpty()) {
            // Utiliser le service ReclamationService pour obtenir les réclamations par nom d'utilisateur
            List<Reclamation> reclamationsParNom = serviceReclamation.getReclamationsByUserName(texteRecherche);

            // Effacer le contenu actuel de la boîte de réclamations
            boxeReclamation1.getChildren().clear();

            // Charger et ajouter les composants de réclamation pour chaque réclamation trouvée
            for (Reclamation reclamation : reclamationsParNom) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/hboxReclamation.fxml"));
                try {
                    boxeReclamation1.getChildren().add(loader.load());
                    HboxReclamationController controller = loader.getController();
                    controller.setReclamationData(reclamation, container);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            // Si le champ de recherche est vide, afficher tous les réclamations
            afficherToutesLesReclamations();        }
    }



    public void AjouterReclamationAction(ActionEvent actionEvent) {
        navigateTo("/AjouterReclamation.fxml");
    }

    public void NavigateToFavorisAction(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        afficherToutesLesReclamations();
    }
}