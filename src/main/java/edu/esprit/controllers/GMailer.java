package edu.esprit.controllers;
import javax.mail.Session;


import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.googleapis.json.GoogleJsonError;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.Message;
import org.apache.commons.codec.binary.Base64;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.*;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.Set;

import static com.google.api.services.gmail.GmailScopes.GMAIL_SEND;
import static javax.mail.Message.RecipientType.TO;

public class GMailer {

    private String testEmail;
    private final Gmail service;

    public GMailer(String email) throws Exception {
        this.testEmail = "rayenbencheikh261@gmail.com";
        GsonFactory jsonFactory = GsonFactory.getDefaultInstance();
        NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        service = new Gmail.Builder(httpTransport, jsonFactory, getCredentials(httpTransport ,jsonFactory))
                .setApplicationName("brainhive")
                .build();
    }

    private static Credential getCredentials(final NetHttpTransport httpTransport, GsonFactory jsonFactory)
            throws IOException {
        // Load client secrets.
     //   InputStream in = GMailer.class.getResourceAsStream("/...json");
      // if (in == null) {
        //  throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
      //}
        //GsonFactory jsonFactory = getDefaultInstance();
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(jsonFactory, new InputStreamReader(GMailer.class.getResourceAsStream("/client_secret_102398430450-1hj5dqjukb0aaa7ldd5p8f7m4q8umdij.apps.googleusercontent.com.json")));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                httpTransport, jsonFactory, clientSecrets, Set.of(GMAIL_SEND))
                .setDataStoreFactory(new FileDataStoreFactory(Paths.get("tokens").toFile()))
                .setAccessType("offline")
                .build();
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        //returns an authorized Credential object.
        return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
    }

    public void sendMail(String subject, String msg) throws Exception {


        // Create the email content

        // Encode as MIME message
        Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);
        MimeMessage email = new MimeMessage(session);
        email.setFrom(new InternetAddress(testEmail));
        email.addRecipient(TO, new InternetAddress(testEmail));
        email.setSubject(subject);
        email.setText(msg);

        // Encode and wrap the MIME message into a gmail message
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        email.writeTo(buffer);
        byte[] rawMessageBytes = buffer.toByteArray();
        String encodedEmail = Base64.encodeBase64URLSafeString(rawMessageBytes);
        Message message = new Message();
        message.setRaw(encodedEmail);

        try {
            // Create send message
            message = service.users().messages().send("me", message).execute();
            System.out.println("Message id: " + message.getId());
            System.out.println(message.toPrettyString());
        } catch (GoogleJsonResponseException e) {
            // TODO(developer) - handle error appropriately
            GoogleJsonError error = e.getDetails();
            if (error.getCode() == 403) {
                System.err.println("Unable to send message: " + e.getDetails());
            } else {
                throw e;
            }
        }

    }}
/*


public static void main(String[] args) throws Exception {
        new GMailer("rayenbencheikh261@gmail.com"  ).sendMail("A new message", """
                Dear reader,

                Hello world.

                Best regards,
                myself
                """);}}

 */







       /*
       private String mail;
       private final Gmail service;

private static final  String TEST_EMAIL="rayenbencheikh261@gmail.com";
       private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT,GsonFactory jsonFactory)
               throws IOException{
           GoogleClientSecrets clientSecrets =
                   GoogleClientSecrets.load(jsonFactory.getDefaultInstance(), new InputStreamReader(GMailer.class.getResourceAsStream("/file")));
           // Build flow and trigger user authorization request.
           GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                   HTTP_TRANSPORT, jsonFactory, clientSecrets, Set.of(GMAIL_SEND))
                   .setDataStoreFactory(new FileDataStoreFactory(Paths.get("tokens").toFile()))
                   .setAccessType("offline")
                   .build();

    public GMailer(String testEmail) throws Exception {
        this.mail = testEmail;
        GsonFactory jsonFactory = GsonFactory.getDefaultInstance();
        NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        service = new Gmail.Builder(httpTransport, jsonFactory, getCredentials(httpTransport ,jsonFactory))
                .setApplicationName("test")
                .build();




        // Load client secrets.
        InputStream in = GMailer.class.getResourceAsStream("/file");
        if (in == null) {
            throw new FileNotFoundException("Resource not found: " + file);
        }


               GoogleClientSecrets clientSecrets =
                       GoogleClientSecrets.load(jsonFactory.getDefaultInstance(), new InputStreamReader(GMailer.class.getResourceAsStream("/file")));


        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        Credential credential = new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
        //returns an authorized Credential object.
        return credential;
    }}}

//
//
//
//    public void sendMail(String subject,String msg)  throws Exception {
//        Properties props = new Properties();
//        Session session = Session.getDefaultInstance(props, null);
//        MimeMessage email = new MimeMessage(session);
//        email.setFrom(new InternetAddress(mail));
//        email.addRecipient(TO, new InternetAddress(mail));
//        email.setSubject(subject);
//        email.setText(msg);
//
//        // Encode and wrap the MIME message into a gmail message
//        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
//        email.writeTo(buffer);
//        byte[] rawMessageBytes = buffer.toByteArray();
//        String encodedEmail = Base64.encodeBase64URLSafeString(rawMessageBytes);
//        Message message = new Message();
//        message.setRaw(encodedEmail);
//
//        try {
//            // Create send message
//            message = service.users().messages().send("me", message).execute();
//            System.out.println("Message id: " + message.getId());
//            System.out.println(message.toPrettyString());
//        } catch (GoogleJsonResponseException e) {
//            // TODO(developer) - handle error appropriately
//            GoogleJsonError error = e.getDetails();
//            if (error.getCode() == 403) {
//                System.err.println("Unable to send message: " + e.getDetails());
//            } else {
//                throw e;
//            }
//        }
//    }
//
//
//
//
//
//
//
//
//
//}
///*
//    private static final String TEST_EMAIL = "<your-gmail-address>";
//    private final Gmail service;
//
//    public GMailer() throws Exception {
//        NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
//        GsonFactory jsonFactory = GsonFactory.getDefaultInstance();
//        service = new Gmail.Builder(httpTransport, jsonFactory, getCredentials(httpTransport, jsonFactory))
//                .setApplicationName("Test Mailer")
//                .build();
//    }
//
//    private static Credential getCredentials(final NetHttpTransport httpTransport, GsonFactory jsonFactory)
//            throws IOException {
//        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(jsonFactory, new InputStreamReader(GMailer.class.getResourceAsStream("/<your_client_secret>.json")));
//
//        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
//                httpTransport, jsonFactory, clientSecrets, Set.of(GMAIL_SEND))
//                .setDataStoreFactory(new FileDataStoreFactory(Paths.get("tokens").toFile()))
//                .setAccessType("offline")
//                .build();
//
//        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
//        return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
//    }
//
//    public void sendMail(String subject, String message) throws Exception {
//        Properties props = new Properties();
//        Session session = Session.getDefaultInstance(props, null);
//        MimeMessage email = new MimeMessage(session);
//        email.setFrom(new InternetAddress(TEST_EMAIL));
//        email.addRecipient(TO, new InternetAddress(TEST_EMAIL));
//        email.setSubject(subject);
//        email.setText(message);
//
//        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
//        email.writeTo(buffer);
//        byte[] rawMessageBytes = buffer.toByteArray();
//        String encodedEmail = Base64.encodeBase64URLSafeString(rawMessageBytes);
//        Message msg = new Message();
//        msg.setRaw(encodedEmail);
//
//        try {
//            msg = service.users().messages().send("me", msg).execute();
//            System.out.println("Message id: " + msg.getId());
//            System.out.println(msg.toPrettyString());
//        } catch (GoogleJsonResponseException e) {
//            GoogleJsonError error = e.getDetails();
//            if (error.getCode() == 403) {
//                System.err.println("Unable to send message: " + e.getDetails());
//            } else {
//                throw e;
//            }
//        }
//    }
//
//    public static void main(String[] args) throws Exception {
//        new GMailer().sendMail("A new message", """
//                Dear reader,
//
//                Hello world.
//
//                Best regards,
//                myself
//                """);
//    }

//}

        */

