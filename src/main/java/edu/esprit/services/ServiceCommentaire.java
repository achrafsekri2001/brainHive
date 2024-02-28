package edu.esprit.services;

import edu.esprit.entities.CommentaireProduit;
import edu.esprit.entities.Produit;
import edu.esprit.utils.DataSource;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServiceCommentaire {
   /* Connection cnx = DataSource.getInstance().getCnx();

    public void ajouter(CommentaireProduit cp) throws SQLException {

        String req = "INSERT INTO `cp`(`user_id`, `contenu`, `id_produit`) VALUES (?,?)";
        PreparedStatement ps = cnx.prepareStatement(req);

        ps.setString(1, cp.getUser_id());
        ps.setString(2, cp.getContenu());
        ps.setString(3, cp.getId_produit());
        ps.setInt(4, cp.getId_produit());
        ps.executeUpdate();
        System.out.println("commentaire added !");
    }

    public void modifier(CommentaireProduit cp) throws SQLException {
        String sql = "UPDATE produit SET `id_commentaire`=?, `user_id`=?, `contenu`=?, `id_produit`=? WHERE id_commentaire=" + cp.getId_commentaire();
        PreparedStatement ste;
        try {
            ste = cnx.prepareStatement(sql);

            ste.setString(1, cp.getNom());
            ste.setString(2, p.getImage());
            ste.setString(3, p.getDescription());
            ste.setString(4, p.getMatiere());


            ste.executeUpdate();
            int rowsUpdated = ste.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("La modification de produit : " + p.getNom() + " a été éffectuée avec succès ");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    public void supprimer(Produit p) {

        try {
            String req = "DELETE FROM `produit` WHERE `produit`.`id_produit` = ?";
            PreparedStatement ste = cnx.prepareStatement(req);
            ste.setInt(1, p.getId_produit());
            ste.executeUpdate();
            System.out.println("Produit supprimé");


        } catch (SQLException ex) {
            Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    public Set<Produit> getAll() {
        Set<Produit> produits = new HashSet<>();

        String req = "Select * from produit";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
                int id = rs.getInt("id");
                String nom = rs.getString(2);
                String image = rs.getString("image");
                String description = rs.getString("prenom");
                String matiere = rs.getString("matiere");
                int note = rs.getInt("note");
                Produit p = new Produit(id,nom,image,description,matiere,note);
                produits.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return produits;
    }
*/
}
