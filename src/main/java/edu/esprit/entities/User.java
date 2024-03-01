package edu.esprit.entities;

import java.util.Objects;

public class User {
    private  int id;
    private  String email;
    private  String nom;
    private  String prenom;
    private  String password;
    private  int roles;
    public User(){}

    public User(String email, String nom, String prenom, String password, int roles) {

        this.email = email;
        this.nom = nom;
        this.prenom = prenom;
        this.password = password;
        this.roles = roles;
    }

    public User(int id, String email, String nom, String prenom, String password, int roles) {
        this.id=id;
        this.email = email;
        this.nom = nom;
        this.prenom = prenom;
        this.password = password;
        this.roles = roles;
    }

    public User(String email, String nom, String prenom, String password) {
        this.email = email;
        this.nom = nom;
        this.prenom = prenom;
        this.password = password;
    }

    public  int getId() {
        return id;
    }

    public  void setId(int id) {
        this.id = id;
    }

    public  String getEmail() {
        return email;
    }

    public  void setEmail(String email) {
        this.email = email;
    }

    public  String getNom() {
        return nom;
    }

    public  void setNom(String nom) {
        this.nom = nom;
    }

    public  String getPrenom() {
        return prenom;
    }

    public  void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public  String getPassword() {
        return password;
    }

    public  void setPassword(String password) {
        this.password = password;
    }

    public  int getRoles() {
        return roles;
    }

    public void setRoles(int roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", password='" + password + '\'' +
                ", roles='" + roles + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return (id == user.id );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);

    }

}



