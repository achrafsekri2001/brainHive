package edu.esprit.entities;

public class Admin {
    private int id_admin;
    private String login;
    private String password;


    public Admin() {
    }

    public Admin(String login, String password) {

        this.login = login;
        this.password = password;

    }

    public Admin(int id_admin, String login, String password) {
        this.id_admin = id_admin;
        this.login = login;
        this.password = password;
    }

    public int getId_admin() {
        return id_admin;
    }

    public void setId_admin(int id_admin) {
        this.id_admin = id_admin;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Admin{" + "id_admin=" + id_admin + ", login=" + login + ", password=" + password +  '}';
    }




}

