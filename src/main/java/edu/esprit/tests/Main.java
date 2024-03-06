package edu.esprit.tests;

import edu.esprit.Services.ServiceUser;
import edu.esprit.controllers.GMailer;
import edu.esprit.entities.User;

public class Main {
    public static void main(String[] args) throws Exception {
        ServiceUser sp = new ServiceUser();
        User u = new User("df","dsdf","dff","rr",1);
        sp.ajouter(u);
    }}
