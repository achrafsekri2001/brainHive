package edu.esprit.services;

import edu.esprit.entities.Produit;
import edu.esprit.utils.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServiceProduit implements IService<Produit>{
    Connection cnx = DataSource.getInstance().getCnx();

    public void ajouter(Produit produit) throws SQLException {

        String req = "INSERT INTO `produit`(`nom`,`image`, `description`, `matiere`,`note` ) VALUES (?,?,?,?,?)";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, produit.getNom());
        ps.setString(2, produit.getImage());
        ps.setString(3, produit.getDescription());
        ps.setString(4, produit.getMatiere());
        ps.setInt(5, produit.getNote());
        ps.executeUpdate();
        System.out.println("produit ajouté !");
    }

    public void modifier(Produit p) throws SQLException {
        String sql = "UPDATE produit SET `nom`=?,`image`=?,`description`=?,`matiere`=?,`note`=? WHERE id_produit=" + p.getId_produit();
        PreparedStatement ste;
        try {
            ste = cnx.prepareStatement(sql);

            ste.setString(1, p.getNom());
            ste.setString(2, p.getImage());
            ste.setString(3, p.getDescription());
            ste.setString(4, p.getMatiere());
            ste.setInt(5, p.getNote());

            int rowsUpdated = ste.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("La modification du produit : " + p.getNom() + " a été effectuée avec succès ");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void supprimer(int id) {
        try {
            String req = "DELETE FROM produit WHERE id_produit=?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Produit supprimé avec succès !");
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la suppression du produit : " + ex.getMessage());
        }
    }

    public List<Produit> getAll() {
        List<Produit> produits = new ArrayList<>();

        String req = "SELECT * FROM produit";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
                int id = rs.getInt("id_produit");
                String nom = rs.getString("nom");
                String image = rs.getString("image");
                String description = rs.getString("description");
                String matiere = rs.getString("matiere");
                int note = rs.getInt("note");
                Produit p = new Produit(id, image, nom, description, matiere, note);
                produits.add(p);
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération des produits : " + e.getMessage());
        }

        return (List<Produit>) produits;
    }

    public Produit getOneByID(int id_produit) {
        Produit p = null;
        try {
            String req = "SELECT * FROM produit WHERE id_produit=?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id_produit);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String nom = rs.getString("nom");
                String image = rs.getString("image");
                String description = rs.getString("description");
                String matiere = rs.getString("matiere");
                int note = rs.getInt("note");
                p = new Produit(id_produit, nom, image, description, matiere, note);
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération du produit : " + e.getMessage());
        }
        return p;
    }

    public void ajouterNote(int idProduit, int note) throws SQLException {
        String requete = "INSERT INTO note (id_produit, note) VALUES (?, ?)";
        PreparedStatement preparedStatement = cnx.prepareStatement(requete);
        preparedStatement.setInt(1, idProduit);
        preparedStatement.setInt(2, note);
        preparedStatement.executeUpdate();
    }

    public double calculerMoyenneNotes(int idProduit) throws SQLException {
        String requete = "SELECT AVG(note) AS moyenne FROM note WHERE id_produit=?";
        PreparedStatement preparedStatement = cnx.prepareStatement(requete);
        preparedStatement.setInt(1, idProduit);
        ResultSet resultat = preparedStatement.executeQuery();
        double moyenne = 0;
        if (resultat.next()) {
            moyenne = resultat.getDouble("moyenne");
        }
        return moyenne;
    }

    public List<String> getMatiereList() {
        // Initialise une liste pour stocker les matières disponibles
        List<String> matieres = new ArrayList<>();

        try {
            // Remplacer cette partie avec votre propre logique pour récupérer les matières depuis la source de données
            // Par exemple, si vous stockez les matières dans une base de données, vous devrez exécuter une requête pour les récupérer
            // Ici, nous simulons des données de test
            matieres.add("Arabe");
            matieres.add("Anglais");
            matieres.add("Allemand");
            matieres.add("Economie");
            matieres.add("Espagnole");
            matieres.add("Français");
            matieres.add("Gestion");
            matieres.add("Histoire et géographie");
            matieres.add("Informatique");
            matieres.add("Italien");
            matieres.add("Mathématique");
            matieres.add("Philosophie");
            matieres.add("Physique et chimie");
            matieres.add("Sciences de la vie et de la terre");
            matieres.add("Technique");

        } catch (Exception e) {
            // Gérer les éventuelles exceptions, telles que les erreurs de connexion à la base de données
            e.printStackTrace();
        }

        // Retourne la liste des matières disponibles
        return matieres;
    }
    public List<Produit> getProduitsByMatiere(String matiere) {
        List<Produit> produitsParMatiere = new ArrayList<>();
        String req = "SELECT * FROM produit WHERE matiere=?";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, matiere);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id_produit");
                String nom = rs.getString("nom");
                String image = rs.getString("image");
                String description = rs.getString("description");
                int note = rs.getInt("note");
                Produit p = new Produit(id, image, nom, description, matiere, note);
                produitsParMatiere.add(p);
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération des produits par matière : " + e.getMessage());
        }
        return produitsParMatiere;
    }


}
