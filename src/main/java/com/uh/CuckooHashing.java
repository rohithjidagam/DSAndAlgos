package com.uh;

public class CuckooHashing {

    private static final int MAX = 11;
    private static final int VER = 2;
    Integer[][] table = new Integer[VER][MAX];
    Integer[] pos = new Integer[VER];

    public static void main(String[] args) {

        CuckooHashing cuckoo = new CuckooHashing();
        int keys_1[] = { 20, 50, 53, 75, 100, 67, 105, 3, 36, 39 };
        cuckoo.cuckoo(keys_1, keys_1.length);

    }

    private void cuckoo(int[] keys, int n) {

        intiTable();

        // i = cnt
        for (int i = 0; i < n; i++) {
            placeKeys(keys[i], 0, i, n);
        }

        printTable();

    }

    private void printTable() {

        for (int i = 0; i < VER; i++) {
            for (int j = 0; j < MAX; j++) {
                if (table[i][j] == Integer.MIN_VALUE)
                    System.out.print("-");
                else
                    System.out.print(table[i][j]);
            }
            System.out.println();
        }
    }

    /*
     * function to place a key in one of its possible positions tableID: table
     * in which key has to be placed, also equal to function according to which
     * key must be hashed cnt: number of times function has already been called
     * in order to place the first input key n: maximum number of times function
     * can be recursively called before stopping and declaring presence of cycle
     */
    private void placeKeys(int key, int tableId, int cnt, int n) {

        if (cnt == n) {
            System.out.println("unpositioned Key:" + key);
            System.out.println("REHASH....");
            return;
        }

        for (int i = 0; i < VER; i++) {
            pos[i] = hash(i + 1, key);
            if (table[i][pos[i]] == key) {
                return;
            }
        }

        if (table[tableId][pos[tableId]] != Integer.MIN_VALUE) {
            int d = table[tableId][pos[tableId]];
            table[tableId][pos[tableId]] = key;
            placeKeys(d, (tableId + 1) % 2, cnt + 1, n);
        } else {
            table[tableId][pos[tableId]] = key;
        }

    }

    /*
     * return hashed value for a key function: ID of hash function according to
     * which key has to hashed key: item to be hashed
     */
    private Integer hash(int f, int key) {
        switch (f) {
        case 1:
            return key % MAX;
        case 2:
            return (key / MAX) % MAX;
        default:
            break;
        }
        return Integer.MAX_VALUE;
    }

    private void intiTable() {

        for (int i = 0; i < VER; i++) {
            for (int j = 0; j < MAX; j++) {
                table[i][j] = Integer.MIN_VALUE;
            }

        }
    }

}
