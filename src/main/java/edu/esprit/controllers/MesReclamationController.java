package edu.esprit.controllers;

import com.gluonhq.charm.glisten.control.Avatar;
import edu.esprit.entities.Reclamation;
import edu.esprit.entities.User;
import edu.esprit.services.ReclamationService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class MesReclamationController implements Initializable  {
    @FXML
    private VBox boxeReclamation1;

    @FXML
    private AnchorPane container;

    @FXML
    private Avatar imageUser;



    @FXML
    private Label titre;

    private final ReclamationService serviceReclamation = new ReclamationService();


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
    @FXML
    void AjouterReclamationAction(ActionEvent event) {
        navigateTo("/Fxml/AjouterReclamation.fxml");

    }

    @FXML
    void MessagerieAction(ActionEvent event) {
        navigateTo("/Fxml/Message.fxml");


    }



    @FXML
    void ReclamationAction(ActionEvent event) {
        navigateTo("/Fxml/Reclamation.fxml");


    }

    @FXML
    void RetourAction(ActionEvent event) {
        navigateTo("/Fxml/Reclamation.fxml");


    }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        int userId = GlobalHolder.getcurrentUser().getId();
            try {

                List<Reclamation> reclamations = serviceReclamation.afficherReclamationsParUtilisateur(userId);

                for (Reclamation reclamation : reclamations) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/hboxReclamation.fxml"));
                    boxeReclamation1.getChildren().add(loader.load());
                    HboxReclamationController controller = loader.getController();
                    controller.setReclamationData(reclamation, container);
                }
            } catch (Exception error) {
                error.printStackTrace();
            }
    }

    public void NavigateToFavorisAction(ActionEvent actionEvent) {
    }

    public void Reclamation(ActionEvent actionEvent) {
    }
}
