package com.salesforce.library;

public class Patron {
    public String name;
    public BookList<CheckedOutBook> books = new BookList<CheckedOutBook>();

    public Patron() {
    }

    public boolean equals(Object node) {
        return name.equals(((Patron) node).name);
    }

    public void display() {
        if (!books.isEmpty()) {
            System.out.println(name + " has the following books:");
            books.display();
        } else
            System.out.print(name + " has no books");
    }
}
