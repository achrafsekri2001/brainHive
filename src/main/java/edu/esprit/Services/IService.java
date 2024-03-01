package edu.esprit.Services;

import java.sql.SQLException;
import java.util.Set;

public interface IService <User> {
    public void ajouter(User t);
    public void modifier(User t) throws SQLException;
    public void supprimer(int id);
    public Set<User > getAll();
    public User getOneByID(int id);


}
