package edu.esprit.controllers;

import edu.esprit.Services.ServiceUser;
import edu.esprit.entities.User;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class ModifierUser  implements Initializable {
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






        @FXML
        void save_modification(ActionEvent event) {
            ServiceUser serviceUtilisateur = new ServiceUser();
            try {

                // 1. Retrieve data from UI controls
                int id = Integer.parseInt(id_admin.getText()); // Assuming "id_admin" field holds admin ID
                String nom = nouveau_nom.getText();
                String prenom = nouveauprenom.getText();
                String email = nouveauemail.getText();
                String mdp = nouveaumdp.getText();

                // 2. Validate user input (optional but recommended)
                // Add logic to validate data types and formats (e.g., email format, password strength)

                // 3. Create an Admin object with the retrieved data
                User adminToModify = new User(nom, prenom, email, mdp); // Assuming Admin constructor has appropriate parameters

                // 4. Call the modifier_admin method to update the admin in the database
                  serviceUtilisateur.modifierAdmin(adminToModify);

                // 5. Display a success message
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Modification");
                alert.setHeaderText(null);
                alert.setContentText("Admin modifié avec succès!");
                alert.showAndWait();

            } catch (NumberFormatException e) {
                // 6. Handle invalid ID format (assuming id_admin is numeric)
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("L'identifiant de l'administrateur doit être un nombre entier.");
                alert.showAndWait();
            } catch (SQLException e) {
                // 7. Handle any SQL exceptions gracefully
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            } catch (Exception e) { // Catch any other unexpected exceptions
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("Une erreur inattendue est survenue.");
                alert.showAndWait();
            }
        }




    }










