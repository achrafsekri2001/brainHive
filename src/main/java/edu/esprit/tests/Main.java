package edu.esprit.tests;

import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import edu.esprit.entities.Quiz;
import edu.esprit.services.ServiceQuiz;
import org.jpedal.parser.gs.Q;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        System.out.println("Hello World !");
        ServiceQuiz qs = new ServiceQuiz();
        qs.supprimer(1);
        System.out.println("question supprimé");



    }
}
