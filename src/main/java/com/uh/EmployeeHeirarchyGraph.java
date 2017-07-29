package com.uh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class EmployeeHeirarchyGraph {

    public static void main(String[] args) {

        Map<String, String> input = new HashMap<String, String>();
        input.put("A", "C");
        input.put("B", "C");
        input.put("C", "F");
        input.put("D", "E");
        input.put("E", "F");
        input.put("F", "F");

        GSNode[] graph = new GSNode[6];
        graph[0] = new GSNode("A", 0);
        graph[1] = new GSNode("B", 1);
        graph[2] = new GSNode("C", 2);
        graph[2].child.add(graph[0]);
        graph[2].child.add(graph[1]);
        graph[3] = new GSNode("D", 3);
        graph[4] = new GSNode("E", 4);
        graph[4].child.add(graph[3]);
        graph[5] = new GSNode("F", 5);
        //graph[6] = new GSNode("G", 6);
        graph[5].child.add(graph[2]);
        graph[5].child.add(graph[4]);
        graph[5].child.add(graph[5]);

        int parent = 0;
        Stack<String> stack = new Stack<>();

        for (int i = 0; i < graph.length; i++) {
            if (!graph[i].vis) {
                dfs(graph, i, stack);
                parent = i;
            }
        }
        
        for (int i = 0; i < graph.length; i++) {
            graph[i].vis = false;
        }
        
        while(!stack.isEmpty()){
            System.out.println(stack.pop());
        }
        
        dfs(graph, parent, stack);
        
        for (int i = 0; i < graph.length; i++) {
            System.out.println(graph[i].vis);
        }
        
        while(!stack.isEmpty()){
            System.out.println(stack.pop());
        }
        

    }

    private static void dfs(GSNode[] graph, int i, Stack<String> stack) {

        graph[i].vis = true;
        List<GSNode> child = graph[i].child;
        for (GSNode gsNode : child) {
            if (!gsNode.vis)
                dfs(graph, gsNode.i, stack);
        }
        stack.push(graph[i].s);
    }

}

class GSNode {
    int i;
    String s;
    boolean vis;
    List<GSNode> child;

    public GSNode(String s, int i) {
        this.i = i;
        this.s = s;
        child = new ArrayList<>();
        vis = false;
    }
}
