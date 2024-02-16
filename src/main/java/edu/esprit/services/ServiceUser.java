package edu.esprit.services;




import edu.esprit.entities.user;
import edu.esprit.utils.DataSource;

import java.nio.file.attribute.UserPrincipal;
import java.sql.*;

import java.util.HashSet;
import java.util.Set;



public class ServicesUser implements  IService <user> {

    Connection cnx = DataSource.getInstance().getCnx();


    @Override
    public void ajouter(user t) {
        String req ="INSERT INTO `user`( `nom`, `prenom`) VALUES (?,?)";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(2, t.getPrenom());
            ps.setString(1, t.getNom());
            ps.executeUpdate();
            System.out.println("user added !");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void modifier(user  t) {

        String req = "UPDATE personne SET nom = ?, prenom = ? WHERE id = ?";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, t.getNom());
            ps.setString(2, t.getPrenom());
            ps.setInt(3, t.getId());
            // Supposant qu'il y a un champ ID dans la classe Personne
            int lignesModifiees = ps.executeUpdate();
            if (lignesModifiees > 0) {
                System.out.println("Personne modifiée avec succès !");
            } else {
                System.out.println("Aucune personne trouvée avec l'ID donné.");
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la modification de la personne : " + e.getMessage());
        }

    }

    @Override
    public void supprimer(int id) {
        String req = "DELETE FROM user WHERE id = ?";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id);
            int lignesSupprimees = ps.executeUpdate();
            if (lignesSupprimees > 0) {
                System.out.println("Personne supprimée avec succès !");
            } else {
                System.out.println("Aucune personne trouvée avec l'ID donné.");
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression de la personne : " + e.getMessage());
        }

    }

    @Override
    public Set<user> getAll() {
        Set<user> users = new HashSet<>();

        String req = "Select * from personne";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
                int id = rs.getInt("id");
                String nom = rs.getString(2);
                String prenom = rs.getString("prenom");
                user p = new user(id,nom,prenom);
                users.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return users;


    }

    @Override
    public user getOneByID(int id) {
        user p = null;
        String req = "SELECT * FROM personne WHERE id = ?";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");
                p = new user(id, nom, prenom);
            } else {
                System.out.println("Aucune personne trouvée avec l'ID donné.");
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération de la personne : " + e.getMessage());
        }
        return p;
    }
}
