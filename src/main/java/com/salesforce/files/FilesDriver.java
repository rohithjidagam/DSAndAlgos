package com.salesforce.files;

import java.util.Scanner;

public class FilesDriver {

	static FileNode curDir;
	static FileNode rootDir;
	static final String defaultPermissions = "rwxrwxrwx";

	public static void main(String[] args) {

		rootDir = new FileNode("/", true, false, defaultPermissions, null);
		curDir = rootDir;

		readInput();
	}

	private static void readInput() {

		System.out.println("Enter a command:");
		Scanner sc = new Scanner(System.in);
		String nextLine = null;
		while (!(nextLine = sc.nextLine()).equals("exit")) {
			parse(nextLine);
		}
		sc.close();
	}

	private static void parse(String command) {

		String[] split = command.split(" ");
		switch (split[0]) {
		case "ls":
			lsCommand(split);
			break;
		case "vi":
			viCommand(split);
			break;
		case "mkdir":
			mkdirCommand(split);
			break;
		case "pwd":
			pwdCommand(split);
			break;
		case "cd":
			cdCommand(split);
			break;
		default:
			break;
		}

	}

	private static void pwdCommand(String[] split) {
		FileNode temp = curDir.getParent();
		String path = curDir.getName();
		while (temp != null) {
			path = temp.getName() + "/" + path;
			temp = temp.getParent();
		}
		System.out.println(path);
	}

	private static void lsCommand(String[] split) {
		if (split.length == 1) {
			curDir.printchildRecursively(curDir.getChild(), 0, 0, false);
		} else {
			curDir.printchildRecursively(curDir.getChild(), 0, 0, true);
		}
	}

	private static void viCommand(String[] command) {
		if (command.length == 1)
			System.out.println("Invalid vi command. Enter filename.");
		else
			try {
				String permission = defaultPermissions;
				if (command.length > 2)
					permission = command[2];
				curDir.addChild(new FileNode(command[1], false, true, permission, curDir));
			} catch (Exception e) {
				throw new FilesException("Error in vi command", e);
			}
	}

	private static void mkdirCommand(String[] command) {
		if (command.length == 1)
			System.out.println("Invalid mkdir command. Enter directory name.");
		else
			try {
				String permission = defaultPermissions;
				if (command.length > 2)
					permission = command[2];
				curDir.addChild(new FileNode(command[1], false, false, permission, curDir));
			} catch (Exception e) {
				throw new FilesException("Error in mkdir command", e);
			}
	}

	private static void cdCommand(String[] command) {
		if (command.length == 1)
			System.out.println("Invalid cd command.");
		else
			try {
				switch (command[1]) {
				case "/":
					curDir = rootDir;
					break;
				case "..":
					if (curDir.getParent() == null) {
						System.out.println("Reached root directory. Cannot run cd command.");
						return;
					}
					curDir = curDir.getParent();
					break;
				default:
					curDir = curDir.getChild(command[1]);
					break;
				}
			} catch (Exception e) {
				throw new FilesException("Error in cd command", e);
			}
	}

}
