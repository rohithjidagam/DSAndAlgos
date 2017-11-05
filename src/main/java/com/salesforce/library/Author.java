package com.salesforce.library;

public class Author {
    public String name;
    public BookList<Book> books = new BookList<Book>();

    public Author() {
    }

    public boolean equals(Object node) {
        return name.equals(((Author) node).name);
    }

    public void display() {
        System.out.println(name);
        books.display();
    }
}
