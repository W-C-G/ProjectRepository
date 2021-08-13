package com.example.demo.vo;

public class FileVO {
	private int fileNum;
	private String fileName;
	private long fileSize;
	private int uno;
	
	public FileVO() {}

	public FileVO(int fileNum, String fileName, long fileSize, int uno) {
		this.fileNum = fileNum;
		this.fileName = fileName;
		this.fileSize = fileSize;
		this.uno = uno;
	}

	public int getFileNum() {
		return fileNum;
	}

	public void setFileNum(int fileNum) {
		this.fileNum = fileNum;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public long getFileSize() {
		return fileSize;
	}

	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

	public int getUno() {
		return uno;
	}

	public void setUno(int uno) {
		this.uno = uno;
	}

	
}
