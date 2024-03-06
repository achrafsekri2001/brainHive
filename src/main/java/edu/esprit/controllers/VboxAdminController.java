package edu.esprit.controllers;

import com.gluonhq.charm.glisten.control.Avatar;
import edu.esprit.entities.Reclamation;
import edu.esprit.services.ReclamationService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class VboxAdminController {
    @FXML
    private    VBox boxeReclamation1;

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
    @FXML
    void supprimerAction(ActionEvent event) {

    }
    public void setReclamationData(Reclamation reclamation, AnchorPane container) {
        this.reclamation = reclamation;
        this.container = container;

        // Set data to UI elements
        username.setText(reclamation.getUtilisateur().getUserName());

        probleme1.setText(reclamation.getContenu());
        objet1.setText(reclamation.getObjet());
        dateRec.setText(String.valueOf(reclamation.getDate()));
    }


}
