package edu.esprit.services;

import edu.esprit.entities.Utilisateur;
import edu.esprit.utils.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserCRUD {
    Connection cnx = DataSource.getInstance().getCnx();

    public void ajouter(Utilisateur utilisateur) throws SQLException {
        /*String req = "INSERT INTO `personne`(`nom`, `prenom`) VALUES ('"+personne.getNom()+"','"+personne.getPrenom()+"')";
        try {
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Personne added !");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }*/
        String req = "INSERT INTO `utilisateur`(`userName`, `imgUser`) VALUES (?,?)";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1,utilisateur.getUserName());
        ps.setString(2,utilisateur.getImgUser());
        ps.executeUpdate();
        System.out.println("Personne added !");
    }



    public Utilisateur getOneByID(int id) {
        Utilisateur utilisateur = null;
        try {
            String req = "SELECT * FROM utilisateur WHERE id=?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String nom = rs.getString("userName");
                String image = rs.getString("imgUser");

                utilisateur = new Utilisateur(id, nom, image);
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération de l'utilisateur : " + e.getMessage());
        }
        return utilisateur;
    }


        public Utilisateur getByUsername(String username) {
           List<Utilisateur> utilisateurs = new ArrayList<>();

            for (Utilisateur utilisateur : utilisateurs) {
                if (utilisateur.getUserName().equals(username)) {
                    return utilisateur;
                }
            }
            return null; // Retourne null si aucun utilisateur n'est trouvé avec ce nom d'utilisateur
        }
    }



