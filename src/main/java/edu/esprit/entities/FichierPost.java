package edu.esprit.entities;

import java.util.Objects;

public class FichierPost {
    private int id;
    private int postId;
    private String fileLink;

    public FichierPost(){

    }

    public FichierPost(int postId, String fileLink) {
        this.postId = postId;
        this.fileLink = fileLink;
    }

    public FichierPost(int id, int postId, String fileLink) {
        this.id = id;
        this.postId = postId;
        this.fileLink = fileLink;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getFileLink() {
        return fileLink;
    }

    public void setFileLink(String fileLink) {
        this.fileLink = fileLink;
    }

    @Override
    public String toString() {
        return "FichierPost{" +
                "id=" + id +
                ", postId=" + postId +
                ", fileLink='" + fileLink + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FichierPost that = (FichierPost) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}