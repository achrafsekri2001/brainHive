package edu.esprit.utils;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource {
    private final String URL = "jdbc:mysql://127.0.0.1:3307/brainhive";
    private final String USER = "root";
    private final String PWD = "123456";
    private Connection cnx;

    private static DataSource instance;

    private DataSource(){
        try {
            cnx = DriverManager.getConnection(URL, USER, PWD);
            System.out.println("Connected to DB !");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static DataSource getInstance(){
        if(instance == null)
            instance = new DataSource();
        return instance;
    }

    public Connection getCnx(){
        return cnx;
    }
}
