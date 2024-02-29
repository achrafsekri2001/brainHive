package edu.esprit.controllers;

import com.gluonhq.charm.glisten.control.Avatar;
import edu.esprit.entities.Reclamation;
import edu.esprit.services.ReclamationService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class HboxReclamationController implements Initializable {
    @FXML
    private VBox boxeReclamation1;

    @FXML
    private Label dateRec;

    @FXML
    private Avatar imageuser1;

    @FXML
    private Label objet1;

    @FXML
    private AnchorPane pane;

    @FXML
    private Label probleme1;

    @FXML
    private Label username;

    ReclamationService serviceReclamation = new ReclamationService();

    // Reference to the Reclamation object
    private Reclamation reclamation;

    // Reference to the container (parent) HBox
    private AnchorPane container;

    // Create a method to set data from Reclamation object
    public void setReclamationData(Reclamation reclamation, AnchorPane container) {
        this.reclamation = reclamation;
        this.container = container;

        // Set data to UI elements
        username.setText(reclamation.getUser());
        probleme1.setText(reclamation.getContenu());
        objet1.setText(reclamation.getObjet());

        //userCode.setText(reclamation.getPub().getCode_pub());
        dateRec.setText(String.valueOf(reclamation.getDate()));
    }

    // Create a method to set data from Reclamation object


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


   /* public void navigateToAfficherReclamationAction(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/AfficherReclamation.fxml"));
            userCode.getScene().setRoot(root);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Sorry");
            alert.setTitle("Error");
            alert.show();
        }

    }*/
}
