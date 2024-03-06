package edu.esprit.controllers;


import edu.esprit.Services.ServiceUser;
import edu.esprit.entities.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author Aziz
 */
public class InscrireController implements Initializable {

    @FXML
    private Button button_sign_up;
    @FXML
    private Button button_login;

    @FXML
    private TextField tf_email;
    @FXML
    private TextField tf_password;
    @FXML
    private TextField tf_lastname;
    @FXML
    private TextField tf_nom;

    @FXML
    private TextField tf_role;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    @FXML
    void retour_page(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Login.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);


        // Obtenir la scène actuelle
        Stage currentStage = (Stage) button_login.getScene().getWindow();

        // Afficher la nouvelle scène
        currentStage.setScene(scene);
        currentStage.show();

    }

    @FXML
    private void ajouter(ActionEvent event) {


        ServiceUser serviceUtilisateur = new ServiceUser();

        // Get user input
        String roleText = tf_role.getText();
        String nom = tf_nom.getText();
        String prenom = tf_lastname.getText();
        String email = tf_email.getText();
        String mdp = tf_password.getText();

        // Validate user input
try{
            int role = Integer.parseInt(roleText); // Check for valid integer

            // Check for empty fields

            if (nom.isEmpty() || prenom.isEmpty() || email.isEmpty() || mdp.isEmpty() || roleText.isEmpty())  {
                throw new IllegalArgumentException("Veuillez remplir tous les champs obligatoires.");
            }


            // Validate email format
            if (!email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}")) {
                throw new IllegalArgumentException("Veuillez saisir une adresse email valide.");

            }

// Vérifier le format de l'email
    if (!email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}")) {
        throw new IllegalArgumentException("Veuillez saisir une adresse email valide.");
    }


    if (mdp.isEmpty() || mdp.length() < 8 || !mdp.matches(".*[A-Z]+.*") || !mdp.matches(".*[a-z]+.*") || !mdp.matches(".*[0-9]+.*")) {
        // Si le mot de passe est vide, trop court ou ne contient pas de lettres majuscules, minuscules et chiffres
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Mot de passe invalide");
        alert.setContentText("Veuillez saisir un mot de passe valide (8 caractères minimum, avec au moins une lettre majuscule, une lettre minuscule et un chiffre).");
        alert.showAndWait();
        return;}

            // Validate role
            if ( role != 1 && role != 2) {
                throw new IllegalArgumentException("Veuillez saisir un rôle valide ( 1 pour étudiant, 2 pour enseignant).");
            }


            // Create User object
            User u = new User();
            u.setNom(nom);
            u.setPrenom(prenom);
            u.setEmail(email);
            u.setPassword(mdp);
            u.setRoles(role);

            // Add user to database (assuming serviceUtilisateur handles this)
            serviceUtilisateur.ajouter(u);

            // Display success message with role specified
            String roleMessage = "";

             if (role == 1) {
                roleMessage = "Etudiant";
            } else if(role==2 ){
                roleMessage = "Enseignant";
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("User added with role: " + roleMessage);
            alert.show();



        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText("Veuillez saisir un rôle valide.");
            alert.show();

        } catch (IllegalArgumentException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText(e.getMessage());
            alert.show();}

         catch (Exception e) { // Handle any other unexpected errors
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText("Une erreur inattendue s'est produite.");
            alert.show();
        }
    }



    @FXML
    private void handleLogin(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Login.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        tf_nom.clear();

        // Obtenir la scène actuelle
        Stage currentStage = (Stage) button_login.getScene().getWindow();

        // Afficher la nouvelle scène
        currentStage.setScene(scene);
        currentStage.show();
    }
}

