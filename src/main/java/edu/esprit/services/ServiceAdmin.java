package edu.esprit.services;
import edu.esprit.entities.Admin;
import edu.esprit.utils.DataSource;
import java.sql.*;


import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class ServiceAdmin {

    Connection cnx = DataSource.getInstance().getCnx();
    private Statement ste;
    Admin e =new Admin();

    public ServiceAdmin() {
        try {
            ste = cnx.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }



    public void ajouter(Admin t) {
        String req ="INSERT INTO `user`( `nom`, `prenom`) VALUES (?,?)";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(2, t.getLogin());
            ps.setString(1, t.getPassword());
            ps.executeUpdate();
            System.out.println("Administrateur ajouté avec succés !");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public void modifier(Admin a) throws SQLException {
        String sql = "UPDATE admin SET `login`=?,`password`=? WHERE id_admin=" + a.getId_admin();
        PreparedStatement ste;
        try {
            ste = cnx.prepareStatement(sql);

            ste.setString(1, a.getLogin());

            ste.setString(2, a.getPassword());

            ste.executeUpdate();
            int rowsUpdated = ste.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("La modification de l'administrateur : " + a.getLogin() + " a été éffectuée avec succès ");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void supprimer(Admin a) {

        try {
            String req = "DELETE FROM `admin` WHERE `admin`.`id_admin` = ?";
            PreparedStatement ste = cnx.prepareStatement(req);
            ste.setInt(1, a.getId_admin());
            ste.executeUpdate();
            System.out.println("Administrateur supprimé");


        } catch (SQLException ex) {
            Logger.getLogger(ServiceAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }

    }


    public Set<Admin> getAll() {
        Set<Admin> users = new HashSet<>();

        String req = "Select * from personne";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
                int id = rs.getInt("id");
                String nom = rs.getString(2);
                String prenom = rs.getString("prenom");
                Admin p = new Admin(id,nom,prenom);
                users.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return users;


    }


    public Admin getOneByID(int id) {
        Admin p = null;
        String req = "SELECT * FROM personne WHERE id = ?";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");
                p = new Admin(id, nom, prenom);
            } else {
                System.out.println("Aucune personne trouvée avec l'ID donné.");
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération de la personne : " + e.getMessage());
        }
        return p;
    }
}

