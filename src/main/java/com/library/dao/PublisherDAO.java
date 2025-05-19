package com.library.dao;

import com.library.model.Publisher;
import java.util.List;

public interface PublisherDAO {
    public Publisher findById(int id);
    public List<Publisher> findAll();
    public boolean insert(Publisher publisher);
    public boolean update(Publisher publisher);
    public boolean delete(int id);
} 