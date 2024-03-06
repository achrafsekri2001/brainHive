package edu.esprit.controllers;

import edu.esprit.entities.Quiz;
import edu.esprit.services.ServiceQuiz;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Optional;

public class QuizItemController {

    @FXML
    private Text LabelDispo;

    @FXML
    private Text LabelDuree;

    @FXML
    private Text LabelMatiere;

    @FXML
    private Text LabelScore;

    @FXML
    private Button btnModifier;

    @FXML
    private Button btnSupprimer;

    @FXML
    private Text labelCode;

    @FXML
    private Text labelDatedecreation;
    private Quiz quiz;
    private ServiceQuiz qs;
    public void setData(Quiz quiz) {
        this.quiz = quiz;
        LabelDispo.setText(String.valueOf(quiz.isDisponibilitee()));
        LabelDuree.setText(String.valueOf(quiz.getDureeEnMinutes()));
        labelCode.setText(String.valueOf(quiz.getCode()));
        LabelScore.setText(String.valueOf(quiz.getScore()));
        LabelMatiere.setText(quiz.getMatiere());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(quiz.getDateDeCreation());
        labelDatedecreation.setText(dateString);
    }
    public void actualiserVueQuiz() {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Fxml/AfficherQuiz.fxml"));
            LabelScore.getScene().setRoot(root);
        } catch (IOException e) {

            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setContentText("Une erreur s'est produite lors de la redirection.");
            errorAlert.setTitle("Erreur de redirection");
            errorAlert.show();
        }
    }
    @FXML
    void modifierQuizOnClick(ActionEvent event) throws SQLException {
        if (quiz != null) {
            Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationAlert.setTitle("Confirmation de suppression");
            confirmationAlert.setHeaderText(null);
            confirmationAlert.setContentText("Êtes-vous sûr de vouloir supprimer ce quiz ?");

            Optional<ButtonType> result = confirmationAlert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                if (qs == null) {
                    qs = new ServiceQuiz();
                }
                qs.supprimer(quiz.getId());
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Quiz a été supprimé avec succès.");
                alert.setTitle("Quiz supprimé");
                alert.show();
                actualiserVueQuiz();
            }
        } else {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setContentText("Impossible de supprimer le quiz car aucun quiz n'est sélectionnée.");
            errorAlert.setTitle("Erreur de suppression");
            errorAlert.show();
        }

    }

    @FXML
    void supprimerQuizOnClick(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/ModifierQuiz.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) labelCode.getScene().getWindow(); // Utilisez la même fenêtre (Stage) actuelle
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.sizeToScene(); // Redimensionne le stage pour s'adapter à la taille de la scène
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
