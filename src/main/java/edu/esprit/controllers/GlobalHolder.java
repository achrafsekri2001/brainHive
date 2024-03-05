package edu.esprit.controllers;

import edu.esprit.entities.Post;

public class GlobalHolder {
    public static int currentPostId;
    public static int currentUserId;
    public static int currentCommentId;
    public static Post currentPost;

    public static int getCurrentPostId() {
        return currentPostId;
    }

    public static void setCurrentPostId(int currentPostId) {
        GlobalHolder.currentPostId = currentPostId;
    }

    public static int getCurrentUserId() {
        return currentUserId;
    }

    public static void setCurrentUserId(int currentUserId) {
        GlobalHolder.currentUserId = currentUserId;
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