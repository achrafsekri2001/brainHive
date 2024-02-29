package edu.esprit.services;

import edu.esprit.entities.Messagerie;

import java.sql.*;
import java.util.*;
import java.util.Date;

public abstract class MessagerieService implements IService<Messagerie> {
   /* private Connection connection;

    public MessagerieService() {
        // Initialisation de la connexion à la base de données
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pidevusers", "username", "password");
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la connexion à la base de données: " + ex.getMessage());
        }
    }

    @Override
    public void ajouter(Messagerie messagerie) {
        try {
            PreparedStatement pre = connection.prepareStatement("INSERT INTO `message` (`id_disc`,`id_sender`,`message`) VALUES (?,?,?)");
            pre.setInt(1, messagerie.getIdDiscussion());
            pre.setInt(2, messagerie.getIdEmetteur());
            pre.setString(3, messagerie.getMessage());
            pre.executeUpdate();
            System.out.println("Message ajouté avec succès !");
        } catch (SQLException ex) {
            System.out.println("Exception lors de l'ajout du message: " + ex.getMessage());
        }
    }

    @Override
    public void modifier(Messagerie messagerie) {
        try {
            PreparedStatement pst = connection.prepareStatement("UPDATE message SET id_disc = ?, id_sender = ?, message = ? WHERE id_message = ?");
            pst.setInt(1, messagerie.getIdDiscussion());
            pst.setInt(2, messagerie.getIdEmetteur());
            pst.setString(3, messagerie.getMessage());
            pst.setInt(4, messagerie.getIdMessage());
            int rowsUpdated = pst.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Message modifié avec succès !");
            } else {
                System.out.println("Aucune modification effectuée, le message avec l'ID " + messagerie.getIdMessage() + " n'existe pas.");
            }
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la modification du message: " + ex.getMessage());
        }
    }



    public void supprimer(Messagerie messagerie) {
        try {
            String requete = "DELETE FROM message WHERE `id_message`=?";
            PreparedStatement pst = connection.prepareStatement(requete);
            pst.setInt(1, messagerie.getIdMessage());
            pst.executeUpdate();
            System.out.println("Message supprimé avec succès !");
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la suppression du message: " + ex.getMessage());
        }
    }

    @Override
    public Set<Messagerie> getAll() {
        Set<Messagerie> listOfMsgs = new HashSet<>();
        try {
            Statement ste = connection.createStatement();
            ResultSet rs = ste.executeQuery("SELECT * FROM message");
            while (rs.next()) {
                int idRecepteur = rs.getInt("id_recepteur");
                int idEmetteur = rs.getInt("id_emetteur");
                int idDiscussion= rs.getInt("id_discution");
                int idMessage = rs.getInt("id_message");
                Date date = rs.getDate("date_message");
                String contenu = rs.getString("contenu_message");
                Messagerie messagerie = new Messagerie(idMessage, idEmetteur, idRecepteur, idDiscussion,date, contenu);
                listOfMsgs.add(messagerie);
            }
            System.out.println("Liste des messages récupérée avec succès !");
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la récupération des messages: " + ex.getMessage());
        }
        return listOfMsgs;
    }

    @Override
    public Messagerie getOneByID(int id) {
        try {
            PreparedStatement pst = connection.prepareStatement("SELECT * FROM message WHERE id_message = ?");
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                int idRecepteur = rs.getInt("id_recepteur");
                int idEmetteur = rs.getInt("id_emetteur");
                int idDiscussion= rs.getInt("id_discution");
                Date date = rs.getDate("date_message");
                String contenu = rs.getString("contenu_message");
                return new Messagerie(id, idEmetteur, idRecepteur,idDiscussion, date, contenu);
            }
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la récupération du message: " + ex.getMessage());
        }
        return null;
    }*/

}
