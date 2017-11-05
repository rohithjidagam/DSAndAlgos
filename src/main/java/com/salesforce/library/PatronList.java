package com.salesforce.library;

import java.util.Iterator;
import java.util.LinkedList;

public class PatronList extends LinkedList<Patron> {
    static final long serialVersionUID = 125;

    public PatronList() {
        super();
    }

    public void display() {
        for (Iterator it = iterator(); it.hasNext();)
            ((Patron) it.next()).display();
    }
}