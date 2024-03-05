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
    private final PostService postService = new PostService();

    @Override
    public void ajouter(Commentaire commentaire) {
        String req = "INSERT INTO commentaire (content, postId, userId, upVotes, downVotes, createdAt, updatedAt,parentComment) VALUES (?,?,?,?,?,?,?,?)";

        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setString(1, commentaire.getContent());
            pst.setInt(2, commentaire.getPost().getId());
            pst.setInt(3, commentaire.getUserId());
            pst.setInt(4, 0);
            pst.setInt(5, 0);
            pst.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
            pst.setTimestamp(7, new Timestamp(System.currentTimeMillis()));
            if (commentaire.getParent() != null) {
                pst.setInt(8, commentaire.getParent().getId());
            } else {
                pst.setInt(8, -1);
            }
            pst.executeUpdate();
            System.out.println("Comment added !");
//            postService.incrementComments(commentaire.getPost().getId());
            postService.incrementComments(commentaire.getPost().getId());

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    //? add bad words filter
    @Override
    public void modifier(Commentaire commentaire) {
        String req = "UPDATE commentaire SET content=?, postId=?, userId=?, upVotes=?, upVotes=?, nbreDownVotes=?, createdAt=?, updatedAt=?, parentId=? WHERE id=?";
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

    public void supprimerFichier(int id) {
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
        String req = "SELECT c.*, v.type as voted FROM commentaire c LEFT JOIN userVotes v ON c.id = v.comment_id";
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
                commentaire.setNbreVotes(pst.getResultSet().getInt("upVotes") - pst.getResultSet().getInt("downVotes"));
                commentaire.setNbreUpVotes(pst.getResultSet().getInt("upVotes"));
                commentaire.setNbreDownVotes(pst.getResultSet().getInt("downVotes"));
                commentaire.setCreatedAt(pst.getResultSet().getTimestamp("createdAt"));
                commentaire.setUpdatedAt(pst.getResultSet().getTimestamp("updatedAt"));
                // if voted is 0 then user didn't vote, if 1 then user upvoted, if 2 then user downvoted
                commentaire.setVoted(pst.getResultSet().getInt("voted"));
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
        String req = "SELECT c.*, v.type as voted FROM commentaire c LEFT JOIN userVotes v ON c.id = v.comment_id WHERE c.id=?";
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
            commentaire.setNbreVotes(pst.getResultSet().getInt("upVotes") - pst.getResultSet().getInt("downVotes"));
            commentaire.setNbreUpVotes(pst.getResultSet().getInt("upVotes"));
            commentaire.setNbreDownVotes(pst.getResultSet().getInt("downVotes"));
            commentaire.setCreatedAt(pst.getResultSet().getTimestamp("createdAt"));
            commentaire.setUpdatedAt(pst.getResultSet().getTimestamp("updatedAt"));
            commentaire.setVoted(pst.getResultSet().getInt("voted"));
            Commentaire parent = new Commentaire();
            parent.setId(pst.getResultSet().getInt("parentId"));
            commentaire.setParent(parent);
            System.out.println("Comment selected !");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return commentaire;
    }

    public Set<Commentaire> getCommentairesByPost(Post post) {
        String req = "SELECT c.*, v.type as voted FROM commentaire c LEFT JOIN userVotes v ON c.id = v.comment_id WHERE c.postId=?";
        Set<Commentaire> commentaires = new HashSet<>();
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, post.getId());

            pst.executeQuery();
            while (pst.getResultSet().next()) {
                Commentaire commentaire = new Commentaire();
                commentaire.setId(pst.getResultSet().getInt("id"));
                commentaire.setContent(pst.getResultSet().getString("content"));
                commentaire.setPost(post);
                commentaire.setUserId(pst.getResultSet().getInt("userId"));
                commentaire.setNbreVotes(pst.getResultSet().getInt("upVotes") - pst.getResultSet().getInt("downVotes"));
                commentaire.setNbreUpVotes(pst.getResultSet().getInt("upVotes"));
                commentaire.setNbreDownVotes(pst.getResultSet().getInt("downVotes"));
                commentaire.setCreatedAt(pst.getResultSet().getTimestamp("createdAt"));
                commentaire.setUpdatedAt(pst.getResultSet().getTimestamp("updatedAt"));
                commentaire.setVoted(pst.getResultSet().getInt("voted"));
//                Commentaire parent = new Commentaire();
//                parent.setId(pst.getResultSet().getInt("parentId"));
//                commentaire.setParent(parent);
                commentaires.add(commentaire);
            }


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return commentaires;
    }

    public void upVote(Commentaire commentaire) {
        String req = "UPDATE commentaire SET upVotes=? WHERE id=?";
        String req2 = "insert into userVotes (user_id,comment_id,type) values (?,?,1)";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, commentaire.getNbreUpVotes() + 1);
            pst.setInt(2, commentaire.getId());
            pst.executeUpdate();
            PreparedStatement pst2 = cnx.prepareStatement(req2);
            //? add user id
            pst2.setInt(1, 1);
            pst2.setInt(2, commentaire.getId());
            pst2.executeUpdate();
            System.out.println("Comment upvoted !");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void downVote(Commentaire commentaire) {
        String req = "UPDATE commentaire SET downVotes=? WHERE id=?";
        String req2 = "insert into userVotes (user_id,comment_id,type) values (?,?,2)";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, commentaire.getNbreDownVotes() + 1);
            pst.setInt(2, commentaire.getId());
            pst.executeUpdate();
            PreparedStatement pst2 = cnx.prepareStatement(req2);
            //? add user id
            pst2.setInt(1, 1);
            pst2.setInt(2, commentaire.getId());
            pst2.executeUpdate();
            System.out.println("Comment downvoted !");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void cancelVote(Commentaire commentaire) {
        String req = "DELETE FROM userVotes WHERE comment_id=?";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, commentaire.getId());
            pst.executeUpdate();
            System.out.println("Vote canceled !");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
