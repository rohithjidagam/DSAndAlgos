package com.salesforce.test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FileDriver {

    private static final String END = "END";
    private static final String LIST = "LIST";
    private static final String REMOVE = "REMOVE";
    private static final String INSTALL = "INSTALL";
    private static final String DEPEND = "DEPEND";
    private static final String INSTALLED = "INSTALLED";
    static Map<String, FileNode> map = new HashMap<>();

    @SuppressWarnings("resource")
    public static void main(String[] args) {

        BufferedReader br;
        String curLine = null;

        try {
            br = new BufferedReader(new FileReader("./src/main/java/com/salesforce/test/input2.txt"));
            while ((curLine = br.readLine()) != null) {
                String[] split = curLine.split(" ");

                if (split[0].equals(DEPEND)) {
                    System.out.println(curLine);
                    buildGraph(split);
                } else if (split[0].equals(INSTALL)) {
                    System.out.println(curLine);
                    install(split);
                } else if (split[0].equals(REMOVE)) {
                    System.out.println(curLine);
                    remove(split);
                } else if (split[0].equals(LIST)) {
                    System.out.println(curLine);
                    list();
                } else if (split[0].equals(END)) {
                    System.out.println(END);
                    break;
                } else {
                    System.out.println("INVALID COMMAND");
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

    }

    private static void remove(String[] split) {

        if (split.length > 2) {
            System.out.println("INVALID REMOVE COMMAND");
            return;
        }

        String s = split[1];
        if (!map.containsKey(s) || !INSTALLED.equals(map.get(s).getStatus())) {
            System.out.println(s + " Not Installed");
            return;
        }

        FileNode node = map.get(s);

        remove(node, true);

    }

    private static void remove(FileNode node, boolean flag) {

        boolean b = isRemovable(node);

        if (b) {
            node.setStatus(null);
            System.out.println("Removing " + node.getName());
            for (FileNode p : node.getChilds()) {
                if (!p.isExplicit() && INSTALLED.equals(p.getStatus()))
                    remove(p, false);
            }
        } else if (flag) {
            System.out.println(node.getName() + " is still Needed.");
        }
    }

    static boolean isRemovable(FileNode node) {

        List<FileNode> parents = node.getParents();
        for (FileNode p : parents) {

            if (INSTALLED.equals(p.getStatus()))
                return false;

            if (!isRemovable(p))
                return false;
        }

        return true;
    }

    private static void list() {

        if (map.size() == 0)
            System.out.println("No Commands");
        else {
            Set<String> keySet = map.keySet();
            for (String command : keySet) {
                if (map.get(command).getStatus() != null && map.get(command).getStatus().equals(INSTALLED))
                    System.out.println(command);
            }
        }
    }

    private static void install(String[] split) {

        if (split.length > 2) {
            System.out.println("INVALID INSTALL COMMAND");
            return;
        }

        String s = split[1];
        FileNode node = map.get(s);
        if (node == null) {
            node = new FileNode(s);
        }
        map.put(node.getName(), node);
        if (INSTALLED.equals(node.getStatus())) {
            System.out.println(node.getName() + " is already Installed");
            return;
        }
        install(node, true);
        node.setExplicit(true);

    }

    static void install(FileNode node, boolean b) {

        if (!INSTALLED.equals(node.getStatus())) {
            node.setStatus(INSTALLED);
            node.setExplicit(b);
            for (FileNode f : node.getChilds()) {
                install(f, false);
            }
            System.out.println("Installing " + node.getName());
        }
    }

    private static void buildGraph(String[] split) {

        String s = split[1];
        FileNode root = null;
        if (map.containsKey(s)) {
            root = map.get(s);
        } else {
            root = new FileNode(s);
        }

        FileNode child = null;
        for (int i = 2; i < split.length; i++) {
            String ch = split[i];
            if (map.containsKey(ch)) {
                child = map.get(ch);
            } else {
                child = new FileNode(ch);
            }
            child.getParents().add(root);
            root.getChilds().add(child);
            map.put(ch, child);
        }

        map.put(s, root);

    }

}
