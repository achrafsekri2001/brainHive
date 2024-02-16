package edu.esprit.entities;
import java.lang.*;

public class user {
    private int id;
    private static String nom ;
    private static String prenom;
    public user(){}

    public user(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }

    public user(int id, String nom, String prenom) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public static String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    @java.lang.Override

    public String toString() {
        return "user{" +
                "nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                '}';
    }

    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof user personne)) return false;
        if (!super.equals(object)) return false;
        return id == user.id;
    }

    public int hashCode() {
        return java.util.Objects.hash(super.hashCode(), id);
    }


}
