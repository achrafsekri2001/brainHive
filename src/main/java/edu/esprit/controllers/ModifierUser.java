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





    ServiceUser serviceUtilisateur = new ServiceUser();
        @FXML
        void save_modification(ActionEvent event) {


                // Get user input (assuming input fields are correctly linked)
                String nom = nouveau_nom.getText();
                String prenom = nouveauprenom.getText();
                String email = nouveauemail.getText(); // Assuming this is the intended email field
                String mdp = nouveaumdp.getText();

                // Validate user input (implement validation logic here)

                try {
                    // Use findByEmailAndRole for precise search (assuming email field is correct)
                    User admin = serviceUtilisateur.findByRole( 0);

                    if (admin != null) {
                        // Update administrator attributes
                        admin.setNom(nom);
                        admin.setPrenom(prenom);
                        admin.setPassword(mdp);

                        // Update admin information using serviceUtilisateur.Modifier
                        serviceUtilisateur.Modifier(admin);

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
                } catch (SQLException e) {
                    // Error message in case of database issues
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erreur");
                    alert.setHeaderText("Échec de la modification des informations");
                    alert.setContentText("Une erreur est survenue lors de la modification des informations de l'administrateur. Veuillez réessayer ultérieurement.");
                    alert.showAndWait();
                }
            }

        }









            /*


                // Get user input
                String nom = nouveau_nom.getText();
                String prenom = nouveauprenom.getText();
                String email = nouveau_email.getText();
                String mdp = nouveau_mdp.getText();

                // Validate user input
                // ... (code de validation inchangé)

                // Recherche de l'administrateur
                User admin = null;
            admin = serviceUtilisateur.findByRole( 0);

            // Si l'administrateur est trouvé
                if (admin != null) {
                    // Mettre à jour les attributs de l'administrateur
                    admin.setNom(nom);
                    admin.setPrenom(prenom);
                    admin.setEmail(email);
                    admin.setPassword(mdp);

                    // Enregistrer les modifications
                    try {
                        serviceUtilisateur.Modifier(admin);
                    } catch (SQLException e) {
                        // Afficher un message d'erreur si la modification échoue
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Erreur");
                        alert.setHeaderText("Échec de la modification des informations");
                        alert.setContentText("Une erreur est survenue lors de la modification des informations de l'administrateur. Veuillez réessayer ultérieurement.");
                        alert.showAndWait();
                        return;
                    }

                    // Afficher un message de succès
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Succès");
                    alert.setHeaderText("Modifications enregistrées");
                    alert.setContentText("Les coordonnées de l'administrateur ont été modifiées avec succès.");
                    alert.showAndWait();
                } else {
                    // Afficher un message d'erreur si l'administrateur n'est pas trouvé
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erreur");
                    alert.setHeaderText("Administrateur introuvable");
                    alert.setContentText("L'administrateur avec l'email \"" + email + "\" et le rôle \"administrateur\" n'existe pas.");
                    alert.showAndWait();
                }
            }



        }

             */
            /*



                // Get user input
                String nom = nouveau_nom.getText();
                String prenom = nouveauprenom.getText();
                String email = nouveau_email.getText();
                String mdp = nouveau_mdp.getText();

                // Validate user input
                // ... (code de validation inchangé)

                // Recherche de l'administrateur
                User admin ;
            admin = serviceUtilisateur.findByRole( 0);

            // Si l'administrateur est trouvé

                    // Mettre à jour les attributs de l'administrateur
                    admin.setNom(nom);
                    admin.setPrenom(prenom);
                    admin.setEmail(email);
                    admin.setPassword(mdp);

                    // Enregistrer les modifications
                    try {
                        serviceUtilisateur.Modifier(admin);
                    } catch (SQLException e) {
                        // Afficher un message d'erreur si la modification échoue
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Erreur");
                        alert.setHeaderText("Échec de la modification des informations");
                        alert.setContentText("Une erreur est survenue lors de la modification des informations de l'administrateur. Veuillez réessayer ultérieurement.");
                        alert.showAndWait();
                        return;
                    }

                    // Afficher un message de succès
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Succès");
                    alert.setHeaderText("Modifications enregistrées");
                    alert.setContentText("Les coordonnées de l'administrateur ont été modifiées avec succès.");
                    alert.showAndWait();
                }
            }

             */





            /*



            // Get user input

            String nom = nouveau_nom.getText();
            String prenom = nouveauprenom.getText();
            String email = nouveau_email.getText();
            String mdp = nouveau_mdp.getText();

            // Validate user input


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
            User admin = serviceUtilisateur.findByRole(0); // Recherche l'utilisateur avec le rôle "administrateur" (supposé être 0)

// Mettre à jour les attributs de l'administrateur
            admin.setNom(nom);
            admin.setPrenom(prenom);
            admin.setEmail(email);
            admin.setPassword(mdp);

// Enregistrer les modifications

            ServiceUser.Modifier(admin);

// Afficher un message de succès
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succès");
            alert.setHeaderText("Modifications enregistrées");
            alert.setContentText("Les coordonnées de l'administrateur ont été modifiées avec succès.");
            alert.showAndWait();

             */







            // Validate role



























