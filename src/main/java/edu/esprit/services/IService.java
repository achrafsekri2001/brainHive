package edu.esprit.services;

import java.sql.SQLException;
import java.util.Set;

public interface IService <T>{
    public void ajouter(T t) throws SQLException;
    public void modifier(T t);
    public void supprimer(int id);
    public Set<T> getAll() throws SQLException;
    public T getOneByID(int id);
}