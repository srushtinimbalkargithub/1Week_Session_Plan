package myPackage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class LibraryUtility 
{

    // ArrayList to store book titles
    private ArrayList<String> bookTitles;

    // Constructor to initialize the ArrayList
    public LibraryUtility() 
    {
        bookTitles = new ArrayList<>();
    }

    // Method to add a new book title to the list
    public void addBookTitle(String title) 
    {
        bookTitles.add(title);
        System.out.println("Book title added: " + title);
    }

    // Method to remove a specific book title from the list
    public void removeBookTitle(String title) 
    {
        if (bookTitles.remove(title)) {
            System.out.println("Book title removed: " + title);
        } else 
        {
            System.out.println("Book title not found: " + title);
        }
    }

    // Method to search for a book title and return its index if found
    public int searchBookTitle(String title) 
    {
        int index = bookTitles.indexOf(title);
        if (index >= 0) {
            System.out.println("Book title found at index: " + index);
        } else 
        {
            System.out.println("Book title not found: " + title);
        }
        return index;
    }

    // Method to list all book titles in the list
    public void listAllBookTitles() 
    {
        System.out.println("Listing all book titles:");
        for (String title : bookTitles) 
        {
            System.out.println(title);
        }
    }

    // Method to sort book titles alphabetically
    public void sortBookTitles() 
    {
        Collections.sort(bookTitles);
        System.out.println("Book titles sorted alphabetically.");
    }

    // Main method for testing the utility
    public static void main(String[] args) 
    {
        LibraryUtility libraryUtility = new LibraryUtility();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nLibrary Utility Menu:");
            System.out.println("1. Add Book Title");
            System.out.println("2. Remove Book Title");
            System.out.println("3. Search Book Title");
            System.out.println("4. List All Book Titles");
            System.out.println("5. Sort Book Titles");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter book title to add: ");
                    String titleToAdd = scanner.nextLine();
                    libraryUtility.addBookTitle(titleToAdd);
                    break;
                case 2:
                    System.out.print("Enter book title to remove: ");
                    String titleToRemove = scanner.nextLine();
                    libraryUtility.removeBookTitle(titleToRemove);
                    break;
                case 3:
                    System.out.print("Enter book title to search: ");
                    String titleToSearch = scanner.nextLine();
                    libraryUtility.searchBookTitle(titleToSearch);
                    break;
                case 4:
                    libraryUtility.listAllBookTitles();
                    break;
                case 5:
                    libraryUtility.sortBookTitles();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 6);

        scanner.close();
    }
}

