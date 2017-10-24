package com.salesforce.files;

import java.util.Scanner;

/**
 * File Node Driver class.
 * 
 * @author Rohith Jidagam
 * @version 23 Oct 2017
 *
 */
public class FilesDriver {

	static FileUtils fileUtils = null;

	public static void main(String[] args) {
		FileNode rootDir = new FileNode("/", true, false, FileUtils.defaultPermissions, null);
		FileNode curDir = rootDir;
		fileUtils = new FileUtils();
		fileUtils.setCurDir(curDir);
		fileUtils.setRootDir(rootDir);
		readInput();
	}

	/**
	 * method to read input from console.
	 * 
	 */
	private static void readInput() {

		System.out.println("Enter a command:");
		Scanner sc = new Scanner(System.in);
		String nextLine = null;
		while (!(nextLine = sc.nextLine()).equals("exit")) {
			parse(nextLine);
		}
		sc.close();
	}

	/**
	 * method to parse input read from console.
	 * 
	 * @param command
	 */
	private static void parse(String command) {

		String[] split = command.split(" ");
		switch (split[0]) {
		case "ls":
			fileUtils.lsCommand(split);
			break;
		case "vi":
			fileUtils.viCommand(split);
			break;
		case "mkdir":
			fileUtils.mkdirCommand(split);
			break;
		case "pwd":
			fileUtils.pwdCommand(split);
			break;
		case "cd":
			fileUtils.cdCommand(split);
			break;
		default:
			System.out.println("Invalid Entry.");
			break;
		}

	}

}
