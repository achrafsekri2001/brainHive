package edu.esprit.entities;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Quiz {
      private int id;
      private int code;
      private String matiere;
      private Date dateDeCreation;
      private int score;
      private int dureeEnMinutes;
      private boolean disponibilitee;
    private List<Question> question;

    public Quiz() {

    }

    public Quiz(int id, int code, String matiere, Date dateDeCreation, int score, int dureeEnMinutes, boolean disponibilitee) {
        this.id = id;
        this.code = code;
        this.matiere = matiere;
        this.dateDeCreation = dateDeCreation;
        this.score = score;
        this.dureeEnMinutes = dureeEnMinutes;
        this.disponibilitee = disponibilitee;
    }

    public Quiz(int code, String matiere, Date dateDeCreation, int score, int dureeEnMinutes, boolean disponibilitee) {
        this.code = code;
        this.matiere = matiere;
        this.dateDeCreation = dateDeCreation;
        this.score = score;
        this.dureeEnMinutes = dureeEnMinutes;
        this.disponibilitee = disponibilitee;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMatiere() {
        return matiere;
    }

    public void setMatiere(String matiere) {
        this.matiere = matiere;
    }

    public Date getDateDeCreation() {
        return dateDeCreation;
    }

    public void setDateDeCreation(Date dateDeCreation) {
        this.dateDeCreation = dateDeCreation;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getDureeEnMinutes() {
        return dureeEnMinutes;
    }

    public void setDureeEnMinutes(int dureeEnMinutes) {
        this.dureeEnMinutes = dureeEnMinutes;
    }

    public boolean isDisponibilitee() {
        return disponibilitee;
    }


    public void setDisponibilitee(boolean disponibilitee) {
        this.disponibilitee = disponibilitee;
    }

    public List<Question> getQuestion() {
        return question;
    }

    public void setQuestion(List<Question> question) {
        this.question = question;
    }


    @Override
    public String toString() {
        return "Quiz{" +
                "code=" + code +
                ", matiere='" + matiere + '\'' +
                ", dateDeCreation=" + dateDeCreation +
                ", score=" + score +
                ", dureeEnMinutes=" + dureeEnMinutes +
                ", disponibilitee=" + disponibilitee +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Quiz quiz = (Quiz) o;
        return id == quiz.id && code == quiz.code;
    }
    @Override
    public int hashCode() {
        return Objects.hash(code);
    }
}


