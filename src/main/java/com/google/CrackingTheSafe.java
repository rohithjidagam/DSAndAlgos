package com.google;

import java.util.HashSet;
import java.util.Set;

public class CrackingTheSafe {

    public static void main(String[] args) {

        int n = 2;
        int k = 2;
        
        CrackingTheSafe c = new CrackingTheSafe();
        System.out.println(c.crackSafe(n, k));
    }

    public String crackSafe(int n, int k) {

        StringBuilder sb = new StringBuilder();
        int total = (int)Math.pow(k, n);
        
        for(int i=0;i<n;i++)
            sb.append("0");
        
        Set<String> vis = new HashSet<>();
        vis.add(sb.toString());

        dfs(sb, total, vis, n, k);
        System.out.println(vis);
        return sb.toString();
    }

    private boolean dfs(StringBuilder sb, int total, Set<String> vis, int n, int k) {

        if(vis.size() == total)
            return true;
        String prev = sb.substring(sb.length() - n + 1);
        System.out.println(prev);
        for (int i = 0; i < k; i++) {
            String next = prev + i;
            if(!vis.contains(next)){
                vis.add(next);
                sb.append(i);
                if(dfs(sb, total, vis, n, k)) return true;
                else{
                    vis.remove(next);
                    sb.deleteCharAt(sb.length()-1);
                }
            }
        }
        return false;
        
    }

}
