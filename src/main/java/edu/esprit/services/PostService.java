package edu.esprit.services;

import edu.esprit.controllers.GlobalHolder;
import edu.esprit.entities.Post;
import edu.esprit.entities.User;
import edu.esprit.utils.DataSource;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class PostService implements IService<Post> {

    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Post post) {
        String req = "INSERT INTO post (title, description, matiere, userId, nbrOfComments, createdAt, updatedAt) VALUES (?,?,?,?,?,?,?)";
        String addFiles = "INSERT INTO fichierPost (postId, fileLink) VALUES (?,?)";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setString(1, post.getTitle());
            pst.setString(2, post.getDescription());
            pst.setString(3, post.getMatiere());
            pst.setInt(4, GlobalHolder.getcurrentUser().getId());
            pst.setInt(5, post.getNumberOfComments());
            pst.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
            pst.setTimestamp(7, new Timestamp(System.currentTimeMillis()));
            pst.executeUpdate();
            // get the id of the last post added
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery("SELECT MAX(id) FROM post");
            rs.next();
            int id = rs.getInt(1);
            for (String file : post.getFichiers()) {
                PreparedStatement pst2 = cnx.prepareStatement(addFiles);
                pst2.setInt(1, id);
                pst2.setString(2, file);
                pst2.executeUpdate();
            }
            System.out.println("Post added !");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public void save(Post post) {
        String req = "INSERT INTO postsauveguardee (idUser, idPost) VALUES (?,?)";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, GlobalHolder.getcurrentUser().getId());
            pst.setInt(2, post.getId());
            pst.executeUpdate();
            System.out.println("Post saved !");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void unSave(Post post) {
        String req = "DELETE FROM postsauveguardee WHERE idUser=? AND idPost=?";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, GlobalHolder.getcurrentUser().getId());
            pst.setInt(2, post.getId());
            pst.executeUpdate();
            System.out.println("Post unsaved !");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Set<Post> getSavedPosts(User user) {
        Set<Post> posts = new HashSet<>();
        String req = "SELECT p.* from post p join postsauveguardee ps on p.id = ps.idPost where ps.idUser = ?";
        String req2 = "SELECT * FROM fichierpost WHERE postId=?";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, user.getId());
            pst.executeQuery();
            while (pst.getResultSet().next()) {
                Post post = new Post();
                post.setId(pst.getResultSet().getInt("id"));
                post.setTitle(pst.getResultSet().getString("title"));
                post.setDescription(pst.getResultSet().getString("description"));
                post.setMatiere(pst.getResultSet().getString("matiere"));
                post.setUser(new ServiceUser().getOneByID(pst.getResultSet().getInt("userId")));
                post.setNumberOfComments(pst.getResultSet().getInt("nbrOfComments"));
                post.setCreatedAt(pst.getResultSet().getTimestamp("createdAt"));
                post.setUpdatedAt(pst.getResultSet().getTimestamp("updatedAt"));
                PreparedStatement pst2 = cnx.prepareStatement(req2);
                pst2.setInt(1, post.getId());
                pst2.executeQuery();
                Set<String> fichiers = new HashSet<>();
                while (pst2.getResultSet().next()) {
                    fichiers.add(pst2.getResultSet().getString("fileLink"));
                }
                post.setFichiers(fichiers);
                posts.add(post);
            }
            System.out.println("Posts selected !");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return posts;
    }

    public boolean isSaved(Post post) {
        String req = "SELECT * from postsauveguardee where idUser = ? and idPost = ?";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, GlobalHolder.getcurrentUser().getId());
            pst.setInt(2, post.getId());
            pst.executeQuery();
            if (pst.getResultSet().next()) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public void modifier(Post post) {
        String req = "UPDATE post SET title=?, description=?, matiere=?,  updatedAt=? WHERE id=?";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setString(1, post.getTitle());
            pst.setString(2, post.getDescription());
            pst.setString(3, post.getMatiere());
            pst.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
            pst.setInt(5, post.getId());
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
        // by userId
        String req = "Select p.*, count(c.id) as nbComments from post p left join commentaire c on p.id = c.postId group by p.id";
        String req2 = "SELECT * FROM fichierpost WHERE postId=?";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.executeQuery();
            while (pst.getResultSet().next()) {
                Post post = new Post();
                post.setId(pst.getResultSet().getInt("id"));
                post.setTitle(pst.getResultSet().getString("title"));
                post.setDescription(pst.getResultSet().getString("description"));
                post.setMatiere(pst.getResultSet().getString("matiere"));
                post.setUser(new ServiceUser().getOneByID(pst.getResultSet().getInt("userId")));
                post.setNumberOfComments(pst.getResultSet().getInt("nbComments"));
                post.setCreatedAt(pst.getResultSet().getTimestamp("createdAt"));
                post.setUpdatedAt(pst.getResultSet().getTimestamp("updatedAt"));
                PreparedStatement pst2 = cnx.prepareStatement(req2);
                pst2.setInt(1, post.getId());
                pst2.executeQuery();
                Set<String> fichiers = new HashSet<>();
                while (pst2.getResultSet().next()) {
                    fichiers.add(pst2.getResultSet().getString("fileLink"));
                }
                post.setFichiers(fichiers);
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
        String req = "SELECT p.*, count(c.id) as nbComments from post p left join commentaire c on p.id = c.postId where p.id = ? group by p.id";
        String req2 = "SELECT * FROM fichierpost WHERE postId=?";
        Post post = new Post();
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, id);
            pst.executeQuery();
            pst.getResultSet().next();
            post.setId(pst.getResultSet().getInt("id"));
            post.setTitle(pst.getResultSet().getString("title"));
            post.setDescription(pst.getResultSet().getString("description"));
            post.setMatiere(pst.getResultSet().getString("matiere"));
            post.setUser(new ServiceUser().getOneByID(pst.getResultSet().getInt("userId")));
            post.setNumberOfComments(pst.getResultSet().getInt("nbComments"));
            post.setCreatedAt(pst.getResultSet().getTimestamp("createdAt"));
            post.setUpdatedAt(pst.getResultSet().getTimestamp("updatedAt"));
            PreparedStatement pst2 = cnx.prepareStatement(req2);
            pst2.setInt(1, post.getId());
            pst2.executeQuery();
            Set<String> fichiers = new HashSet<>();
            while (pst2.getResultSet().next()) {
                fichiers.add(pst2.getResultSet().getString("fileLink"));
            }
            post.setFichiers(fichiers);
            System.out.println("Post selected !");
        } catch (SQLException e) {
            System.out.println(" select on post error:" + e.getMessage());
        }
        return post;
    }

    public Set<Post> getPostsByMatiere(String matiere) {
        Set<Post> posts = new HashSet<>();
        String req = "SELECT p.*, count(c.id) as nbComments from post p left join commentaire c on p.id = c.postId where p.matiere = ? group by p.id";
        String req2 = "SELECT * FROM fichierpost WHERE postId=?";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setString(1, matiere);
            pst.executeQuery();
            while (pst.getResultSet().next()) {
                Post post = new Post();
                post.setId(pst.getResultSet().getInt("id"));
                post.setTitle(pst.getResultSet().getString("title"));
                post.setDescription(pst.getResultSet().getString("description"));
                post.setMatiere(pst.getResultSet().getString("matiere"));
                post.setUser(new ServiceUser().getOneByID(pst.getResultSet().getInt("userId")));
                post.setNumberOfComments(pst.getResultSet().getInt("nbComments"));
                post.setCreatedAt(pst.getResultSet().getTimestamp("createdAt"));
                post.setUpdatedAt(pst.getResultSet().getTimestamp("updatedAt"));
                PreparedStatement pst2 = cnx.prepareStatement(req2);
                pst2.setInt(1, post.getId());
                pst2.executeQuery();
                Set<String> fichiers = new HashSet<>();
                while (pst2.getResultSet().next()) {
                    fichiers.add(pst2.getResultSet().getString("fileLink"));
                }
                post.setFichiers(fichiers);
                posts.add(post);
            }
            System.out.println("Posts selected !");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return posts;
    }

    public Set<Post> getPoppulerPosts() {
        Set<Post> posts = new HashSet<>();
        // get most 10 populer posts from last week
        String req = "SELECT p.*, count(c.id) as nbComments from post p left join commentaire c on p.id = c.postId where p.createdAt > DATE_SUB(NOW(), INTERVAL 1 WEEK) group by p.id order by nbComments desc limit 10";
        String req2 = "SELECT * FROM fichierpost WHERE postId=?";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.executeQuery();
            while (pst.getResultSet().next()) {
                Post post = new Post();
                post.setId(pst.getResultSet().getInt("id"));
                post.setTitle(pst.getResultSet().getString("title"));
                post.setDescription(pst.getResultSet().getString("description"));
                post.setMatiere(pst.getResultSet().getString("matiere"));
                post.setUser(new ServiceUser().getOneByID(pst.getResultSet().getInt("userId")));
                post.setNumberOfComments(pst.getResultSet().getInt("nbComments"));
                post.setCreatedAt(pst.getResultSet().getTimestamp("createdAt"));
                post.setUpdatedAt(pst.getResultSet().getTimestamp("updatedAt"));
                PreparedStatement pst2 = cnx.prepareStatement(req2);
                pst2.setInt(1, post.getId());
                pst2.executeQuery();
                Set<String> fichiers = new HashSet<>();
                while (pst2.getResultSet().next()) {
                    fichiers.add(pst2.getResultSet().getString("fileLink"));
                }
                post.setFichiers(fichiers);
                posts.add(post);
            }
            System.out.println("Posts selected !");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return posts;
    }
}