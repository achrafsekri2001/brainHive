package edu.esprit.entities;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Post {
    private int id;
    private String title;
    private String description;
    private String matiere;
    private int groupId;
    private int userId;
    private int numberOfComments;
    public Set<Commentaire> commentaires = new HashSet<>();
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private Set<Object> fichiers = new HashSet<>();

    public Post() {

    }

    public Post(String title, String description, String matiere, int groupId, int userId, int numberOfComments, Timestamp createdAt, Timestamp updatedAt) {
        this.title = title;
        this.description = description;
        this.matiere = matiere;
        this.groupId = groupId;
        this.userId = userId;
        this.numberOfComments = numberOfComments;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Post(int id, String title, String description, String matiere, int groupId, int userId, int numberOfComments, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.matiere = matiere;
        this.groupId = groupId;
        this.userId = userId;
        this.numberOfComments = numberOfComments;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }


    public Set<Commentaire> getCommentaires() {
        return commentaires;
    }

    public void setCommentaires(Set<Commentaire> commentaires) {
        this.commentaires = commentaires;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMatiere() {
        return matiere;
    }

    public void setMatiere(String matiere) {
        this.matiere = matiere;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getNumberOfComments() {
        return numberOfComments;
    }

    public void setNumberOfComments(int numberOfComments) {
        this.numberOfComments = numberOfComments;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Set<Object> getFichiers() {
        return fichiers;
    }

    public void setFichiers(Set<Object> fichiers) {
        this.fichiers = fichiers;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", matiere='" + matiere + '\'' +
                ", groupId=" + groupId +
                ", userId=" + userId +
                ", numberOfComments=" + numberOfComments +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return id == post.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}