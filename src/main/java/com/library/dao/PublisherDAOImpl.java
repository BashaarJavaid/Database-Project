package com.library.dao;

import com.library.model.Publisher;
import com.library.util.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PublisherDAOImpl implements PublisherDAO {

    @Override
    public Publisher findById(int id) {
        String sql = "SELECT * FROM Publisher WHERE publisher_id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Publisher publisher = null;
        
        try {
            conn = DatabaseUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                publisher = new Publisher();
                publisher.setPublisherId(rs.getInt("publisher_id"));
                publisher.setName(rs.getString("name"));
                publisher.setAddress(rs.getString("address"));
                publisher.setContactPerson(rs.getString("contact_person"));
                publisher.setPhone(rs.getString("phone"));
            }
        } catch (SQLException e) {
            System.out.println("Error finding publisher by ID: " + e.getMessage());
        } finally {
            DatabaseUtil.closeResources(rs, pstmt, conn);
        }
        
        return publisher;
    }

    @Override
    public List<Publisher> findAll() {
        String sql = "SELECT * FROM Publisher";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Publisher> publishers = new ArrayList<>();
        
        try {
            conn = DatabaseUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Publisher publisher = new Publisher();
                publisher.setPublisherId(rs.getInt("publisher_id"));
                publisher.setName(rs.getString("name"));
                publisher.setAddress(rs.getString("address"));
                publisher.setContactPerson(rs.getString("contact_person"));
                publisher.setPhone(rs.getString("phone"));
                publishers.add(publisher);
            }
        } catch (SQLException e) {
            System.out.println("Error finding all publishers: " + e.getMessage());
        } finally {
            DatabaseUtil.closeResources(rs, pstmt, conn);
        }
        
        return publishers;
    }

    @Override
    public boolean insert(Publisher publisher) {
        String sql = "INSERT INTO Publisher (publisher_id, name, address, contact_person, phone) VALUES (?, ?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement pstmt = null;
        boolean success = false;
        
        try {
            conn = DatabaseUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, publisher.getPublisherId());
            pstmt.setString(2, publisher.getName());
            pstmt.setString(3, publisher.getAddress());
            pstmt.setString(4, publisher.getContactPerson());
            pstmt.setString(5, publisher.getPhone());
            
            int rowsAffected = pstmt.executeUpdate();
            success = rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Error inserting publisher: " + e.getMessage());
        } finally {
            DatabaseUtil.closeResources(pstmt, conn);
        }
        
        return success;
    }

    @Override
    public boolean update(Publisher publisher) {
        String sql = "UPDATE Publisher SET name = ?, address = ?, contact_person = ?, phone = ? WHERE publisher_id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        boolean success = false;
        
        try {
            conn = DatabaseUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, publisher.getName());
            pstmt.setString(2, publisher.getAddress());
            pstmt.setString(3, publisher.getContactPerson());
            pstmt.setString(4, publisher.getPhone());
            pstmt.setInt(5, publisher.getPublisherId());
            
            int rowsAffected = pstmt.executeUpdate();
            success = rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Error updating publisher: " + e.getMessage());
        } finally {
            DatabaseUtil.closeResources(pstmt, conn);
        }
        
        return success;
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM Publisher WHERE publisher_id = ?";
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
            System.out.println("Error deleting publisher: " + e.getMessage());
        } finally {
            DatabaseUtil.closeResources(pstmt, conn);
        }
        
        return success;
    }
} 