package edu.esprit.Services;

import edu.esprit.entities.User;
import edu.esprit.utils.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        public void modifier(User t) throws SQLException {

            String req = "UPDATE `user` SET `email`=?, `nom`=?, `prenom`=?, `password`=?, `roles`=? WHERE `id`=?";
            PreparedStatement ps;
            try {
                ps = cnx.prepareStatement(req);

                // Set parameters for the prepared statement
                ps.setString(1, t.getEmail());
                ps.setString(2, t.getNom());
                ps.setString(3, t.getPrenom());
                ps.setString(4, t.getPassword());
                ps.setInt(5, t.getRoles());
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






    public void save_modification(User u) throws SQLException {

        String req = "UPDATE `user` SET `email`=?, `nom`=?, `prenom`=?, `password`=? WHERE `id`=? AND `roles`=0";
        PreparedStatement ps;

        try {
            ps = cnx.prepareStatement(req);

            // Set parameters for the prepared statement
            ps.setString(1, u.getEmail());
            ps.setString(2, u.getNom());
            ps.setString(3, u.getPrenom());
            ps.setString(4, u.getPassword());
            ps.setInt(5, u.getId());

            // Execute the update query
            int rowsUpdated = ps.executeUpdate();

            if (rowsUpdated > 0) {
                // Successful modification
                System.out.println("Utilisateur modifié : " + u.getNom());
            } else {
                // No rows were updated, indicating a potential error
                System.out.println("Aucune modification effectuée. Veuillez vérifier que l'utilisateur a le rôle 0 (Admin).");
            }

        } catch (SQLException e) {
            System.out.println("Erreur lors de la modification de l'utilisateur : " + e.getMessage());
            throw e; // Re-throw the exception to propagate it up the call stack
        }
    }




}



