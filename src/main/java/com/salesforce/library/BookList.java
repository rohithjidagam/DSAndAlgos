package com.salesforce.library;

import java.util.LinkedList;

public class BookList<T> extends LinkedList<T> {
    static final long serialVersionUID = 124;

    public BookList() {
        super();
    }

    public void display() {
        for (int i = 0; i < size(); i++)
            System.out.print(get(i));
    }
}