package edu.esprit.entities;

import java.sql.Timestamp;
import java.util.Objects;

public class Reclamation {
  public   String user , contenu, objet , imgUser;
  public Timestamp date;
  public    int idReclamation, idUser;
    private Utilisateur utilisateur;
    public Reclamation(String nom, String contenu, String objet, Timestamp date, int id, int idUser){

    }
    public Reclamation( String contenu, String objet) {

        this.contenu = contenu;
        this.objet = objet;

    }

    public Reclamation() {

    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public Reclamation(String user, String contenu, String objet, String imgUser, Timestamp date, int idReclamation, int idUser) {
        this.user = user;
        this.contenu = contenu;
        this.objet = objet;
        this.imgUser = imgUser;
        this.date = date;
        this.idReclamation = idReclamation;
        this.idUser = idUser;
    }

    public Reclamation(String user, String contenu, String objet, String imgUser, Timestamp date, int idUser) {
        this.user = user;
        this.contenu = contenu;
        this.idUser = idUser;
        this.objet = objet;
        this.imgUser = imgUser;
        this.date = date;
    }

    public String getUser() {
        return user;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public String getObjet() {
        return objet;
    }

    public void setObjet(String objet) {
        this.objet = objet;
    }

    public String getImgUser() {
        return imgUser;
    }

    public void setImgUser(String imgUser) {
        this.imgUser = imgUser;
    }
    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }


    public int getIdReclamation() {
        return idReclamation;
    }

    public void setIdReclamation(int idReclamation) {
        this.idReclamation = idReclamation;
    }

    @Override
    public String toString() {
        return "Reclamation{" +
                "user='" + user + '\'' +
                ", contenu='" + contenu + '\'' +
                ", objet='" + objet + '\'' +
                ", imgUser='" + imgUser + '\'' +
                ", date=" + date +
                ", idReclamation=" + idReclamation +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reclamation that = (Reclamation) o;
        return idReclamation == that.idReclamation;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idReclamation);
    }
}
