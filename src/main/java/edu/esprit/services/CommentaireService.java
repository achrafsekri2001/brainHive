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
        String req = "INSERT INTO commentaire (content, postId, userId, createdAt, updatedAt,parentComment) VALUES (?,?,?,?,?,?)";

        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setString(1, commentaire.getContent());
            pst.setInt(2, commentaire.getPost().getId());
            pst.setInt(3, commentaire.getUserId());
            pst.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
            pst.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
            if (commentaire.getParent() != null) {
                pst.setInt(6, commentaire.getParent().getId());
            } else {
                pst.setInt(6, -1);
            }
            pst.executeUpdate();
            System.out.println("Comment added !");
            postService.incrementComments(commentaire.getPost().getId());

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    //? add bad words filter
    @Override
    public void modifier(Commentaire commentaire) {
        String req = "UPDATE commentaire SET content=?, postId=?, userId=?, createdAt=?, updatedAt=?, parentId=? WHERE id=?";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setString(1, commentaire.getContent());
            pst.setInt(2, commentaire.getPost().getId());
            pst.setInt(3, commentaire.getUserId());
            pst.setTimestamp(4, commentaire.getCreatedAt());
            pst.setTimestamp(5, commentaire.getUpdatedAt());
            pst.setInt(6, commentaire.getParent().getId());
            pst.setInt(7, commentaire.getId());
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
        Post post = getOneByID(id).getPost();
        String req = "DELETE FROM commentaire WHERE id=?";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("Comment deleted !");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        postService.decrementComments(post.getId());
    }

    @Override
    public Set<Commentaire> getAll() {
        String req = "SELECT c.id,\n" +
                "       c.content,\n" +
                "       v.type                    as voted,\n" +
                "       SUM(IF(v.type = 1, 1, 0)) as upVotes,\n" +
                "       SUM(IF(v.type = 2, 1, 0)) as downVotes,\n" +
                "       c.userId,\n" +
                "       c.postId,\n" +
                "       c.parentComment,\n" +
                "       c.verified,\n" +
                "       c.intOfreports,\n" +
                "       c.createdAt,\n" +
                "       c.updatedAt\n" +
                "FROM commentaire c\n" +
                "         LEFT JOIN userVotes v ON c.id = v.comment_id\n" +
                "GROUP BY c.id, c.content, c.createdAt, c.userId, v.type\n";
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
        System.out.println("id: " + id);
        String req = "SELECT c.id,\n" +
                "       c.content,\n" +
                "       v.type                    as voted,\n" +
                "       SUM(IF(v.type = 1, 1, 0)) as upVotes,\n" +
                "       SUM(IF(v.type = 2, 1, 0)) as downVotes,\n" +
                "       c.userId,\n" +
                "       c.postId,\n" +
                "       c.parentComment,\n" +
                "       c.verified,\n" +
                "       c.intOfreports,\n" +
                "       c.createdAt,\n" +
                "       c.updatedAt\n" +
                "FROM commentaire c\n" +
                "         LEFT JOIN userVotes v\n" +
                "                   ON c.id = v.comment_id\n" +
                "WHERE c.id = ?\n" +
                "GROUP BY c.id, c.content, v.type, c.userId, c.postId, c.parentComment, c.verified, c.intOfreports, c.createdAt,\n" +
                "         c.updatedAt;";
        String req2 = "SELECT * FROM commentaire WHERE id=?";
        Commentaire commentaire = new Commentaire();
        try {
            PreparedStatement pst = cnx.prepareStatement(req2);
            pst.setInt(1, id);
            pst.executeQuery();
            pst.getResultSet().next();
            commentaire.setId(pst.getResultSet().getInt("id"));
            commentaire.setContent(pst.getResultSet().getString("content"));
            Post post = new Post();
            try {
                post.setId(pst.getResultSet().getInt("postId"));
                commentaire.setPost(post);
            } catch (SQLException e) {
                System.out.println("Post not found");
                commentaire.setPost(null);
            }
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

            System.out.println("Comment selected !" + commentaire);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return commentaire;
    }

    public Set<Commentaire> getCommentairesByPost(Post post) {
        String req = "SELECT c.id," +
                "       c.content," +
                "       v.type                    as voted,\n" +
                "       SUM(IF(v.type = 1, 1, 0)) as upVotes,\n" +
                "       SUM(IF(v.type = 2, 1, 0)) as downVotes,\n" +
                "       c.userId,\n" +
                "       c.postId,\n" +
                "       c.parentComment,\n" +
                "       c.verified,\n" +
                "       c.intOfreports,\n" +
                "       c.createdAt,\n" +
                "       c.updatedAt\n" +
                "FROM commentaire c\n" +
                "         LEFT JOIN userVotes v ON c.id = v.comment_id\n" +
                "WHERE c.postId = ?\n" +
                "GROUP BY c.id, c.content, v.type, c.userId, c.postId, c.parentComment, c.verified, c.intOfreports, c.createdAt, c.updatedAt\n";
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
        String req2 = "insert into userVotes (user_id,comment_id,type) values (?,?,1)";
        String req3 = "UPDATE userVotes SET type=1 WHERE comment_id=?";
        try {
            if (commentaire.getVoted() == 2) {
                PreparedStatement pst3 = cnx.prepareStatement(req3);
                pst3.setInt(1, commentaire.getId());
                pst3.executeUpdate();
            } else {
                PreparedStatement pst2 = cnx.prepareStatement(req2);
                pst2.setInt(1, 1);
                pst2.setInt(2, commentaire.getId());
                pst2.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Error in upvoting comment" + e.getMessage());
        }
    }

    public void downVote(Commentaire commentaire) {
        String req2 = "insert into userVotes (user_id,comment_id,type) values (?,?,2)";
        String req3 = "UPDATE userVotes SET type=2 WHERE comment_id=?";
        try {
            if (commentaire.getVoted() == 1) {
                PreparedStatement pst3 = cnx.prepareStatement(req3);
                pst3.setInt(1, commentaire.getId());
                pst3.executeUpdate();
            } else {
                PreparedStatement pst2 = cnx.prepareStatement(req2);
                //? add user id
                pst2.setInt(1, 1);
                pst2.setInt(2, commentaire.getId());
                pst2.executeUpdate();
            }
            System.out.println("Comment downvoted !");
        } catch (SQLException e) {
            System.out.println("Error in downvoting comment" + e.getMessage());
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
