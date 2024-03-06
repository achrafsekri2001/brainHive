package edu.esprit.entities;

import java.util.Objects;

public class AvisProduit {
    private int id; // L'identifiant de l'avis
    private int idProduit; // L'identifiant du produit associé à cet avis
    private int idUser; // L'identifiant de l'utilisateur qui a laissé cet avis
    private String contenu; // Le contenu de l'avis
    private int note;




    public AvisProduit(int id, int idUser, String contenu, int idProduit, int note) {
        this.id = id;
        this.idUser = idUser;
        this.contenu = contenu;
        this.idProduit = idProduit;
        this.note = note;

    }



    public AvisProduit(String contenu) {
        this.contenu = contenu;

    }

    public AvisProduit() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
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
                " user_id=" + idUser +
                ", contenu='" + contenu + '\'' +
                ", id_produit=" + idProduit +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AvisProduit that = (AvisProduit) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
