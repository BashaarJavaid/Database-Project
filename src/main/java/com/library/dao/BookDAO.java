package com.library.dao;

import com.library.model.Book;
import java.util.List;

public interface BookDAO {
    public Book findById(int id);
    public List<Book> findAll();
    public List<Book> findByTitle(String title);
    public List<Book> findByPublisher(int publisherId);
    public List<Book> findByBranch(int branchId);
    public boolean insert(Book book);
    public boolean update(Book book);
    public boolean delete(int id);
} 