package com.library.dao;

public class DAOFactory {
    
    private static PublisherDAO publisherDAO;
    private static LibraryBranchDAO libraryBranchDAO;
    private static BookDAO bookDAO;
    private static PatronDAO patronDAO;
    
    public static PublisherDAO getPublisherDAO() {
        if (publisherDAO == null) {
            publisherDAO = new PublisherDAOImpl();
        }
        return publisherDAO;
    }
    
    public static LibraryBranchDAO getLibraryBranchDAO() {
        if (libraryBranchDAO == null) {
            libraryBranchDAO = new LibraryBranchDAOImpl();
        }
        return libraryBranchDAO;
    }
    
    public static BookDAO getBookDAO() {
        if (bookDAO == null) {
            bookDAO = new BookDAOImpl();
        }
        return bookDAO;
    }
    
    public static PatronDAO getPatronDAO() {
        if (patronDAO == null) {
            patronDAO = new PatronDAOImpl();
        }
        return patronDAO;
    }
} 