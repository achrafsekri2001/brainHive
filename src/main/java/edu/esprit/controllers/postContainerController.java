package edu.esprit.controllers;

import com.gluonhq.charm.glisten.control.Avatar;
import com.gluonhq.charm.glisten.control.Chip;
import edu.esprit.entities.Post;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;

public class postContainerController {
    @FXML
    private Chip postChip = new Chip();
    @FXML
    private Text postTitle = new Text();
    @FXML
    private Text postDescription = new Text();
    @FXML
    private Label postDate = new Label();
    @FXML
    private AnchorPane postContainer = new AnchorPane();
    @FXML
    private Avatar postAvatar = new Avatar();
    @FXML
    private ImageView postFile = new ImageView();
    @FXML
    private Button postNbrOfComments = new Button();
    @FXML
    private Button postSaveButton = new Button();
    @FXML
    private ImageView savePostIcon = new ImageView();


    public void setData(Post post) {
        //set width to full width
        postContainer.setPrefWidth(600);
        postTitle.setText(post.getTitle());
        postDescription.setText(post.getDescription());
        postDate.setText(post.getCreatedAt().toString());
        postNbrOfComments.setText(post.getNumberOfComments() + "");
        postChip.setText(post.getMatiere());
//        postAvatar.setImage(post.getUser().getAvatar());
        if (post.getFichiers() != null && !post.getFichiers().isEmpty()) {
            //getFichiers returns a set of files if the first file is not null and is pdf fisplay image link else display the file string link
            if (post.getFichiers().iterator().next().endsWith(".pdf")) {
                postFile.setImage(new Image("https://play-lh.googleusercontent.com/BkRfMfIRPR9hUnmIYGDgHHKjow-g18-ouP6B2ko__VnyUHSi1spcc78UtZ4sVUtBH4g"));
                postFile.setFitWidth(150);
            } else {
                postFile.setImage(new Image("file:" + post.getFichiers().iterator().next()));
                postFile.setFitWidth(150);
            }
        } else {
            postFile.setVisible(true);
        }

        postContainer.setOnMouseClicked(event -> {
            // navigate to post page
            navigateTo("/Fxml/postPage.fxml", post);
            // pass the post to the post page

        });
    }

    // initialize method
    public void initialize() {
        postSaveButton.setOnAction(event -> {
            savePostIcon.setImage(new Image("https://brainhive.s3.eu-west-3.amazonaws.com/material-symbols_bookmark.png"));
        });

        // when clicking on post take to post page

    }

    private void navigateTo(String fxmlFilePath, Post post) {
        try {
            // load global holder
            GlobalHolder globalHolder = (GlobalHolder) postContainer.getScene().getWindow().getUserData();
            globalHolder.setCurrentPost(post);
            Parent root = FXMLLoader.load(getClass().getResource(fxmlFilePath));
            // get the stage
            postContainer.getScene().setRoot(root);

        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Navigation Error");
            alert.setContentText("An error occurred while navigating to the next page.");
            alert.showAndWait();
        }
    }
}
