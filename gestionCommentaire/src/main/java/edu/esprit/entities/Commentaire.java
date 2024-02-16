package edu.esprit.entities;

import java.sql.Timestamp;
import java.util.Objects;

public class Commentaire {
    private int id;
    private String content;
    private String postId;
    private String userId;
    private String parentComment;
    private int upVotes;
    private int downVotes;
    private boolean isverified;
    private int numberOfReports;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public Commentaire() {
        this.content = content;
        this.postId = postId;
        this.userId = userId;
        this.parentComment = parentComment;
        this.upVotes = upVotes;
        this.downVotes = downVotes;
        this.isverified = isverified;
        this.numberOfReports = numberOfReports;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getParentComment() {
        return parentComment;
    }

    public void setParentComment(String parentComment) {
        this.parentComment = parentComment;
    }

    public int getUpVotes() {
        return upVotes;
    }

    public void setUpVotes(int upVotes) {
        this.upVotes = upVotes;
    }

    public int getDownVotes() {
        return downVotes;
    }

    public void setDownVotes(int downVotes) {
        this.downVotes = downVotes;
    }

    public boolean isVerified() {
        return isverified;
    }

    public void setVerified(boolean verified) {
        this.isverified = verified;
    }

    public int getNumberOfReports() {
        return numberOfReports;
    }

    public void setNumberOfReports(int numberOfReports) {
        this.numberOfReports = numberOfReports;
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
    public String toString() {
        return "Commentaire{" +
                ", content='" + content + '\'' +
                ", postId='" + postId + '\'' +
                ", userId='" + userId + '\'' +
                ", parentComment='" + parentComment + '\'' +
                ", upVotes=" + upVotes +
                ", downVotes=" + downVotes +
                ", isverified=" + isverified +
                ", numberOfReports=" + numberOfReports +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
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
}


