package edu.esprit.controllers;

import edu.esprit.services.ServiceUser;
import edu.esprit.entities.User;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class Modifieradmin implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    @FXML
    private Label nouveau_email;

    @FXML
    private Label nouveau_mdp;

    @FXML
    private TextField nouveau_nom;

    @FXML
    private Label nouveau_prenom;

    @FXML
    private TextField nouveauemail;

    @FXML
    private TextField nouveaumdp;

    @FXML
    private TextField nouveauprenom;
    @FXML
    private TextField id_admin;

    @FXML
    private Button save_modif;
    @FXML
    private TextField idadmin;

    @FXML
    private Label labeladmin;





    ServiceUser serviceUtilisateur = new ServiceUser();
        @FXML
        void save_modification(ActionEvent event) {


                // Get user input (assuming input fields are correctly linked)
                String nom = nouveau_nom.getText();
                String prenom = nouveauprenom.getText();
                String email = nouveauemail.getText(); // Assuming this is the intended email field
                String mdp = nouveaumdp.getText();

                // Validate user input (implement validation logic here)

            // Use findByEmailAndRole for precise search (assuming email field is correct)
            User admin = serviceUtilisateur.findByRole( 0);

            if (admin != null) {
                // Update administrator attributes
                admin.setNom(nom);
                admin.setPrenom(prenom);
                admin.setPassword(mdp);

                // Update admin information using serviceUtilisateur.Modifier
                serviceUtilisateur.modifier(admin);

                // Success message
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Succès");
                alert.setHeaderText("Modifications enregistrées");
                alert.setContentText("Les coordonnées de l'administrateur ont été modifiées avec succès.");
                alert.showAndWait();
            } else {
                // Error message if administrator not found
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText("Administrateur introuvable");
                alert.setContentText("L'administrateur avec l'email \"" + email + "\" et le rôle \"administrateur\" n'existe pas.");
                alert.showAndWait();
            }
        }

        }













