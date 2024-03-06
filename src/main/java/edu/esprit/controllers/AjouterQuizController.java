package edu.esprit.controllers;

import edu.esprit.entities.Quiz;
import edu.esprit.services.ServiceQuiz;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class AjouterQuizController implements Initializable {


    @FXML
    private TextField TFCode_quiz;

    @FXML
    private TextField TFDatedecreation;

    @FXML
    private TextField TFDureeenminutes;

    @FXML
    private CheckBox TFFalse;

    @FXML
    private TextField TFScore;

    @FXML
    private CheckBox TFtrue;

    @FXML
    private ChoiceBox<String> TFmatiere;

    private String[] matieres = {"Arabe", "Anglais", "Allemand", "Economie", "Espagnole", "Français", "Gestion", "Histoire et géographie", "Informatique", "Italien", "Mathématique", "Philosophie", "Physique et chimie", "Sciences de la vie et de la terre", "Technique"};

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TFmatiere.getItems().addAll(matieres);
        TFmatiere.setOnAction(this::getmatieres);
    }
    private void getmatieres(ActionEvent event) {
        String maMatiere = TFmatiere.getValue();
    }
    @FXML
    void ajouterAction(ActionEvent event) {
        ServiceQuiz serviceQuiz = new ServiceQuiz();
        String matiere = TFmatiere.getValue();
        Integer Code = Integer.valueOf(TFCode_quiz.getText());
        String dateString = TFDatedecreation.getText();
        String dureeEnMinutesStr = TFDureeenminutes.getText();
        String score = TFScore.getText();

        // Vérifier si la matière est sélectionnée et si tous les champs sont remplis
        if (matiere == null || matiere.isEmpty() || Code == null || dateString.isEmpty() || dureeEnMinutesStr.isEmpty() ||
                score.isEmpty() ) {
            // Afficher une alerte si au moins un champ est vide
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Champs vides");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs.");
            alert.showAndWait();
            return; // Arrêter l'exécution de la méthode si au moins un champ est vide
        }

        int dureeEnMinutes = Integer.parseInt(dureeEnMinutesStr);
        int scoreMaximum = Integer.parseInt(score);
        boolean disponibilitee = TFtrue.isSelected();
        boolean falseValue = TFFalse.isSelected();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date dateDeCreation = null;
        try {
            dateDeCreation = formatter.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return; // Arrêter l'exécution de la méthode si la date n'est pas valide
        }

        Quiz quiz = new Quiz();
        quiz.setMatiere(matiere);
        quiz.setCode(Code);
        quiz.setDateDeCreation(dateDeCreation);
        quiz.setDureeEnMinutes(dureeEnMinutes);
        quiz.setScore(scoreMaximum);
        quiz.setDisponibilitee(disponibilitee);

        try {
            serviceQuiz.ajouter(quiz);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }






    public void navigatetoAfficherQuizActino(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/AfficherQuiz.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);

        Stage currentStage = (Stage) TFmatiere.getScene().getWindow();

        currentStage.setScene(scene);
        currentStage.show();
    }}



