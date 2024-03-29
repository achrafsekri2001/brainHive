package edu.esprit.services;

import edu.esprit.entities.Question;


import edu.esprit.entities.Quiz;
import edu.esprit.utils.DataSource;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;


public class ServiceQuestion implements IService<Question> {
    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Question question) throws SQLException {
        try{
            String query = "INSERT INTO question (question, choix, id,reponse_correcte) VALUES (?, ?, ?,?)";
            try (PreparedStatement preparedStatement = cnx.prepareStatement(query)) {
                preparedStatement.setString(1, question.getQuestion());
                preparedStatement.setString(2, question.getChoix());
                preparedStatement.setInt(3, question.getQuiz().getId());
                preparedStatement.setInt(4, question.getReponse_correcte());

                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void modifier(Question question) throws SQLException {
        try {
            String query = "UPDATE question SET question = ?, choix = ?, id = ?, reponse_correcte = ? WHERE id = ?";
            try (PreparedStatement preparedStatement = cnx.prepareStatement(query)) {
                preparedStatement.setString(1, question.getQuestion());
                preparedStatement.setString(2, question.getChoix());
                preparedStatement.setInt(3, question.getQuiz().getId());
                preparedStatement.setInt(4, question.getReponse_correcte());
                preparedStatement.setInt(5, question.getid());

                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void supprimer(int id) throws SQLException {
        String query = "DELETE FROM `question` WHERE `id`= ?";

        try (
                PreparedStatement preparedStatement = cnx.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Set<Question> getAll() throws SQLException {
        Set<Question> questions = new HashSet<>();

        String req = "Select * from question";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
                int id = rs.getInt("id"); //wala t7ot num colomn kima eli ta7etha
                String question1 = rs.getString("question");
                String choix = rs.getString("choix");
                int quizid= rs.getInt("id");
                int repC = rs.getInt("reponse_correcte");


                ServiceQuiz QS= new ServiceQuiz();
                Quiz Q = QS. getOneByID(id);

                Question q = new Question(id,question1,choix,Q,repC);
                questions.add(q);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return questions;
    }

    @Override
    public Question getOneByID(int id) throws SQLException {try {
        String query = "SELECT * FROM question WHERE id = ?";
        try (PreparedStatement preparedStatement = cnx.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Question question = new Question();
                    question.setid(resultSet.getInt("id"));
                    question.setQuestion(resultSet.getString("question"));
                    question.setChoix(resultSet.getString("choix"));
                    question.getQuiz().setId(resultSet.getInt("id"));
                    question.setReponse_correcte(resultSet.getInt("reponse_correcte"));

                    return question;
                }
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
        return null;
    }




}

