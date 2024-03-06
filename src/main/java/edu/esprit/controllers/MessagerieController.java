package edu.esprit.controllers;

import edu.esprit.entities.Messagerie;

import edu.esprit.entities.Utilisateur;
import edu.esprit.services.MessagerieService;
import edu.esprit.services.UserCRUD;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;

public class MessagerieController implements Initializable {

    Utilisateur user= new Utilisateur();
    Utilisateur user1= new Utilisateur();
    private final MessagerieService ms=new MessagerieService();
    private final UserCRUD su=new UserCRUD();
    //Utilisateur userSender = su.getOneByID(user.getId());
    //  Utilisateur userReciver = su.getOneByID(user1.getId());
    @FXML
    private AnchorPane container;
    @FXML
    private Label contenuMsg;

    @FXML
    private ImageView imageuser11;

    @FXML
    private ImageView imageuser12;

    @FXML
    private TextField msgenvoyee;

    @FXML
    private Label nomEmetteur;

    @FXML
    private VBox vboxMsg1;


    private void navigateTo(String fxmlFilePath) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxmlFilePath));
            nomEmetteur.getScene().setRoot(root);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Navigation Error");
            alert.setContentText("An error occurred while navigating to the next page.");
            alert.showAndWait();
        }
    }


    @FXML
    void NavigateToReclamation(ActionEvent event) {
        navigateTo("/Fxml/Reclamation.fxml");
    }


    public void EnvoyerMsgAction(ActionEvent actionEvent) {
        String messageContent = msgenvoyee.getText();

        // Vérifier si le champ de texte est vide
        if (isTextFieldEmpty(msgenvoyee)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText("Veuillez remplir tous les champs.");
            alert.showAndWait();
            return; // Sortir de la méthode sans envoyer le message
        }
        Messagerie message = new Messagerie();
        message.setContenu(messageContent);
        message.setSender_message(user); // Utilisateur émetteur
        message.setReceiver_message(user1); // Utilisateur récepteur
        //      Envoyer le message en utilisant le service approprié
        MessagerieService messagerieService = new MessagerieService();
        messagerieService.ajouter(message);
        navigateTo("/Fxml/Message.fxml");
    }
    private boolean isTextFieldEmpty(TextField textField) {
        return textField.getText().trim().isEmpty();
    }
    public void NavigateToAcceuil(ActionEvent actionEvent) {
    }

    public void NavigateToFavoris(ActionEvent actionEvent) {
    }

    public void NavigateToMessages(ActionEvent actionEvent) {
        navigateTo("/Fxml/Message.fxml");

    }

    public void NavigateToProduit(ActionEvent actionEvent) {
    }

    public void AjouterDiscussionAction(ActionEvent actionEvent) {
        navigateTo("/Fxml/AjouterMessage.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            MessagerieService messagerieService = new MessagerieService();

            List<Messagerie> messageries = messagerieService.getAll();

            for (Messagerie messagerie : messageries) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/MessageBox.fxml"));
                try {
                    vboxMsg1.getChildren().add(loader.load());
                    MessageBoxController controller = loader.getController();
                    controller.setMessageData(messagerie, container);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception error) {
            error.printStackTrace();
        }
    }

    public void onDiscussionAction(MouseEvent mouseEvent) {
        navigateTo("/Fxml/Message.fxml");
    }

    public void NavigateToMessagerie(ActionEvent actionEvent) {
        navigateTo("/Fxml/Messagerie.fxml");
    }


    public void updateChatMessages() {
        // Get the messages from the database for both sender and receiver
        List<Messagerie> senderMessages = ms.getAllMessagesByReciverAndSender(user.getId(), user1.getId());
        List<Messagerie> receiverMessages = ms.getAllMessagesByReciverAndSender(user1.getId(), user.getId());

        // Combine the messages
        List<Messagerie> allMessages = new ArrayList<>();
        allMessages.addAll(senderMessages);
        allMessages.addAll(receiverMessages);

        // Sort the messages by date
        allMessages.sort(Comparator.comparing(Messagerie::getDate));



    }
}