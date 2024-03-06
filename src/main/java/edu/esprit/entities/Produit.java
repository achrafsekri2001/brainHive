package edu.esprit.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class Produit {
    private int id_produit;
    private  String image;
    private String nom;
    private String description;

    private String matiere;

    public Produit() {
    }

    public Produit(String nom, String image, String description, String matiere) {

        this.nom = nom;
        this.image = image;
        this.description = description;

        this.matiere = matiere;
    }

    public Produit(int id_produit, String image, String nom, String description, String matiere) {
        this.id_produit = id_produit;
        this.image = image;
        this.nom = nom;
        this.description = description;

        this.matiere = matiere;
    }

    public int getId_produit() {
        return id_produit;
    }

    public void setId_produit(int id_produit) {
        this.id_produit = id_produit;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



    public String getMatiere() {
        return matiere;
    }

    public void setMatiere(String matiere) {
        this.matiere = matiere;
    }

    @Override
    public String toString() {
        return "Produit{" +
                "image='" + image + '\'' +
                ", nom='" + nom + '\'' +
                ", description='" + description + '\'' +
                ", matiere='" + matiere + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produit produit = (Produit) o;
        return id_produit == produit.id_produit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_produit);
    }

}
