package edu.esprit.controllers;

import com.gluonhq.charm.glisten.control.Avatar;
import com.gluonhq.charm.glisten.control.Chip;
import edu.esprit.entities.Commentaire;
import edu.esprit.entities.Post;
import edu.esprit.services.CommentaireService;
import edu.esprit.services.PostService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public class PostPageController {

    private final PostService postService = new PostService();
    private final CommentaireService commentaireService = new CommentaireService();

    @FXML
    private ListView<String> matiereList;

    @FXML
    private Hyperlink navigateAcceuil = new Hyperlink();


    @FXML
    private Hyperlink navigatePopulaire = new Hyperlink();

    @FXML
    private Hyperlink navigateSauveguardee = new Hyperlink();

    @FXML
    private Hyperlink navigateMessagerie = new Hyperlink();

    @FXML
    private Hyperlink navigateParascolaire = new Hyperlink();

    @FXML
    private Hyperlink navigateReclamation = new Hyperlink();

    @FXML
    private Chip postChip = new Chip();
    @FXML
    private Text postTitle = new Text();
    @FXML
    private Text postDescription = new Text();
    @FXML
    private Label postDate = new Label();

    @FXML
    private Avatar postAvatar = new Avatar();
    @FXML
    private ImageView postFile = new ImageView();
    @FXML
    private Button postNbrOfComments = new Button();
    @FXML
    private Button postSaveButton = new Button();
    @FXML
    private TextField addCommentField = new TextField();
    @FXML
    private VBox CommentsVBox = new VBox();
    Post post;

    @FXML
    public void initialize() {

        int id = GlobalHolder.getCurrentPost().getId();
        System.out.println("Current post id: " + id);
         post= postService.getOneByID(id);
//         post = GlobalHolder.getCurrentPost();


        List<String> listeMatiere = List.of("Mathématiques", "Physique", "Informatique", "Anglais", "Français", "Histoire", "Géographie", "Philosophie", "SVT", "EPS", "Arts plastiques", "Musique", "Technologie", "Sciences de l'ingénieur", "Langues vivantes", "Latin", "Arabe");
        matiereList.getItems().addAll(listeMatiere);
        // when a subject is selected, navigate to a page called "subjects"
        matiereList.setOnMouseClicked(event -> {
            System.out.println(matiereList.getSelectionModel().getSelectedItem());
        });
        // navigation
        navigateAcceuil.setOnAction(this::Navigate);
        navigatePopulaire.setOnAction(this::Navigate);
        navigateSauveguardee.setOnAction(this::Navigate);
        navigateMessagerie.setOnAction(this::Navigate);
        navigateParascolaire.setOnAction(this::Navigate);
        navigateReclamation.setOnAction(this::Navigate);

        postTitle.setText(post.getTitle());
        postDescription.setText(post.getDescription());
        postDate.setText(post.getCreatedAt().toString());
        postNbrOfComments.setText(post.getNumberOfComments() + "");
        postChip.setText(post.getMatiere());


        addCommentField.setOnAction(event -> {
            // perform form validation
            if (addCommentField.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Comment field is empty");
                alert.setContentText("Please enter a comment before submitting.");
                alert.showAndWait();
                return;
            }
            Commentaire commentaire = new Commentaire();
            commentaire.setContent(addCommentField.getText());
            commentaire.setPost(post);
            commentaire.setUserId(1);
            commentaire.setNbreVotes(0);
            commentaire.setNbreUpVotes(0);
            commentaire.setNbreDownVotes(0);
            commentaire.setParent(null);
            commentaire.setCreatedAt(null);
            commentaire.setUpdatedAt(null);
            try {
                commentaireService.ajouter(commentaire);
                System.out.println("Comment added !");
                //reload the page
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/PostPage.fxml"));
                try {
                    Parent root = loader.load();
                    navigateAcceuil.getScene().setRoot(root);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });

        Set<Commentaire> commentaires = commentaireService.getCommentairesByPost(post);
        for (Commentaire commentaire : commentaires) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/CommentaireView.fxml"));
            try {
                CommentsVBox.getChildren().add(loader.load());
                //set spacing between posts
                CommentsVBox.setSpacing(10);
                CommentaireController controller = loader.getController();
                controller.setData(commentaire);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


    @FXML
    void Navigate(ActionEvent event) {
        if (event.getSource() == navigateAcceuil) {
            navigateTo("/Fxml/pageAcceuil.fxml");
        }
        if (event.getSource() == navigatePopulaire) {
            navigateTo("/Fxml/test.fxml");
        }
        if (event.getSource() == navigateSauveguardee) {
            navigateTo("/Fxml/pageSauveguardee.fxml");
        }
        if (event.getSource() == navigateMessagerie) {
            navigateTo("/Fxml/pageMessagerie.fxml");
        }
        if (event.getSource() == navigateParascolaire) {
            navigateTo("/Fxml/pageParascolaire.fxml");
        }
        if (event.getSource() == navigateReclamation) {
            navigateTo("/Fxml/pageReclamation.fxml");
        }

    }


    private void navigateTo(String fxmlFilePath) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxmlFilePath));
            navigateAcceuil.getScene().setRoot(root);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Navigation Error");
            alert.setContentText("An error occurred while navigating to the next page.");
            alert.showAndWait();
        }
    }

}
