package com.salesforce.library;

import java.util.LinkedList;

public class AuthorList extends LinkedList<Author> {
    static final long serialVersionUID = 123;

    public AuthorList() {
        super();
    }

    public void display() {
        Object[] authors = toArray();
        for (int i = 0; i < authors.length; i++)
            ((Author) authors[i]).display();
    }
}