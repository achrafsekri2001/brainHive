package edu.esprit.entities;

import java.util.Objects;

public class AvisProduit {
    private int id_commentaire;
    private int user_id;
    private String contenu;
    private int id_produit;
    private int note;




    public AvisProduit(int id_commentaire, int user_id, String contenu, int id_produit, int note) {
        this.id_commentaire = id_commentaire;
        this.user_id = user_id;
        this.contenu = contenu;
        this.id_produit = id_produit;
        this.note = note;

    }

    public AvisProduit(int user_id, String contenu, int id_produit, int note) {
        this.user_id = user_id;
        this.contenu = contenu;
        this.id_produit = id_produit;
        this.note = note;

    }

    public AvisProduit(String contenu, int id_produit, int note) {
        this.contenu = contenu;
        this.id_produit = id_produit;
        this.note = note;

    }
    public AvisProduit(String contenu) {
        this.contenu = contenu;

    }

    public AvisProduit() {

    }

    public int getId_commentaire() {
        return id_commentaire;
    }

    public void setId_commentaire(int id_commentaire) {
        this.id_commentaire = id_commentaire;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public int getId_produit() {
        return id_produit;
    }

    public void setId_produit(int id_produit) {
        this.id_produit = id_produit;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "AvisProduit{" +
                " user_id=" + user_id +
                ", contenu='" + contenu + '\'' +
                ", id_produit=" + id_produit +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AvisProduit that = (AvisProduit) o;
        return id_commentaire == that.id_commentaire;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_commentaire);
    }

}
