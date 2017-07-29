package com.uh;

public class ReverseLinkedList {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
        LNode head = new LNode();
        head.add(1);
        head.add(2);
        head.add(3);
        head.add(4);
        head.add(5);
        
        reverse(head);
        
        reverseIt(head);

    }

    private static LNode reverseIt(LNode head) {

        LNode cur = head;
        LNode prev = null;
        LNode next = null;
        while(cur!=null){
            next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }

    private static LNode reverse(LNode head) {


        if(head == null) return null;
        
        if(head.next == null) return head;
        
        LNode second = head.next;
        
        head.next = null;
        
        LNode rev = reverse(second);
        
        second.next = head;
        
        return rev;
    }

}

class LNode{
    int data;
    LNode next;
    public void add(int i) {
        data = i;
    }
}
