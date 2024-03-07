package edu.esprit.controllers;

import edu.esprit.services.ServiceUser;
import edu.esprit.entities.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class Crud  implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    @FXML
    private Button afficher;
    @FXML
    private TextField TF_Nom;

    @FXML
    private Button button_delete;
    @FXML
    private Button modif_admin;


    @FXML
    private Button button_logout;

    @FXML
    private Button button_update;

    @FXML
    private TextField tf_Prenom;

    @FXML
    private TextField tf_email;

    @FXML
    private TextField tf_passwd;
    @FXML
    private TextField roles;
    @FXML
    private TextField id_asuprimer;
    @FXML
    private Button retour;
    @FXML
    private ListView<User> listView;
    @FXML
    void retour_page(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Accueil.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);


        // Obtenir la scène actuelle
        Stage currentStage = (Stage) retour.getScene().getWindow();

        // Afficher la nouvelle scène
        currentStage.setScene(scene);
        currentStage.show();



    }








    @FXML
    void passer_modif_admin(ActionEvent event)  throws IOException{




            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Modifieradmin.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);

                // Get the current stage
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                // Set the new scene and show it
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                // Handle any errors during loading or display
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setContentText("Échec du chargement de la page 'Modifieradmin'. Veuillez réessayer ou contacter le support.");
                alert.showAndWait();
                e.printStackTrace(); // Log the error for debugging
            }
        }


        /*

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Modifieradmin.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);

    }

         */



    @FXML
    void Logout(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Login.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);


        // Obtenir la scène actuelle
        Stage currentStage = (Stage) button_logout.getScene().getWindow();

        // Afficher la nouvelle scène
        currentStage.setScene(scene);
        currentStage.show();
    }



        @FXML
        private void  afficher(ActionEvent event) throws IOException {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/AfficherUser.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);


            // Obtenir la scène actuelle
            Stage currentStage = (Stage)afficher.getScene().getWindow();

            // Afficher la nouvelle scène
            currentStage.setScene(scene);
            currentStage.show();

        }


    @FXML
    void addUser(ActionEvent event) {




            ServiceUser serviceUtilisateur = new ServiceUser();

            // Get user input
        String roleText = roles.getText();
        String nom = TF_Nom.getText();
        String prenom = tf_Prenom.getText();
        String email = tf_email.getText();
        String mdp = tf_passwd.getText();

            // Validate user input
            try {
                int role = Integer.parseInt(roleText); // Check for valid integer

                // Check for empty fields
                if (nom.isEmpty() || prenom.isEmpty() || email.isEmpty() || mdp.isEmpty()) {
                    throw new IllegalArgumentException("Veuillez remplir tous les champs obligatoires.");
                }

                // Validate email format
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
                if (role != 0 && role != 1 && role != 2) {
                    throw new IllegalArgumentException("Veuillez saisir un rôle valide (0 pour admin, 1 pour étudiant, 2 pour enseignant).");
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
                if (role == 0) {
                    roleMessage = "Admin";
                } else if (role == 1) {
                    roleMessage = "Etudiant";
                } else {
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
                alert.show();

            } catch (Exception e) { // Handle any other unexpected errors
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setContentText("Une erreur inattendue s'est produite.");
                alert.show();
            }
        }



    }
