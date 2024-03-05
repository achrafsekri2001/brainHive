package edu.esprit.services;

import java.util.List;
import java.util.Set;

public interface IService <T>{
    public void ajouter(T t);
    public void modifier(T t);
    public void supprimer(int id);
    public List<T> getAll();
    public T getOneByID(int id);
}
