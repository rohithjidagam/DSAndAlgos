package com.uh;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;


class MyStack{
    int ele;
    int min;
    
    public MyStack(int e, int m) {
        ele = e;
        min = m;
    }
}
public class Stacks {

    static int min;
    static int capacity;
    static int size;
    
    public Stacks() {
        min = -1;
        capacity = 999;
        size = 0;
    }
    
    public static void main(String[] args) {

        Stacks st = new Stacks();
        
        customStackWithO1operations();
        
        st.histogramArea();

        st.formMinNumber("DDIDDIID");

        st.decodeString("3[b2[ca]]");

        st.cpuEmulator();

        st.sortTmpstack();
        System.out.println();
        Stack<Integer> s1 = new Stack<>();
        s1.push(34);
        s1.push(3);
        s1.push(31);
        s1.push(98);
        s1.push(92);
        s1.push(23);

        st.sortRecur(s1);

        while (!s1.isEmpty()) {
            System.out.print(s1.pop() + " ");
        }
    }

    private static void customStackWithO1operations() {
        MyStack[] stacks = new MyStack[capacity];
        
        addElement(7, stacks);
        addElement(5, stacks);
        addElement(1, stacks);
        addElement(3, stacks);
        addElement(2, stacks);
        
        int min = getMin(stacks);
        System.out.println(min);
        
        removeElement(stacks);
        
        
        for (int i = 0; i < size; i++) {
            System.out.println(stacks[i].ele + "-->" + stacks[i].min);
        }
        
        
    }

    private static void removeElement(MyStack[] stacks) {

        if(size <= 0)
            System.out.println("Stack underflow..");
        
        if(size == 1){
            min = -1;
        } else {
            min = stacks[size-2].min;
        }
        stacks[size-1] = null;
        size--;
    }

    private static int getMin(MyStack[] stacks) {

        if(size == 0)
            return -1;
        
        return stacks[size-1].min;
    }

    private static void addElement(int i, MyStack[] stacks) {

        if(size > capacity)
            System.out.println("Stack Overflow..");
        
        
        if(size == 0 || i < min)
            min = i;
        
        stacks[size] = new MyStack(i, min);
        size++;
        
    }

    private void sortRecur(Stack<Integer> s1) {

        if (!s1.isEmpty()) {
            int tmp = s1.pop();
            sortRecur(s1);
            insertAtBottom(s1, tmp);
        }

    }

    private void insertAtBottom(Stack<Integer> s1, int tmp) {

        // remove > condition for reverse stack using recursion
        if (s1.isEmpty() || tmp > s1.peek()) {
            s1.push(tmp);
        } else {
            int x = s1.pop();
            insertAtBottom(s1, tmp);
            s1.push(x);
        }
    }

    private void sortTmpstack() {

        Stack<Integer> s1 = new Stack<>();
        s1.push(34);
        s1.push(3);
        s1.push(31);
        s1.push(98);
        s1.push(92);
        s1.push(23);

        Stack<Integer> s2 = new Stack<>();

        while (!s1.isEmpty()) {
            int tmp = s1.pop();

            while (!s2.isEmpty() && s2.peek() < tmp) {
                s1.push(s2.pop());
            }

            s2.push(tmp);
        }

        while (!s2.isEmpty()) {
            System.out.print(s2.pop() + " ");
        }
    }

    private void cpuEmulator() {

        // 50
        String[] subroutine = { "MOV 5,R00", "MOV 10,R01", "JZ 7", "ADD R02,R01", "DEC R00", "JMP 3", "MOV R02,R42" };

        Map<String, Integer> map = new HashMap<>();

    }

    private void decodeString(String s) {

        int n = s.length();

        Stack<Integer> s1 = new Stack<>();
        Stack<Character> s2 = new Stack<>();

        for (int i = 0; i < n; i++) {

            if (Character.isDigit(s.charAt(i))) {
                s1.push(s.charAt(i) - '0');
            } else if (s.charAt(i) == ']') {
                String temp = "";
                while (s2.peek() != '[') {
                    char c = s2.pop();
                    if (c != '[')
                        temp = c + temp;
                }
                System.out.println(temp);
                int k = s1.pop();
                for (int j = 0; j < k; j++) {
                    for (int j2 = 0; j2 < temp.length(); j2++) {
                        s2.push(temp.charAt(j2));
                    }
                }
            } else {
                s2.push(s.charAt(i));
            }
        }

        String res = "";
        while (!s2.isEmpty()) {
            res += s2.pop();
        }

        System.out.println(res);
    }

    private void formMinNumber(String s) {

        Stack<Integer> stack = new Stack<>();
        int n = s.length();
        String res = "";

        for (int i = 0; i <= n; i++) {

            stack.push(i + 1);

            if (i == n || s.charAt(i) == 'I') {

                while (!stack.isEmpty()) {
                    res += stack.pop();
                    res += " ";
                }
            }
        }

        System.out.println(res);

    }

    private void histogramArea() {

        Map<String, Integer> map = new HashMap<>();

        int hist[] = { 6, 2, 5, 4, 5, 1, 6, 6, 6 };
        int n = hist.length;

        Stack<Integer> stack = new Stack<>();

        int i = 0;
        int max_area = Integer.MIN_VALUE;
        int area;
        while (i < n) {

            if (stack.isEmpty() || hist[i] >= hist[stack.peek()])
                stack.push(i++);
            else {
                Integer pop = stack.pop();
                area = hist[pop] * (stack.isEmpty() ? i : i - stack.peek() - 1);
                if (area > max_area) {
                    max_area = area;
                }

            }

        }

        while (!stack.isEmpty()) {
            Integer pop = stack.pop();
            area = hist[pop] * (stack.isEmpty() ? i : i - stack.peek() - 1);
            if (area > max_area) {
                max_area = area;
            }
        }

        System.out.println(max_area);
    }

}
