package edu.esprit.services;

import edu.esprit.utils.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class FichierPostService implements IService<FichierPost> {
    Connection cnx = DataSource.getInstance().getCnx();

    //    fichierPost [icon:file]{
//        id string pk
//        postId string
//        fileLink string
//    }
    @Override
    public void ajouter(FichierPost fichierPost) {
        String req = "INSERT INTO fichierPost (postId, fileLink) VALUES (?,?)";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, fichierPost.getPostId());
            pst.setString(2, fichierPost.getFileLink());
            pst.executeUpdate();
            System.out.println("FichierPost added !");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void modifier(FichierPost fichierPost) {
        String req = "UPDATE fichierPost SET postId=?, fileLink=? WHERE id=?";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, fichierPost.getPostId());
            pst.setString(2, fichierPost.getFileLink());
            pst.setInt(3, fichierPost.getId());
            pst.executeUpdate();
            System.out.println("FichierPost updated !");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void supprimer(int id) {
        String req = "DELETE FROM fichierPost WHERE id=?";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("FichierPost deleted !");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Set<FichierPost> getAll() {
        String req = "SELECT * FROM fichierPost";
        Set<FichierPost> fichierPosts = new HashSet<>();
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.executeQuery();
            while (pst.getResultSet().next()) {
                FichierPost fichierPost = new FichierPost();
                fichierPost.setId(pst.getResultSet().getInt("id"));
                fichierPost.setPostId(pst.getResultSet().getInt("postId"));
                fichierPost.setFileLink(pst.getResultSet().getString("fileLink"));
                fichierPosts.add(fichierPost);
            }
            System.out.println("FichierPost selected !");
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
        return fichierPosts;
    }

    @Override
    public FichierPost getOneByID(int id) {
        String req = "SELECT * FROM fichierPost WHERE id=?";
        FichierPost fichierPost = new FichierPost();
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, id);
            pst.executeQuery();
            fichierPost.setId(pst.getResultSet().getInt("id"));
            fichierPost.setPostId(pst.getResultSet().getInt("postId"));
            fichierPost.setFileLink(pst.getResultSet().getString("fileLink"));
            System.out.println("FichierPost selected !");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return fichierPost;
    }

    public Set<FichierPost> getAllByPostId(int postId) {
        String req = "SELECT * FROM fichierPost WHERE postId=?";
        Set<FichierPost> fichierPosts = new HashSet<>();
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, postId);
            pst.executeQuery();
            while (pst.getResultSet().next()) {
                FichierPost fichierPost = new FichierPost();
                fichierPost.setId(pst.getResultSet().getInt("id"));
                fichierPost.setPostId(pst.getResultSet().getInt("postId"));
                fichierPost.setFileLink(pst.getResultSet().getString("fileLink"));
                fichierPosts.add(fichierPost);
            }
            System.out.println("FichierPost selected !");
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
        return fichierPosts;
    }

}
