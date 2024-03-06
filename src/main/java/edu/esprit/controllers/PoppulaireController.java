package edu.esprit.controllers;

import edu.esprit.entities.Post;
import edu.esprit.services.PostService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.Set;

public class PoppulaireController {


    @FXML
    private VBox postsVBox = new VBox();


    private final PostService postService = new PostService();

    @FXML
    public void initialize() {


        // load a template


        // get all posts
        Set<Post> posts = postService.getPoppulerPosts();
        // all of the nodes are inside the postContainer Pane which is inside the VBOX postsVBox, they are added dynamically to the Pane postContainer and then added to the VBox postsVBox to be displayed.
        for (Post post : posts) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/postContainer.fxml"));
            try {
                postsVBox.getChildren().add(loader.load());
                //set spacing between posts
                postsVBox.setSpacing(10);
                postContainerController controller = loader.getController();
                controller.setData(post);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }

        }

    }
}
