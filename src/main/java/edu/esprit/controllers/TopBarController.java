package edu.esprit.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import java.io.IOException;

public class TopBarController {

    @FXML
    private Button addPostButton = new Button();


    @FXML
    public void initialize() {


        addPostButton.setOnAction(this::Navigate);


    }


    @FXML
    void Navigate(ActionEvent event) {

        if (event.getSource() == addPostButton) {
            navigateTo("/Fxml/createPost.fxml");
        }

    }


    private void navigateTo(String fxmlFilePath) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxmlFilePath));
            addPostButton.getScene().setRoot(root);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Navigation Error");
            alert.setContentText("An error occurred while navigating to the next page.");
            alert.showAndWait();
        }
    }
}
