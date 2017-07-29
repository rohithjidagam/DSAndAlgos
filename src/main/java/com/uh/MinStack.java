package com.uh;

import java.util.Stack;

public class MinStack {
    
    Stack<Integer> stack;
    static Integer min;
    
    public MinStack() {
        stack = new Stack<>();
    }

    public static void main(String[] args) {


        MinStack minstack = new MinStack();
        
        minstack.push(5);
        minstack.push(3);
        minstack.push(-1);
        minstack.push(-2);
        
       minstack.pop();
        minstack.pop();
        
        minstack.push(-9);
        
        System.out.println(min);
        System.out.println(minstack.peek());
    }

    private int peek() {

        if(stack.isEmpty()){
            System.out.println("Stack is empty");
            return -1;
        }
        
        int top =stack.peek();
        
        if(top < min){
            return min;
        }
        
        return top;
    }

    private void pop() {

        if(stack.isEmpty()){
            System.out.println("Stack is empty");
        }
        
        int top = stack.peek();
        stack.pop();
        
        if(top < min){
            min = 2*min - top;
        }
    }

    private void push(int i) {

        if(stack.isEmpty()){
            stack.push(i);
            min = i;
        } else{
            if(i > min)
                stack.push(i);
            else{
                stack.push(2*i - min);
                min = i;
            }
        }
    }

}
