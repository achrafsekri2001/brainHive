package edu.esprit.controllers;

import edu.esprit.entities.Question;
import edu.esprit.entities.Quiz;
import edu.esprit.services.ServiceQuestion;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import edu.esprit.services.ServiceQuiz;
import edu.esprit.utils.DataSource;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class AjouterQuestionController {


    @FXML
    private ComboBox<Integer> TFcode;

    @FXML
    private TextField TFchoix;

    @FXML
    private TextField TFquestion;
    @FXML
    private TextField TFreponse_correcte;

    private final ServiceQuestion qs = new ServiceQuestion();

    private final ServiceQuiz ServiceQuiz = new ServiceQuiz();

    @FXML
    public void initialize() {
        List<Integer> codesQuiz = ServiceQuiz.getQuizcodes();
        ObservableList<Integer> observableCodesQuiz = FXCollections.observableArrayList(codesQuiz);
        TFcode.setItems(observableCodesQuiz);
    }





    @FXML
    void AjouterQuestionAction(ActionEvent event) throws SQLException {
        try {
            // Réinitialiser le style des TextFields
            resetTextFieldStyles();

            // Vérification de la question et des choix non vides
            String question = TFquestion.getText();
            String choix = TFchoix.getText();

            if (question.isEmpty()) {
                setTextFieldErrorStyle(TFquestion);
                showAlert("Erreur de saisie", "Veuillez saisir une question.");
                return;
            }
            if (choix.isEmpty() || !isValidChoiceFormat(choix)) {
                setTextFieldErrorStyle(TFchoix);
                showAlert("Erreur de saisie", "Veuillez saisir des choix sous la forme 1)...2)...3)...");
                return;
            }

            // Vérification de la réponse correcte
            try {
                int reponseCorrecte = Integer.parseInt(TFreponse_correcte.getText());

                if (reponseCorrecte < 1 || reponseCorrecte > 3) {
                    setTextFieldErrorStyle(TFreponse_correcte);
                    showAlert("Erreur de saisie", "La réponse correcte doit être entre 1 et 3.");
                    return;
                }

                // Récupérer l'identifiant du quiz
                int id_quiz = qs.getidByCode(TFcode.getValue());
                System.out.println(TFcode.getValue());
                System.out.println(id_quiz);
                if (id_quiz != -1) {
                    // Récupérer le quiz

                    if (ServiceQuiz.getOneByID(id_quiz) != null) {
                        // Ajouter la question au quiz
                        ServiceQuestion serviceQuestion = new ServiceQuestion();
                        serviceQuestion.ajouter(new Question(question, choix, ServiceQuiz.getOneByID(id_quiz), reponseCorrecte));
                        showAlert("Succès", "La question a été ajoutée avec succès.");
                    } else {
                        showAlert("Avertissement", "Impossible de trouver le quiz pour le code sélectionné.");
                    }
                } else {
                    showAlert("Avertissement", "Impossible de trouver l'identifiant du quiz pour le code sélectionné.");
                }

            } catch (NumberFormatException e) {
                setTextFieldErrorStyle(TFreponse_correcte);
                showAlert("Erreur de saisie", "Veuillez saisir une réponse correcte valide (nombre entier).");
            }

        } catch (Exception e) {
            e.printStackTrace();
            showAlert("GHALTA", e.getMessage());
        }
    }


    private boolean isValidChoiceFormat(String choix) {
        // Le format attendu est "1)..... 2)...... 3)......"
        return choix.matches("^\\d+\\)\\s*.*\\d+\\)\\s*.*\\d+\\)\\s*.*$");
    }





    // Méthode pour réinitialiser le style des TextFields
    private void resetTextFieldStyles() {
        TFquestion.setStyle(null);
        TFchoix.setStyle(null);
        TFcode.setStyle(null);
        TFreponse_correcte.setStyle(null);
    }

    // Méthode pour définir le style d'erreur sur un TextField
    private void setTextFieldErrorStyle(TextField textField) {
        textField.getStyleClass().add("error-text-field");
    }

// Le reste du code reste inchangé

    private void showAlert(String title, String content) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }








    public void navigateToAfficher(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/AfficherQuestion.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) TFchoix.getScene().getWindow(); // Utilisez la même fenêtre (Stage) actuelle
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.sizeToScene(); // Redimensionne le stage pour s'adapter à la taille de la scène
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}





