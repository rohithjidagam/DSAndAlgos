package com.uh;

public class ZeroCount {

    public static void main(String[] args) {

        int arr[] = { 1, 0, 0, 1, 1, 0, 1, 0, 1, 1 };
        int m = 2;

        int n = arr.length;
        int wl = 0;
        int wr = 0;
        int zc = 0, bw = 0, bl = 0;

        while (wr < n) {
            if (zc <= m) {
                if (arr[wr] == 0)
                    zc++;
                wr++;
            }
            if (zc > m) {
                if (arr[wl] == 0)
                    zc--;
                wl++;
            }
            if (wr - wl > bw) {
                bw = wr - wl;
                bl = wl;
            }
        }

        for (int i = 0; i < n - 1; i++) {
            if(bl+i<n && arr[bl+i] == 0)
            System.out.println(bl + i);
        }
    }

}
