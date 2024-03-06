package edu.esprit.controllers;

import edu.esprit.entities.Post;
import edu.esprit.entities.User;

public class GlobalHolder {
    public static int currentPostId;
    public static User currentUser;
    public static int currentCommentId;
    public static Post currentPost;
    public static String currentMatiere;
    public static String textSearch;

    public static String getTextSearch() {
        return textSearch;
    }

    public static void setTextSearch(String textSearch) {
        GlobalHolder.textSearch = textSearch;
    }

    public static String getCurrentMatiere() {
        return currentMatiere;
    }

    public static void setCurrentMatiere(String currentMatiere) {
        GlobalHolder.currentMatiere = currentMatiere;
    }

    public static int getCurrentPostId() {
        return currentPostId;
    }

    public static void setCurrentPostId(int currentPostId) {
        GlobalHolder.currentPostId = currentPostId;
    }

    public static User getcurrentUser() {
        return currentUser;
    }

    public static void setcurrentUser(User currentUser) {
        GlobalHolder.currentUser = currentUser;
    }

    public static int getCurrentCommentId() {
        return currentCommentId;
    }

    public static void setCurrentCommentId(int currentCommentId) {
        GlobalHolder.currentCommentId = currentCommentId;
    }

    public static Post getCurrentPost() {
        return currentPost;
    }

    public void setCurrentPost(Post currentPost) {
        this.currentPost = currentPost;
    }
}
