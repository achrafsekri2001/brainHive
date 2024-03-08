package edu.esprit.controllers;

import edu.esprit.entities.Question;
import edu.esprit.entities.Quiz;
import edu.esprit.services.ServiceQuestion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class QuizDispoItemController {

    @FXML
    private Text LabelDispo;

    @FXML
    private Text LabelDuree;

    @FXML
    private Text LabelMatiere;

    @FXML
    private Text LabelScore;

    @FXML
    private Button btnPasser;

    @FXML
    private Text labelCode;

    @FXML
    private Text labelDatedecreation;
    ServiceQuestion qs=new ServiceQuestion();

    @FXML
    void PasserQuizOnClick(ActionEvent event) throws SQLException {
        // Obtenez le code du quiz à partir du Text (convertissez-le en int si nécessaire)
        int code = Integer.parseInt(labelCode.getText());

        // Appelez la méthode pour obtenir les questions, choix et réponses correctes
        List<Map<String, Object>> questionsData = qs.getQuestionsForQuiz(code);

        // Créez une liste de questions
        List<Question> questions = new ArrayList<>();

        // Maintenant, questionsData contient les données nécessaires pour chaque question du quiz
        // Vous pouvez traiter ces données selon vos besoins
        for (Map<String, Object> questionData : questionsData) {
            String questionText = (String) questionData.get("question");
            String choix = (String) questionData.get("choix");
            int reponseCorrecte = (int) questionData.get("reponse_correcte");
            Quiz Q = new Quiz();
            Q.setCode(code);

            // Créez un objet Question avec les données récupérées
            Question question = new Question(questionText, choix, Q, reponseCorrecte);
            questions.add(question);
        }

        // Affichez les questions dans la vue QuizToDo
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/QuizToDo.fxml"));
        Parent root;
        try {
            root = loader.load();
            PasserQuizController passerQuizController = loader.getController();

            // Initialisez et démarrez le chronomètre avec une durée en minutes (6 minutes pour l'exemple)
            int dureeQuizEnMinutes = 10;

            PasserQuizController ps = new PasserQuizController();
                   ps .initialiserChronometre(dureeQuizEnMinutes);

             ps.displayQuestions(questions);

            // Créez une nouvelle scène avec la vue QuizToDo
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Quiz To Do");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }}}










