package com.uh.streams;

class RecursionDemo {

    int val = 3;
    
    private RecursionDemo(){
    }
    
    private static class Sing{
        private static RecursionDemo rec = new RecursionDemo();
    }
    
    public static RecursionDemo getInstance(){
        return Sing.rec;
    }

    public void recur() {
        --val;
        if (val >= 0) {
            System.out.println("recurring..." + val);
            recur();
        }
        val = 3;
    }
    

}

public class Recursion{
    public static void main(String[] args) {
        RecursionDemo r = RecursionDemo.getInstance();
        System.out.println(r);
        r.recur();
        RecursionDemo r1 = RecursionDemo.getInstance();
        System.out.println(r1);
        r1.recur();
        RecursionDemo r2 = RecursionDemo.getInstance();
        System.out.println(r2);
        r2.recur();
    }
}
