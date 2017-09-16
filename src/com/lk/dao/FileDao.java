package com.lk.dao;

public interface FileDao {
	public boolean fileUpload(String user, String tempPath, String diskPath, String filename);
	public String fileDownload(String user, String diskPath);
	public boolean fileDelete(String user, String diskPath);
	public boolean fileRename(String user, String diskPath, String newName);
	public String[] listPath(String user, String diskPath);
	public boolean makeDir(String user, String diskPath);
}
