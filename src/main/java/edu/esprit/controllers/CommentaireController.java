package edu.esprit.controllers;

import edu.esprit.entities.Commentaire;
import edu.esprit.services.CommentaireService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

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
    private AnchorPane commentContainer = new AnchorPane();

    public Commentaire commentaire = new Commentaire();

    public void setData(Commentaire comment) {
        CommentCreator.setText("Achraf Sekri");
        commentContent.getChildren().add(new Text(comment.getContent()));
        CommentDate.setText(comment.getCreatedAt().toString());
        commentVotes.setText(String.valueOf(comment.getNbreVotes()));
    }

    // initialize method
    public void initialize() {
        upVote.setOnAction(event -> {
            if (commentaire.getVoted() == 0) {
                commentaireService.upVote(commentaire);
            } else {
                commentaireService.cancelVote(commentaire);
            }
            refreshState();
        });
        downVote.setOnAction(event -> {
            if (commentaire.getVoted() == 0) {
                commentaireService.downVote(commentaire);
            } else {
                commentaireService.cancelVote(commentaire);
            }
            refreshState();
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
}
