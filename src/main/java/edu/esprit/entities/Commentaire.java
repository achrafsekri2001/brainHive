package edu.esprit.entities;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Commentaire {
    private int id;
    private String content;
    private Post post;
    private int userId;
    public int nbreVotes;
    public int nbreUpVotes;
    public int nbreDownVotes;
    public Set<Object> Fichier = new HashSet<>();
    private Commentaire parent;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public Commentaire(String content, Post post, int userId, int nbreVotes, int nbreUpVotes, int nbreDownVotes, Set<Object> fichier, Commentaire parent, Timestamp createdAt, Timestamp updatedAt) {
        this.content = content;
        this.post = post;
        this.userId = userId;
        this.nbreVotes = nbreVotes;
        this.nbreUpVotes = nbreUpVotes;
        this.nbreDownVotes = nbreDownVotes;
        Fichier = fichier;
        this.parent = parent;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Commentaire(int id, String content, Post post, int userId, int nbreVotes, int nbreUpVotes, int nbreDownVotes, Set<Object> fichier, Commentaire parent, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.content = content;
        this.post = post;
        this.userId = userId;
        this.nbreVotes = nbreVotes;
        this.nbreUpVotes = nbreUpVotes;
        this.nbreDownVotes = nbreDownVotes;
        Fichier = fichier;
        this.parent = parent;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Commentaire() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getNbreVotes() {
        return nbreVotes;
    }

    public void setNbreVotes(int nbreVotes) {
        this.nbreVotes = nbreVotes;
    }

    public int getNbreUpVotes() {
        return nbreUpVotes;
    }

    public void setNbreUpVotes(int nbreUpVotes) {
        this.nbreUpVotes = nbreUpVotes;
    }

    public int getNbreDownVotes() {
        return nbreDownVotes;
    }

    public void setNbreDownVotes(int nbreDownVotes) {
        this.nbreDownVotes = nbreDownVotes;
    }

    public Set<Object> getFichier() {
        return Fichier;
    }

    public void setFichier(Set<Object> fichier) {
        Fichier = fichier;
    }

    public Commentaire getParent() {
        return parent;
    }

    public void setParent(Commentaire parent) {
        this.parent = parent;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Commentaire that = (Commentaire) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Commentaire{" +
                "content='" + content + '\'' +
                ", post=" + post +
                ", userId=" + userId +
                ", nbreVotes=" + nbreVotes +
                ", nbreUpVotes=" + nbreUpVotes +
                ", nbreDownVotes=" + nbreDownVotes +
                ", Fichier=" + Fichier +
                ", parent=" + parent +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
