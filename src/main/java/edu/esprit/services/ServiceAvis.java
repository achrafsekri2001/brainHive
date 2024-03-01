package edu.esprit.services;

import edu.esprit.entities.AvisProduit;
import edu.esprit.utils.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServiceAvis {

    Connection cnx = DataSource.getInstance().getCnx();

    public void ajouter(AvisProduit avis) throws SQLException {
        String req = "INSERT INTO avis (id_produit, contenu, user_id, user_name, user_img) VALUES (?, ?, ?, ?, ?)";

            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, avis.getId_produit());
            ps.setString(2, avis.getContenu());
            ps.setInt(3, avis.getUser_id());
            ps.setString(4, avis.getUser_name());
            ps.setString(5, avis.getUser_img());
            ps.executeUpdate();
            System.out.println("Avis ajouté avec succès !");

    }

    public void modifier(AvisProduit avis) {
        String req = "UPDATE avis_produit SET contenu = ? WHERE id_commentaire = ?";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, avis.getContenu());
            ps.setInt(2, avis.getId_commentaire());
            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Avis modifié avec succès !");
            } else {
                System.out.println("Aucun avis modifié. Aucun avis avec cet ID n'a été trouvé.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceAvis.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void supprimer(AvisProduit avis) {
        String req = "DELETE FROM avis_produit WHERE id_commentaire = ?";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, avis.getId_commentaire());
            int rowsDeleted = ps.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Avis supprimé avec succès !");
            } else {
                System.out.println("Aucun avis supprimé. Aucun avis avec cet ID n'a été trouvé.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceAvis.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


        public List<AvisProduit> getAll() {
            List<AvisProduit> avisList = new ArrayList<>();
            String req = "SELECT * FROM avis_produit";
            try {
                Statement st = cnx.createStatement();
                ResultSet rs = st.executeQuery(req);
                while (rs.next()) {
                    int id_commentaire = rs.getInt("id_commentaire");
                    int user_id = rs.getInt("user_id");
                    String contenu = rs.getString("contenu");
                    int id_produit = rs.getInt("id_produit");
                    String user_name = rs.getString("user_name");
                    String user_img = rs.getString("user_img");
                    AvisProduit avis = new AvisProduit(id_commentaire, user_id, contenu, id_produit, user_name, user_img);
                    avisList.add(avis);
                }
            } catch (SQLException e) {
                System.out.println("Erreur lors de la récupération des avis : " + e.getMessage());
            }
            return avisList;
        }

        public AvisProduit getOneById(int id) {
            AvisProduit avis = null;
            String req = "SELECT * FROM avis WHERE id_commentaire = ?";
            try {
                PreparedStatement ps = cnx.prepareStatement(req);
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    int id_commentaire = rs.getInt("id_commentaire");
                    int user_id = rs.getInt("user_id");
                    String contenu = rs.getString("contenu");
                    int id_produit = rs.getInt("id_produit");
                    String user_name = rs.getString("user_name");
                    String user_img = rs.getString("user_img");
                    avis = new AvisProduit(id_commentaire, user_id, contenu, id_produit, user_name, user_img);
                }
            } catch (SQLException e) {
                System.out.println("Erreur lors de la récupération de l'avis : " + e.getMessage());
            }
            return avis;
        }
}
