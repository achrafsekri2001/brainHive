package edu.esprit.controllers;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.apache.commons.mail.Email;


import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javax.mail.MessagingException;
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

import javax.mail.MessagingException;
/**
 * FXML Controller class
 *
 * @author HP
 */
public class EnvoyerEmail  {
//public class EnvoyerEmail implements Initializable {
//
//    @FXML
//    private TextField fgmail;
//    @FXML
//    private Button fgentry;
//    @FXML
//    private Button vrfbutt;
//    @FXML
//    private TextField vrfcodetxt;
//
//    /**
//     * Initializes the controller class.
//     */
//    @Override
//    public void initialize(URL url, ResourceBundle rb) {
//        // TODO
//    }
//    int randomcode;
//
//
//    @FXML
//    private void SendCodeMail(ActionEvent event) throws SQLException, MessagingException, UnsupportedEncodingException, IOException {
////        String getmail=fgmail.getText();
////        String mai = fgmail.getText();
////        ServiceUser p = new ServiceUser();
////        //String a=p.forgotcrudpasss(mai);
////        Random rand =new Random();
////        randomcode=rand.nextInt(99999999);
////        String a=p.getMailpersonne(mai);
////        if(p.getMailpersonne(mai)!="")
////        {
////            Email email = new Email() ;
////            try {
////                email.setFrom("serviceclient@gmail.com", "E-skills");
////            } catch (EmailException e) {
////                throw new RuntimeException(e);
////            }
////            email.setSubject("Get your password");
////            email.setContent("<h1>This is your verification code :</h1>"+randomcode, "text/html");
////           // email.addRecipient(mai);
////            try {
////                email.send();
////            } catch (EmailException e) {
////                throw new RuntimeException(e);
////            }
////
////
////        }
////        else{
////            JOptionPane.showMessageDialog(null,fgmail+" doesn't existing, PLEASE SIGN UP");
////            FXMLLoader fxml=new FXMLLoader(getClass().getResource("Inscrire.fxml"));
////            Parent root=fxml.load();
////            fgmail.getScene().setRoot(root);
////            InscrireController rc=fxml.getController();
////        }
//    }
//
//    @FXML
//    private void VerifierCode(ActionEvent event) {
//        String mail=fgmail.getText();
//        String pwd=vrfcodetxt.getText();
//        ServiceUser cn=new ServiceUser();
//        if(Integer.valueOf(vrfcodetxt.getText())==randomcode)
//        {
//            try {
//
//                FXMLLoader fxml=new FXMLLoader(getClass().getResource("newpassword.fxml"));
//                Parent root=fxml.load();
//                vrfbutt.getScene().setRoot(root);
//                NewpasswordController rc=fxml.getController();
//
//            } catch (Exception e) {
//            }
//        }
//    }
//
//
//    //  String getmail=fgmail.getText();
//

}

