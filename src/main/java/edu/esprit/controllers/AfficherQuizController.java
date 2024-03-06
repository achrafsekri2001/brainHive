package edu.esprit.controllers;

import edu.esprit.entities.Quiz;
import edu.esprit.services.ServiceQuiz;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.Set;

public class AfficherQuizController {

    @FXML
    private GridPane gridA;

    @FXML
    private ScrollPane scrollA;
    private ServiceQuiz serviceQ = new ServiceQuiz();
    Set<Quiz> setQ;

    {
        try {
            setQ = serviceQ.getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void initialize(URL url, ResourceBundle resourceBundle) {

        int column = 0;
        int row = 1;
        try {
            for (Quiz quiz : setQ) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/QuizItem.fxml"));
                HBox hbox = fxmlLoader.load();

                QuizItemController itemController = fxmlLoader.getController();
                itemController.setData(quiz);

                if (column == 1) {
                    column = 0;
                    row++;
                }

                gridA.add(hbox, column++, row);
                gridA.setMinWidth(Region.USE_COMPUTED_SIZE);
                gridA.setPrefWidth(Region.USE_COMPUTED_SIZE);
                gridA.setMaxWidth(Region.USE_PREF_SIZE);
                gridA.setMinHeight(Region.USE_COMPUTED_SIZE);
                gridA.setPrefHeight(Region.USE_COMPUTED_SIZE);
                gridA.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(hbox, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void navigateToAjouter(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AfficherQuestion.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) gridA.getScene().getWindow(); // Utilisez la même fenêtre (Stage) actuelle
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.sizeToScene(); // Redimensionne le stage pour s'adapter à la taille de la scène
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void navigateToafficherQuestions(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AjouterQuiz.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) gridA.getScene().getWindow(); // Utilisez la même fenêtre (Stage) actuelle
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.sizeToScene(); // Redimensionne le stage pour s'adapter à la taille de la scène
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
