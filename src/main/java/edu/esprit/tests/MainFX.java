package edu.esprit.tests;

import edu.esprit.controllers.GlobalHolder;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainFX extends Application {
    @Override
    public void start(Stage stage) throws Exception {
//        Application.setUserAgentStylesheet(new PrimerLight().getUserAgentStylesheet());
//        Application.setUserAgentStylesheet(new PrimerDark().getUserAgentStylesheet());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/pageAcceuil.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        //add GlobalHolder to the stage to hold the current post
        GlobalHolder globalHolder = new GlobalHolder();
        stage.setUserData(globalHolder);
        stage.setTitle("Post App");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
