package edu.esprit.entities;

import java.util.Date;
import java.util.Objects;

public class Messagerie {
    public int  idDiscussion;
    public Date date;
    private Utilisateur receiver_message;
    private Utilisateur sender_message;
    public int idMessage;
    public String contenu;


    public Messagerie() {
    }

    public Messagerie(int idDiscussion, Date date, Utilisateur receiver_message, Utilisateur sender_message, int idMessage, String contenu) {
        this.idDiscussion = idDiscussion;
        this.date = date;
        this.receiver_message = receiver_message;
        this.sender_message = sender_message;
        this.idMessage = idMessage;
        this.contenu = contenu;
    }

    public Messagerie(int idDiscussion, Date date, Utilisateur receiver_message, Utilisateur sender_message, String contenu) {
        this.idDiscussion = idDiscussion;
        this.date = date;
        this.receiver_message = receiver_message;
        this.sender_message = sender_message;
        this.contenu = contenu;
    }

    public int getIdDiscussion() {
        return idDiscussion;
    }

    public void setIdDiscussion(int idDiscussion) {
        this.idDiscussion = idDiscussion;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Utilisateur getReceiver_message() {
        return receiver_message;
    }

    public void setReceiver_message(Utilisateur receiver_message) {
        this.receiver_message = receiver_message;
    }

    public Utilisateur getSender_message() {
        return sender_message;
    }

    public void setSender_message(Utilisateur sender_message) {
        this.sender_message = sender_message;
    }

    public int getIdMessage() {
        return idMessage;
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

    @Override
    public String toString() {
        return "Messagerie{" +
                "date=" + date +
                ", receiver_message=" + receiver_message +
                ", sender_message=" + sender_message +
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
}