package com.salesforce.library;

public class Book {
    public String title;
    public Patron patron = null;

    public Book() {
    }

    public boolean equals(Object node) {
        return title.equals(((Book) node).title);
    }

    public String toString() {
        return "    * " + title + (patron != null ? " - checked out to " + patron.name : "") + "\n";
    }
}
