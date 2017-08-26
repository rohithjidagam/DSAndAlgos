package com.uh;

public class Add2NumbersLinkedList {

    public static void main(String[] args) {

        // reverse order
        // 31365
        LinkedNode list1 = new LinkedNode(5);
        list1.next = new LinkedNode(6);
        list1.next.next = new LinkedNode(3);
        list1.next.next.next = new LinkedNode(1);
        list1.next.next.next.next = new LinkedNode(3);

        // 248
        LinkedNode list2 = new LinkedNode(8);
        list2.next = new LinkedNode(4);
        list2.next.next = new LinkedNode(2);

        add(list1, list2);

        System.out.println();

        // same order with reversing
        // 31365
        LinkedNode list3 = new LinkedNode(3);
        list3.next = new LinkedNode(1);
        list3.next.next = new LinkedNode(3);
        list3.next.next.next = new LinkedNode(6);
        list3.next.next.next.next = new LinkedNode(5);

        // 248
        LinkedNode list4 = new LinkedNode(2);
        list4.next = new LinkedNode(4);
        list4.next.next = new LinkedNode(8);

        list3 = reverse(list3);
        list4 = reverse(list4);
        add(list3, list4);

        System.out.println();

        // without reversing
        list3 = reverse(list3);
        list4 = reverse(list4);
        
        list4.next.next.next = new LinkedNode(1);
        list4.next.next.next.next = new LinkedNode(2);

        addRecursion(list3, list4);

    }

    private static void addRecursion(LinkedNode l1, LinkedNode l2) {

        int c1 = getCount(l1);
        int c2 = getCount(l2);

        Sum carry = new Sum();
        LinkedNode res = null;

        if (c1 == c2) {
            res = addEqual(l1, l2, carry);
        } else {
            if(c1 < c2){
                LinkedNode temp = l1;
                l1 = l2;
                l2 = temp;
            }
            
            int diff = Math.abs(c1-c2);
            
            LinkedNode temp = l1;
            LinkedNode cur = null;
            while(diff-- > 0){
                cur = temp;
                temp = temp.next;
            }
            
            addEqual(cur, l2, carry);
        }
        
        while (res != null) {
            System.out.print(res.data + " ");
            res = res.next;
        }
    }

    private static LinkedNode addEqual(LinkedNode l1, LinkedNode l2, Sum carry) {

        if (l1 == null)
            return null;

        int sum;
        LinkedNode res = new LinkedNode(0);
        res.next = addEqual(l1.next, l2.next, carry);

        sum = carry.sum + l1.data + l2.data;
        carry.sum = sum / 10;
        sum = sum % 10;

        res.data = sum;

        return res;
    }

    private static int getCount(LinkedNode l1) {
        int count = 0;
        while (l1 != null) {
            count++;
            l1 = l1.next;
        }
        return count;
    }

    private static LinkedNode reverse(LinkedNode list) {
        LinkedNode next = null;
        LinkedNode prev = null;
        LinkedNode cur = list;
        while (cur != null) {
            next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }

    private static void add(LinkedNode list1, LinkedNode list2) {

        LinkedNode res = null;
        int sum = 0;
        int carry = 0;
        LinkedNode temp = null;

        while (list1 != null || list2 != null) {
            sum = carry + (list1 != null ? list1.data : 0) + (list2 != null ? list2.data : 0);
            carry = sum >= 10 ? 1 : 0;
            sum = sum % 10;

            res = new LinkedNode(sum);

            res.next = temp;
            temp = res;

            if (list1 != null)
                list1 = list1.next;
            if (list2 != null)
                list2 = list2.next;
        }

        if (carry > 0) {
            temp = new LinkedNode(carry);
            if (res != null)
                res.next = temp;
            else
                res = temp;
        }

        while (res != null) {
            System.out.print(res.data + " ");
            res = res.next;
        }

    }

}

class LinkedNode {
    int data;
    LinkedNode next;

    public LinkedNode(int data) {
        this.data = data;
        next = null;
    }
}
