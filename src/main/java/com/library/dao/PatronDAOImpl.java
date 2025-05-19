package com.library.dao;

import com.library.model.Patron;
import com.library.util.DatabaseUtil;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class PatronDAOImpl implements PatronDAO {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public Patron findById(int id) {
        String sql = "SELECT * FROM Patron WHERE patron_id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Patron patron = null;
        
        try {
            conn = DatabaseUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                patron = extractPatronFromResultSet(rs);
            }
        } catch (SQLException e) {
            System.out.println("Error finding patron by ID: " + e.getMessage());
        } finally {
            DatabaseUtil.closeResources(rs, pstmt, conn);
        }
        
        return patron;
    }

    @Override
    public List<Patron> findAll() {
        String sql = "SELECT * FROM Patron";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Patron> patrons = new ArrayList<>();
        
        try {
            conn = DatabaseUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Patron patron = extractPatronFromResultSet(rs);
                patrons.add(patron);
            }
        } catch (SQLException e) {
            System.out.println("Error finding all patrons: " + e.getMessage());
        } finally {
            DatabaseUtil.closeResources(rs, pstmt, conn);
        }
        
        return patrons;
    }
    
    @Override
    public List<Patron> findByLastName(String lastName) {
        String sql = "SELECT * FROM Patron WHERE last_name LIKE ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Patron> patrons = new ArrayList<>();
        
        try {
            conn = DatabaseUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "%" + lastName + "%");
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Patron patron = extractPatronFromResultSet(rs);
                patrons.add(patron);
            }
        } catch (SQLException e) {
            System.out.println("Error finding patrons by last name: " + e.getMessage());
        } finally {
            DatabaseUtil.closeResources(rs, pstmt, conn);
        }
        
        return patrons;
    }

    @Override
    public boolean insert(Patron patron) {
        String sql = "INSERT INTO Patron (patron_id, first_name, last_name, email, phone, address, registration_date) VALUES (?, ?, ?, ?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement pstmt = null;
        boolean success = false;
        
        try {
            conn = DatabaseUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, patron.getPatronId());
            pstmt.setString(2, patron.getFirstName());
            pstmt.setString(3, patron.getLastName());
            pstmt.setString(4, patron.getEmail());
            pstmt.setString(5, patron.getPhone());
            pstmt.setString(6, patron.getAddress());
            pstmt.setString(7, patron.getRegistrationDate() != null ? dateFormat.format(patron.getRegistrationDate()) : null);
            
            int rowsAffected = pstmt.executeUpdate();
            success = rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Error inserting patron: " + e.getMessage());
        } finally {
            DatabaseUtil.closeResources(pstmt, conn);
        }
        
        return success;
    }

    @Override
    public boolean update(Patron patron) {
        String sql = "UPDATE Patron SET first_name = ?, last_name = ?, email = ?, phone = ?, address = ?, registration_date = ? WHERE patron_id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        boolean success = false;
        
        try {
            conn = DatabaseUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, patron.getFirstName());
            pstmt.setString(2, patron.getLastName());
            pstmt.setString(3, patron.getEmail());
            pstmt.setString(4, patron.getPhone());
            pstmt.setString(5, patron.getAddress());
            pstmt.setString(6, patron.getRegistrationDate() != null ? dateFormat.format(patron.getRegistrationDate()) : null);
            pstmt.setInt(7, patron.getPatronId());
            
            int rowsAffected = pstmt.executeUpdate();
            success = rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Error updating patron: " + e.getMessage());
        } finally {
            DatabaseUtil.closeResources(pstmt, conn);
        }
        
        return success;
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM Patron WHERE patron_id = ?";
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
            System.out.println("Error deleting patron: " + e.getMessage());
        } finally {
            DatabaseUtil.closeResources(pstmt, conn);
        }
        
        return success;
    }
    
    private Patron extractPatronFromResultSet(ResultSet rs) throws SQLException {
        Patron patron = new Patron();
        patron.setPatronId(rs.getInt("patron_id"));
        patron.setFirstName(rs.getString("first_name"));
        patron.setLastName(rs.getString("last_name"));
        patron.setEmail(rs.getString("email"));
        patron.setPhone(rs.getString("phone"));
        patron.setAddress(rs.getString("address"));
        
        String dateStr = rs.getString("registration_date");
        if (dateStr != null && !dateStr.isEmpty()) {
            try {
                patron.setRegistrationDate(dateFormat.parse(dateStr));
            } catch (ParseException e) {
                System.out.println("Error parsing date: " + dateStr);
            }
        }
        
        return patron;
    }
} 