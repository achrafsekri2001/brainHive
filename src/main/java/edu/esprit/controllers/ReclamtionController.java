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
    private Avatar imageUser;

    @FXML
    private AnchorPane container;

    private final ReclamationService serviceReclamation = new ReclamationService();

    @FXML
    private Label titre;

    private void navigateTo(String fxmlFilePath) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxmlFilePath));
            imageUser.getScene().setRoot(root);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Navigation Error");
            alert.setContentText("An error occurred while navigating to the next page.");
            alert.showAndWait();
        }
    }


      @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

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
    void NavigateToFavorisAction(ActionEvent event) {

    }





    public void AjouterReclamationAction(ActionEvent actionEvent) {
        navigateTo("/AjouterReclamation.fxml");
    }

}



