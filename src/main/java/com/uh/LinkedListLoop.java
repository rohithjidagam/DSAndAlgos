package com.uh;

public class LinkedListLoop {

    public static void main(String[] args) {

        LLNode head = new LLNode(1);
        head.next = new LLNode(2);
        head.next.next = new LLNode(3);
        head.next.next.next = new LLNode(4);
        head.next.next.next.next = new LLNode(5);
        head.next.next.next.next.next = new LLNode(6);

        loop(head);

        head = pairWiseSwap(head);

        LLNode node = pairWiseSwapRecursive(head);

        printList(node);

        LLNode node2 = reverseKNodes(node, 3);
        printList(node2);
    }

    private static LLNode reverseKNodes(LLNode head, int k) {

        LLNode cur = head;
        LLNode prev = null;
        LLNode next = null;
        int i = 0;
        while (cur != null && i < k) {
            next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
            i++;
        }
        
        System.out.println(cur);
        System.out.println(prev);
        System.out.println(next);
        System.out.println(head);
        
        if (next != null)
            head.next = reverseKNodes(next, k);

        return prev;

    }

    private static LLNode pairWiseSwapRecursive(LLNode head) {

        if (head == null || head.next == null)
            return head;

        LLNode rem = head.next.next;

        LLNode newhead = head.next;

        head.next.next = head;

        head.next = pairWiseSwapRecursive(rem);

        return newhead;
    }

    private static LLNode pairWiseSwap(LLNode head) {

        if (head == null || head.next == null)
            return head;

        LLNode cur = head;
        LLNode next = head.next;

        head = next;

        while (true) {

            LLNode cuNext = next.next;

            next.next = cur;

            if (cuNext == null || cuNext.next == null) {
                cur.next = cuNext;
                break;
            }

            cur.next = cuNext.next;

            cur = cuNext;
            next = cur.next;
        }

        printList(head);

        return head;

    }

    private static void printList(LLNode head) {
        while (head != null) {
            System.out.print(head.data + " ");
            head = head.next;
        }
        System.out.println();
    }

    private static void loop(LLNode head) {
        LLNode slow = head;
        LLNode fast = head.next;

        while (slow != null && fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast)
                break;
        }

        System.out.println(slow.data);
        System.out.println(fast.data);
        if (slow == fast) {
            slow = head;
            while (slow != fast.next) {
                slow = slow.next;
                fast = fast.next;
            }
            System.out.println(slow.data);
            System.out.println(fast.data);
        }
    }

}

class LLNode {
    int data;
    LLNode next;

    public LLNode(int d) {
        data = d;
        next = null;
    }
    
    @Override
    public String toString() {
        return data + "";
    }
}
