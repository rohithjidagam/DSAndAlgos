package com.uh;

public class RemoveDupsRecur {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        System.out.println(removeDuplicates("mississipie"));

        removeDups("abbaccd");
        
        removeAdjacent("mississipie".toCharArray(),"mississipie".length());

    }

    private static void removeDups(String string) {

        int st = -1;
        int i = 0;
        char[] ch = string.toCharArray();
        int len = ch.length;

        while (i < len) {
            if (st == -1 || ch[st] != ch[i]) {
                ch[++st] = ch[i++];
            } else {
                while (i < len && ch[st] == ch[i])
                    i++;
                st--;
            }
        }
        System.out.println(new String(ch));
        System.out.println(st);
        System.out.println(new String(ch).substring(0, st + 1));
    }

    private static void removeAdjacent(char[] str, int len) {

        int j = 0;
        for (int i = 1; i < len; i++) {

            while ((j >= 0) && i < len && (str[i] == str[j])) {
                i++;
                j--;
            }

            if (i < len)
                str[++j] = str[i];

        }
        

        System.out.println(new String(str).substring(0,j+1));
    }

    public static String removeDuplicates(String s) {
        if (s.isEmpty()) {
            return s;
        }
        char[] buf = s.toCharArray();
        char lastchar = buf[0];

        // i: index of input char
        // o: index of output char
        int o = 1;
        for (int i = 1; i < buf.length; i++) {
            if (o > 0 && buf[i] == buf[o - 1]) {
                lastchar = buf[o - 1];
                while (o > 0 && buf[o - 1] == lastchar) {
                    o--;
                }
            } else if (buf[i] == lastchar) {
                // Don't copy to output
            } else {
                buf[o++] = buf[i];
            }
        }
        return new String(buf, 0, o);
    }
}
