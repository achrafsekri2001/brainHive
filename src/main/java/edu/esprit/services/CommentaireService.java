package edu.esprit.services;

import edu.esprit.entities.Commentaire;
import edu.esprit.entities.Post;
import edu.esprit.utils.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

public class CommentaireService implements IService<Commentaire> {

    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Commentaire commentaire) {
//        private int id;
//        private String content;
//        private Post post;
//        private int userId;
//        public int nbreVotes;
//        public int nbreUpVotes;
//        public int nbreDownVotes;
//        public Set<Object> Fichier = new HashSet<>();
//        private Commentaire parent;
//        private Timestamp createdAt;
//        private Timestamp updatedAt;
        String req = "INSERT INTO commentaire (content, postId, userId, nbreVotes, nbreUpVotes, nbreDownVotes, createdAt, updatedAt,parentId) VALUES (?,?,?,?,?,?,?,?,?)";

        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setString(1, commentaire.getContent());
            pst.setInt(2, commentaire.getPost().getId());
            pst.setInt(3, commentaire.getUserId());
            pst.setInt(4, 0);
            pst.setInt(5, 0);
            pst.setInt(6, 0);
            pst.setTimestamp(7, new Timestamp(System.currentTimeMillis()));
            pst.setTimestamp(8, new Timestamp(System.currentTimeMillis()));
            pst.setInt(9, commentaire.getParent().getId());
            pst.executeUpdate();
            System.out.println("Comment added !");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void modifier(Commentaire commentaire) {
        String req = "UPDATE commentaire SET content=?, postId=?, userId=?, nbreVotes=?, nbreUpVotes=?, nbreDownVotes=?, createdAt=?, updatedAt=?, parentId=? WHERE id=?";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setString(1, commentaire.getContent());
            pst.setInt(2, commentaire.getPost().getId());
            pst.setInt(3, commentaire.getUserId());
            pst.setInt(4, commentaire.getNbreVotes());
            pst.setInt(5, commentaire.getNbreUpVotes());
            pst.setInt(6, commentaire.getNbreDownVotes());
            pst.setTimestamp(7, commentaire.getCreatedAt());
            pst.setTimestamp(8, commentaire.getUpdatedAt());
            pst.setInt(9, commentaire.getParent().getId());
            pst.setInt(10, commentaire.getId());
            pst.executeUpdate();
            System.out.println("Comment updated !");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void ajouterFichier(Commentaire commentaire, String fichier) {
        String req = "INSERT INTO fichierCommentaire (commentaireId, fileLink) VALUES (?,?)";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, commentaire.getId());
            pst.setString(2, fichier);
            pst.executeUpdate();
            System.out.println("Fichier added !");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void suppFichier(int id) {
        String req = "DELETE FROM fichierCommentaire WHERE id=?";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("Fichier deleted !");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void supprimer(int id) {
        String req = "DELETE FROM commentaire WHERE id=?";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("Comment deleted !");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Set<Commentaire> getAll() {
        String req = "SELECT * FROM commentaire";
        Set<Commentaire> commentaires = new HashSet<>();
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.executeQuery();
            while (pst.getResultSet().next()) {
                Commentaire commentaire = new Commentaire();
                commentaire.setId(pst.getResultSet().getInt("id"));
                commentaire.setContent(pst.getResultSet().getString("content"));
                Post post = new Post();
                post.setId(pst.getResultSet().getInt("postId"));
                commentaire.setPost(post);
                commentaire.setUserId(pst.getResultSet().getInt("userId"));
                commentaire.setNbreVotes(pst.getResultSet().getInt("nbreVotes"));
                commentaire.setNbreUpVotes(pst.getResultSet().getInt("nbreUpVotes"));
                commentaire.setNbreDownVotes(pst.getResultSet().getInt("nbreDownVotes"));
                commentaire.setCreatedAt(pst.getResultSet().getTimestamp("createdAt"));
                commentaire.setUpdatedAt(pst.getResultSet().getTimestamp("updatedAt"));
                Commentaire parent = new Commentaire();
                parent.setId(pst.getResultSet().getInt("parentId"));
                commentaire.setParent(parent);
                commentaires.add(commentaire);
            }
            System.out.println("Comment selected !");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return commentaires;
    }

    @Override
    public Commentaire getOneByID(int id) {
        String req = "SELECT * FROM commentaire WHERE id=?";
        Commentaire commentaire = new Commentaire();
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, id);
            pst.executeQuery();
            commentaire.setId(pst.getResultSet().getInt("id"));
            commentaire.setContent(pst.getResultSet().getString("content"));
            Post post = new Post();
            post.setId(pst.getResultSet().getInt("postId"));
            commentaire.setPost(post);
            commentaire.setUserId(pst.getResultSet().getInt("userId"));
            commentaire.setNbreVotes(pst.getResultSet().getInt("nbreVotes"));
            commentaire.setNbreUpVotes(pst.getResultSet().getInt("nbreUpVotes"));
            commentaire.setNbreDownVotes(pst.getResultSet().getInt("nbreDownVotes"));
            commentaire.setCreatedAt(pst.getResultSet().getTimestamp("createdAt"));
            commentaire.setUpdatedAt(pst.getResultSet().getTimestamp("updatedAt"));
            Commentaire parent = new Commentaire();
            parent.setId(pst.getResultSet().getInt("parentId"));
            commentaire.setParent(parent);
            System.out.println("Comment selected !");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return commentaire;
    }


}
