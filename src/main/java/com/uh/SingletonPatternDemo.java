package com.uh;

import java.io.Serializable;

class SingletonPattern implements Serializable {

    private static final long serialVersionUID = 1L;

    private SingletonPattern() {
    }

    private static class SingletonBuilder {
        private static SingletonPattern instance = new SingletonPattern();
    }

    public static SingletonPattern getInstance() {
        return SingletonBuilder.instance;
    }

    protected Object readResolve() {
        return getInstance();
    }

}

public class SingletonPatternDemo {
    public static void main(String[] args) {
        SingletonPattern sp1 = SingletonPattern.getInstance();
        SingletonPattern sp2 = SingletonPattern.getInstance();

        System.out.println(sp1);
        System.out.println(sp2);

        System.out.println(sp1.equals(sp2));
        
    }
}
