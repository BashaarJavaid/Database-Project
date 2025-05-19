package com.library.dao;

import com.library.model.LibraryBranch;
import java.util.List;

public interface LibraryBranchDAO {
    public LibraryBranch findById(int id);
    public List<LibraryBranch> findAll();
    public boolean insert(LibraryBranch branch);
    public boolean update(LibraryBranch branch);
    public boolean delete(int id);
} 