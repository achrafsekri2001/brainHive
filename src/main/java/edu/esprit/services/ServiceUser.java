package edu.esprit.services;

import edu.esprit.entities.User;
import edu.esprit.utils.DataSource;
import javafx.scene.control.TextField;

import java.sql.*;
import java.util.*;

public class ServiceUser implements IService<User> {

    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(User t) {

        String req = "INSERT INTO `utilisateur`(`email`, `nom`, `prenom`, `password`, `role`) VALUES (?, ?, ?, ?, ?)";
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
    public void modifier(User t) {String req = "UPDATE `utilisateur` SET `email`=?, `nom`=?, `prenom`=?, `password`=?, `role`=? WHERE `id`=?";
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
    public void modifier(User t) {


        String req = "UPDATE `utilisateur` SET `email` = ?, `nom` = ?, `prenom` = ?, `password` = ? WHERE `id` = ?";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, t.getEmail());
            ps.setString(2, t.getNom());
            ps.setString(3, t.getPrenom());
            ps.setString(4, t.getPassword());
            ps.setInt(5, t.getId()); // Use the utilisateur's ID for identification
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

            String req = "UPDATE `utilisateur` SET `email`=?, `nom`=?, `prenom`=?, `password`=?,  WHERE `id`=?";
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
        String req = "UPDATE `utilisateur` SET `nom`=?, `prenom`=?, `email`=?, `password`=? WHERE `id`=?";
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

        String req = "DELETE FROM `utilisateur` WHERE `id`=?";
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
        List<User> test = new ArrayList<>();
        String req = "SELECT * FROM `utilisateur`";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String email = rs.getString("email");
                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");
                String password = rs.getString("password");
                int role = rs.getInt("role");

                // Créer un nouvel utilisateur avec les données récupérées
                User utilisateur = new User(id, email, nom, prenom, password, role);

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
        String req = "SELECT * FROM `utilisateur` WHERE `id`=?";
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
                utilisateur.setRoles(rs.getInt("role"));
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération de l'utilisateur : " + e.getMessage());
        }
        return utilisateur;
    }

    public User getUser(String username, String password) throws SQLException {
        User u = null;
        String sql = "SELECT * FROM utilisateur WHERE prenom = ? AND password = ?";
        PreparedStatement stmt = cnx.prepareStatement(sql);
        stmt.setString(1, username);
        stmt.setString(2, password);

        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            u = new User();
        }
        return u;
    }

    public String forgotcrudpasss(String mail) throws SQLException {

        String a = "";
        Statement ste = cnx.createStatement();
        ResultSet rs = ste.executeQuery("SELECT * FROM `utilisateur` WHERE email='" + mail + "' ;");
        while (rs.next()) {
            a = rs.getString("mail");
            System.out.println(a);
        }
        return a;
    }

    public String getMailpersonne(String mail) throws SQLException {
        String a = "";
        Statement ste = cnx.createStatement();
        ResultSet rs = ste.executeQuery("SELECT * FROM `utilisateur` WHERE email='" + mail + "' ;");
        while (rs.next()) {
            a = rs.getString("email");
        }
        return a;

    }

    public User findByEmail(String email) {

        User utilisateur = null;
        String req = "SELECT * FROM `utilisateur` WHERE `email`=?";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                // Create a new User object with the retrieved data
                utilisateur = new User();
                utilisateur.setId(rs.getInt("id"));
                utilisateur.setEmail(rs.getString("email"));
                utilisateur.setNom(rs.getString("nom"));
                utilisateur.setPrenom(rs.getString("prenom"));
                utilisateur.setPassword(rs.getString("password"));
                utilisateur.setRoles(rs.getInt("role"));
            }
        } catch (SQLException e) {
            System.out.println("Error while finding utilisateur by email: " + e.getMessage());
        }
        return utilisateur;
    }

    public User findByRole(int role) {
        User utilisateur = null;
        String req = "SELECT * FROM `utilisateur` WHERE `role`=?";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, role);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                // Créer un nouvel objet User avec les données récupérées
                utilisateur = new User();
                utilisateur.setId(rs.getInt("id"));
                utilisateur.setEmail(rs.getString("email"));
                utilisateur.setNom(rs.getString("nom"));
                utilisateur.setPrenom(rs.getString("prenom"));
                utilisateur.setPassword(rs.getString("password"));
                utilisateur.setRoles(rs.getInt("role"));
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la recherche d'utilisateur par rôle: " + e.getMessage());
        }
        return utilisateur;
    }


    public Set<User> getUsersByRole(int role) throws SQLException {
        Set<User> users = new HashSet<>();

        String req = "SELECT * FROM `utilisateur` WHERE `role`=?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, role); // Set role to 1

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            // Create a new User object with the retrieved data
            User utilisateur = new User();
            utilisateur.setId(rs.getInt("id"));
            utilisateur.setEmail(rs.getString("email"));
            utilisateur.setNom(rs.getString("nom"));
            utilisateur.setPrenom(rs.getString("prenom"));
            utilisateur.setPassword(rs.getString("password")); // Consider if displaying password is appropriate
            utilisateur.setRoles(rs.getInt("role"));

            // Add the utilisateur to the set
            users.add(utilisateur);
        }

        return users;
    }

    public Set<User> getUsersEnseignant(int role) throws SQLException {
        Set<User> users = new HashSet<>();

        String req = "SELECT * FROM `utilisateur` WHERE `role`=?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, role); // Set role to 2

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            // Create a new User object with the retrieved data
            User utilisateur = new User();
            utilisateur.setId(rs.getInt("id"));
            utilisateur.setEmail(rs.getString("email"));
            utilisateur.setNom(rs.getString("nom"));
            utilisateur.setPrenom(rs.getString("prenom"));
            utilisateur.setPassword(rs.getString("password")); // Consider if displaying password is appropriate
            utilisateur.setRoles(rs.getInt("role"));

            // Add the utilisateur to the set
            users.add(utilisateur);
        }

        return users;
    }


    public void delete(User selectedUser) throws SQLException {
        if (selectedUser == null) {
            throw new IllegalArgumentException("User cannot be null");
        }

        // Vérifier l'existence de l'utilisateur (optionnel)
        if (userExists(selectedUser.getId())) {
            String req = "DELETE FROM `utilisateur` WHERE `id` = ?";

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
        String req = "SELECT COUNT(*) FROM `utilisateur` WHERE `id` = ?";
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
            String req = "UPDATE `utilisateur` SET `nom` = ?, `prenom` = ?, `email` = ?, `password` = ? WHERE `id` = ?";

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

        String req = "SELECT COUNT(*) FROM `utilisateur` WHERE `email`=?";
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
    }

    public User getByUsername(String receiverName) {
        return null;
    }
}






 






