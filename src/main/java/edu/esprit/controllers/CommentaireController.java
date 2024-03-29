package edu.esprit.controllers;

import edu.esprit.entities.Commentaire;
import edu.esprit.services.CommentaireService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.io.IOException;

public class CommentaireController {
    private final CommentaireService commentaireService = new CommentaireService();
    @FXML
    private Text CommentCreator = new Text();
    @FXML
    private TextFlow commentContent = new TextFlow();
    @FXML
    private Label CommentDate = new Label();
    @FXML
    private Label commentVotes = new Label();
    @FXML
    private Button upVote = new Button();
    @FXML
    private Button downVote = new Button();
    @FXML
    private Button deleteComment = new Button();
    @FXML
    private Button editComment = new Button();


    public Commentaire commentaire;

    public void setData(Commentaire comment) {
        this.commentaire = comment;

    }

    public void initialize() {
        System.out.println("comment" + commentaire);
        CommentCreator.setText("Achraf Sekri");
        commentContent.getChildren().add(new Text(commentaire.getContent()));
        CommentDate.setText(commentaire.getCreatedAt().toString());
        commentVotes.setText(String.valueOf(commentaire.getNbreVotes()));

        // setting voting colors
        if (commentaire.getVoted() == 1) {
            upVote.setStyle("-fx-background-color: #00ff00");
        } else if (commentaire.getVoted() == 2) {
            downVote.setStyle("-fx-background-color: #ff0000");
        }

        upVote.setOnAction(event -> {
            if (commentaire.getVoted() == 1) {
                commentaireService.cancelVote(commentaire);
                commentaire.setVoted(0);
                commentaire.setNbreVotes(commentaire.getNbreVotes() - 1);
                commentVotes.setText(String.valueOf(commentaire.getNbreVotes()));
            } else {
                commentaireService.upVote(commentaire);
                commentaire.setVoted(1);
                commentaire.setNbreVotes(commentaire.getNbreVotes() + 1);
                commentVotes.setText(String.valueOf(commentaire.getNbreVotes()));
            }
            refreshState();
        });

        downVote.setOnAction(event -> {
            if (commentaire.getVoted() == 2) {
                commentaireService.cancelVote(commentaire);
                commentaire.setVoted(0);
                commentaire.setNbreVotes(commentaire.getNbreVotes() + 1);
                commentVotes.setText(String.valueOf(commentaire.getNbreVotes()));
            } else {
                commentaireService.downVote(commentaire);
                commentaire.setVoted(2);
                commentaire.setNbreVotes(commentaire.getNbreVotes() - 1);
                commentVotes.setText(String.valueOf(commentaire.getNbreVotes()));
            }
            refreshState();
        });

        deleteComment.setOnAction(event -> {
            commentaireService.supprimer(commentaire.getId());
            navigateTo("/Fxml/postPage.fxml");
        });

        editComment.setOnAction(event -> {
            EditCommentaireController controller = new EditCommentaireController();
            controller.setData(commentaire);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/EditCommentaire.fxml"));
            loader.setController(controller);
            try {
                Parent root = loader.load();
                editComment.getScene().setRoot(root);
            } catch (IOException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Navigation Error");
                alert.setContentText("An error occurred while navigating to the next page.");
                alert.showAndWait();
            }
        });

    }


    public void refreshState() {
        if (commentaire.getVoted() == 1) {
            upVote.setStyle("-fx-background-color: #00ff00");
            downVote.setStyle("-fx-background-color: transparent");
        } else if (commentaire.getVoted() == 2) {
            downVote.setStyle("-fx-background-color: #ff0000");
            upVote.setStyle("-fx-background-color: transparent");
        } else {
            upVote.setStyle("-fx-background-color: transparent");
            downVote.setStyle("-fx-background-color: transparent");
        }
    }

    private void navigateTo(String fxmlFilePath) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxmlFilePath));
            deleteComment.getScene().setRoot(root);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Navigation Error");
            alert.setContentText("An error occurred while navigating to the next page.");
            alert.showAndWait();
        }
    }
}
