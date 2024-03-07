package edu.esprit.services;

import edu.esprit.controllers.GlobalHolder;
import edu.esprit.entities.AvisProduit;
import edu.esprit.entities.User;
import edu.esprit.utils.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServiceAvis {

    Connection cnx = DataSource.getInstance().getCnx();

    public void ajouter(AvisProduit avis) throws SQLException {
        String req = "INSERT INTO avisproduit (idProduit, contenu, idUser, note) VALUES (?, ?, ?, ?)";

        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, avis.getIdProduit());
        ps.setString(2, avis.getContenu());
        ps.setInt(3, GlobalHolder.currentUser.getId());
        ps.setInt(4, avis.getNote());

        ps.executeUpdate();
        System.out.println("Avis ajouté avec succès !");
    }


   /* public void modifier(AvisProduit avis) {
        String req = "UPDATE avis_produit SET contenu = ? WHERE id_commentaire = ?";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, avis.getContenu());
            ps.setInt(2, avis.getId);
            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Avis modifié avec succès !");
            } else {
                System.out.println("Aucun avis modifié. Aucun avis avec cet ID n'a été trouvé.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceAvis.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/

    public void supprimer(int  id) {
        String req = "DELETE FROM avisproduit WHERE id = ?";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1,  id);
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
        String req = "SELECT * FROM avisproduit";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                int id_commentaire = rs.getInt("id");
                User user = new ServiceUser().getOneByID(rs.getInt("idUser"));
                String contenu = rs.getString("contenu");
                int id_produit = rs.getInt("id_produit");
                int note = rs.getInt("note");

                AvisProduit avis = new AvisProduit(id_commentaire, user, contenu, id_produit, note);
                avisList.add(avis);
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération des avis : " + e.getMessage());
        }
        return avisList;
    }

    public AvisProduit getOneById(int id) {
        AvisProduit avis = null;
        String req = "SELECT * FROM avisproduit WHERE id = ?";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int id_commentaire = rs.getInt("id");
                User user = new ServiceUser().getOneByID(rs.getInt("idUser"));
                String contenu = rs.getString("contenu");
                int id_produit = rs.getInt("id_produit");
                int note = rs.getInt("note");

                avis = new AvisProduit(id_commentaire, user, contenu, id_produit, note);
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération de l'avis : " + e.getMessage());
        }
        return avis;
    }

    public double getMoyenneNotesParProduit(int idProduit) {
        String req = "SELECT AVG(note) AS moyenne FROM avisproduit WHERE idProduit = ?";
        double moyenne = 0;

        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, idProduit);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                moyenne = rs.getDouble("moyenne");
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération de la moyenne des notes : " + e.getMessage());
        }

        return moyenne;
    }

    public List<AvisProduit> recupererReclamationsParProduit(int idProduit) throws SQLException {
        List<AvisProduit> reclamations = new ArrayList<>();
        String query = "SELECT * FROM avisproduit WHERE idProduit = ?";
        try (PreparedStatement statement = cnx.prepareStatement(query)) {
            statement.setInt(1, idProduit);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    AvisProduit avis = new AvisProduit();
                    avis.setId(resultSet.getInt("id"));
                    avis.setIdProduit(resultSet.getInt("idProduit"));
                    avis.setUser(new ServiceUser().getOneByID(resultSet.getInt("idUser")));
                    avis.setContenu(resultSet.getString("contenu"));
                    avis.setNote(resultSet.getInt("note"));
                    // Ajoute l'avis à la liste des réclamations
                    reclamations.add(avis);
                }
            }
        }
        return reclamations;
    }
    public List<AvisProduit> recupererAvisParUtilisateur(int idUtilisateur) {
        List<AvisProduit> avisUtilisateur = new ArrayList<>();
        String req = "SELECT * FROM avisproduit WHERE idUser = ?";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, idUtilisateur);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int idAvis = rs.getInt("id");
                User user = new ServiceUser().getOneByID(rs.getInt("idUser"));
                int idProduit = rs.getInt("idProduit");
                String contenu = rs.getString("contenu");
                int note = rs.getInt("note");

                // Créer un objet AvisProduit pour chaque ligne de résultat
                AvisProduit avis = new AvisProduit(idAvis, user, contenu, idProduit, note);
                avisUtilisateur.add(avis);
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération des avis de l'utilisateur : " + e.getMessage());
        }
        return avisUtilisateur;
    }
}
