package com.uh;

public class RobinKarpPatternSearch {

    public static void main(String[] args) {

        String txt = "GEEKS FOR GEEKS";
        String pat = "GEEK";
        robinKarp(txt, pat);
        
        zsalgorithms(txt, pat);
    }

    private static void zsalgorithms(String txt, String pat) {

        String s = pat + "$" + txt;
        int[] z = new int[s.length()];
        int n = z.length;
        
        int l = 0, r = 0;
        for (int i = 1; i < n; i++) {
            if(i > r){
                l = r = i;
                
                while(r < n && s.charAt(r-l) == s.charAt(r))
                    r++;
                z[i] = r-l;
                r--;
            } else{
                int k = i - l;
                
                if(z[k] < z[r-i+1])
                    z[i] = z[k];
                else{
                    l = i;
                    while(r < n && s.charAt(r-l) == s.charAt(r))
                        r++;
                    z[i] = r-l;
                    r--;
                }
            }
        }
        
        for (int i = 0; i < z.length; i++) {
            if(z[i] == pat.length())
                System.out.println("Pattern Found at:" + (i - pat.length() - 1));
        }
    }

    private static void robinKarp(String txt, String pat) {
        int q = 101;
        int d = 256;

        int n = txt.length();
        int m = pat.length();

        int h = (int) Math.pow(d, m - 1) % q;

        int hp = 0;
        int ht = 0;

        for (int i = 0; i < m; i++) {
            hp = (hp * d + pat.charAt(i)) % q;
            ht = (ht * d + txt.charAt(i)) % q;
        }

        for (int i = 0; i < n - m; i++) {
            if (hp == ht) {

                int c = 0;
                for (int j = 0; j < m; j++) {
                    if (pat.charAt(j) == txt.charAt(i + j))
                        c++;
                }

                if (c == m) {
                    System.out.println("Pattern Found at:" + i);
                }
            }

            if (i < n - m) {
                ht = (d * (ht - txt.charAt(i) * h) + txt.charAt(i+m))%q;
                
                if(ht < 0)
                    ht += q;
            }
        }
    }

}
