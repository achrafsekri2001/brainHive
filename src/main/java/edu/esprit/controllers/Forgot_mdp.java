package edu.esprit.controllers;

import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

import edu.esprit.Services.ServiceUser;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;



import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Random;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;


/**
 * FXML Controller class
 *
 * @author HP
 */
public class Forgot_mdp implements Initializable {

    @FXML
    private TextField fgmail;
    @FXML
    private Button fgentry;
    @FXML
    private Button vrfbutt;
    @FXML
    private TextField vrfcodetxt;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }



    int randomcode;


    @FXML
    private void SendCodeMail(ActionEvent event) throws SQLException, IOException, EmailException {



            // Get email address from UI
            String emailAddress = fgmail.getText();

            // Retrieve user information
            ServiceUser serviceUser = new ServiceUser();
            Optional<String> existingEmail = Optional.ofNullable(serviceUser.getMailpersonne(emailAddress));

            if (existingEmail.isPresent()) {
                // Generate verification code
                int verificationCode = new Random().nextInt(99999999);

                // Send email (assuming secure email sending method)
                try {
                    sendEmail(emailAddress, "Get your password",
                            "<h1>This is your verification code:</h1>" + verificationCode);
                    // Proceed with verification process
                    // ...
                } catch (EmailException e) {
                    // Handle email sending errors
                    // ...
                }
            } else {
                // Email address not found
                JOptionPane.showMessageDialog(null, emailAddress + " doesn't exist. Please sign up.");
                try {
                    // Load sign-up FXML
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Inscrire.fxml"));
                    Parent root = fxmlLoader.load();
                    fgmail.getScene().setRoot(root);
                    SampleController controller = fxmlLoader.getController();
                    // ... (initialize controller if needed)
                } catch (IOException e) {
                    // Handle FXML loading errors
                    // ...
                }
            }
        }

// Secure email sending method (replace with your implementation)
        private void sendEmail(String recipientEmail, String subject, String body) throws EmailException {
            // ... (Use a secure authentication method like OAuth 2.0)
        }






        /*
        String getmail=fgmail.getText();
        String mai = fgmail.getText();
        ServiceUser p = new ServiceUser();
        String a=p.forgotcrudpasss(mai);
        Random rand =new Random();
        randomcode=rand.nextInt(99999999);
        //String a=p.getMailpersonne(mai);
        if(p.getMailpersonne(mai)!="")
        {
            Email email;
            email = new Email("serviceclient619@gmail.com", "25163816");
            email.setFrom("serviceclient@gmail.com", "E-skills");
            email.setSubject("Get your password");
            email.setContent("<h1>This is your verification code :</h1>"+randomcode, "text/html");
            email.addReplyTo(mai);
            email.send();



        }
        else{
            JOptionPane.showMessageDialog(null,fgmail+" doesn't existing, PLEASE SIGN UP");
            FXMLLoader fxml=new FXMLLoader(getClass().getResource("Inscrire.fxml"));
            Parent root=fxml.load();
            fgmail.getScene().setRoot(root);
            SampleController rc=fxml.getController();
        }
    }}

         */





    @FXML
    private void VerifierCode(ActionEvent event) {
    }}
    /*
        String mail = fgmail.getText();
        String pwd = vrfcodetxt.getText();
        ServiceUser cn = new ServiceUser();
        if (Integer.valueOf(vrfcodetxt.getText()) == randomcode) {
            try {

                FXMLLoader fxml = new FXMLLoader(getClass().getResource("newpassword.fxml"));
                Parent root = fxml.load();
                vrfbutt.getScene().setRoot(root);
                NewpasswordController rc = fxml.getController();

            } catch (Exception e) {
            }
        }


    }}

     */






    //  String getmail=fgmail.getText();


