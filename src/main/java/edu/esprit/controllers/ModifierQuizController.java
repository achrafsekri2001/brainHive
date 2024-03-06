package edu.esprit.controllers;

import edu.esprit.entities.Quiz;
import edu.esprit.services.ServiceQuiz;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class ModifierQuizController implements Initializable {

    @FXML
    private ChoiceBox<String> TFMatiere1;

    @FXML
    private TextField TFCode1;

    @FXML
    private TextField TFDate1;

    @FXML
    private TextField TFDuree1;

    @FXML
    private CheckBox TFFalse1;

    @FXML
    private TextField TFScore1;

    @FXML
    private CheckBox TFtrue1;
    private ServiceQuiz serviceQuiz = new ServiceQuiz();
    private String[] matiere = {"Arabe","Anglais","Allemand","Economie","Espagnole","Français","Gestion","Histoire et géographie","Informatique","Italien","Mathématique","Philosophie","Physique et chimie","Sciences de la vie et de la terre","Technique"};
    private Quiz quiz;


    @FXML
    void ModifierQuizAction(ActionEvent event) throws SQLException {
        String matiere = TFMatiere1.getValue();
        Integer Code_quiz = Integer.valueOf(TFCode1.getText());
        String dateString = TFDate1.getText();
        String dureeEnMinutesStr = TFDuree1.getText();
        String score = TFScore1.getText();
        // Vérifier si la matière est sélectionnée et si tous les champs sont remplis
        if (matiere == null || matiere.isEmpty() || Code_quiz == null || dateString.isEmpty() || dureeEnMinutesStr.isEmpty() ||
                score.isEmpty() ) {
            // Afficher une alerte si au moins un champ est vide
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Champs vides");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs.");
            alert.showAndWait();
             return;
        }
        Quiz nouveauQuiz = new Quiz(quiz.getCode(),quiz.getMatiere(),quiz.getDateDeCreation(),quiz.getScore(), quiz.getDureeEnMinutes(), quiz.isDisponibilitee());


            serviceQuiz.modifier(nouveauQuiz);
            // Afficher un message de succès
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succès");
            alert.setHeaderText(null);
            alert.setContentText("Le parascolaire a été modifié avec succès.");
            alert.showAndWait();

        }

    @FXML
    void navigateToAfficher(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AfficherQuiz.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) TFCode1.getScene().getWindow(); // Utilisez la même fenêtre (Stage) actuelle
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.sizeToScene(); // Redimensionne le stage pour s'adapter à la taille de la scène
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TFMatiere1.getItems().addAll(matiere);
        TFMatiere1.setOnAction(this::getmatiere);

        serviceQuiz= new ServiceQuiz();
    }
    private void getmatiere(ActionEvent event) {
        String maMatiere =  TFMatiere1.getValue();
    }
    public void initData(Quiz quiz) {
        this.quiz = quiz;
        TFCode1.setText(String.valueOf(quiz.getCode()));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(quiz.getDateDeCreation());
        TFDate1.setText(dateString);
        TFScore1.setText(String.valueOf(quiz.getScore()));
        TFDuree1.setText(String.valueOf(quiz.getDureeEnMinutes()));
        TFMatiere1.getSelectionModel().select(quiz.getMatiere());
}

}
