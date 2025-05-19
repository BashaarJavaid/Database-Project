package com.library.dao;

import com.library.model.LibraryBranch;
import com.library.util.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LibraryBranchDAOImpl implements LibraryBranchDAO {

    @Override
    public LibraryBranch findById(int id) {
        String sql = "SELECT * FROM Library_Branch WHERE branch_id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        LibraryBranch branch = null;
        
        try {
            conn = DatabaseUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                branch = new LibraryBranch();
                branch.setBranchId(rs.getInt("branch_id"));
                branch.setName(rs.getString("name"));
                branch.setAddress(rs.getString("address"));
                branch.setPhone(rs.getString("phone"));
                branch.setManager(rs.getString("manager"));
            }
        } catch (SQLException e) {
            System.out.println("Error finding branch by ID: " + e.getMessage());
        } finally {
            DatabaseUtil.closeResources(rs, pstmt, conn);
        }
        
        return branch;
    }

    @Override
    public List<LibraryBranch> findAll() {
        String sql = "SELECT * FROM Library_Branch";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<LibraryBranch> branches = new ArrayList<>();
        
        try {
            conn = DatabaseUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                LibraryBranch branch = new LibraryBranch();
                branch.setBranchId(rs.getInt("branch_id"));
                branch.setName(rs.getString("name"));
                branch.setAddress(rs.getString("address"));
                branch.setPhone(rs.getString("phone"));
                branch.setManager(rs.getString("manager"));
                branches.add(branch);
            }
        } catch (SQLException e) {
            System.out.println("Error finding all branches: " + e.getMessage());
        } finally {
            DatabaseUtil.closeResources(rs, pstmt, conn);
        }
        
        return branches;
    }

    @Override
    public boolean insert(LibraryBranch branch) {
        String sql = "INSERT INTO Library_Branch (branch_id, name, address, phone, manager) VALUES (?, ?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement pstmt = null;
        boolean success = false;
        
        try {
            conn = DatabaseUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, branch.getBranchId());
            pstmt.setString(2, branch.getName());
            pstmt.setString(3, branch.getAddress());
            pstmt.setString(4, branch.getPhone());
            pstmt.setString(5, branch.getManager());
            
            int rowsAffected = pstmt.executeUpdate();
            success = rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Error inserting branch: " + e.getMessage());
        } finally {
            DatabaseUtil.closeResources(pstmt, conn);
        }
        
        return success;
    }

    @Override
    public boolean update(LibraryBranch branch) {
        String sql = "UPDATE Library_Branch SET name = ?, address = ?, phone = ?, manager = ? WHERE branch_id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        boolean success = false;
        
        try {
            conn = DatabaseUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, branch.getName());
            pstmt.setString(2, branch.getAddress());
            pstmt.setString(3, branch.getPhone());
            pstmt.setString(4, branch.getManager());
            pstmt.setInt(5, branch.getBranchId());
            
            int rowsAffected = pstmt.executeUpdate();
            success = rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Error updating branch: " + e.getMessage());
        } finally {
            DatabaseUtil.closeResources(pstmt, conn);
        }
        
        return success;
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM Library_Branch WHERE branch_id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        boolean success = false;
        
        try {
            conn = DatabaseUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            
            int rowsAffected = pstmt.executeUpdate();
            success = rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Error deleting branch: " + e.getMessage());
        } finally {
            DatabaseUtil.closeResources(pstmt, conn);
        }
        
        return success;
    }
} 