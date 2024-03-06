package edu.esprit.services;

import edu.esprit.controllers.GlobalHolder;
import edu.esprit.entities.Question;
import edu.esprit.entities.Quiz;
import edu.esprit.utils.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ServiceQuiz implements IService<Quiz> {
    Connection cnx = DataSource.getInstance().getCnx();


    @Override
    public void ajouter(Quiz quiz) throws SQLException {
        String req = "INSERT INTO `quiz`(`code`, `matiere`, `dateCreation`, `score`, `dureeEnMinutes`, `disponibilitee`,`userId`) VALUES (?,?,?,?,?,?,?)";
        try {
            PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(1, quiz.getCode());
            st.setString(2, quiz.getMatiere());
            java.util.Date utilDate = quiz.getDateDeCreation();
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            st.setDate(3, sqlDate);
            st.setInt(4, quiz.getScore());
            st.setInt(5, quiz.getDureeEnMinutes());
            st.setBoolean(6, quiz.isDisponibilitee());
            st.setInt(7, GlobalHolder.getcurrentUser().getId());
            st.executeUpdate();
            System.out.println("Quiz added !");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void modifier(Quiz quiz) {
        String req = "UPDATE `quiz` SET `code`=?, `matiere`=?, `dateCreation`=?, `score`=?, `dureeEnMinutes`=?, `disponibilitee`=? WHERE `id`=?";
        try (PreparedStatement ps = cnx.prepareStatement(req)) {
            ps.setInt(1, quiz.getCode());
            ps.setString(2, quiz.getMatiere());
            java.util.Date utilDate = quiz.getDateDeCreation();
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            ps.setDate(3, sqlDate);
            ps.setInt(4, quiz.getScore());
            ps.setInt(5, quiz.getDureeEnMinutes());
            ps.setBoolean(6, quiz.isDisponibilitee());
            ps.setInt(7, quiz.getId());
            ps.executeUpdate();
            System.out.println("Quiz modified !");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void supprimer(int id) {
        try {
            String query = "DELETE FROM quiz WHERE id = ?";
            try (PreparedStatement preparedStatement = cnx.prepareStatement(query)) {
                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Set<Quiz> getAll() {
        Set<Quiz> quizSet = new HashSet<>();

        try {
            String query = "SELECT * FROM quiz where userId = ?";
            PreparedStatement preparedStatement = cnx.prepareStatement(query);
            preparedStatement.setInt(1, GlobalHolder.getcurrentUser().getId());
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Quiz quiz = new Quiz();
                    quiz.setId(resultSet.getInt("id"));
                    quiz.setCode(resultSet.getInt("code"));
                    quiz.setMatiere(resultSet.getString("matiere"));
                    quiz.setDateDeCreation(resultSet.getDate("dateDeCreation"));
                    quiz.setScore(resultSet.getInt("score"));
                    quiz.setDureeEnMinutes(resultSet.getInt("dureeEnMinutes"));
                    quiz.setDisponibilitee(resultSet.getBoolean("disponibilitee"));
                    quizSet.add(quiz);
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return quizSet;
    }

    @Override
    public Quiz getOneByID(int id) {
        try {
            String query = "SELECT * FROM quiz WHERE id = ? and userId = ?";
            try (PreparedStatement preparedStatement = cnx.prepareStatement(query)) {
                preparedStatement.setInt(1, id);
                preparedStatement.setInt(2, GlobalHolder.getcurrentUser().getId());
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        Quiz quiz = new Quiz();
                        quiz.setId(resultSet.getInt("id"));
                        quiz.setCode(resultSet.getInt("code"));
                        quiz.setMatiere(resultSet.getString("matiere"));
                        quiz.setDateDeCreation(resultSet.getDate("dateDeCreation"));
                        quiz.setScore(resultSet.getInt("score"));
                        quiz.setDureeEnMinutes(resultSet.getInt("dureeEnMinutes"));
                        quiz.setDisponibilitee(resultSet.getBoolean("disponibilitee"));
                        return quiz;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public List<Integer> getQuizcodes() {
        List<Integer> quizcodes = new ArrayList<>();
        try {
            String sql = "SELECT code FROM quiz";
            try (PreparedStatement preparedStatement = cnx.prepareStatement(sql)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        int codeQuiz = resultSet.getInt("code");
                        quizcodes.add(codeQuiz);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return quizcodes;
    }

    public List<Question> getQuestionsByCodeQuiz(int codeQuiz) {
        List<Question> questions = new ArrayList<>();
        try {
            int idQuiz = getIdQuizByCode(codeQuiz);
            String query = "SELECT * FROM question WHERE id = ?";
            try (PreparedStatement preparedStatement = cnx.prepareStatement(query)) {
                preparedStatement.setInt(1, idQuiz);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        Question question = new Question();
                        question.setid(resultSet.getInt("id"));
                        question.setQuestion(resultSet.getString("question"));
                        question.setChoix(resultSet.getString("choix"));
                        // ...
                        question.setReponse_correcte(resultSet.getInt("reponse_correcte"));
                        questions.add(question);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return questions;
    }

    public int getIdQuizByCode(Integer codeQuiz) {
        try {
            String sql = "SELECT id FROM quiz WHERE code = ?";
            try (PreparedStatement preparedStatement = cnx.prepareStatement(sql)) {
                preparedStatement.setInt(1, codeQuiz);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getInt("id");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // ou une autre valeur de retour pour indiquer l'absence de correspondance
    }

    // get quiz where quistion is not null
    public Set<Quiz> getQuizWithQuestions() {
        Set<Quiz> quizSet = new HashSet<>();
        try {
            String query = "SELECT * FROM quiz WHERE id IN (SELECT id FROM question)";
            try (PreparedStatement preparedStatement = cnx.prepareStatement(query)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        Quiz quiz = new Quiz();
                        quiz.setId(resultSet.getInt("id"));
                        quiz.setCode(resultSet.getInt("code"));
                        quiz.setMatiere(resultSet.getString("matiere"));
                        quiz.setDateDeCreation(resultSet.getDate("dateCreation"));
                        quiz.setScore(resultSet.getInt("score"));
                        quiz.setDureeEnMinutes(resultSet.getInt("dureeEnMinutes"));
                        quiz.setDisponibilitee(resultSet.getBoolean("disponibilitee"));
                        quizSet.add(quiz);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return quizSet;
    }
}
