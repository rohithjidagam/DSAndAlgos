package com.uh;

public class ArrayaRotation {

    public static void main(String[] args) {

        int arr[] = { 4, 5, 6, 7, 8, 9, 1, 2, 3 };
        int max = binSearchMax(arr, 0, arr.length - 1);
        System.out.println(max + " -->" + arr[max]);

        reverseLinkedListKNodes();
    }

    private static void reverseLinkedListKNodes() {

        LLNode node = new LLNode(1);
        node.next = new LLNode(2);
        node.next.next = new LLNode(3);
        node.next.next.next = new LLNode(4);
        node.next.next.next.next = new LLNode(5);
        node.next.next.next.next.next = new LLNode(6);

        int k = 4;

        LLNode cur = node;
        int i = 1;
        while (i < k && cur != null) {
            cur = cur.next;
            i++;
        }

        if (cur == null)
            return;

        LLNode kth = cur;

        while (cur.next != null) {
            cur = cur.next;
        }

        cur.next = node;
        node = kth.next;
        kth.next = null;

        while (node != null){
            System.out.print(node.data + "--");
            node = node.next;
        }

        System.out.println();
    }

    private static int binSearchMax(int[] arr, int i, int j) {

        if (i > j)
            return -1;

        int mid = (i + j) / 2;

        if (arr[mid] > arr[mid - 1] && arr[mid] > arr[mid + 1])
            return mid;

        if (arr[i] < arr[mid])
            return binSearchMax(arr, mid + 1, j);

        return binSearchMax(arr, i, mid - 1);
    }
}
