package edu.esprit.controllers;

import edu.esprit.entities.Post;
import edu.esprit.services.PostService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class AdminPostContainerController {
    @FXML
    private Label postChip ;
    @FXML
    private Text postTitle ;
    @FXML
    private Text postDescription ;
    @FXML
    private Label postDate ;
    @FXML
    private AnchorPane postContainer ;
    @FXML
    private ImageView postAvatar ;
    @FXML
    private ImageView postFile ;
    @FXML
    private Button postNbrOfComments ;
    @FXML
    private Button deletePost ;


    private final PostService postService = new PostService();


    public void setData(Post post) {
        // make postAvatar image view rounded
        postAvatar.setClip(new ImageView(new Image("https://brainhive.s3.eu-west-3.amazonaws.com/blank-profile-picture-973460_1280.png")));

        //set width to full width
        postContainer.setPrefWidth(600);
        postTitle.setText(post.getTitle());
        postDescription.setText(post.getDescription());
        postDate.setText(post.getCreatedAt().toString());
        postNbrOfComments.setText(post.getNumberOfComments() + "");
        postChip.setText(post.getMatiere());
        deletePost.setOnAction(event -> {
            postService.supprimer(post.getuser().getId());
            postContainer.setVisible(false);
        });

        if (post.getFichiers() != null && !post.getFichiers().isEmpty()) {
            //getFichiers returns a set of files if the first file is not null and is pdf fisplay image link else display the file string link
            if (post.getFichiers().iterator().next().endsWith(".pdf")) {
                postFile.setImage(new Image("https://brainhive.s3.eu-west-3.amazonaws.com/pdf-file.png"));
                postFile.setFitWidth(100);
            } else {
                postFile.setImage(new Image("file:" + post.getFichiers().iterator().next()));
                postFile.setFitWidth(200);
            }
        } else {
            postFile.setVisible(true);
        }
    }

    // initialize method
    public void initialize() {
    }


}
