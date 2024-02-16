package edu.esprit.services;


import edu.esprit.entities.Commentaire;
import edu.esprit.utils.DataSource;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;


public class ServiceCommentaire implements IService<Commentaire> {
    Connection cnx = DataSource.getInstance().getCnx();
    @Override
    public void ajouter(Commentaire commentaire) {
        String req="INSERT INTO `commentaire`(`content`, `postId`, `userId`, `parentComment`, `upVotes`, `downVotes`, `isverified`, `numberOfReports`, `createdAt`, `updatedAt`) VALUES ('"+commentaire.getContent()+"','"+commentaire.getPostId()+"','"+commentaire.getUserId()+"','"+commentaire.getParentComment()+"','"+commentaire.getUpVotes()+"','"+commentaire.getDownVotes()+"','"+commentaire.isVerified()+"','"+commentaire.getNumberOfReports()+"','"+commentaire.getCreatedAt()+"','"+commentaire.getUpdatedAt()+"')";
        try {
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Commentaire added !");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }}
      /*  String req="INSERT INTO `commentaire`(`content`, `postId`, `userId`, `parentComment`, `upVotes`, `downVotes`, `isverified`, `numberOfReports`, `createdAt`, `updatedAt`) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1,commentaire.getContent());
            ps.setString(2,commentaire.getPostId());
            ps.setString(3,commentaire.getUserId());
            ps.setString(4,commentaire.getParentComment());
            ps.setInt(5,commentaire.getUpVotes());
            ps.setInt(6,commentaire.getDownVotes());
            ps.setBoolean(7,commentaire.getisverified());
            ps.setInt(8,commentaire.getNumberOfReports());
            ps.setString(9,commentaire.getContent());
            ps.setString(10,commentaire.getContent());
            ps.setString(11,commentaire.getContent());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }*/

    @Override
    public void modifier(Commentaire commentaire) {
        String req = "UPDATE `commentaire` SET `content`=?, `postId`=?, `userId`=?, `parentComment`=?, `upVotes`=?, `downVotes`=?, `isverified`=?, `numberOfReports`=?, `createdAt`=?, `updatedAt`=? WHERE `id`=?";
        try (PreparedStatement ps = cnx.prepareStatement(req)) {
            ps.setString(1, commentaire.getContent());
            ps.setString(2, commentaire.getPostId());
            ps.setString(3, commentaire.getUserId());
            ps.setString(4, commentaire.getParentComment());
            ps.setInt(5, commentaire.getUpVotes());
            ps.setInt(6, commentaire.getDownVotes());
            ps.setBoolean(7, commentaire.isVerified());
            ps.setInt(8, commentaire.getNumberOfReports());
            ps.setTimestamp(9, commentaire.getCreatedAt());
            ps.setTimestamp(10, commentaire.getUpdatedAt());
            ps.setInt(11, commentaire.getId());
            ps.executeUpdate();
            System.out.println("Commentaire modifié !");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }



    @Override
    public void supprimer(int id) {
        String req = "DELETE FROM `commentaire` WHERE `id`=?";
        try (PreparedStatement ps = cnx.prepareStatement(req)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Commentaire supprimé !");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public Set<Commentaire> getAll() {
       Set<Commentaire> commentaires = new HashSet<>();
       String req = "Select * from commentaire";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()){
                int id = rs.getInt(1);
                String content = rs.getNString(2);
                String postId= rs.getNString(3);
                String userId = rs.getNString(4);
                String parentComment = rs.getNString(5);
                int upVotes =rs.getInt(6);
                int downVotes =rs.getInt(7);
                boolean isverified = rs.getBoolean(8);
                int numberOfReports =rs.getInt(9);
                Timestamp createdAt = rs.getTimestamp(10);
                Timestamp updatedAt  = rs.getTimestamp(11)  ;
                Commentaire c = new Commentaire( id,content, postId, userId, parentComment, upVotes, downVotes, isverified, numberOfReports, createdAt, updatedAt);
                Commentaire.add(c);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return commentaires;
    }

    @Override
    public Commentaire getONeById(int id) {
        String req = "SELECT * FROM `commentaire` WHERE `id`=?";
        try (PreparedStatement ps = cnx.prepareStatement(req)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int commentId = rs.getInt(1);
                    String content = rs.getString(2);
                    String postId = rs.getString(3);
                    String userId = rs.getString(4);
                    String parentComment = rs.getString(5);
                    int upVotes = rs.getInt(6);
                    int downVotes = rs.getInt(7);
                    boolean isVerified = rs.getBoolean(8);
                    int numberOfReports = rs.getInt(9);
                    Timestamp createdAt = rs.getTimestamp(10);
                    Timestamp updatedAt = rs.getTimestamp(11);
                    return new Commentaire(commentId, content, postId, userId, parentComment, upVotes, downVotes, isVerified, numberOfReports, createdAt, updatedAt);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;}
    }
}
