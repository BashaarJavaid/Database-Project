package com.library.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseUtil {
    private static final String DB_URL = "jdbc:sqlite:library.db";
    
    // Get database connection
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }
    
    // Initialize database by creating tables
    public static void initializeDatabase() {
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            
            // Drop tables if they exist
            stmt.executeUpdate("DROP TABLE IF EXISTS Book");
            stmt.executeUpdate("DROP TABLE IF EXISTS Library_Branch");
            stmt.executeUpdate("DROP TABLE IF EXISTS Publisher");
            stmt.executeUpdate("DROP TABLE IF EXISTS Patron");
            
            // Create Publisher table
            String createPublisherTable = 
                "CREATE TABLE Publisher (" +
                "publisher_id INTEGER PRIMARY KEY, " +
                "name VARCHAR(100) NOT NULL, " +
                "address VARCHAR(255), " +
                "contact_person VARCHAR(100), " +
                "phone VARCHAR(20)" +
                ")";
            stmt.executeUpdate(createPublisherTable);
            
            // Create Library_Branch table
            String createLibraryBranchTable = 
                "CREATE TABLE Library_Branch (" +
                "branch_id INTEGER PRIMARY KEY, " +
                "name VARCHAR(100) NOT NULL, " +
                "address VARCHAR(255) NOT NULL, " +
                "phone VARCHAR(20), " +
                "manager VARCHAR(100)" +
                ")";
            stmt.executeUpdate(createLibraryBranchTable);
            
            // Create Patron table
            String createPatronTable = 
                "CREATE TABLE Patron (" +
                "patron_id INTEGER PRIMARY KEY, " +
                "first_name VARCHAR(50) NOT NULL, " +
                "last_name VARCHAR(50) NOT NULL, " +
                "email VARCHAR(100) UNIQUE, " +
                "phone VARCHAR(20), " +
                "address VARCHAR(255), " +
                "registration_date TEXT" +  // Use TEXT for SQLite date storage
                ")";
            stmt.executeUpdate(createPatronTable);
            
            // Create Book table
            String createBookTable = 
                "CREATE TABLE Book (" +
                "book_id INTEGER PRIMARY KEY, " +
                "title VARCHAR(255) NOT NULL, " +
                "isbn VARCHAR(20), " +
                "publication_year INTEGER, " +
                "publisher_id INTEGER, " +
                "copy_number INTEGER NOT NULL, " +
                "branch_id INTEGER NOT NULL, " +
                "FOREIGN KEY (publisher_id) REFERENCES Publisher(publisher_id), " +
                "FOREIGN KEY (branch_id) REFERENCES Library_Branch(branch_id)" +
                ")";
            stmt.executeUpdate(createBookTable);
            
            // Create indexes
            stmt.executeUpdate("CREATE INDEX idx_book_title ON Book(title)");
            stmt.executeUpdate("CREATE INDEX idx_patron_name ON Patron(last_name, first_name)");
            
            System.out.println("Database tables created successfully");
            
        } catch (SQLException e) {
            System.out.println("Error initializing database: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    // Populate database with sample data
    public static void populateSampleData() {
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            
            // Insert sample Publishers
            String[] publisherInserts = {
                "INSERT INTO Publisher VALUES (1, 'Penguin Random House', '1745 Broadway, New York', 'John Smith', '212-782-9000')",
                "INSERT INTO Publisher VALUES (2, 'HarperCollins', '195 Broadway, New York', 'Sarah Johnson', '212-207-7000')",
                "INSERT INTO Publisher VALUES (3, 'Simon & Schuster', '1230 Avenue of the Americas, New York', 'Michael Brown', '212-698-7000')"
            };
            
            for (String insert : publisherInserts) {
                stmt.executeUpdate(insert);
            }
            
            // Insert sample Library Branches
            String[] branchInserts = {
                "INSERT INTO Library_Branch VALUES (1, 'Main Branch', '123 Main St, Anytown', '555-123-4567', 'Jane Doe')",
                "INSERT INTO Library_Branch VALUES (2, 'East Side Branch', '456 East Blvd, Anytown', '555-987-6543', 'John Major')",
                "INSERT INTO Library_Branch VALUES (3, 'West End Library', '789 West Ave, Anytown', '555-456-7890', 'Robert Smith')"
            };
            
            for (String insert : branchInserts) {
                stmt.executeUpdate(insert);
            }
            
            // Insert sample Patrons
            String[] patronInserts = {
                "INSERT INTO Patron VALUES (1, 'Alice', 'Johnson', 'alice.johnson@email.com', '555-111-2222', '123 Oak St, Anytown', '2021-01-15')",
                "INSERT INTO Patron VALUES (2, 'Bob', 'Smith', 'bob.smith@email.com', '555-333-4444', '456 Pine St, Anytown', '2021-02-20')",
                "INSERT INTO Patron VALUES (3, 'Carol', 'Williams', 'carol.williams@email.com', '555-555-6666', '789 Maple St, Anytown', '2021-03-25')",
                "INSERT INTO Patron VALUES (4, 'David', 'Brown', 'david.brown@email.com', '555-777-8888', '101 Elm St, Anytown', '2021-04-30')"
            };
            
            for (String insert : patronInserts) {
                stmt.executeUpdate(insert);
            }
            
            // Insert sample Books
            String[] bookInserts = {
                "INSERT INTO Book VALUES (1, 'The Great Gatsby', '9780743273565', 1925, 1, 1, 1)",
                "INSERT INTO Book VALUES (2, 'To Kill a Mockingbird', '9780061120084', 1960, 2, 1, 1)",
                "INSERT INTO Book VALUES (3, '1984', '9780451524935', 1949, 3, 1, 2)",
                "INSERT INTO Book VALUES (4, 'Pride and Prejudice', '9780141439518', 1813, 1, 1, 2)",
                "INSERT INTO Book VALUES (5, 'The Hobbit', '9780547928227', 1937, 2, 1, 3)",
                "INSERT INTO Book VALUES (6, 'Harry Potter and the Sorcerer''s Stone', '9780590353427', 1997, 3, 1, 3)",
                "INSERT INTO Book VALUES (7, 'The Catcher in the Rye', '9780241950425', 1951, 1, 1, 1)",
                "INSERT INTO Book VALUES (8, 'The Great Gatsby', '9780743273565-2', 1925, 1, 2, 2)",
                "INSERT INTO Book VALUES (9, 'To Kill a Mockingbird', '9780061120084-2', 1960, 2, 2, 3)"
            };
            
            for (String insert : bookInserts) {
                stmt.executeUpdate(insert);
            }
            
            System.out.println("Sample data inserted successfully");
            
        } catch (SQLException e) {
            System.out.println("Error populating database: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    // Close resources safely
    public static void closeResources(AutoCloseable... resources) {
        for (AutoCloseable resource : resources) {
            if (resource != null) {
                try {
                    resource.close();
                } catch (Exception e) {
                    System.out.println("Error closing resource: " + e.getMessage());
                }
            }
        }
    }
} 