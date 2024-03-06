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
    private ComboBox<Integer> TFcode_quiz;

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
        TFcode_quiz.setItems(observableCodesQuiz);
    }





    @FXML
    void AjouterQuestionAction(ActionEvent event) throws SQLException {
        try {
            // Réinitialiser le style des TextFields
            resetTextFieldStyles();

            // Vérification de la question et des choix non vides
            String question = TFquestion.getText();
            String choix = TFchoix.getText();

            if (question.isEmpty()) /*|| choix.isEmpty()||!isValidChoiceFormat(choix))*/  {
                // Afficher un message d'erreur
                setTextFieldErrorStyle(TFquestion);

                showAlert("Erreur de saisie", "Veuillez saisir une question .");
                return;
            }
            if (choix.isEmpty()) /*|| choix.isEmpty()||!isValidChoiceFormat(choix))*/  {
                // Afficher un message d'erreur

                setTextFieldErrorStyle(TFchoix);
                showAlert("Erreur de saisie", "Veuillez saisir des choix.");
                return;
            }
            if (!isValidChoiceFormat(choix)) /*|| choix.isEmpty()||!isValidChoiceFormat(choix))*/  {
                // Afficher un message d'erreur

                setTextFieldErrorStyle(TFchoix);
                showAlert("Erreur de saisie", "Veuillez saisir des choix sous la forme 1)...2)...3)...");
                return;
            }


            // Vérification de la réponse correcte
            try {
                int reponseCorrecte = Integer.parseInt(TFreponse_correcte.getText());

                if (reponseCorrecte < 1 || reponseCorrecte > 3) {
                    // Afficher un message d'erreur
                    setTextFieldErrorStyle(TFreponse_correcte);
                    showAlert("Erreur de saisie", "La réponse correcte doit être entre 1 et 3.");
                    return;
                }

                ServiceQuiz qs1 = new ServiceQuiz();
                int id = getidByCode(TFcode_quiz.getValue());

                if (id != -1) {
                    qs.ajouter(new Question(question, choix, qs1.getOneByID(id), reponseCorrecte));
                    showAlert("Success", "GG");
                } else {
                    showAlert("Warning", "Impossible de trouver l'id_quiz pour le code_quiz sélectionné.");
                }

            } catch (NumberFormatException e) {
                // Afficher un message d'erreur
                setTextFieldErrorStyle(TFreponse_correcte);
                showAlert("Erreur de saisie", "Veuillez saisir une réponse correcte valide (nombre entier).");
            }

        } catch (Exception e) {
            // Gérer les autres exceptions
            e.printStackTrace();
            showAlert("SQL Exception", e.getMessage());
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
        TFcode_quiz.setStyle(null);
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





    public int getidByCode(Integer codeQuiz) throws SQLException {

        Connection connection = DataSource.getInstance().getCnx();

        try {
            String sql = "SELECT id FROM quiz WHERE code_quiz = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, codeQuiz);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getInt("id");
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return -1;
    }
    public void navigateToAfficher(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AfficherQuestion.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) TFcode_quiz.getScene().getWindow(); // Utilisez la même fenêtre (Stage) actuelle
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.sizeToScene(); // Redimensionne le stage pour s'adapter à la taille de la scène
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}





