package edu.esprit.controllers;

import com.google.protobuf.compiler.PluginProtos;
import edu.esprit.entities.User;
import edu.esprit.services.ServiceUser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;


public class SampleController implements Initializable {



    @FXML
    private Button button_login;
    @FXML
    private Hyperlink ForgotPassword;
    @FXML
    private Button mdp_oublié;
    @FXML
    private Button retou;
    @FXML
    private Button ajout_img;


    @FXML

    private Button button_sign_up;
    @FXML
    private PasswordField pf_password;
    public User u;
    @FXML
    private TextField email;

    @FXML
    private PasswordField password;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        email.setText("achraf@achraf.com");
        password.setText("brainHive123");
    }


    @FXML
    private void handleLogin(ActionEvent event) throws SQLException, IOException {


            ServiceUser serviceUtilisateur = new ServiceUser();
            String Email = email.getText(); // Assuming 'email' is a TextField object
            String mdp = password.getText(); // Assuming 'password' is a PasswordField object

            // Check for empty fields
            if (Email.isEmpty() || mdp.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setContentText("Veuillez remplir tous les champs obligatoires.");
                alert.show();
                return;
            }

            // Check user existence and authenticate
            User existingUser = serviceUtilisateur.findByEmail(Email);

            if (existingUser != null) {
                if (existingUser.getPassword().equals(mdp)) {
                    GlobalHolder.setcurrentUser(existingUser);
                    System.out.println("Utilisateur authentifié avec succès !");

                    // Determine and navigate based on user role
                    if (existingUser.getRoles() == 0) { // Assuming "0" indicates admin role
                        System.out.println("Utilisateur administrateur authentifié avec succès !");
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/CRUD.fxml"));
                        Parent root = loader.load();
                        Scene scene = new Scene(root);
                        email.clear();
                        Stage currentStage = (Stage) button_login.getScene().getWindow();
                        currentStage.setScene(scene);
                        currentStage.show();
                    } else {
                        // User is not admin, navigate to a different view (e.g., Accueil.fxml)
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Reclamation.fxml")); // Replace with your desired user view
                        Parent root = loader.load();
                        Scene scene = new Scene(root);
                        email.clear();
                        Stage currentStage = (Stage) button_login.getScene().getWindow();
                        currentStage.setScene(scene);
                        currentStage.show();
                    }
                } else {
                    // Incorrect password
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erreur");
                    alert.setContentText("Mot de passe incorrect.");
                    alert.show();
                }
            } else {
                // User does not exist, display information message
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setContentText("Aucun utilisateur avec cet email n'existe. Veuillez créer un compte.");
                alert.show();
            }
        }



        /*
        ServiceUser serviceUtilisateur = new ServiceUser();
        String Email = email.getText();
        String mdp = password.getText();
        if (Email.isEmpty() || mdp.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText("Veuillez remplir tous les champs obligatoires.");
            alert.show();
            return;
        }



        // Check if user exists in the database
        User existingUser = serviceUtilisateur.findByEmail(Email);
        System.out.println("existingUser" + existingUser.equals(mdp));

        // If user exists, authenticate
        if (existingUser != null) {
            if (existingUser.getPassword().equals(mdp)) {
                GlobalHolder.setcurrentUser(existingUser);
                // User authenticated successfully
                System.out.println("Utilisateur authentifié avec succès !");
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Reclamation.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                email.clear();

                // Obtenir la scène actuelle
                Stage currentStage = (Stage) button_login.getScene().getWindow();

                // Afficher la nouvelle scène
                currentStage.setScene(scene);
                currentStage.show();

            } else {
                // Incorrect password
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setContentText("Mot de passe incorrect.");
                alert.show();
            }


            if (existingUser.getPassword().equals(mdp)) {
                GlobalHolder.setcurrentUser(existingUser);
                if (existingUser.getRoles() == 0) { // Assuming "0" indicates admin role
                    System.out.println("Utilisateur administrateur authentifié avec succès !");
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/CRUD.fxml")); // Load CRUD page
                    Parent root = loader.load();
                    Scene scene = new Scene(root);
                    email.clear();

                    // Obtenir la scène actuelle
                    Stage currentStage = (Stage) button_login.getScene().getWindow();

                    // Afficher la nouvelle scène
                    currentStage.setScene(scene);
                    currentStage.show();

                }
            } else {
                // Incorrect password
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setContentText("Mot de passe incorrect.");
                alert.show();
            }
        } else {
            // User does not exist, display information message
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setContentText("Aucun utilisateur avec cet email n'existe. Veuillez créer un compte.");
            alert.show();


        }
    }

         */


    @FXML
    private void handlebutton_sign_up(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Signup.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        email.clear();

        // Obtenir la scène actuelle
        Stage currentStage = (Stage) button_login.getScene().getWindow();

        // Afficher la nouvelle scène
        currentStage.setScene(scene);
        currentStage.show();
    }



    @FXML
    void handlebutton_forgot(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/ForgotPassword.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);


        // Obtenir la scène actuelle
        Stage currentStage = (Stage) mdp_oublié.getScene().getWindow();

        // Afficher la nouvelle scène
        currentStage.setScene(scene);
        currentStage.show();
    }
}



