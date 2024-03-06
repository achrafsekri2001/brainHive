package edu.esprit.controllers;

import edu.esprit.entities.Post;
import edu.esprit.services.PostService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CreatePostController {

    private final PostService postService = new PostService();

    @FXML
    private TextArea descriptionInput = new TextArea();

    @FXML
    private TextField titleInput = new TextField();

    @FXML
    private ChoiceBox matiere = new ChoiceBox();

    @FXML
    private Button submitButton = new Button();

    @FXML
    private Button uploadButton = new Button();

    Set<String> files = new HashSet<String>();

    @FXML
    public void initialize() {

        // file uploader
        uploadButton.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Attacher des fichiers");
            FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Image Files (*.png, *.jpg, *.jpeg)", "*.png", "*.jpg", "*.jpeg");
            FileChooser.ExtensionFilter pdfFilter = new FileChooser.ExtensionFilter("PDF Files (*.pdf)", "*.pdf");
            fileChooser.getExtensionFilters().addAll(imageFilter, pdfFilter);
            List<File> selectedFiles = fileChooser.showOpenMultipleDialog(uploadButton.getScene().getWindow());

            if (selectedFiles != null) {
                for (File file : selectedFiles) {
                    files.add(file.getAbsolutePath());
                }
            }
        });

        // populate matiere choice box
        List<String> listeMatiere = List.of("Mathématiques", "Physique", "Informatique", "Anglais", "Français", "Histoire", "Géographie", "Philosophie", "SVT", "EPS", "Arts plastiques", "Musique", "Technologie", "Sciences de l'ingénieur", "Langues vivantes", "Latin", "Arabe");
        List<String> listeMatiereCree = new java.util.ArrayList<>(List.copyOf(listeMatiere));
        listeMatiereCree.add(0, "Sélectionner une matière");
        matiere.getItems().addAll(listeMatiereCree);
        matiere.getSelectionModel().selectFirst();


        // submit button
        submitButton.setOnAction(event -> {

            if (matiere.getSelectionModel().getSelectedIndex() == 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Matière non sélectionnée");
                alert.setContentText("Veuillez sélectionner une matière.");
                alert.showAndWait();
                return;
            }
            //form validation
            if (titleInput.getText().isEmpty() || descriptionInput.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Champs vides");
                alert.setContentText("Veuillez remplir tous les champs.");
                alert.showAndWait();
                return;
            }
            // create a post
            Post post = new Post();
            post.setTitle(titleInput.getText());
            post.setDescription(descriptionInput.getText());
            post.setMatiere(matiere.getSelectionModel().getSelectedItem().toString());
            post.setUserId(1);
            post.setFichiers(files);
            postService.ajouter(post);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Post créé");
            alert.setContentText("Votre post a été créé avec succès.");
            alert.showAndWait();
            // when click on "OK", navigate to the postPage and send the post id
            navigateTo("/Fxml/pageAcceuil.fxml");
        });

    }


    private void navigateTo(String fxmlFilePath) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxmlFilePath));
            submitButton.getScene().setRoot(root);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Navigation Error");
            alert.setContentText("An error occurred while navigating to the next page.");
            alert.showAndWait();
        }
    }

}
