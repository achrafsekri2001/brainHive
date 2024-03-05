package edu.esprit.controllers;

import edu.esprit.entities.Commentaire;
import edu.esprit.services.CommentaireService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

public class EditCommentaireController {
    private final CommentaireService commentaireService = new CommentaireService();
    @FXML
    private TextField commentaireInput = new TextField();

    @FXML
    private Button saveComment = new Button();

    public Commentaire commentaire;

    public void setData(Commentaire comment) {
        this.commentaire = comment;

    }

    public void initialize() {
        commentaireInput.setText(commentaire.getContent());
        saveComment.setOnAction(event -> {
            if (commentaireInput.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Empty Comment");
                alert.setContentText("You cannot save an empty comment.");
                alert.showAndWait();
                return;
            }
            commentaire.setContent(commentaireInput.getText());
            commentaireService.modifier(commentaire);
            navigateTo("/edu/esprit/views/Commentaire.fxml");
        });

    }


    private void navigateTo(String fxmlFilePath) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxmlFilePath));
            saveComment.getScene().setRoot(root);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Navigation Error");
            alert.setContentText("An error occurred while navigating to the next page.");
            alert.showAndWait();
        }
    }
}
