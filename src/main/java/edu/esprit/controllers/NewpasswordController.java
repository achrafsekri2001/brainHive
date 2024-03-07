package edu.esprit.controllers;

import com.google.api.client.googleapis.json.GoogleJsonError;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.services.gmail.model.Message;
import edu.esprit.services.ServiceUser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import edu.esprit.utils.DataSource;




import javafx.fxml.Initializable;
import javafx.stage.Stage;
import org.apache.commons.codec.binary.Base64;

import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Random;
import java.util.ResourceBundle;
import edu.esprit.services.ServiceUser;


import static javax.mail.Message.RecipientType.TO;


public class NewpasswordController  implements Initializable {






        private String sentCode; // Member variable to store the generated code

        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
            // Add any initialization logic here, if needed
        }

        @FXML
        private Button fgentry;

        @FXML
        private TextField fgmail;

        @FXML
        private Button vrfbutt;

        @FXML
        private TextField vrfcodetxt;

        @FXML
        void sendMail(ActionEvent event) throws SQLException {
            ServiceUser serviceUtilisateur = new ServiceUser();


            String email = fgmail.getText();

            if (email.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Please enter your email address.");
                alert.showAndWait();
                return;

            }


            if (!serviceUtilisateur.checkEmailExists(email)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Email not found");
                alert.setContentText("The email address you entered does not exist. Please try again with a valid email.");
                alert.showAndWait();
                return;
            }

            String code = generateRandomCode();

            try {
                GMailer mailer = new GMailer(email);
                mailer.sendMail("Verification Code", "Your verification code is: " + code);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Code sent");
                alert.setContentText("A verification code has been sent to your email.");
                alert.showAndWait();

                // Store the generated code
                sentCode = code; // Assign to the member variable

            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Failed to send email: " + e.getMessage());
                alert.showAndWait();
            }
        }

        private String generateRandomCode() {
            // Code for generating random code (already implemented)

                int length = 6; // Adjust the desired code length
                String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
                StringBuilder sb = new StringBuilder();
                Random random = new Random();

                for (int i = 0; i < length; i++) {
                    sb.append(characters.charAt(random.nextInt(characters.length())));
                }


                return sb.toString();
            }


    private String retrieveSentCode() {
        // Assuming sentCode is stored as a member variable after email sending
        return sentCode;
    }
    @FXML
    void VerifierCode(ActionEvent event) throws IOException {



            String enteredCode = vrfcodetxt.getText(); // Get the entered code

            // Retrieve the sent code securely
            String sentCode = retrieveSentCode(); // Replace with your secure retrieval method

            if (enteredCode.equals(sentCode)) {
                try {
                    // Load the new password scene
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/newpassword.fxml"));
                    Parent root = loader.load();
                    Scene scene = new Scene(root);

                    // Get the current stage
                    Stage currentStage = (Stage) vrfbutt.getScene().getWindow();

                    // Set the new scene and show it
                    currentStage.setScene(scene);
                    currentStage.show();

                    // Optionally, pass data to the new password controller
                    NewpasswordController controller = loader.getController();
                    if (controller != null) {
                        // Pass any necessary data (e.g., user email) to the controller
                        // controller.setUserEmail(userEmail); // Example
                    }
                } catch (IOException e) {
                    // Handle loading errors gracefully
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setContentText("Failed to load new password screen. Please try again.");
                    alert.showAndWait();
                    e.printStackTrace(); // Log the error for debugging
                }
            } else {
                // Display an error message for incorrect code
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Incorrect Code");
                alert.setContentText("The verification code you entered is incorrect. Please try again.");
                alert.showAndWait();
            }
        }





    }
            /*



            String enteredCode = vrfcodetxt.getText(); // Get the code entered by the user

            // Retrieve the sent code from a suitable storage location
            String sentCode = retrieveSentCode();// Replace with your method to get the sent code


            if (enteredCode.equals(sentCode))

            {

                //try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/newpassword.fxml"));
                    Parent root = loader.load();
                    Scene scene = new Scene(root);
                    Stage currentStage = (Stage) vrfbutt.getScene().getWindow();
                    currentStage.setScene(scene);
                    currentStage.show();}else {
                //} catch (IOException e) {
                    // Provide a more user-friendly error message
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setContentText("Failed to load new password screen. Please try again.");
                    alert.showAndWait();
                    //e.printStackTrace(); // Log the error for debugging
                }}}

             */


