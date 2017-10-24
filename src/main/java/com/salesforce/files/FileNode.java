package com.salesforce.files;

import java.util.ArrayList;
import java.util.List;

public class FileNode {

	private FileNode parent;
	private boolean isRoot;
	private boolean isFile;
	private List<FileNode> child;
	private String name;
	private String permissions;

	public FileNode(String name, boolean isRoot, boolean isFile, String permissions, FileNode parent) {
		this.name = name;
		if (isRoot && isFile) {
			throw new FilesException("A Single file cannot be root directory.");
		}
		this.isFile = isFile;
		this.isRoot = isRoot;
		if (isRoot) {
			this.parent = null;
		} else {
			this.parent = parent;
		}
		this.permissions = permissions;
		this.child = new ArrayList<>();
	}

	public FileNode getParent() {
		return parent;
	}

	public void setParent(FileNode parent) {
		this.parent = parent;
	}

	public boolean isRoot() {
		return isRoot;
	}

	public void setRoot(boolean isRoot) {
		this.isRoot = isRoot;
	}

	public boolean isFile() {
		return isFile;
	}

	public void setFile(boolean isFile) {
		this.isFile = isFile;
	}

	public List<FileNode> getChild() {
		return child;
	}

	public void setChild(List<FileNode> child) {
		this.child = child;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPermissions() {
		return permissions;
	}

	public void setPermissions(String permissions) {
		this.permissions = permissions;
	}

	public void setRoot() {
		if (this.isFile) {
			throw new FilesException("A Single file cannot be root directory.");
		}
		this.isRoot = true;
		this.parent = null;
	}

	public void setFileType() {
		if (this.child != null) {
			throw new FilesException("A Directory cannot be a file.");
		}
		this.isFile = true;
	}

	public void addChild(FileNode child) {
		if (this.isFile) {
			throw new FilesException("A file cannot be added to a file.");
		}
		this.child.add(child);
	}

	public FileNode getChild(String name) {
		if (this.child.isEmpty()) {
			throw new FilesException("The current entity is empty directory or a file.");
		}

		for (FileNode fileNode : this.child) {
			if (name.equals(fileNode.getName())) {
				return fileNode;
			}
		}

		return null;
	}

	public void printchildRecursively(List<FileNode> files, int index, int level, boolean flag) {
		if (index == files.size())
			return;
		for (int i = 0; i < level; i++) {
			System.out.print("\t");
		}

		if (files.get(index).isFile()) {
			if (!flag) {
				System.out.println(files.get(index).getName());
			} else {
				System.out.println(" " + files.get(index).getPermissions() + " " + files.get(index).getName());
			}
		} else {
			System.out.println("[" + files.get(index).getName() + "]");
			printchildRecursively(files.get(index).child, 0, level + 1, flag);
		}

		printchildRecursively(files, ++index, level, flag);
	}

}
