package edu.esprit.services;

import edu.esprit.controllers.GlobalHolder;
import edu.esprit.entities.Reclamation;
import edu.esprit.utils.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ReclamationService {

    static Connection cnx = DataSource.getInstance().getCnx();

    public List<Reclamation> afficherReclamationsParUtilisateur(int idUser) {
        List<Reclamation> reclamations = new ArrayList<>();

        // Remplacez la requête SQL par celle qui récupère les réclamations par utilisateur
        String req = "SELECT * FROM reclamation WHERE idUser = ?";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, idUser);
            ResultSet res = ps.executeQuery();
            while (res.next()) {
                // Récupérer les détails de la réclamation depuis le ResultSet
                int id = res.getInt(2);
                String user = res.getString("user");
                String contenu = res.getString("contenu");
                String objet = res.getString("objet");
                //  String imgUser = res.getString("imgUser");
                Timestamp date = res.getTimestamp("date");

                // Créer un objet Reclamation et l'ajouter à la liste
                Reclamation reclamation = new Reclamation(contenu, objet, date, id);
                reclamations.add(reclamation);
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération des réclamations par utilisateur : " + e.getMessage());
        }

        return reclamations;
    }

    public void ajouterReclamation(Reclamation reclamtion) throws SQLException {
        String req = "INSERT INTO `reclamation`( `idUser`,`contenu`, `objet`, `date`) VALUES ( ?,?, ?,?)";
        PreparedStatement ps = cnx.prepareStatement(req);
        // Utiliser l'identifiant de l'utilisateur associé à la réclamation
        ps.setInt(1, GlobalHolder.getcurrentUser().getId());
        ps.setString(2, reclamtion.getContenu());
        ps.setString(3, reclamtion.getObjet());
        ps.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
        //ps.setString(6, reclamtion.getImgUser());

        ps.executeUpdate();
        System.out.println("Reclamation added !");
    }


    public void modifier(Reclamation reclamtion) {

    }


    public void supprimer(int id) {
        try {
            PreparedStatement pre = cnx.prepareStatement("DELETE FROM `reclamation` WHERE `id`=?");
            pre.setInt(1, id);
            pre.executeUpdate();
            System.out.println("Reclamation deleted!");
        } catch (SQLException e) {
            System.err.println("Error deleting reclamation: " + e.getMessage());
        }

    }


    public Reclamation getOneById(int id) {
        String req = "SELECT * FROM reclamation WHERE idUser = ?";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int idUser = rs.getInt("idUser");
                String objet = rs.getString("objet");
                String contenu = rs.getString("contenu");
                Timestamp date = rs.getTimestamp("date");
//                UserCRUD su = new UserCRUD();
//                Utilisateur utilisateur= su.getOneByID(idUser);
                Reclamation reclamation1 = new Reclamation(contenu, objet, date, idUser);
                return reclamation1;
            } else {
                System.out.println("Reclamation with ID " + id + " not found");
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving reclamation: " + e.getMessage());
            return null;
        }
    }


    public List<Reclamation> getAll() {
        List<Reclamation> reclamations = new ArrayList<>();

        String req = "Select * from reclamation";
        try {
            Statement st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);
            while (res.next()) {
                Reclamation rec = new Reclamation();
//                commentaire.setId(pst.getResultSet().getInt("id"));
                rec.setIdReclamation(res.getInt("id"));
                rec.setContenu(res.getString("contenu"));
                rec.setObjet(res.getString("objet"));
                rec.setDate(res.getTimestamp("date"));
                rec.setIdUser(res.getInt("idUser"));
                rec.setUtilisateur(new ServiceUser().getOneByID(res.getInt("idUser")));
                reclamations.add(rec);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return reclamations;
    }


    public List<Reclamation> getReclamationsByUserName(String texteRecherche) {
        List<Reclamation> reclamations = new ArrayList<>();

        String req = "SELECT * FROM reclamation WHERE reclamation.idUser LIKE ?";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, "%" + texteRecherche + "%"); // Utilisation de LIKE pour rechercher les réclamations par nom d'utilisateur partiel
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("idReclamation");
                //  String nom = rs.getString("user");
                String contenu = rs.getString("contenu");
                String objet = rs.getString("objet");
                //  String imgUser = rs.getString("imgUser");
                Timestamp date = rs.getTimestamp("date");
                int idUser = rs.getInt("idUser");

                Reclamation p = new Reclamation(contenu, objet, date, id, idUser);
                reclamations.add(p);
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération des reclamations par nom : " + e.getMessage());
        }

        return reclamations;
    }

}