package edu.esprit.services;

import edu.esprit.entities.Post;
import edu.esprit.utils.DataSource;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class PostService implements IService<Post> {

    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Post post) {
        String req = "INSERT INTO post (title, description, matiere, userId, nbrOfComments, createdAt, updatedAt) VALUES (?,?,?,?,?,?,?)";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setString(1, post.getTitle());
            pst.setString(2, post.getDescription());
            pst.setString(3, post.getMatiere());
            pst.setInt(4, post.getUserId());
            pst.setInt(5, post.getNumberOfComments());
            pst.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
            pst.setTimestamp(7, new Timestamp(System.currentTimeMillis()));
            pst.executeUpdate();
            System.out.println("Post added !");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void modifier(Post post) {
        String req = "UPDATE post SET title=?, description=?, matiere=?, groupeId=?, userId=?, nbrOfComments=?, createdAt=?, updatedAt=? WHERE id=?";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setString(1, post.getTitle());
            pst.setString(2, post.getDescription());
            pst.setString(3, post.getMatiere());
            pst.setInt(4, post.getGroupId());
            pst.setInt(5, post.getUserId());
            pst.setInt(6, post.getNumberOfComments());
            pst.setTimestamp(7, post.getCreatedAt());
            pst.setTimestamp(8, post.getUpdatedAt());
            pst.setInt(9, post.getId());
            pst.executeUpdate();
            System.out.println("Post updated !");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public void incrementComments(int id) {
        String req = "UPDATE post SET nbrOfComments=nbrOfComments+1 WHERE id=?";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("Post updated !");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public void decrementComments(int id) {
        String req = "UPDATE post SET nbrOfComments=nbrOfComments-1 WHERE id=?";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("Post updated !");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public void ajouterFichier(Post post, String fichier) {
        String req = "INSERT INTO fichierPost (postId, fileLink) VALUES (?,?)";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, post.getId());
            pst.setString(2, fichier);
            pst.executeUpdate();
            System.out.println("FichierPost added !");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void supprimerFichier(int id) {
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
    public void supprimer(int id) {
        String req = "DELETE FROM post WHERE id=?";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("Post deleted !");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public Set<Post> getAll() {
        Set<Post> posts = new HashSet<>();
        String req = "Select * from post";
        try {
            Statement pst = cnx.createStatement();
            pst.executeQuery(req);
            while (pst.getResultSet().next()) {
                Post post = new Post();
                post.setId(pst.getResultSet().getInt("id"));
                post.setTitle(pst.getResultSet().getString("title"));
                post.setDescription(pst.getResultSet().getString("description"));
                post.setMatiere(pst.getResultSet().getString("matiere"));
                post.setGroupId(pst.getResultSet().getInt("groupeId"));
                post.setUserId(pst.getResultSet().getInt("userId"));
                post.setNumberOfComments(pst.getResultSet().getInt("nbrOfComments"));
                post.setCreatedAt(pst.getResultSet().getTimestamp("createdAt"));
                post.setUpdatedAt(pst.getResultSet().getTimestamp("updatedAt"));
                posts.add(post);
            }
            System.out.println("Posts selected !");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return posts;
    }

    @Override
    public Post getOneByID(int id) {
//        private int id;
//        private String title;
//        private String description;
//        private String matiere;
//        private int groupId;
//        private int userId;
//        private int numberOfComments;
//        public Set<Commentaire> commentaires = new HashSet<>();
//        private Timestamp createdAt;
//        private Timestamp updatedAt;
//        private Set<Object> fichiers = new HashSet<>();

        String req = "SELECT * FROM post WHERE id=?";
        Post post = new Post();
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, id);
            pst.executeQuery();
            post.setId(pst.getResultSet().getInt("id"));
            post.setTitle(pst.getResultSet().getString("title"));
            post.setDescription(pst.getResultSet().getString("description"));
            post.setMatiere(pst.getResultSet().getString("matiere"));
            post.setGroupId(pst.getResultSet().getInt("groupeId"));
            post.setUserId(pst.getResultSet().getInt("userId"));
            post.setNumberOfComments(pst.getResultSet().getInt("nbrOfComments"));
            post.setCreatedAt(pst.getResultSet().getTimestamp("createdAt"));
            post.setUpdatedAt(pst.getResultSet().getTimestamp("updatedAt"));
            System.out.println("Post selected !");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return post;
    }
}