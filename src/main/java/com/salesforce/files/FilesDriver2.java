package com.salesforce.files;

import java.util.Scanner;

public class FilesDriver2 {

    static FileUtils fileUtils = null;

    public static void main(String[] args) {
        FileNode rootDir = new FileNode("root", true, false, FileUtils.defaultPermissions, null);
        FileNode curDir = rootDir;
        fileUtils = new FileUtils();
        fileUtils.setCurDir(curDir);
        fileUtils.setRootDir(rootDir);
        readInput();
    }

    private static void readInput() {
        Scanner sc = new Scanner(System.in);
        String nextLine = null;
        System.out.println("Enter a Command with format (Command: xyz)");
        while (!(nextLine = sc.nextLine()).equals("exit")) {
            parse(nextLine);
        }
        sc.close();
    }

    /**
     * method to parse input read from console.
     * 
     * @param arguments
     */
    private static void parse(String arguments) {

        String[] split = arguments.split(":");
        if (split.length < 2) {
            System.out.println("Invalid Entry.");
            return;
        }
        String command = split[1].trim();
        String[] commands = command.split(" ");
        switch (commands[0]) {
        case "dir":
            fileUtils.pwdCommand(commands);
            fileUtils.dirCommand();
            break;
        case "mkdir":
            fileUtils.mkdirCommand(commands);
            break;
        case "cd":
            fileUtils.cdCommand(commands);
            break;
        case "up":
            fileUtils.upCommand();
            break;
        default:
            System.out.println("Invalid Entry.");
            break;
        }

    }

}
