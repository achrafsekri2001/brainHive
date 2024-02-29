package edu.esprit.entities;

import java.util.Date;
import java.util.Objects;

public class Messagerie {/*
  public   int idRecepteur , idEmetteur ,idDiscussion ;
 public    Date date;
 public int idMessage;
   public String contenu;
    public Messagerie(int idMessage, int idEmetteur, int idRecepteur, int idDiscussion, Date date, String contenu){

    }
    public Messagerie(int idRecepteur, int idEmetteur,  Date date, String contenu,int idDiscussion) {
        this.idDiscussion = idDiscussion;
        this.idRecepteur = idRecepteur;
        this.idEmetteur = idEmetteur;
        this.date = date;
        this.contenu = contenu;
    }

    public Messagerie(int idRecepteur, int idEmetteur, int idMessage, Date date, String contenu, int idDiscussion) {
        this.idRecepteur = idRecepteur;
        this.idDiscussion = idDiscussion;

        this.idEmetteur = idEmetteur;
        this.idMessage = idMessage;
        this.date = date;
        this.contenu = contenu;
    }

    public int getIdRecepteur() {
        return idRecepteur;
    }

    public void setIdRecepteur(int idRecepteur) {
        this.idRecepteur = idRecepteur;
    }

    public int getIdEmetteur() {
        return idEmetteur;
    }

    public void setIdEmetteur(int idEmetteur) {
        this.idEmetteur = idEmetteur;
    }

    public int getIdMessage() {
        return idMessage;
    }


    public int getIdDiscussion() {
        return  idDiscussion;
    }

    public void setIdDiscussion(int idDiscussion) {
        this.idDiscussion = idDiscussion;
    }

    public void setIdMessage(int idMessage) {
        this.idMessage = idMessage;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Messagerie{" +
                "idRecepteur=" + idRecepteur +
                ", idEmetteur=" + idEmetteur +
                ", id message=" + idMessage +
                ", date=" + date +
                ", contenu='" + contenu + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Messagerie that = (Messagerie) o;
        return idMessage == that.idMessage;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idMessage);
    }
*/

}
