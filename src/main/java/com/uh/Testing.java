package com.uh;

public class Testing {

    public static void main(String[] args) {

        C a = new C();
        a.draw();
        //System.out.println(a.a);
        
        System.out.println( (char) ( 1 + 48));
    }

}

interface A {
    int a = 3;
    
    void draw();
}

interface B {
    int b = 4;
    void draw();
}

class C implements A, B {
    
    public C(){
        super();
    }

    @Override
    public void draw() {
        System.out.println("draw");
    }

}
