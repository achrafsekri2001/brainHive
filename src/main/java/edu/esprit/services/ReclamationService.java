package edu.esprit.services;

import edu.esprit.entities.Reclamation;
import edu.esprit.entities.Utilisateur;
import edu.esprit.utils.DataSource;

import java.sql.*;

import java.util.*;




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
            while (res.next()){
                // Récupérer les détails de la réclamation depuis le ResultSet
                int id = res.getInt(2);
                String user = res.getString("user");
                String contenu = res.getString("contenu");
                String objet = res.getString("objet");
              //  String imgUser = res.getString("imgUser");
                Timestamp date = res.getTimestamp("date");

                // Créer un objet Reclamation et l'ajouter à la liste
                Reclamation reclamation = new Reclamation( contenu, objet,date, id);
                reclamations.add(reclamation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reclamations;
    }
    public void ajouterReclamation(Reclamation reclamtion) throws SQLException {
        String req = "INSERT INTO `reclamation`( `idUser`,`contenu`, `objet`, `date`) VALUES ( ?,?, ?, ?,?)";
        PreparedStatement ps = cnx.prepareStatement(req);
        // Utiliser l'identifiant de l'utilisateur associé à la réclamation
        ps.setInt(1, reclamtion.getIdReclamation()); // Assurez-vous que getIdUser() retourne l'identifiant de l'utilisateur
        ps.setString(2, reclamtion.getUser());
        ps.setString(3, reclamtion.getContenu());
        ps.setString(4, reclamtion.getObjet());
        //    ps.setDate(6, (Date) reclamtion.getDate());
        ps.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
        //ps.setString(6, reclamtion.getImgUser());

        ps.executeUpdate();
        System.out.println("Reclamation added !");
    }


    public void modifier(Reclamation reclamtion) {

    }


    public void supprimer(int id) {
        try  {
            PreparedStatement pre = cnx.prepareStatement( "DELETE FROM `reclamation` WHERE `id`=?");
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
                UserCRUD su = new UserCRUD();
                Utilisateur utilisateur= su.getOneByID(idUser);
                Reclamation reclamation1 =new Reclamation(contenu,objet,date,idUser);
                return reclamation1 ;
            } else {
                System.out.println("Reclamation with ID " + id + " not found");
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving reclamation: " + e.getMessage());
            return null;
        }
    }


    public  List<Reclamation> getAll() {
        List<Reclamation> reclamations = new ArrayList<>();

        String req = "Select * from reclamation";
        try {
            Statement st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);
            while (res.next()){
                Reclamation rec = new Reclamation();
                rec.setIdReclamation(res.getInt(1));
                rec.setObjet(res.getString(4));
                rec.setContenu(res.getString(3));
                rec.setDate(res.getTimestamp(5));
                Utilisateur user = new UserCRUD().getOneByID(res.getInt(2));
                 rec.setUtilisateur(user);
                reclamations.add(rec);

                //  Utilisateur utilisateur = new Utilisateur(4,"feriel ben mamia","f");
                // utilisateur.setId(idUser);
                //    Reclamation reclamation = new Reclamation(user, contenu, objet,imgUser,date,id);
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
            while(rs.next()){
                int id = rs.getInt("idReclamation");
              //  String nom = rs.getString("user");
                String contenu = rs.getString("contenu");
                String objet = rs.getString("objet");
              //  String imgUser = rs.getString("imgUser");
                Timestamp date= rs.getTimestamp("date");
                int idUser = rs.getInt("idUser");

                Reclamation p = new Reclamation( contenu, objet,  date, id, idUser);
                reclamations.add(p);
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération des reclamations par nom : " + e.getMessage());
        }

        return reclamations;
    }

}