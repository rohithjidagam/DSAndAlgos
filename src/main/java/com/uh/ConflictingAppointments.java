package com.uh;

public class ConflictingAppointments {

    static ITree root;

    public static void main(String[] args) {

        INode[] interval = { new INode(1, 5), new INode(3, 7), new INode(2, 6), new INode(10, 15), new INode(5, 6),
                new INode(4, 100) };
        int n = interval.length;
        printConflicts(interval, n);
    }

    private static void printConflicts(INode[] interval, int n) {

        root = null;
        insert(interval[0], root);

        for (int i = 1; i < n; i++) {

            INode res = overLap(root, interval[i]);
            if (res != null) {
                System.out.println(interval[i] + " conflicts with" + res);
            }
            
            root = insert(interval[i], root);
        }

    }

    static boolean doOVerlap(INode i1, INode i2) {
        if (i1.low < i2.high && i2.low < i1.high)
            return true;
        return false;
    }

    private static INode overLap(ITree root, INode iNode) {
        if (root == null)
            return null;

        if (doOVerlap((root.i), iNode))
            return root.i;

        if (root.left != null && root.left.max >= iNode.low)
            return overLap(root.left, iNode);

        return overLap(root.right, iNode);
    }

    private static ITree insert(INode iNode, ITree node) {
        if (node == null) {
            node = new ITree(iNode);
            return node;
        }

        int l = node.i.low;

        if (iNode.low < l) {
            node.left = insert(iNode, node.left);
        } else {
            node.right = insert(iNode, node.right);
        }

        if (node.max < iNode.high) {
            node.max = iNode.high;
        }

        return node;

    }

}

class ITree {

    INode i;
    int max;
    ITree left, right;

    public ITree(INode i) {
        this.i = i;
        this.max = i.high;
        left = right = null;
    }

}

class INode {
    int low, high;

    INode(int l, int h) {
        this.low = l;
        this.high = h;
    }

    @Override
    public String toString() {
        return "[" + low + "," + high + "]";
    }
}
