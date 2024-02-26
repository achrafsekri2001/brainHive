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
        String req = "INSERT INTO post (title, description, matiere, groupId, userId, numberOfComments, createdAt, updatedAt) VALUES (?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setString(1, post.getTitle());
            pst.setString(2, post.getDescription());
            pst.setString(3, post.getMatiere());
            pst.setInt(4, post.getGroupId());
            pst.setInt(5, post.getUserId());
            pst.setInt(6, 0);
            pst.setTimestamp(7, new Timestamp(System.currentTimeMillis()));
            pst.setTimestamp(8, new Timestamp(System.currentTimeMillis()));
            pst.executeUpdate();
            System.out.println("Post added !");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void modifier(Post post) {
        String req = "UPDATE post SET title=?, description=?, matiere=?, groupId=?, userId=?, numberOfComments=?, createdAt=?, updatedAt=? WHERE id=?";
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
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {
                Post post = new Post();
                post.setId(rs.getInt(1));
                post.setTitle(rs.getString(2));
                post.setDescription(rs.getString(3));
                post.setMatiere(rs.getString(4));
                post.setGroupId(rs.getInt(5));
                post.setUserId(rs.getInt(6));
                post.setNumberOfComments(rs.getInt(7));
                post.setCreatedAt(rs.getTimestamp(8));
                post.setUpdatedAt(rs.getTimestamp(9));
                posts.add(post);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return posts;
    }

    @Override
    public Post getOneByID(int id) {
        Post post = new Post();
        // get post and comments associated with it
        String req = "Select post.*, count(comment.id) as numberOfComments from post left join comment on post.id = comment.postId where post.id = " + id;
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                post.setId(rs.getInt(1));
                post.setTitle(rs.getString(2));
                post.setDescription(rs.getString(3));
                post.setMatiere(rs.getString(4));
                post.setGroupId(rs.getInt(5));
                post.setUserId(rs.getInt(6));
                post.setNumberOfComments(rs.getInt(7));
                post.setCreatedAt(rs.getTimestamp(8));
                post.setUpdatedAt(rs.getTimestamp(9));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return post;
    }
}