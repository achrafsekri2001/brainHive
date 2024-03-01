package edu.esprit.services;

import edu.esprit.entities.Utilisateur;
import edu.esprit.utils.DataSource;

import java.sql.*;
import java.util.HashSet;
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

    public Set<Utilisateur> getAll() {
    Set<Utilisateur> utilisateurs = new HashSet<>();

    String req = "Select * from utilisateur";
        try {
        Connection cnx = null;
        Statement st = cnx.createStatement();
        ResultSet res = st.executeQuery(req);
        while (res.next()){
            int id = res.getInt("id");
            String nom = res.getString(2);
            String prenom = res.getString("prenom");
            Utilisateur p = new Utilisateur(id,nom,prenom);
            utilisateurs.add(p);
        }
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }

        return utilisateurs;
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

                int note = rs.getInt("note");
                utilisateur = new Utilisateur(id, nom, image);
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération du produit : " + e.getMessage());
        }
        return utilisateur;
    }
    }


