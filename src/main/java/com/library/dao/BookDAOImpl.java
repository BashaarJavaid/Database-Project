package com.library.dao;

import com.library.model.Book;
import com.library.util.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDAOImpl implements BookDAO {

    @Override
    public Book findById(int id) {
        String sql = "SELECT * FROM Book WHERE book_id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Book book = null;
        
        try {
            conn = DatabaseUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                book = extractBookFromResultSet(rs);
            }
        } catch (SQLException e) {
            System.out.println("Error finding book by ID: " + e.getMessage());
        } finally {
            DatabaseUtil.closeResources(rs, pstmt, conn);
        }
        
        return book;
    }

    @Override
    public List<Book> findAll() {
        String sql = "SELECT * FROM Book";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Book> books = new ArrayList<>();
        
        try {
            conn = DatabaseUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Book book = extractBookFromResultSet(rs);
                books.add(book);
            }
        } catch (SQLException e) {
            System.out.println("Error finding all books: " + e.getMessage());
        } finally {
            DatabaseUtil.closeResources(rs, pstmt, conn);
        }
        
        return books;
    }
    
    @Override
    public List<Book> findByTitle(String title) {
        String sql = "SELECT * FROM Book WHERE title LIKE ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Book> books = new ArrayList<>();
        
        try {
            conn = DatabaseUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "%" + title + "%");
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Book book = extractBookFromResultSet(rs);
                books.add(book);
            }
        } catch (SQLException e) {
            System.out.println("Error finding books by title: " + e.getMessage());
        } finally {
            DatabaseUtil.closeResources(rs, pstmt, conn);
        }
        
        return books;
    }
    
    @Override
    public List<Book> findByPublisher(int publisherId) {
        String sql = "SELECT * FROM Book WHERE publisher_id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Book> books = new ArrayList<>();
        
        try {
            conn = DatabaseUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, publisherId);
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Book book = extractBookFromResultSet(rs);
                books.add(book);
            }
        } catch (SQLException e) {
            System.out.println("Error finding books by publisher: " + e.getMessage());
        } finally {
            DatabaseUtil.closeResources(rs, pstmt, conn);
        }
        
        return books;
    }
    
    @Override
    public List<Book> findByBranch(int branchId) {
        String sql = "SELECT * FROM Book WHERE branch_id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Book> books = new ArrayList<>();
        
        try {
            conn = DatabaseUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, branchId);
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Book book = extractBookFromResultSet(rs);
                books.add(book);
            }
        } catch (SQLException e) {
            System.out.println("Error finding books by branch: " + e.getMessage());
        } finally {
            DatabaseUtil.closeResources(rs, pstmt, conn);
        }
        
        return books;
    }

    @Override
    public boolean insert(Book book) {
        String sql = "INSERT INTO Book (book_id, title, isbn, publication_year, publisher_id, copy_number, branch_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement pstmt = null;
        boolean success = false;
        
        try {
            conn = DatabaseUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, book.getBookId());
            pstmt.setString(2, book.getTitle());
            pstmt.setString(3, book.getIsbn());
            pstmt.setInt(4, book.getPublicationYear());
            pstmt.setInt(5, book.getPublisherId());
            pstmt.setInt(6, book.getCopyNumber());
            pstmt.setInt(7, book.getBranchId());
            
            int rowsAffected = pstmt.executeUpdate();
            success = rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Error inserting book: " + e.getMessage());
        } finally {
            DatabaseUtil.closeResources(pstmt, conn);
        }
        
        return success;
    }

    @Override
    public boolean update(Book book) {
        String sql = "UPDATE Book SET title = ?, isbn = ?, publication_year = ?, publisher_id = ?, copy_number = ?, branch_id = ? WHERE book_id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        boolean success = false;
        
        try {
            conn = DatabaseUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, book.getTitle());
            pstmt.setString(2, book.getIsbn());
            pstmt.setInt(3, book.getPublicationYear());
            pstmt.setInt(4, book.getPublisherId());
            pstmt.setInt(5, book.getCopyNumber());
            pstmt.setInt(6, book.getBranchId());
            pstmt.setInt(7, book.getBookId());
            
            int rowsAffected = pstmt.executeUpdate();
            success = rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Error updating book: " + e.getMessage());
        } finally {
            DatabaseUtil.closeResources(pstmt, conn);
        }
        
        return success;
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM Book WHERE book_id = ?";
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
            System.out.println("Error deleting book: " + e.getMessage());
        } finally {
            DatabaseUtil.closeResources(pstmt, conn);
        }
        
        return success;
    }
    
    private Book extractBookFromResultSet(ResultSet rs) throws SQLException {
        Book book = new Book();
        book.setBookId(rs.getInt("book_id"));
        book.setTitle(rs.getString("title"));
        book.setIsbn(rs.getString("isbn"));
        book.setPublicationYear(rs.getInt("publication_year"));
        book.setPublisherId(rs.getInt("publisher_id"));
        book.setCopyNumber(rs.getInt("copy_number"));
        book.setBranchId(rs.getInt("branch_id"));
        return book;
    }
} 