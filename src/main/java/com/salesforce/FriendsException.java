package com.salesforce;

public class FriendsException extends Exception {

    private static final long serialVersionUID = 1L;

    public FriendsException(Throwable ex) {
        super(ex);
    }

    public FriendsException(String s) {
        super(s);
    }

    public FriendsException(String s, Throwable ex) {
        super(s, ex);
    }
}
