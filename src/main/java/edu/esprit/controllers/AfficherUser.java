package edu.esprit.controllers;

import edu.esprit.services.ServiceUser;
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
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Set;

public class AfficherUser implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }




    @FXML
    private ListView<User> listView;

    @FXML
    private Button retour;
    @FXML
    private Button modif_btn;

    @FXML
    private Button sup_button;
    @FXML
    private Button clear;
    @FXML
    private Button afficher_Enseignant;

    @FXML
    private TextField nouveau_nom;

    @FXML
    private TextField nouveau_prenom;

    @FXML
    private TextField nouveauemail;

    @FXML
    private TextField nouveaupswd;
    @FXML
    private Button affiche_etudiant;



    private class UserListCell extends ListCell<User> {


        protected void updateItem(User user, boolean empty) {
            super.updateItem(user, empty);

            if (empty || user == null) {
                setText(null);
            } else {
                // Afficher les informations du client
                nouveau_nom.setText(user.getNom());
                nouveau_prenom.setText(user.getPrenom());
                nouveauemail.setText(user.getEmail());
                nouveaupswd.setText(user.getPassword()); // Affichage du mot de passe à confirmer

                // Vous pouvez afficher d'autres attributs ici
                // ...
            }
        }
    }




    /*
            // Get the selected user from the ListView
            User selectedUser = listView.getSelectionModel().getSelectedItem();

            if (selectedUser != null) {
                // Confirm deletion with the user
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirm Deletion");
                alert.setHeaderText("Confirm Deletion");
                alert.setContentText("Are you sure you want to delete the user " + selectedUser.getNom() + " " + selectedUser.getPrenom() + "?");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    try {
                        // Delete the user from the database
                        ServiceUser serviceUtilisateur = new ServiceUser();
                        serviceUtilisateur.supprimer(selectedUser);

                        // Remove the user from the ListView
                        listView.getItems().remove(selectedUser);

                        // Display a success message
                        Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                        successAlert.setTitle("Success");
                        successAlert.setHeaderText("User Deleted");
                        successAlert.setContentText("The user has been successfully deleted.");
                        successAlert.show();
                    } catch (SQLException e) {
                        // Handle database errors
                        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                        errorAlert.setTitle("Error");
                        errorAlert.setHeaderText("Failed to delete user");
                        errorAlert.setContentText("An error occurred while deleting the user: " + e.getMessage());
                        errorAlert.show();
                    }
                }
            } else {
                // No user selected
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("No User Selected");
                alert.setHeaderText("Please select a user to delete");
                alert.show();
            }

             */
            @FXML
            void supprimer(ActionEvent event) throws SQLException {

                    User selectedUser = listView.getSelectionModel().getSelectedItem();

                    if (selectedUser != null) {
                        // Confirm deletion with the user
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Confirm Deletion");
                        alert.setHeaderText("Confirm Deletion");
                        alert.setContentText("Are you sure you want to delete the user " + selectedUser.getNom() + " " + selectedUser.getPrenom() + "?");

                        Optional<ButtonType> result = alert.showAndWait();
                        if (result.get() == ButtonType.OK) {
                            ServiceUser serviceUtilisateur = new ServiceUser();
                            serviceUtilisateur.delete(selectedUser);

                            // Remove the user from the ListView
                            listView.getItems().remove(selectedUser);

                            // Display a success message
                            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                            successAlert.setTitle("Success");
                            successAlert.setHeaderText("User Deleted");
                            successAlert.setContentText("The user has been successfully deleted.");
                            successAlert.show();
                        }
                    } else {
                        // No user selected
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("No User Selected");
                        alert.setHeaderText("Please select a user to delete");
                        alert.show();
                    }
                }
    ServiceUser serviceUser = new ServiceUser();
    @FXML

    void modifier(ActionEvent event) throws SQLException {


            User userSelected = listView.getSelectionModel().getSelectedItem();

            if (userSelected != null) {
                userSelected.setNom(nouveau_nom.getText());
                userSelected.setPrenom(nouveau_prenom.getText());
                userSelected.setPassword(nouveaupswd.getText());
                userSelected.setEmail(nouveauemail.getText());

                // Call the service method to update the user in the database
                serviceUser.modifier(userSelected);

                // Refresh the ListView with the updated data from the database
                listView.setItems(FXCollections.observableArrayList(serviceUser.getAll()));}

                // Show a success message
               // showAlert(Alert.AlertType.INFORMATION, "Modification réussie", "Le client a été modifié avec succès.");
          //  } //else {
               // showAlert(Alert.AlertType.WARNING, "Sélectionnez un client", "Veuillez sélectionner un client à modifier.");
            //}




/*

        User userselected = listView.getSelectionModel().getSelectedItem();

        if (userselected != null) {
            userselected.setNom(nouveau_nom.getText());
            userselected.setPrenom(nouveau_prenom.getText());
            userselected.setPassword(nouveaupswd.getText());

            userselected.setEmail(nouveauemail.getText());



            ServiceUser.Modifier(userselected) ;
            ListView.getItems().add(userselected);
            // Update the ListView with the modified client object
            int index = ListView.getItems().indexOf(selectclient);
            listView.getItems().set(index, ServiceUser);
            listView.setItems(FXCollections.observableArrayList(ServiceUser.getAll()));
            // Show a success message
            showAlert(Alert.AlertType.INFORMATION, "Modification réussie", "Le client a été modifié avec succès.");
        } else {
            showAlert(Alert.AlertType.WARNING, "Sélectionnez un client", "Veuillez sélectionner un client à modifier.");
        }

 */
    }








                /*
        User selectedUser = listView.getSelectionModel().getSelectedItem();
        System.out.println(selectedUser);

        if (selectedUser != null) {
            // Récupération des informations modifiées à partir des TextFields
            String Nouveau_nom = nouveau_nom.getText();
            String Nouveau_prenom = nouveau_prenom.getText();
            String Nouveauemail = nouveauemail.getText();
            String Nouveaupswd = nouveaupswd.getText();

            // Valider les informations saisies (à implémenter)
            // ...


            // Appeler le service de modification
            ServiceUser serviceUtilisateur = new ServiceUser();
            serviceUtilisateur.modifier(selectedUser, nouveau_nom, nouveau_prenom, nouveauemail, nouveaupswd);

            // Mettre à jour la ListView
            int selectedIndex = listView.getSelectionModel().getSelectedIndex();
            listView.getItems().set(selectedIndex, selectedUser); // Replace with updated user

            // Afficher un message de confirmation
            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
            successAlert.setTitle("Success");
            successAlert.setHeaderText("User Updated");
            successAlert.setContentText("The user has been successfully updated.");
            successAlert.show();
        } else {
            // No user selected
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No User Selected");
            alert.setHeaderText("Please select a user to modify");
            alert.show();
        }

                 */








    @FXML

    void retour_page(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/CRUD.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        // Obtenir la scène actuelle
        Stage currentStage = (Stage) retour.getScene().getWindow();

        // Afficher la nouvelle scène
        currentStage.setScene(scene);
        currentStage.show();}
    @FXML
    void clear(ActionEvent event) {
        listView.getItems().clear();


    }

                /*

        // Créer une instance du service utilisateur
        ServiceUser serviceUtilisateur = new ServiceUser();

        // Obtenir tous les utilisateurs ayant un rôle égal à 1
        Set<User> utilisateurs = (Set<User>) serviceUtilisateur.getUsersEnseignant(2); // Supposons que 1 représente le rôle étudiant

        // Convertir le Set en ObservableList (requis pour ListView)
        ObservableList<User> usersList = FXCollections.observableArrayList(utilisateurs);

        // Définir les éléments de la ListView
        listView.setItems(usersList);

        // Set cell factory for custom user display
        listView.setCellFactory(new Callback<>() {
            @Override
            public ListCell<User> call(ListView<User> param) {
                return new ListCell<User>() {
                    @Override
                    protected void updateItem(User item, boolean empty) {
                        super.updateItem(item, empty);

                        if (empty || item == null) {
                            setText(null);
                        } else {
                            // Consistent format string
                            String format = "%-90s%-20s%-25s%-30s%-10d";

                            // Afficher les informations de l'utilisateur
                            setText(String.format(format, item.getNom(), item.getPrenom(), item.getEmail(), "sfsdf", 0));  // Removed password for security
                        }

                 */



        @FXML
        public void afficherEnseignant(ActionEvent event) throws SQLException {

            // Créer une instance du service utilisateur
            ServiceUser serviceUtilisateur = new ServiceUser();

            // Obtenir tous les utilisateurs ayant un rôle égal à 2
            Set<User> utilisateurs = serviceUtilisateur.getUsersByRole(2); // Remplacez 1 par 2 pour le rôle "enseignant"

            // Convertir le Set en ObservableList (requis pour ListView)
            ObservableList<User> usersList = FXCollections.observableArrayList(utilisateurs);

            // Définir les éléments de la ListView
            listView.setItems(usersList);

            // Set cell factory for custom user display
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
                                // Formatage cohérent
                                String format = "%-90s%-20s%-25s%-30s%-10d";

                                // Afficher les informations de l'utilisateur
                                setText(String.format(format, item.getNom(), item.getPrenom(), item.getEmail(), "sfsdf", 0)); // Mot de passe supprimé pour des raisons de sécurité
                            }
                        }
                    };
                }
            });
        }












        @FXML
        public void afficherEtudiant(ActionEvent event) throws SQLException {

            // Créer une instance du service utilisateur
            ServiceUser serviceUtilisateur = new ServiceUser();

            // Obtenir tous les utilisateurs ayant un rôle égal à 1
            Set<User> utilisateurs = (Set<User>) serviceUtilisateur.getUsersByRole(1); // Supposons que 1 représente le rôle étudiant

            // Convertir le Set en ObservableList (requis pour ListView)
            ObservableList<User> usersList = FXCollections.observableArrayList(utilisateurs);

            // Définir les éléments de la ListView
            listView.setItems(usersList);

            // Set cell factory for custom user display
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

                                // Afficher les informations de l'utilisateur
                                setText(String.format(format, item.getNom(), item.getPrenom(), item.getEmail(), "sfsdf", 0));  // Removed password for security
                            }
                        }
                    };
                }
            });







/*
            // Créer une instance du service utilisateur
            ServiceUser serviceUtilisateur = new ServiceUser();

            // Obtenir tous les utilisateurs ayant un rôle égal à 1
            Set<User> utilisateurs = serviceUtilisateur.findByRole(1); // Supposons que 1 représente le rôle étudiant

            // Convertir le Set en ObservableList (requis pour ListView)
            ObservableList<User> usersList = FXCollections.observableArrayList(utilisateurs);

            // Définir les éléments de la ListView
            listView.setItems(usersList);

            // Set cell factory for custom user display
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

                                // Display user information
                                setText(String.format(format, item.getNom(), item.getPrenom(), item.getEmail(), "sfsdf", 0));  // Removed password for security
                            }

 */
                        }







/*
    @FXML
    public void afficherEtudiant(ActionEvent event) throws SQLException {
        // Créer une instance du service utilisateur
        ServiceUser serviceUtilisateur = new ServiceUser();

        // Obtenir les étudiants (utilisateurs avec rôle 1)
        Set<User> etudiants = serviceUtilisateur.getEtudiants(); // Modifiez la méthode pour filtrer par rôle dans le service

        // Convertir le Set en ObservableList (requis pour ListView)
        ObservableList<User> usersList = FXCollections.observableArrayList(etudiants);

        // Définir les éléments de la ListView
        listView.setItems(usersList);

        // Set cell factory for custom user display
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

                            // Display relevant attributes, removing password
                            setText(String.format(format, item.getNom(), item.getPrenom(), item.getEmail(), "sfsdf", 0)); // Removed password
                        }
                    }
                };
            }
        });

 */










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
                                setText(String.format(format, item.getNom(), item.getPrenom(), item.getEmail(), "sfsdf", 0));  // Removed password for security
                            }
                        }
                    };

                }});
    }}