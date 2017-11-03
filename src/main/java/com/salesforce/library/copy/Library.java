package com.salesforce.library.copy;

import java.util.ArrayList;
import java.util.Scanner;

public class Library {
    private ArrayList<AuthorList> catalog = new ArrayList<AuthorList>('Z' + 1);
    private ArrayList<PatronList> people = new ArrayList<PatronList>('Z' + 1);
    private String input;
    Scanner scanner = new Scanner(System.in);

    public Library() {
        for (int i = 0; i <= 'Z'; i++) {
            catalog.add(i, new AuthorList());
            people.add(i, new PatronList());
        }
    }

    private String getString(String msg) {
        System.out.print(msg + " ");
        System.out.flush();
        input = scanner.nextLine();
        return input.substring(0, 1).toUpperCase() + input.substring(1);
    }

    private void status() {
        System.out.println("Library has the following books:\n ");
        for (int i = 'A'; i <= 'Z'; i++)
            if (catalog.get(i).size() > 0)
                catalog.get(i).display();
        System.out.println("\nThe following people are using the library:\n ");
        for (int i = 'A'; i <= 'Z'; i++)
            if (people.get(i).size() > 0)
                people.get(i).display();
    }

    private void includeBook() {
        Author newAuthor = new Author();
        int oldAuthor;
        Book newBook = new Book();
        newAuthor.name = getString("Enter author's name:");
        newBook.title = getString("Enter the title of the book:");
        oldAuthor = catalog.get(newAuthor.name.charAt(0)).indexOf(newAuthor);
        if (oldAuthor == -1) {
            newAuthor.books.add(newBook);
            catalog.get(newAuthor.name.charAt(0)).add(newAuthor);
        } else
            (catalog.get(newAuthor.name.charAt(0)).get(oldAuthor)).books.add(newBook);
    }

    private void checkOutBook() {
        Patron patron = new Patron(), patronRef;
        Author author = new Author(), authorRef = new Author();
        Book book = new Book();
        int patronIndex, bookIndex = -1, authorIndex = -1;
        patron.name = getString("Enter patron's name:");
        while (authorIndex == -1) {
            author.name = getString("Enter author's name:");
            authorIndex = catalog.get(author.name.charAt(0)).indexOf(author);
            if (authorIndex == -1)
                System.out.println("Misspelled author's name");
        }
        while (bookIndex == -1) {
            book.title = getString("Enter the title of the book:");
            authorRef = catalog.get(author.name.charAt(0)).get(authorIndex);
            bookIndex = authorRef.books.indexOf(book);
            if (bookIndex == -1)
                System.out.println("Misspelled title");
        }
        Book bookRef = authorRef.books.get(bookIndex);
        CheckedOutBook bookToCheckOut = new CheckedOutBook();
        bookToCheckOut.author = authorRef;
        bookToCheckOut.book = bookRef;
        patronIndex = people.get(patron.name.charAt(0)).indexOf(patron);
        if (patronIndex == -1) { // a new patron in the library;
            patron.books.add(bookToCheckOut);
            people.get(patron.name.charAt(0)).add(patron);
            bookRef.patron = people.get(patron.name.charAt(0)).getFirst();
        } else {
            patronRef = people.get(patron.name.charAt(0)).get(patronIndex);
            patronRef.books.add(bookToCheckOut);
            bookRef.patron = patronRef;
        }
    }

    private void returnBook() {
        Patron patron = new Patron();
        Book book = new Book();
        Author author = new Author(), authorRef = new Author();
        int patronIndex = -1, bookIndex = -1, authorIndex = -1;
        while (patronIndex == -1) {
            patron.name = getString("Enter patron's name:");
            patronIndex = people.get(patron.name.charAt(0)).indexOf(patron);
            if (patronIndex == -1)
                System.out.println("Patron's name misspelled");
        }
        while (authorIndex == -1) {
            author.name = getString("Enter author's name:");
            authorIndex = catalog.get(author.name.charAt(0)).indexOf(author);
            if (authorIndex == -1)
                System.out.println("Misspelled author's name");
        }
        while (bookIndex == -1) {
            book.title = getString("Enter the title of the book:");
            authorRef = catalog.get(author.name.charAt(0)).get(authorIndex);
            bookIndex = authorRef.books.indexOf(book);
            if (bookIndex == -1)
                System.out.println("Misspelled title");
        }
        CheckedOutBook checkedOutBook = new CheckedOutBook();
        checkedOutBook.author = authorRef;
        checkedOutBook.book = authorRef.books.get(bookIndex);
        (authorRef.books.get(bookIndex)).patron = null;
        (people.get(patron.name.charAt(0)).get(patronIndex)).books.remove(checkedOutBook);
    }

    public void run() {
        while (true) {
            char option = getString("\nEnter one of the following options:\n" + "1. Include a book in the catalog\n"
                    + "2. Check out a book\n" + "3. Return a book\n4. Status\n5. Exit\n" + "Your option:").charAt(0);
            switch (option) {
            case '1':
                includeBook();
                break;
            case '2':
                checkOutBook();
                break;
            case '3':
                returnBook();
                break;
            case '4':
                status();
                break;
            case '5':
                return;
            default:
                System.out.println("Wrong option, try again.");
            }
        }
    }

    public static void main(String args[]) {
        (new Library()).run();
    }
}