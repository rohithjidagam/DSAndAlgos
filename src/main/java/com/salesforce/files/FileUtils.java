package com.salesforce.files;

/**
 * File Node Utils class.
 * 
 * @author Rohith Jidagam
 * @version 23 Oct 2017
 *
 */
public class FileUtils {

	private FileNode curDir;
	private FileNode rootDir;
	public static final String defaultPermissions = "rwxrwxrwx";

	public FileNode getCurDir() {
		return curDir;
	}

	public void setCurDir(FileNode curDir) {
		this.curDir = curDir;
	}

	public FileNode getRootDir() {
		return rootDir;
	}

	public void setRootDir(FileNode rootDir) {
		this.rootDir = rootDir;
	}

	/**
	 * method to print current directory path from root.
	 * 
	 * @param split
	 */
	public void pwdCommand(String[] split) {
		if (curDir == null) {
			System.out.println("Reached Above Root. Invalid operation.");
		}
		FileNode temp = curDir.getParent();
		String path = curDir.getName();
		while (temp != null) {
			if (temp.getName() == "/") {
				path = "root" + "/" + path;
			} else {
				path = temp.getName() + "/" + path;
			}
			temp = temp.getParent();
		}
		System.out.println(path);
	}

	/**
	 * method to view contents of directory.
	 * 
	 * @param split
	 */
	public void lsCommand(String[] split) {
		if (split.length == 1) {
			curDir.printchildRecursively(curDir.getChild(), 0, 0, false);
		} else {
			curDir.printchildRecursively(curDir.getChild(), 0, 0, true);
		}
	}

	/**
	 * method to create a file.
	 * 
	 * @param command
	 */
	public void viCommand(String[] command) {
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

	/**
	 * method to create a directory.
	 * 
	 * @param command
	 */
	public void mkdirCommand(String[] command) {
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

	/**
	 * method to change directory.
	 * 
	 * @param command
	 */
	public void cdCommand(String[] command) {
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
					if(curDir.getChild(command[1]) == null){
						System.out.println("Directory does not Exist. Please Check.");
						return;
					}
					curDir = curDir.getChild(command[1]);
					break;
				}
			} catch (Exception e) {
				throw new FilesException("Error in cd command", e);
			}
	}
}
