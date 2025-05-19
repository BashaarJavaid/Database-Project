package com.library.dao;

import com.library.model.Patron;
import java.util.List;

public interface PatronDAO {
    public Patron findById(int id);
    public List<Patron> findAll();
    public List<Patron> findByLastName(String lastName);
    public boolean insert(Patron patron);
    public boolean update(Patron patron);
    public boolean delete(int id);
} 