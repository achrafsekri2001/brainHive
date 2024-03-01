package edu.esprit.controllers;

import edu.esprit.Services.ServiceUser;
import edu.esprit.entities.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class SampleController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {



        // TODO
    }

    @FXML
    private Button button_login;
    @FXML
    private Hyperlink ForgotPassword;
    @FXML
    private Button mdp_oublié;


    @FXML

    private Button button_sign_up;
    @FXML
    private PasswordField pf_password;
    public User u ;
    @FXML
    private TextField email;

    @FXML
    private PasswordField password;


    /**
     * Initializes the controller class.
     */


    @FXML
    private void handleLogin(ActionEvent event) throws SQLException, IOException {
        ServiceUser serviceUtilisateur = new ServiceUser();

// Get user input
        String Email =email.getText();
        String mdp = password.getText();

// Validate user input
        if (Email.isEmpty() || mdp.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText("Veuillez remplir tous les champs obligatoires.");
            alert.show();
            return;
        }

// Check for specific email and password
        if (Email.equals("rayen@rayen.tn") && mdp.equals("rayen")) {
            // Navigate to Accueil.fxml directly for specific credentials
            System.out.println("Utilisateur rayen@rayen.tn authentifié avec succès !");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/CRUD.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            email.clear();

            // Obtenir la scène actuelle
            Stage currentStage = (Stage) button_login.getScene().getWindow();

            // Afficher la nouvelle scène
            currentStage.setScene(scene);
            currentStage.show();

        } else {
            // Check if user exists in the database
            User existingUser = serviceUtilisateur.findByEmail(Email);

            // If user exists, authenticate
            if (existingUser != null) {
                if (existingUser.getPassword().equals(mdp)) {
                    // User authenticated successfully
                    System.out.println("Utilisateur authentifié avec succès !");
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/Accueil.fxml"));
                    Parent root = loader.load();
                    Scene scene = new Scene(root);
                    email.clear();

                    // Obtenir la scène actuelle
                    Stage currentStage = (Stage) button_login.getScene().getWindow();

                    // Afficher la nouvelle scène
                    currentStage.setScene(scene);
                    currentStage.show();

                }
                else {
                    // Incorrect password
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erreur");
                    alert.setContentText("Mot de passe incorrect.");
                    alert.show();
                }}
            else{
                // User does not exist, display information message
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setContentText("Aucun utilisateur avec cet email n'existe. Veuillez créer un compte.");
                alert.show();


            }}}

                /*

                    // Navigate to Accueil.fxml (code corrected for navigation)
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Accueil.fxml"));
                        Parent root = loader.load();
                        Scene scene = new Scene(root);

                        // Get the current stage
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException e) {
                        System.out.println("Erreur lors du chargement de Accueil.fxml");
                        e.printStackTrace();
                    }
                } else {
                    // Incorrect password
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erreur");
                    alert.setContentText("Mot de passe incorrect.");
                    alert.show();
                }
            } else {
                // User does not exist
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setContentText("Aucun utilisateur avec cet email n'existe. Veuillez créer un compte.");
                alert.show();
            } /*
        }

    }

/*

           ServiceUser serviceUtilisateur = new ServiceUser();

            // Get user input
            String Email = email.getText();
            String mdp = password.getText();

            // Validate user input
            if (Email.isEmpty() || mdp.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setContentText("Veuillez remplir tous les champs obligatoires.");
                alert.show();
                return;
            }

            // Check if user exists
            User existingUser = serviceUtilisateur.findByEmail(Email);

            // If user exists, authenticate
            if (existingUser != null) {
                if (existingUser.getPassword().equals(mdp)) {
                    // User authenticated successfully
                    // ... handle successful login (e.g., navigate to home page)
                    System.out.println("Utilisateur authentifié avec succès !");
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/Accueil.fxml"));
                    Parent root = loader.load();
                    Scene scene = new Scene(root);
                }





                else {
                    // Incorrect password
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erreur");
                    alert.setContentText("Mot de passe incorrect.");
                    alert.show();
                }}
             else{
            // User does not exist, display information message
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setContentText("Aucun utilisateur avec cet email n'existe. Veuillez créer un compte.");
            alert.show();
        }}

/*




        /*
            // Get user input
            String Email = email.getText();
            String mdp = password.getText();

            // Validate user input
            if (Email.isEmpty() || mdp.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setContentText("Veuillez remplir tous les champs obligatoires.");
                alert.show();
                return;
            }

            // Check if user exists
            User existingUser = ServiceUser.findByEmail ( Email);

            // If user exists, authenticate
            if (existingUser != null) {
                if (existingUser.getPassword().equals(mdp)) {
                    // User authenticated successfully
                    // ... handle successful login (e.g., navigate to home page)
                    System.out.println("Utilisateur authentifié avec succès !");

                    // Load the Accueil.fxml scene
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/Accueil.fxml"));
                    Parent root = loader.load();

                    // Get the current stage
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                    // Set the new scene on the stage
                    stage.setScene(new Scene(root));

                    // Show the new scene
                    stage.show();
                } else {
                    // Incorrect password
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erreur");
                    alert.setContentText("Mot de passe incorrect.");
                    alert.show();
                }
            } else {
                // User does not exist
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setContentText("Aucun utilisateur avec cet email n'existe. Veuillez créer un compte.");
                alert.show();
            }
        }*/


        @FXML
    private void handlebutton_sign_up(ActionEvent event) throws IOException {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Signup.fxml"));
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
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/ForgotPassword.fxml"));
    Parent root = loader.load();
    Scene scene = new Scene(root);


    // Obtenir la scène actuelle
    Stage currentStage = (Stage)mdp_oublié.getScene().getWindow();

    // Afficher la nouvelle scène
    currentStage.setScene(scene);
    currentStage.show();}}



