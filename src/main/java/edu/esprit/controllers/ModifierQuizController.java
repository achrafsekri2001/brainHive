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
    public void setData(Quiz quiz) {
        this.quiz = quiz;
        TFCode1.setText(String.valueOf(quiz.getCode()));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(quiz.getDateCreation());
        TFDate1.setText(dateString);
        TFScore1.setText(String.valueOf(quiz.getScore()));
        TFDuree1.setText(String.valueOf(quiz.getDureeEnMinutes()));
        TFMatiere1.getSelectionModel().select(quiz.getMatiere());
    }



    @FXML
    void ModifierQuizAction(ActionEvent event) throws SQLException {
        if (quiz == null) {
            // Handle the case where quiz is null, perhaps by showing an error message
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("L'objet Quiz n'a pas été initialisé.");
            alert.showAndWait();
            return;
        }
        Quiz nouveauQuiz = new Quiz(quiz.getCode(),quiz.getMatiere(),quiz.getDateCreation(),quiz.getScore(), quiz.getDureeEnMinutes(), quiz.isDisponibilitee());


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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/AfficherQuiz.fxml"));
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


}
