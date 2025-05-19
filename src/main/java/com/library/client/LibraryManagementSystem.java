package com.library.client;

import com.library.dao.BookDAO;
import com.library.dao.DAOFactory;
import com.library.dao.LibraryBranchDAO;
import com.library.dao.PatronDAO;
import com.library.dao.PublisherDAO;
import com.library.model.Book;
import com.library.model.LibraryBranch;
import com.library.model.Patron;
import com.library.model.Publisher;
import com.library.util.DatabaseUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class LibraryManagementSystem {
    
    private static final Scanner scanner = new Scanner(System.in);
    private static final PublisherDAO publisherDAO = DAOFactory.getPublisherDAO();
    private static final LibraryBranchDAO branchDAO = DAOFactory.getLibraryBranchDAO();
    private static final BookDAO bookDAO = DAOFactory.getBookDAO();
    private static final PatronDAO patronDAO = DAOFactory.getPatronDAO();
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    
    public static void main(String[] args) {
        // Initialize database
        initializeDatabase();
        
        boolean exit = false;
        while (!exit) {
            displayMainMenu();
            int choice = getUserChoice();
            
            switch (choice) {
                case 1:
                    handlePublisherOperations();
                    break;
                case 2:
                    handleBranchOperations();
                    break;
                case 3:
                    handleBookOperations();
                    break;
                case 4:
                    handlePatronOperations();
                    break;
                case 5:
                    exit = true;
                    System.out.println("Exiting the system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        
        scanner.close();
    }
    
    private static void initializeDatabase() {
        System.out.println("Initializing database...");
        DatabaseUtil.initializeDatabase();
        DatabaseUtil.populateSampleData();
        System.out.println("Database initialized successfully!\n");
    }
    
    private static void displayMainMenu() {
        System.out.println("\n===== LIBRARY MANAGEMENT SYSTEM =====");
        System.out.println("1. Publisher Management");
        System.out.println("2. Library Branch Management");
        System.out.println("3. Book Management");
        System.out.println("4. Patron Management");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
    }
    
    private static int getUserChoice() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    
    private static void handlePublisherOperations() {
        boolean back = false;
        while (!back) {
            System.out.println("\n===== PUBLISHER MANAGEMENT =====");
            System.out.println("1. View All Publishers");
            System.out.println("2. Add New Publisher");
            System.out.println("3. Update Publisher");
            System.out.println("4. Delete Publisher");
            System.out.println("5. Back to Main Menu");
            System.out.print("Enter your choice: ");
            
            int choice = getUserChoice();
            
            switch (choice) {
                case 1:
                    displayAllPublishers();
                    break;
                case 2:
                    addPublisher();
                    break;
                case 3:
                    updatePublisher();
                    break;
                case 4:
                    deletePublisher();
                    break;
                case 5:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    
    private static void handleBranchOperations() {
        boolean back = false;
        while (!back) {
            System.out.println("\n===== LIBRARY BRANCH MANAGEMENT =====");
            System.out.println("1. View All Branches");
            System.out.println("2. Add New Branch");
            System.out.println("3. Update Branch");
            System.out.println("4. Delete Branch");
            System.out.println("5. Back to Main Menu");
            System.out.print("Enter your choice: ");
            
            int choice = getUserChoice();
            
            switch (choice) {
                case 1:
                    displayAllBranches();
                    break;
                case 2:
                    addBranch();
                    break;
                case 3:
                    updateBranch();
                    break;
                case 4:
                    deleteBranch();
                    break;
                case 5:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    
    private static void handleBookOperations() {
        boolean back = false;
        while (!back) {
            System.out.println("\n===== BOOK MANAGEMENT =====");
            System.out.println("1. View All Books");
            System.out.println("2. Search Books by Title");
            System.out.println("3. View Books by Publisher");
            System.out.println("4. View Books by Branch");
            System.out.println("5. Add New Book");
            System.out.println("6. Update Book");
            System.out.println("7. Delete Book");
            System.out.println("8. Back to Main Menu");
            System.out.print("Enter your choice: ");
            
            int choice = getUserChoice();
            
            switch (choice) {
                case 1:
                    displayAllBooks();
                    break;
                case 2:
                    searchBooksByTitle();
                    break;
                case 3:
                    viewBooksByPublisher();
                    break;
                case 4:
                    viewBooksByBranch();
                    break;
                case 5:
                    addBook();
                    break;
                case 6:
                    updateBook();
                    break;
                case 7:
                    deleteBook();
                    break;
                case 8:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    
    private static void handlePatronOperations() {
        boolean back = false;
        while (!back) {
            System.out.println("\n===== PATRON MANAGEMENT =====");
            System.out.println("1. View All Patrons");
            System.out.println("2. Search Patrons by Last Name");
            System.out.println("3. Add New Patron");
            System.out.println("4. Update Patron");
            System.out.println("5. Delete Patron");
            System.out.println("6. Back to Main Menu");
            System.out.print("Enter your choice: ");
            
            int choice = getUserChoice();
            
            switch (choice) {
                case 1:
                    displayAllPatrons();
                    break;
                case 2:
                    searchPatronsByLastName();
                    break;
                case 3:
                    addPatron();
                    break;
                case 4:
                    updatePatron();
                    break;
                case 5:
                    deletePatron();
                    break;
                case 6:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    
    // Publisher Operations
    private static void displayAllPublishers() {
        System.out.println("\n===== ALL PUBLISHERS =====");
        List<Publisher> publishers = publisherDAO.findAll();
        
        if (publishers.isEmpty()) {
            System.out.println("No publishers found.");
        } else {
            System.out.printf("%-5s %-30s %-40s %-20s %-15s%n", "ID", "Name", "Address", "Contact Person", "Phone");
            System.out.println("------------------------------------------------------------------------------------------------------------");
            
            for (Publisher publisher : publishers) {
                System.out.printf("%-5d %-30s %-40s %-20s %-15s%n",
                        publisher.getPublisherId(),
                        publisher.getName(),
                        publisher.getAddress(),
                        publisher.getContactPerson(),
                        publisher.getPhone());
            }
        }
    }
    
    private static void addPublisher() {
        System.out.println("\n===== ADD NEW PUBLISHER =====");
        
        System.out.print("Enter publisher ID: ");
        int id = getUserChoice();
        
        System.out.print("Enter publisher name: ");
        String name = scanner.nextLine();
        
        System.out.print("Enter publisher address: ");
        String address = scanner.nextLine();
        
        System.out.print("Enter contact person: ");
        String contactPerson = scanner.nextLine();
        
        System.out.print("Enter phone number: ");
        String phone = scanner.nextLine();
        
        Publisher publisher = new Publisher(id, name, address, contactPerson, phone);
        
        if (publisherDAO.insert(publisher)) {
            System.out.println("Publisher added successfully!");
        } else {
            System.out.println("Failed to add publisher.");
        }
    }
    
    private static void updatePublisher() {
        System.out.println("\n===== UPDATE PUBLISHER =====");
        
        System.out.print("Enter publisher ID to update: ");
        int id = getUserChoice();
        
        Publisher publisher = publisherDAO.findById(id);
        
        if (publisher == null) {
            System.out.println("Publisher not found.");
            return;
        }
        
        System.out.println("Current details: " + publisher);
        
        System.out.print("Enter new name (or press Enter to keep current): ");
        String name = scanner.nextLine();
        if (!name.isEmpty()) {
            publisher.setName(name);
        }
        
        System.out.print("Enter new address (or press Enter to keep current): ");
        String address = scanner.nextLine();
        if (!address.isEmpty()) {
            publisher.setAddress(address);
        }
        
        System.out.print("Enter new contact person (or press Enter to keep current): ");
        String contactPerson = scanner.nextLine();
        if (!contactPerson.isEmpty()) {
            publisher.setContactPerson(contactPerson);
        }
        
        System.out.print("Enter new phone number (or press Enter to keep current): ");
        String phone = scanner.nextLine();
        if (!phone.isEmpty()) {
            publisher.setPhone(phone);
        }
        
        if (publisherDAO.update(publisher)) {
            System.out.println("Publisher updated successfully!");
        } else {
            System.out.println("Failed to update publisher.");
        }
    }
    
    private static void deletePublisher() {
        System.out.println("\n===== DELETE PUBLISHER =====");
        
        System.out.print("Enter publisher ID to delete: ");
        int id = getUserChoice();
        
        if (publisherDAO.delete(id)) {
            System.out.println("Publisher deleted successfully!");
        } else {
            System.out.println("Failed to delete publisher. It might be referenced by books.");
        }
    }
    
    // Branch Operations
    private static void displayAllBranches() {
        System.out.println("\n===== ALL LIBRARY BRANCHES =====");
        List<LibraryBranch> branches = branchDAO.findAll();
        
        if (branches.isEmpty()) {
            System.out.println("No branches found.");
        } else {
            System.out.printf("%-5s %-20s %-40s %-15s %-20s%n", "ID", "Name", "Address", "Phone", "Manager");
            System.out.println("------------------------------------------------------------------------------------------------------------");
            
            for (LibraryBranch branch : branches) {
                System.out.printf("%-5d %-20s %-40s %-15s %-20s%n",
                        branch.getBranchId(),
                        branch.getName(),
                        branch.getAddress(),
                        branch.getPhone(),
                        branch.getManager());
            }
        }
    }
    
    private static void addBranch() {
        System.out.println("\n===== ADD NEW LIBRARY BRANCH =====");
        
        System.out.print("Enter branch ID: ");
        int id = getUserChoice();
        
        System.out.print("Enter branch name: ");
        String name = scanner.nextLine();
        
        System.out.print("Enter branch address: ");
        String address = scanner.nextLine();
        
        System.out.print("Enter phone number: ");
        String phone = scanner.nextLine();
        
        System.out.print("Enter manager name: ");
        String manager = scanner.nextLine();
        
        LibraryBranch branch = new LibraryBranch(id, name, address, phone, manager);
        
        if (branchDAO.insert(branch)) {
            System.out.println("Library branch added successfully!");
        } else {
            System.out.println("Failed to add library branch.");
        }
    }
    
    private static void updateBranch() {
        System.out.println("\n===== UPDATE LIBRARY BRANCH =====");
        
        System.out.print("Enter branch ID to update: ");
        int id = getUserChoice();
        
        LibraryBranch branch = branchDAO.findById(id);
        
        if (branch == null) {
            System.out.println("Library branch not found.");
            return;
        }
        
        System.out.println("Current details: " + branch);
        
        System.out.print("Enter new name (or press Enter to keep current): ");
        String name = scanner.nextLine();
        if (!name.isEmpty()) {
            branch.setName(name);
        }
        
        System.out.print("Enter new address (or press Enter to keep current): ");
        String address = scanner.nextLine();
        if (!address.isEmpty()) {
            branch.setAddress(address);
        }
        
        System.out.print("Enter new phone number (or press Enter to keep current): ");
        String phone = scanner.nextLine();
        if (!phone.isEmpty()) {
            branch.setPhone(phone);
        }
        
        System.out.print("Enter new manager name (or press Enter to keep current): ");
        String manager = scanner.nextLine();
        if (!manager.isEmpty()) {
            branch.setManager(manager);
        }
        
        if (branchDAO.update(branch)) {
            System.out.println("Library branch updated successfully!");
        } else {
            System.out.println("Failed to update library branch.");
        }
    }
    
    private static void deleteBranch() {
        System.out.println("\n===== DELETE LIBRARY BRANCH =====");
        
        System.out.print("Enter branch ID to delete: ");
        int id = getUserChoice();
        
        if (branchDAO.delete(id)) {
            System.out.println("Library branch deleted successfully!");
        } else {
            System.out.println("Failed to delete library branch. It might be referenced by books.");
        }
    }
    
    // Book Operations
    private static void displayAllBooks() {
        System.out.println("\n===== ALL BOOKS =====");
        List<Book> books = bookDAO.findAll();
        
        if (books.isEmpty()) {
            System.out.println("No books found.");
        } else {
            displayBooks(books);
        }
    }
    
    private static void searchBooksByTitle() {
        System.out.println("\n===== SEARCH BOOKS BY TITLE =====");
        
        System.out.print("Enter title to search: ");
        String title = scanner.nextLine();
        
        List<Book> books = bookDAO.findByTitle(title);
        
        if (books.isEmpty()) {
            System.out.println("No books found with title containing '" + title + "'.");
        } else {
            System.out.println("Books matching '" + title + "':");
            displayBooks(books);
        }
    }
    
    private static void viewBooksByPublisher() {
        System.out.println("\n===== VIEW BOOKS BY PUBLISHER =====");
        
        // Display publishers first
        displayAllPublishers();
        
        System.out.print("Enter publisher ID: ");
        int publisherId = getUserChoice();
        
        List<Book> books = bookDAO.findByPublisher(publisherId);
        
        if (books.isEmpty()) {
            System.out.println("No books found for publisher ID " + publisherId + ".");
        } else {
            System.out.println("Books from publisher ID " + publisherId + ":");
            displayBooks(books);
        }
    }
    
    private static void viewBooksByBranch() {
        System.out.println("\n===== VIEW BOOKS BY BRANCH =====");
        
        // Display branches first
        displayAllBranches();
        
        System.out.print("Enter branch ID: ");
        int branchId = getUserChoice();
        
        List<Book> books = bookDAO.findByBranch(branchId);
        
        if (books.isEmpty()) {
            System.out.println("No books found in branch ID " + branchId + ".");
        } else {
            System.out.println("Books in branch ID " + branchId + ":");
            displayBooks(books);
        }
    }
    
    private static void displayBooks(List<Book> books) {
        System.out.printf("%-5s %-40s %-15s %-6s %-12s %-12s %-10s%n", 
                "ID", "Title", "ISBN", "Year", "Publisher ID", "Copy Number", "Branch ID");
        System.out.println("------------------------------------------------------------------------------------------------------------");
        
        for (Book book : books) {
            System.out.printf("%-5d %-40s %-15s %-6d %-12d %-12d %-10d%n",
                    book.getBookId(),
                    book.getTitle(),
                    book.getIsbn(),
                    book.getPublicationYear(),
                    book.getPublisherId(),
                    book.getCopyNumber(),
                    book.getBranchId());
        }
    }
    
    private static void addBook() {
        System.out.println("\n===== ADD NEW BOOK =====");
        
        System.out.print("Enter book ID: ");
        int id = getUserChoice();
        
        System.out.print("Enter title: ");
        String title = scanner.nextLine();
        
        System.out.print("Enter ISBN: ");
        String isbn = scanner.nextLine();
        
        System.out.print("Enter publication year: ");
        int year = getUserChoice();
        
        // Display publishers
        displayAllPublishers();
        System.out.print("Enter publisher ID: ");
        int publisherId = getUserChoice();
        
        System.out.print("Enter copy number: ");
        int copyNumber = getUserChoice();
        
        // Display branches
        displayAllBranches();
        System.out.print("Enter branch ID: ");
        int branchId = getUserChoice();
        
        Book book = new Book(id, title, isbn, year, publisherId, copyNumber, branchId);
        
        if (bookDAO.insert(book)) {
            System.out.println("Book added successfully!");
        } else {
            System.out.println("Failed to add book.");
        }
    }
    
    private static void updateBook() {
        System.out.println("\n===== UPDATE BOOK =====");
        
        System.out.print("Enter book ID to update: ");
        int id = getUserChoice();
        
        Book book = bookDAO.findById(id);
        
        if (book == null) {
            System.out.println("Book not found.");
            return;
        }
        
        System.out.println("Current details: " + book);
        
        System.out.print("Enter new title (or press Enter to keep current): ");
        String title = scanner.nextLine();
        if (!title.isEmpty()) {
            book.setTitle(title);
        }
        
        System.out.print("Enter new ISBN (or press Enter to keep current): ");
        String isbn = scanner.nextLine();
        if (!isbn.isEmpty()) {
            book.setIsbn(isbn);
        }
        
        System.out.print("Enter new publication year (or 0 to keep current): ");
        int year = getUserChoice();
        if (year > 0) {
            book.setPublicationYear(year);
        }
        
        System.out.print("Enter new publisher ID (or 0 to keep current): ");
        int publisherId = getUserChoice();
        if (publisherId > 0) {
            book.setPublisherId(publisherId);
        }
        
        System.out.print("Enter new copy number (or 0 to keep current): ");
        int copyNumber = getUserChoice();
        if (copyNumber > 0) {
            book.setCopyNumber(copyNumber);
        }
        
        System.out.print("Enter new branch ID (or 0 to keep current): ");
        int branchId = getUserChoice();
        if (branchId > 0) {
            book.setBranchId(branchId);
        }
        
        if (bookDAO.update(book)) {
            System.out.println("Book updated successfully!");
        } else {
            System.out.println("Failed to update book.");
        }
    }
    
    private static void deleteBook() {
        System.out.println("\n===== DELETE BOOK =====");
        
        System.out.print("Enter book ID to delete: ");
        int id = getUserChoice();
        
        if (bookDAO.delete(id)) {
            System.out.println("Book deleted successfully!");
        } else {
            System.out.println("Failed to delete book.");
        }
    }
    
    // Patron Operations
    private static void displayAllPatrons() {
        System.out.println("\n===== ALL PATRONS =====");
        List<Patron> patrons = patronDAO.findAll();
        
        if (patrons.isEmpty()) {
            System.out.println("No patrons found.");
        } else {
            System.out.printf("%-5s %-15s %-15s %-30s %-15s %-30s %-12s%n", 
                    "ID", "First Name", "Last Name", "Email", "Phone", "Address", "Reg. Date");
            System.out.println("---------------------------------------------------------------------------------------------------------------------");
            
            for (Patron patron : patrons) {
                System.out.printf("%-5d %-15s %-15s %-30s %-15s %-30s %-12s%n",
                        patron.getPatronId(),
                        patron.getFirstName(),
                        patron.getLastName(),
                        patron.getEmail(),
                        patron.getPhone(),
                        patron.getAddress(),
                        patron.getRegistrationDate() != null ? dateFormat.format(patron.getRegistrationDate()) : "");
            }
        }
    }
    
    private static void searchPatronsByLastName() {
        System.out.println("\n===== SEARCH PATRONS BY LAST NAME =====");
        
        System.out.print("Enter last name to search: ");
        String lastName = scanner.nextLine();
        
        List<Patron> patrons = patronDAO.findByLastName(lastName);
        
        if (patrons.isEmpty()) {
            System.out.println("No patrons found with last name containing '" + lastName + "'.");
        } else {
            System.out.println("Patrons matching '" + lastName + "':");
            System.out.printf("%-5s %-15s %-15s %-30s %-15s %-30s %-12s%n", 
                    "ID", "First Name", "Last Name", "Email", "Phone", "Address", "Reg. Date");
            System.out.println("---------------------------------------------------------------------------------------------------------------------");
            
            for (Patron patron : patrons) {
                System.out.printf("%-5d %-15s %-15s %-30s %-15s %-30s %-12s%n",
                        patron.getPatronId(),
                        patron.getFirstName(),
                        patron.getLastName(),
                        patron.getEmail(),
                        patron.getPhone(),
                        patron.getAddress(),
                        patron.getRegistrationDate() != null ? dateFormat.format(patron.getRegistrationDate()) : "");
            }
        }
    }
    
    private static void addPatron() {
        System.out.println("\n===== ADD NEW PATRON =====");
        
        System.out.print("Enter patron ID: ");
        int id = getUserChoice();
        
        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();
        
        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();
        
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        
        System.out.print("Enter phone number: ");
        String phone = scanner.nextLine();
        
        System.out.print("Enter address: ");
        String address = scanner.nextLine();
        
        System.out.print("Enter registration date (YYYY-MM-DD): ");
        String dateStr = scanner.nextLine();
        Date registrationDate = null;
        
        try {
            if (!dateStr.isEmpty()) {
                registrationDate = dateFormat.parse(dateStr);
            }
        } catch (ParseException e) {
            System.out.println("Invalid date format. Using current date.");
            registrationDate = new Date();
        }
        
        Patron patron = new Patron(id, firstName, lastName, email, phone, address, registrationDate);
        
        if (patronDAO.insert(patron)) {
            System.out.println("Patron added successfully!");
        } else {
            System.out.println("Failed to add patron.");
        }
    }
    
    private static void updatePatron() {
        System.out.println("\n===== UPDATE PATRON =====");
        
        System.out.print("Enter patron ID to update: ");
        int id = getUserChoice();
        
        Patron patron = patronDAO.findById(id);
        
        if (patron == null) {
            System.out.println("Patron not found.");
            return;
        }
        
        System.out.println("Current details: " + patron);
        
        System.out.print("Enter new first name (or press Enter to keep current): ");
        String firstName = scanner.nextLine();
        if (!firstName.isEmpty()) {
            patron.setFirstName(firstName);
        }
        
        System.out.print("Enter new last name (or press Enter to keep current): ");
        String lastName = scanner.nextLine();
        if (!lastName.isEmpty()) {
            patron.setLastName(lastName);
        }
        
        System.out.print("Enter new email (or press Enter to keep current): ");
        String email = scanner.nextLine();
        if (!email.isEmpty()) {
            patron.setEmail(email);
        }
        
        System.out.print("Enter new phone number (or press Enter to keep current): ");
        String phone = scanner.nextLine();
        if (!phone.isEmpty()) {
            patron.setPhone(phone);
        }
        
        System.out.print("Enter new address (or press Enter to keep current): ");
        String address = scanner.nextLine();
        if (!address.isEmpty()) {
            patron.setAddress(address);
        }
        
        System.out.print("Enter new registration date (YYYY-MM-DD) (or press Enter to keep current): ");
        String dateStr = scanner.nextLine();
        if (!dateStr.isEmpty()) {
            try {
                patron.setRegistrationDate(dateFormat.parse(dateStr));
            } catch (ParseException e) {
                System.out.println("Invalid date format. Keeping current date.");
            }
        }
        
        if (patronDAO.update(patron)) {
            System.out.println("Patron updated successfully!");
        } else {
            System.out.println("Failed to update patron.");
        }
    }
    
    private static void deletePatron() {
        System.out.println("\n===== DELETE PATRON =====");
        
        System.out.print("Enter patron ID to delete: ");
        int id = getUserChoice();
        
        if (patronDAO.delete(id)) {
            System.out.println("Patron deleted successfully!");
        } else {
            System.out.println("Failed to delete patron.");
        }
    }
} 