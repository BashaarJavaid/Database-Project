# Library Management System

A robust Java-based Library Management System that provides a comprehensive solution for managing library resources, including books, publishers, branches, and patrons. This system is designed to streamline library operations and provide an efficient way to manage library assets.

## Table of Contents
- [Features](#features)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Building the Project](#building-the-project)
- [Running the Application](#running-the-application)
- [Database Schema](#database-schema)
- [Usage Guide](#usage-guide)
- [Contributing](#contributing)

## Features

### Core Functionality
- **Database Management**
  - Initialize database with schema
  - Populate with sample data for testing
  - Automatic data validation and integrity checks

### Publisher Management
- View all publishers
- Add new publishers
- Update existing publisher information
- Delete publishers (with constraint checking)
- Search publishers by name

### Library Branch Operations
- View all library branches
- Add new branch locations
- Update branch information
- Delete branches (with safety checks)
- View books available at each branch

### Book Management
- Comprehensive book catalog system
- Add new books with publisher and branch information
- Update book details
- Remove books from the system
- Advanced search functionality:
  - Search by title
  - Filter by publisher
  - Filter by branch location
  - Search by availability status

### Patron Services
- Patron registration and management
- View patron information
- Update patron details
- Track borrowing history

## Prerequisites

- Java JDK 11 or higher
- Maven 3.6.x or higher
- SQLite 3.x
- Minimum 256MB RAM
- 1GB free disk space

## Installation

1. Clone the repository:
   ```bash
   git clone [repository-url]
   cd library-management-system
   ```

2. Install dependencies:
   ```bash
   mvn install
   ```

## Building the Project

To build the project, run:

```bash
mvn clean package
```

This will:
- Clean previous builds
- Compile the source code
- Run unit tests
- Create an executable JAR file

## Running the Application

Execute the application using:

```bash
java -cp "target/classes:lib/sqlite-jdbc-3.40.1.0.jar" com.library.client.LibraryManagementSystem
```

## Database Schema

The system uses SQLite and implements the following main tables:

### 1. Publisher
- `publisher_id` (Primary Key)
- `name`
- `address`
- `phone`
- `email`

### 2. Library_Branch
- `branch_id` (Primary Key)
- `branch_name`
- `address`
- `phone`

### 3. Book
- `book_id` (Primary Key)
- `title`
- `publisher_id` (Foreign Key)
- `branch_id` (Foreign Key)
- `isbn`
- `publication_year`
- `available_copies`

### 4. Patron
- `patron_id` (Primary Key)
- `name`
- `address`
- `phone`
- `email`
- `membership_date`

## Usage Guide

1. **Starting the Application**
   - Launch the application using the command provided above
   - Navigate through the menu using number inputs

2. **Managing Publishers**
   - Select option 1 from the main menu
   - Follow prompts for adding, updating, or deleting publishers

3. **Managing Branches**
   - Select option 2 from the main menu
   - Use sub-menus to perform branch-related operations

4. **Book Management**
   - Select option 3 for book-related operations
   - Follow the interactive prompts for various book management tasks

5. **Patron Services**
   - Select option 4 for patron management
   - Follow the menu system for patron-related operations

## Contributing

1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Push to the branch
5. Create a Pull Request

---

For additional support or questions, please open an issue in the repository.

