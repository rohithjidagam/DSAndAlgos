package com.google;

public class ArrayMoreThanNByK {

    public static void main(String[] args) {

        int[] arr = { 3, 1, 2, 2, 2, 5, 5, 5, 5, 1, 4, 3, 3, 5, 3 };
        int k = 4;

        Record[] temp = new Record[k - 1];
        for (int i = 0; i < temp.length; i++) {
            temp[i] = new Record(0, 0);
        }

        for (int i = 0; i < arr.length; i++) {

            int j;
            for (j = 0; j < temp.length; j++) {
                if (temp[j].val == arr[i]) {
                    temp[j].count++;
                    break;
                }
            }

            if (j == k - 1) {
                int l;
                for (l = 0; l < temp.length; l++) {
                    if (temp[l].count == 0) {
                        temp[l] = new Record(arr[i], 1);
                        break;
                    }
                }

                if (l == k - 1)
                    for (l = 0; l < k; l++)
                        temp[l].count -= 1;
            }

        }

        for (int i = 0; i < k - 1; i++) {
            int ac = 0;
            for (int j = 0; j < arr.length; j++)
                if (arr[j] == temp[i].val)
                    ac++;

            // If actual count is more than n/k, then print it
            if (ac > arr.length / k)
                System.out.println(temp[i].val + "--" + temp[i].count);
        }

    }

}

class Record {
    int val;
    int count;

    public Record(int v, int c) {
        val = v;
        count = c;
    }
}
