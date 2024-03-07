package edu.esprit.services;

import edu.esprit.controllers.GlobalHolder;
import edu.esprit.entities.Messagerie;
import edu.esprit.utils.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MessagerieService {

    static Connection connection = DataSource.getInstance().getCnx();

    // Méthode pour ajouter un message à la base de données
    public void ajouter(Messagerie messagerie) {
        try {
            PreparedStatement pre = connection.prepareStatement("INSERT INTO message (`idEmetteur`,`idRecepteur`,`date`,`contenu`) VALUES (?,?,?,?)");
            pre.setInt(1,  GlobalHolder.getcurrentUser().getId());
            pre.setInt(2, 2/*messagerie.getReceiver_message().getId()*/);
//            pre.setInt(1, messagerie.getSender_message().getId());
//            pre.setInt(2, messagerie.getReceiver_message().getId());
            pre.setString(4, messagerie.getContenu());
            pre.setTimestamp(3, new Timestamp(System.currentTimeMillis()));

            pre.executeUpdate();
            System.out.println("Messaagerie added!");
        } catch (SQLException e) {
            System.err.println("Error adding Messaagerie: " + e.getMessage());
        }
    }

//     Méthode pour ajouter une nouvelle discussion à la base de données
//    public void ajouterDiscussion(Messagerie nouvelleDiscussion) {
//        try {
//            PreparedStatement pre = connection.prepareStatement("INSERT INTO `discussion` (`idDiscussion`, `contenu`) VALUES (?, ?)");
//            pre.setInt(1, nouvelleDiscussion.getIdDiscussion());
//            pre.setString(2, nouvelleDiscussion.getContenu());
//            pre.executeUpdate();
//            System.out.println("Nouvelle discussion ajoutée avec succès !");
//        } catch (SQLException ex) {
//            System.out.println("Exception lors de l'ajout de la nouvelle discussion: " + ex.getMessage());
//        }
//    }

    public void modifier(Messagerie messagerie) {
        try {
            PreparedStatement pre = connection.prepareStatement("UPDATE message SET `idEmetteur`=?,`idRecepteur`=?,`date`=?,`contenu`=? WHERE `id`=?");

            pre.setTimestamp(3, new java.sql.Timestamp(messagerie.getDate().getTime()));

            pre.setString(4, messagerie.getContenu());
//        pre.setInt(1, messagerie.getReceiver_message().getId());        pre.setInt(2, messagerie.getSender_message().getId());
//        pre.setInt(2, messagerie.getSender_message().getId());
            pre.setInt(1, 2);
            pre.setInt(2, 1);
            pre.setInt(5, messagerie.getIdMessage());
            pre.executeUpdate();
            System.out.println("Messagerie updated!");
        } catch (SQLException e) {
            System.err.println("Error updating Messagerie: " + e.getMessage());
        }
    }


    public void supprimer(int id) {

        try {
            PreparedStatement pre = connection.prepareStatement("DELETE FROM `message` WHERE `id`=?");
            pre.setInt(1, id);
            pre.executeUpdate();
            System.out.println("Messagerie deleted!");
        } catch (SQLException e) {
            System.err.println("Error deleting Messagerie: " + e.getMessage());
        }
    }

    // Méthode pour récupérer tous les messages de la base de données
    public List<Messagerie> getAll() {
        List<Messagerie> listOfMsgs = new ArrayList<>();
        String req = "Select * from message";
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Messagerie mess = new Messagerie();
                mess.setIdMessage(rs.getInt("id"));
                mess.setContenu(rs.getString("contenu"));
                mess.setDate(rs.getTimestamp("date"));
                mess.setSender_message(GlobalHolder.getcurrentUser());


                // Utilisez ServiceUtilisateur pour obtenir l'Utilisateur par ID
//                Utilisateur sender = new UserCRUD().getOneByID(rs.getInt("idEmetteur"));
//                Utilisateur receiver = new UserCRUD().getOneByID(rs.getInt("idRecepteur"));
//                mess.setSender_message(sender);
//                mess.setReceiver_message(receiver);

                listOfMsgs.add(mess);
            }
        } catch (SQLException e) {
            System.err.println("Error getting Messagerie: " + e.getMessage());
        }
        return listOfMsgs;
    }


    public Messagerie getOneByID(int id) {
        String req = "SELECT * FROM message WHERE `id` = ?";
        try (PreparedStatement ps = connection.prepareStatement(req)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Messagerie mess = new Messagerie();
                    mess.setIdMessage(rs.getInt("id"));
                    mess.setContenu(rs.getString("contenu"));
                    mess.setDate(rs.getTimestamp("date"));

                    // Utilisez ServiceUtilisateur pour obtenir l'Utilisateur par ID
//                    Utilisateur sender = new UserCRUD().getOneByID(rs.getInt("idEmetteur"));
//                    Utilisateur receiver = new UserCRUD().getOneByID(rs.getInt("idRecepteur"));
//                    mess.setSender_message(sender);
//                    mess.setReceiver_message(receiver);

                    return mess;
                }
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving Messagerie: " + e.getMessage());
        }
        return null;
    }

    public List<Messagerie> getAllMessagesByReciverAndSender(int senderId, int receiverId) {
        List<Messagerie> messages = new ArrayList<>();
        String req = "SELECT * FROM message WHERE (idEmetteur = ? AND idRecepteur = ?) OR (idEmetteur = ? AND idRecepteur = ?) ORDER BY date";
        try (PreparedStatement ps = connection.prepareStatement(req)) {
            ps.setInt(1, senderId);
            ps.setInt(2, receiverId);
            ps.setInt(3, receiverId);
            ps.setInt(4, senderId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Messagerie mess = new Messagerie();
                    mess.setIdMessage(rs.getInt("id"));
                    mess.setContenu(rs.getString("contenu"));
                    mess.setDate(rs.getTimestamp("date"));

//                    Utilisateur sender = new UserCRUD().getOneByID(rs.getInt("sender_id"));
//                    Utilisateur receiver = new UserCRUD().getOneByID(rs.getInt("reciver_id"));
//                    mess.setSender_message(sender);
//                    mess.setReceiver_message(receiver);

                    messages.add(mess);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error getting Messagerie: " + e.getMessage());
        }
        return messages;
    }


}
