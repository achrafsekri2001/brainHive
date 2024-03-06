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
    private Button retou;


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

        /*(Email.equals("raytn") && mdp.equals("rayn")) {
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

         */


            // Check if user exists in the database
            User existingUser = serviceUtilisateur.findByEmail(Email);


            // If user exists, authenticate
            if ( existingUser!= null) {
                if (existingUser.equals(mdp)) {
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

                }  else {
                    // Incorrect password
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erreur");
                    alert.setContentText("Mot de passe incorrect.");
                    alert.show();
                }




                 if (existingUser.equals(mdp) )
                 {
                     if (existingUser.getRoles() == 0)
                     { // Assuming "0" indicates admin role
                    System.out.println("Utilisateur administrateur authentifié avec succès !");
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/CRUD.fxml")); // Load CRUD page
                    Parent root = loader.load();
                    Scene scene = new Scene(root);
                    email.clear();

                    // Obtenir la scène actuelle
                    Stage currentStage = (Stage) button_login.getScene().getWindow();

                    // Afficher la nouvelle scène
                    currentStage.setScene(scene);
                    currentStage.show();

                } }else {
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


            }}/*

        // If user exists, authenticate and check role
        if (existingUser != null) {
            if (existingUser.equals(mdp)) {
                // User authenticated successfully
                if (existingUser.getRoles() == 0) { // Assuming "0" indicates admin role
                    System.out.println("Utilisateur administrateur authentifié avec succès !");
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/CRUD.fxml")); // Load CRUD page
                    Parent root = loader.load();
                    Scene scene = new Scene(root);
                    email.clear();

                    // Obtenir la scène actuelle
                    Stage currentStage = (Stage) button_login.getScene().getWindow();

                    // Afficher la nouvelle scène
                    currentStage.setScene(scene);
                    currentStage.show();
                } else {
                    System.out.println("Utilisateur authentifié avec succès, mais n'a pas le rôle administrateur.");
                    // Handle non-administrator login here (e.g., navigate to a different page)
                }
            } else {
                // Incorrect password
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setContentText("Mot de passe incorrect.");
                alert.show();
            }
        } else {
            // User not found
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText("Utilisateur non trouvé.");
            alert.show();
        }
    }
    */





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



