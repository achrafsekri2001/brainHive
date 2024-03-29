package edu.esprit.Services;

import edu.esprit.entities.User;
import edu.esprit.utils.DataSource;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import org.apache.commons.mail.EmailException;

import javax.mail.MessagingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import java.sql.*;
import java.util.*;

public class ServiceUser  implements IService<User> {

    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(User t) {

            String req = "INSERT INTO `user`(`email`, `nom`, `prenom`, `password`, `roles`) VALUES (?, ?, ?, ?, ?)";
            try {
                PreparedStatement ps = cnx.prepareStatement(req);
                ps.setString(1, t.getEmail());
                ps.setString(2, t.getNom());
                ps.setString(3, t.getPrenom());
                ps.setString(4, t.getPassword());
                ps.setInt(5, t.getRoles());
                ps.executeUpdate();
                System.out.println("Utilisateur ajouté !");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
    }



         /*
           @Override
    public void modifier(User t) {String req = "UPDATE `user` SET `email`=?, `nom`=?, `prenom`=?, `password`=?, `roles`=? WHERE `id`=?";
            try {
                PreparedStatement ps = cnx.prepareStatement(req);
                ps.setString(1, t.getEmail());
                ps.setString(2, t.getNom());
                ps.setString(3, t.getPrenom());
                ps.setString(4, t.getPassword());
                ps.setInt(5, t.getRoles());
                ps.setInt(6, t.getId());
                ps.executeUpdate();
                System.out.println("Utilisateur modifié !");
            } catch (SQLException e) {
                System.out.println("Erreur lors de la modification de l'utilisateur : " + e.getMessage());
            }*/







    @Override
        public void Modifier(User t) throws SQLException {


            String req = "UPDATE `user` SET `email` = ?, `nom` = ?, `prenom` = ?, `password` = ? WHERE `id` = ?";
            try {
                PreparedStatement ps = cnx.prepareStatement(req);
                ps.setString(1, t.getEmail());
                ps.setString(2, t.getNom());
                ps.setString(3, t.getPrenom());
                ps.setString(4, t.getPassword());
                ps.setInt(5, t.getId()); // Use the user's ID for identification
                int rowsUpdated = ps.executeUpdate();

                if (rowsUpdated > 0) {
                    System.out.println("Utilisateur modifié !");
                } else {
                    System.out.println("Aucun utilisateur trouvé avec l'ID spécifié.");
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }




        /*

            String req = "UPDATE `user` SET `email`=?, `nom`=?, `prenom`=?, `password`=?,  WHERE `id`=?";
            PreparedStatement ps;
            try {
                ps = cnx.prepareStatement(req);

                // Set parameters for the prepared statement
                ps.setString(1, t.getEmail());
                ps.setString(2, t.getNom());
                ps.setString(3, t.getPrenom());
                ps.setString(4, t.getPassword());

                ps.setInt(6, t.getId());

                // Execute the update query
                int rowsUpdated = ps.executeUpdate();

                if (rowsUpdated > 0) {
                    // Successful modification
                    System.out.println("Utilisateur modifié : " + t.getNom());
                } else {
                    // No rows were updated, indicating a potential error
                    System.out.println("Aucune modification effectuée.");
                }

            } catch (SQLException e) {
                System.out.println("Erreur lors de la modification de l'utilisateur : " + e.getMessage());
                throw e; // Re-throw the exception to propagate it up the call stack
            }

         */
        }





    public void modifierAdmin(User userToUpdate) throws SQLException {
        String req = "UPDATE `user` SET `nom`=?, `prenom`=?, `email`=?, `password`=? WHERE `id`=?";
        PreparedStatement ps = cnx.prepareStatement(req);

        try {
            // Set parameters for the prepared statement
            ps.setString(1, userToUpdate.getNom());
            ps.setString(2, userToUpdate.getPrenom());
            ps.setString(3, userToUpdate.getEmail());
            ps.setString(4, userToUpdate.getPassword());
            ps.setInt(5, userToUpdate.getId());

            // Execute the update query
            int rowsUpdated = ps.executeUpdate();

            if (rowsUpdated > 0) {
                // Successful modification
                System.out.println("Utilisateur modifié : " + userToUpdate.getNom());
            } else {
                // No rows were updated, indicating a potential error
                System.out.println("Aucune modification effectuée.");
            }

        } catch (SQLException e) {
            System.out.println("Erreur lors de la modification de l'utilisateur : " + e.getMessage());
            throw e; // Re-throw the exception to propagate it up the call stack
        }
    }






    @Override
    public void supprimer(int id) {

            String req = "DELETE FROM `user` WHERE `id`=?";
            try {
                PreparedStatement ps = cnx.prepareStatement(req);
                ps.setInt(1, id);
                ps.executeUpdate();
                System.out.println("Utilisateur avec l'ID " + id + " supprimé !");
            } catch (SQLException e) {
                System.out.println("Erreur lors de la suppression de l'utilisateur : " + e.getMessage());
            }
        }








    @Override
    public Set<User> getAll() {


            Set<User> utilisateurs = new HashSet<>();
        List<User> test=new ArrayList<>();
            String req = "SELECT * FROM `user`";
            try {
                PreparedStatement ps = cnx.prepareStatement(req);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String email = rs.getString("email");
                    String nom = rs.getString("nom");
                    String prenom = rs.getString("prenom");
                    String password = rs.getString("password");
                    int roles = rs.getInt("roles");

                    // Créer un nouvel utilisateur avec les données récupérées
                    User utilisateur = new User (id,email,nom,prenom,password,roles);

                    // Ajouter l'utilisateur à l'ensemble
                   utilisateurs.add(utilisateur);

                }
            } catch (SQLException e) {
                System.out.println("Erreur lors de la récupération des utilisateurs : " + e.getMessage());
            }


            return utilisateurs;
        }



    @Override
    public User getOneByID(int id) {

            User utilisateur = null;
            String req = "SELECT * FROM `user` WHERE `id`=?";
            try {
                PreparedStatement ps = cnx.prepareStatement(req);
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    // Créer un nouvel objet User avec les données récupérées de la base de données
                    utilisateur = new User();
                    utilisateur.setId(rs.getInt("id"));
                    utilisateur.setEmail(rs.getString("email"));
                    utilisateur.setNom(rs.getString("nom"));
                    utilisateur.setPrenom(rs.getString("prenom"));
                    utilisateur.setPassword(rs.getString("password"));
                    utilisateur.setRoles(rs.getInt("roles"));
                }
            } catch (SQLException e) {
                System.out.println("Erreur lors de la récupération de l'utilisateur : " + e.getMessage());
            }
            return utilisateur;
        }
    public User getUser(String username , String password) throws SQLException{
        User u=null;
        String sql ="SELECT * FROM user WHERE lastname = ? AND password = ?";
        PreparedStatement  stmt = cnx.prepareStatement(sql);
        stmt.setString(1, username);
        stmt.setString(2, password);

        ResultSet  rs = stmt.executeQuery();
        while(rs.next()){
            u = new User();
        }
        return u;
    }
    public String forgotcrudpasss(String mail) throws SQLException {

        String a="";
        Statement ste = cnx.createStatement();
        ResultSet rs=ste.executeQuery("SELECT * FROM `user` WHERE email='"+mail+"' ;");
        while (rs.next()) {
            a=rs.getString("mail");
            System.out.println(a);
        }
        return a;
    }
    public String getMailpersonne(String mail) throws SQLException {
        String a="";
        Statement ste = cnx.createStatement();
        ResultSet rs=ste.executeQuery("SELECT * FROM `user` WHERE email='"+mail+"' ;");
        while (rs.next()) {
            a=rs.getString("email");
        }
        return a;

    }

    public User findByEmail(String email) {

            User user = null;
            String req = "SELECT * FROM `user` WHERE `email`=?";
            try {
                PreparedStatement ps = cnx.prepareStatement(req);
                ps.setString(1, email);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    // Create a new User object with the retrieved data
                    user = new User();
                    user.setId(rs.getInt("id"));
                    user.setEmail(rs.getString("email"));
                    user.setNom(rs.getString("nom"));
                    user.setPrenom(rs.getString("prenom"));
                    user.setPassword(rs.getString("password"));
                    user.setRoles(rs.getInt("roles"));
                }
            } catch (SQLException e) {
                System.out.println("Error while finding user by email: " + e.getMessage());
            }
            return user;
        }

    public User findByRole(int role) {
        User user = null;
        String req = "SELECT * FROM `user` WHERE `roles`=?";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, role);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                // Créer un nouvel objet User avec les données récupérées
                user = new User();
                user.setId(rs.getInt("id"));
                user.setEmail(rs.getString("email"));
                user.setNom(rs.getString("nom"));
                user.setPrenom(rs.getString("prenom"));
                user.setPassword(rs.getString("password"));
                user.setRoles(rs.getInt("roles"));
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la recherche d'utilisateur par rôle: " + e.getMessage());
        }
        return user;
    }



    public Set<User> getUsersByRole(int role) throws SQLException {
        Set<User> users = new HashSet<>();

        String req = "SELECT * FROM `user` WHERE `roles`=?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, role); // Set role to 1

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            // Create a new User object with the retrieved data
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setEmail(rs.getString("email"));
            user.setNom(rs.getString("nom"));
            user.setPrenom(rs.getString("prenom"));
            user.setPassword(rs.getString("password")); // Consider if displaying password is appropriate
            user.setRoles(rs.getInt("roles"));

            // Add the user to the set
            users.add(user);
        }

        return users;
    }
    public Set<User> getUsersEnseignant(int role) throws SQLException {
        Set<User> users = new HashSet<>();

        String req = "SELECT * FROM `user` WHERE `roles`=?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(2, role); // Set role to 2

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            // Create a new User object with the retrieved data
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setEmail(rs.getString("email"));
            user.setNom(rs.getString("nom"));
            user.setPrenom(rs.getString("prenom"));
            user.setPassword(rs.getString("password")); // Consider if displaying password is appropriate
            user.setRoles(rs.getInt("roles"));

            // Add the user to the set
            users.add(user);
        }

        return users;
    }




    public void delete(User selectedUser) throws SQLException {
        if (selectedUser == null) {
            throw new IllegalArgumentException("User cannot be null");
        }

        // Vérifier l'existence de l'utilisateur (optionnel)
        if (userExists(selectedUser.getId())) {
            String req = "DELETE FROM `user` WHERE `id` = ?";

            try (PreparedStatement ps = cnx.prepareStatement(req)) {
                ps.setInt(1, selectedUser.getId());
                ps.executeUpdate();
                System.out.println("Utilisateur supprimé avec succès !");
            } catch (SQLException e) {
                System.out.println("Erreur lors de la suppression de l'utilisateur : " + e.getMessage());
                throw e; // Re-throw the exception for proper handling
            }
        } else {
            System.out.println("Utilisateur introuvable.");
        }
    }
    private boolean userExists(int userId) throws SQLException {
        String req = "SELECT COUNT(*) FROM `user` WHERE `id` = ?";
        try (PreparedStatement ps = cnx.prepareStatement(req)) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1) > 0;
        } catch (SQLException e) {
            System.out.println("Erreur lors de la vérification de l'existence de l'utilisateur : " + e.getMessage());
            throw e; // Re-throw the exception for proper handling
        }
    }
    public void modifier(User selectedUser, TextField nouveauNom, TextField nouveauPrenom, TextField nouvelEmail, TextField nouveauPswd) throws SQLException {
        if (selectedUser == null) {
            throw new IllegalArgumentException("Utilisateur cannot be null");
        }

        // Vérifier l'existence de l'utilisateur (optionnel)
        if (userExists(selectedUser.getId())) {
            String req = "UPDATE `user` SET `nom` = ?, `prenom` = ?, `email` = ?, `password` = ? WHERE `id` = ?";

            try (PreparedStatement ps = cnx.prepareStatement(req)) {
                ps.setString(1, String.valueOf(nouveauNom));
                ps.setString(2, String.valueOf(nouveauPrenom));
                ps.setString(3, String.valueOf(nouvelEmail));
                ps.setString(4, String.valueOf(nouveauPswd));
                ps.setInt(5, selectedUser.getId());
                ps.executeUpdate();
                System.out.println("Utilisateur modifié avec succès !");
            } catch (SQLException e) {
                System.out.println("Erreur lors de la modification de l'utilisateur : " + e.getMessage());
                throw e; // Re-throw the exception for proper handling
            }
        } else {
            System.out.println("Utilisateur introuvable.");
        }
    }














    public boolean checkEmailExists(String email) throws SQLException {

        String req = "SELECT COUNT(*) FROM `user` WHERE `email`=?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, email);

        ResultSet rs = ps.executeQuery();
        rs.next();

        // Return true if at least one row is found (email exists)
        return rs.getInt(1) > 0;
    }











    public String generateVerificationCode() {
        // Longueur du code
        int codeLength = 6;

        // Ensemble de caractères possibles
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        // Générateur de nombres aléatoires
        Random random = new Random();

        // StringBuilder pour construire le code
        StringBuilder sb = new StringBuilder();

        // Boucle pour générer le code
        for (int i = 0; i < codeLength; i++) {
            sb.append(characters.charAt(random.nextInt(characters.length())));
        }

        // Retourne le code généré
        return sb.toString();
    }}

/*
    public void envoyer_code(String email) throws SQLException, MessagingException {

        // Vérifier si l'adresse email existe
        boolean emailExists = checkEmailExists(email);

        if (emailExists) {
            // Générer un code de vérification
            int verificationCode = generateRandomVerificationCode();

            // Préparer le contenu de l'email
            StringBuilder content = new StringBuilder("<h1>Voici votre code de vérification :</h1>");
            content.append("<h2>").append(verificationCode).append("</h2>");

            // Envoyer l'email
            Email emailObject = new Email("serviceclient619@gmail.com", "25163816");
            emailObject.setFrom("serviceclient@gmail.com", "E-skills");
            emailObject.setSubject("Récupérer votre mot de passe");
            emailObject.setContent(content.toString(), "text/html");
            emailObject.addRecipient(email);
            emailObject.send();

            // Stocker le code de vérification temporairement (par exemple, variable de session)
            verificationCodeHolder.setVerificationCode(verificationCode); // Remplacez par votre mécanisme de stockage spécifique

            // Afficher un message de succès (optionnel)
            System.out.println("Code de vérification envoyé avec succès à " + email);
        } else {
            // Afficher un message d'erreur (optionnel)
            System.out.println("Adresse email non trouvée : " + email);
        }
    }

    // En supposant que vous avez défini une classe ou une méthode pour stocker le code de vérification
    private static class VerificationCodeHolder {
        private int verificationCode;

        public void setVerificationCode(int verificationCode) {
            this.verificationCode = verificationCode;
        }

        public int getVerificationCode() {
            return verificationCode;
        }
    }

    // En supposant que vous avez implémenté la méthode generateRandomVerificationCode()

    public String forgotcrudpasss(String mail) throws SQLException {

        String a="";
        Statement ste = cnx.createStatement();
        ResultSet rs=ste.executeQuery("SELECT * FROM `user` WHERE mail='"+mail+"' ;");
        while (rs.next()) {
            a=rs.getString("mail");
            System.out.println(a);
        }
        return a;
    }
    public String getMailpersonne(String mail) throws SQLException {
        String a="";
        Statement ste = cnx.createStatement();
        ResultSet rs=ste.executeQuery("SELECT * FROM `user` WHERE mail='"+mail+"' ;");
        while (rs.next()) {
            a=rs.getString("mail");
        }
        return a;

    }





    public Set<User> getEtudiants() throws SQLException {
        Set<User> etudiants = new HashSet<>();

        String req = "SELECT * FROM `user` WHERE `role`=?";
        
        PreparedStatement ps = ps.prepareStatement(req);
        ps.setInt(1, 1); // Remplacez par la valeur du rôle "étudiant"

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            //int id = rs.getInt("id");
            String nom = rs.getString("nom");
            String prenom = rs.getString("prenom");
            String email = rs.getString("email");
            // ... (ajoutez d'autres attributs de la table Utilisateur si nécessaire)

            // Créez un objet Utilisateur et ajoutez-le à la liste
            etudiants.add(new User( nom, prenom, email, 1)); // Remplacez 1 par la valeur réelle du rôle
        }

        return etudiants;
    }

 */






 






