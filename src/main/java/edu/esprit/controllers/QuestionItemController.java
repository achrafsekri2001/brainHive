package edu.esprit.controllers;

import edu.esprit.entities.Question;
import edu.esprit.services.ServiceQuestion;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

public class QuestionItemController {
    @FXML
    private Text LabelChoix;

    @FXML
    private Text LabelReponseCorrecte;

    @FXML
    private Text labelCodeQuiz;

    @FXML
    private Text labelQuestion;


    private Question question;

    private ServiceQuestion qs;

    public void setData(Question question) {
        this.question = question;
        labelQuestion.setText(question.getQuestion());
        LabelChoix.setText(question.getChoix());
        labelCodeQuiz.setText(String.valueOf(question.getQuiz().getCode()));
        LabelReponseCorrecte.setText(String.valueOf(question.getReponse_correcte()));


    }

    @FXML
    void supprimerQuestionOnClick() throws SQLException {
        if (question != null) {
            Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationAlert.setTitle("Confirmation de suppression");
            confirmationAlert.setHeaderText(null);
            confirmationAlert.setContentText("Êtes-vous sûr de vouloir supprimer cette question ?");

            Optional<ButtonType> result = confirmationAlert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                if (qs == null) {
                    qs = new ServiceQuestion();
                }
                qs.supprimer(question.getid());
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Question a été supprimée avec succès.");
                alert.setTitle("Question supprimé");
                alert.show();
                actualiserVueQuestions();
            }
        } else {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setContentText("Impossible de supprimer la question car aucune question n'est sélectionnée.");
            errorAlert.setTitle("Erreur de suppression");
            errorAlert.show();
        }
    }










   /* void modifierQuestionOnClick(ActionEvent event) {
        try {
            // Initialiser le serviceEvenement si ce n'est pas déjà fait
            if (qs == null) {
                qs = new ServiceQuestion();
            }

            // Charger la vue de modification d'événement
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ModifierEvent.fxml"));
            Parent root = loader.load();

            // Passer l'événement à modifier au contrôleur de modification
            ModifierQuestion controller = loader.getController();
            controller.setData(question);

            // Afficher la vue de modification d'événement
            labelCodeQuiz.getScene().setRoot(root);
        } catch (IOException e) {
            // Gérer les exceptions liées au chargement de la vue de modification
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Erreur lors de la modification de l'événement.");
            alert.setTitle("Erreur de modification");
            alert.show();
        }
    }*/



    public void actualiserVueQuestions() {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Fxml/AfficherQuestion.fxml"));
            LabelReponseCorrecte.getScene().setRoot(root);
        } catch (IOException e) {

            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setContentText("Une erreur s'est produite lors de la redirection.");
            errorAlert.setTitle("Erreur de redirection");
            errorAlert.show();
        }
    }
    public void modifierQuestionOnClick(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/ModifierQuestion.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) labelCodeQuiz.getScene().getWindow(); // Utilisez la même fenêtre (Stage) actuelle
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.sizeToScene(); // Redimensionne le stage pour s'adapter à la taille de la scène
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
