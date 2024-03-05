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


    public void setData(Post post) {
        //set padding to 20
        postContainer.setStyle("-fx-padding: 20");
        postTitle.setText(post.getTitle());
        postDescription.setText(post.getDescription());
        postDate.setText(post.getCreatedAt().toString());
        postNbrOfComments.setText(post.getNumberOfComments() + "");
        postChip.setText(post.getMatiere());
//        postAvatar.setImage(post.getUser().getAvatar());
//        postFile.setImage(post.getFichiers().iterator().next().getLink());

        postContainer.setOnMouseClicked(event -> {
            // navigate to post page
            navigateTo("/Fxml/postPage.fxml", post);
            // pass the post to the post page

        });
    }

    // initialize method
    public void initialize() {
        postSaveButton.setOnAction(event -> {
            // save post
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