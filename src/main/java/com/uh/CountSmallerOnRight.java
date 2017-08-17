package com.uh;

import java.util.Arrays;

public class CountSmallerOnRight {

    public static void main(String[] args) {

        Integer[] arr = { 3, 2, 2, 6, 1 };
        Integer[] res = new Integer[arr.length];

        CSNode root = null;
        for (int i = arr.length - 1; i >= 0; i--) {
            root = insert(arr[i], root, i, res, 0);
        }

        System.out.println(Arrays.deepToString(res));
    }

    private static CSNode insert(Integer num, CSNode node, int i, Integer[] res, int preSum) {

        if (node == null) {
            node = new CSNode(num, 0);
            res[i] = preSum;
        } else if (node.val == num) {
            node.dup++;
            res[i] = node.sum + preSum;
        } else if (node.val > num) {
            node.sum++;
            node.left = insert(num, node.left, i, res, preSum);
        } else {
            node.right = insert(num, node.right, i, res, preSum + node.sum + node.dup);
        }
        return node;

    }

}

/**
 * Every node will maintain a val sum recording the total of number on it's left
 * bottom side, dup counts the duplication
 *              
 *              1(0, 1)
                     \
                     6(3, 1)
                     /
                   2(0, 2)
                       \
                        3(0, 1)
 *
 *
 */
class CSNode {
    int val;
    int sum;
    int dup;
    CSNode left;
    CSNode right;

    public CSNode(int n, int s) {
        val = n;
        sum = s;
        dup = 1;
        left = right = null;
    }

}
