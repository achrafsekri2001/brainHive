package edu.esprit.controllers;

import edu.esprit.Services.ServiceUser;
import edu.esprit.entities.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.Set;

public class AfficherUser implements Initializable {



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    @FXML
    private Button retour;
    @FXML
    private Button clear;

    @FXML
    private TextField nouveau_nom;

    @FXML
    private TextField nouveau_prenom;

    @FXML
    private TextField nouveauemail;

    @FXML
    private TextField nouveaupswd;


    @FXML
    void modifier(ActionEvent event) {

    }










           /* User selectedUser = listView.getSelectionModel().getSelectedItem();

            if (selectedUser == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setContentText("Veuillez sélectionner un utilisateur à modifier.");
                alert.show();

            }

            */


           /* User selectedUser = listView.getSelectionModel().getSelectedItem();

            if (selectedUser == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setContentText("Veuillez sélectionner un utilisateur à modifier.");
                alert.showAndWait(); // Wait for user to acknowledge
                return;
            }

            // Retrieve the new name from the TextField
            String nouveauNom = nouveau_nom.getText();

            // Update the user's name
            selectedUser.setNom(nouveauNom);
            /*


            // Update the database

                // Replace with your specific database update method
                ServiceUser.modifier  (selectedUser);

                // Display success message
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Succès");
                alert.setContentText("Le nom de l'utilisateur a été modifié avec succès.");
                alert.showAndWait();

                // Optionally refresh the list view
                listView.refresh();

             catch (SQLException e) {
                // Handle database errors
                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setContentText("Une erreur est survenue lors de la modification du nom.");
                alert.showAndWait();
            }

             */





        @FXML
        void save(ActionEvent event) throws IOException, SQLException {
            // Récupération de l'utilisateur sélectionné
            User selectedUser = listView.getSelectionModel().getSelectedItem();

            // Vérification de la sélection
            if (selectedUser == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setContentText("Veuillez sélectionner un utilisateur à modifier.");
                alert.showAndWait();
                return;
            }

            // Récupération des nouvelles valeurs saisies
            String nouveauNom = nouveau_nom.getText();
            String nouveauPrenom = nouveau_prenom.getText();
            String nouveauEmail = nouveauemail.getText();
            String nouveauPswd = nouveaupswd.getText(); // Gérer la sécurité du mot de passe

            // Affectation des nouvelles valeurs à l'utilisateur
            selectedUser.setNom(nouveauNom);
            selectedUser.setPrenom(nouveauPrenom);
            selectedUser.setEmail(nouveauEmail);
            selectedUser.setPassword(nouveauPswd); // Hachage ou autre méthode de sécurisation

            // Confirmation de la modification
            Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
            confirmation.setTitle("Confirmation");
            confirmation.setContentText("Souhaitez-vous confirmer les modifications apportées à l'utilisateur " + selectedUser.getNom() + " ?");
            confirmation.showAndWait();

            }

    @FXML
    private ListView<User> listView;
    @FXML

    void retour_page(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/CRUD.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        // Obtenir la scène actuelle
        Stage currentStage = (Stage) retour.getScene().getWindow();

        // Afficher la nouvelle scène
        currentStage.setScene(scene);
        currentStage.show();}




        @FXML
        void afficheruserbtn(ActionEvent event) throws SQLException {
            // Créer une instance du service utilisateur
      ServiceUser serviceUtilisateur = new ServiceUser();
//
//            // Obtenir tous les utilisateurs
            Set<User> utilisateurs = serviceUtilisateur.getAll();
            System.out.println(utilisateurs);

//
//            // Convertir le Set en ObservableList (requis pour ListView)
            ObservableList<User> usersList = FXCollections.observableArrayList(utilisateurs);
//
//            // Définir les éléments de la ListView
            listView.setItems(usersList);
//
//            // Set cell factory for custom user display
            listView.setCellFactory(new Callback<>() {
                @Override
                public ListCell<User> call(ListView<User> param) {
                    return new ListCell<>() {
                        @Override
                        protected void updateItem(User item, boolean empty) {
                            super.updateItem(item, empty);

                            if (empty || item == null) {
                                setText(null);
                            } else {
                                // Consistent format string
                                String format = "%-90s%-20s%-25s%-30s%-10d";

                                // Display all user types with relevant attributes
                                setText(String.format(format, item.getNom(), item.getPrenom(), item.getEmail(),"sfsdf",0));  // Removed password for security
                            }
                        }
                    };
                }
            });


        }


















    @FXML
    void clear(ActionEvent event) {
        listView.getItems().clear();


    }

    }


