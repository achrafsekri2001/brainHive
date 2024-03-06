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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.jpedal.examples.viewer.Viewer;
import org.jpedal.examples.viewer.ViewerCommands;

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
    private HBox postFile = new HBox();
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
        post = postService.getOneByID(id);


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

        if (post.getFichiers().isEmpty()) {
            postFile.setVisible(false);
        } else {
            //if post is pdf print pdf else print image
            try {
                for (String file : post.getFichiers()) {
                    if (file.endsWith(".pdf")) {
                        // add button to open pdf
                        Button openPdfButton = new Button("Open PDF");
                        openPdfButton.setOnAction(event -> {
                            Viewer viewer = new Viewer();
                            viewer.setupViewer();
                            viewer.executeCommand(ViewerCommands.OPENFILE, new Object[]{file});
                        });
                        postFile.getChildren().add(openPdfButton);

                    } else {
                        ImageView imageView = new ImageView();
                        // url is the absolute path of the file
                        imageView.setImage(new Image("file:" + file));
                        imageView.setFitWidth(350);
                        imageView.setFitHeight(223);
                        imageView.setPreserveRatio(true);
                        imageView.setSmooth(true);
                        postFile.getChildren().add(imageView);
                    }
                }
                postFile.setSpacing(10);
                // add a border in between the files
                postFile.setStyle("-fx-border-color: #000000; -fx-border-width: 1px; -fx-border-radius: 5px;");


            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

// add comment
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
                    System.out.println(e.getMessage());
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });

        Set<Commentaire> commentaires = commentaireService.getCommentairesByPost(post);
        for (Commentaire commentaire : commentaires) {
            CommentaireController controller = new CommentaireController();
            controller.setData(commentaire);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/CommentaireView.fxml"));
            loader.setController(controller);
            try {
                CommentsVBox.getChildren().add(loader.load());
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            CommentsVBox.setSpacing(10);

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
