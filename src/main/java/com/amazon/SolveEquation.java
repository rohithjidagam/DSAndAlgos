package com.amazon;

public class SolveEquation {

    public static void main(String[] args) {

        String eq = "x+5-3+x=6+x-2";

        String solve = solve(eq);
        System.out.println(solve);
        
        System.out.println(solve("x=x"));
        System.out.println(solve("x=x+1"));
    }

    private static String solve(String equation) {
        int[] res = evaluateExpression(equation.split("=")[0]);
        int[] res2 = evaluateExpression(equation.split("=")[1]);
        res[0] -= res2[0];
        res[1] = res2[1] - res[1];
        if (res[0] == 0 && res[1] == 0)
            return "Infinite solutions";
        if (res[0] == 0)
            return "No solution";
        return "x=" + res[1] / res[0];
    }

    public static int[] evaluateExpression(String exp) {
        String[] tokens = exp.split("(?=[-+])");
        int[] res = new int[2];
        for (String token : tokens) {
            if (token.equals("+x") || token.equals("x"))
                res[0] += 1;
            else if (token.equals("-x"))
                res[0] -= 1;
            else if (token.contains("x"))
                res[0] += Integer.parseInt(token.substring(0, token.indexOf("x")));
            else
                res[1] += Integer.parseInt(token);
        }
        return res;
    }

}
