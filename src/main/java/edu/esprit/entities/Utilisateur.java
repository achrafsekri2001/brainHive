package edu.esprit.entities;

import java.util.Objects;

public class Utilisateur {
    private static int id;

    private String UserName , imgUser;


   public static Utilisateur Current_User;


    public Utilisateur(){
    }

    public Utilisateur(int id, String UserName, String imgUser) {
        this.id = id;
        this.UserName = UserName;
        this.imgUser = imgUser;

    }

    public Utilisateur(String UserName, String imgUserd) {
        this.imgUser = imgUserd;
        this.UserName = UserName;

    }

    public static Utilisateur getCurrent_User() {
        return Current_User;
    }

    public static void setCurrent_User(Utilisateur Current_User) {
        Utilisateur.Current_User = Current_User;
    }
    public static int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public String getImgUser() {
        return imgUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Utilisateur that = (Utilisateur) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Utilisateur{" +
                "id=" + id +
                ", UserName='" + UserName + '\'' +
                ", imgUser='" + imgUser + '\'' +
                '}';
    }

    public void setImgUser(String imgUser) {
        this.imgUser = imgUser;
    }

}