package com.lk.dao;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class DFSFileDao implements FileDao {
	private static final String CMD_HEAD = "hadoop jar /bin/dfsmanager.jar com.lk.core.FileOperatorHandler";

	@Override
	public boolean fileUpload(String user, String tempPath, String diskPath, String filename) {
		StringBuffer sb = new StringBuffer(DFSFileDao.CMD_HEAD);
		sb.append(" upload ").append(user).append(" ").append(tempPath).append(" ").append(diskPath).append(" ").append(filename);
		System.out.println(this.execCMD(sb.toString()));
		return true;
	}

	@Override
	public String fileDownload(String user, String diskPath) {
		StringBuffer sb = new StringBuffer(DFSFileDao.CMD_HEAD);
		sb.append(" download ").append(user).append(" ").append(diskPath);
		return this.execCMD(sb.toString());
	}

	@Override
	public boolean fileDelete(String user, String diskPath) {
		StringBuffer sb = new StringBuffer(DFSFileDao.CMD_HEAD);
		sb.append(" delete ").append(user).append(" ").append(diskPath);
		this.execCMD(sb.toString());
		return true;
	}

	@Override
	public boolean fileRename(String user, String diskPath, String newName) {
		StringBuffer sb = new StringBuffer(DFSFileDao.CMD_HEAD);
		sb.append(" rename ").append(user).append(" ").append(diskPath).append(" ").append(newName);
		this.execCMD(sb.toString());
		return true;
	}

	@Override
	public String[] listPath(String user, String diskPath) {
		StringBuffer sb = new StringBuffer(DFSFileDao.CMD_HEAD);
		sb.append(" list_dir ").append(user).append(" " + diskPath);
		String[] strs = execCMD(sb.toString()).split("\n");
		return strs;
	}

	@Override
	public boolean makeDir(String user, String diskPath) {
		StringBuffer sb = new StringBuffer(DFSFileDao.CMD_HEAD);
		sb.append(" make_dir ").append(user).append(" " + diskPath);
		this.execCMD(sb.toString());
		return true;
	}
	

	private String execCMD(String cmd) {
		String res = null;
		try {
			Runtime runtime = Runtime.getRuntime();
			Process process = runtime.exec(cmd);
			process.waitFor();// 执行命令并等待输出

			InputStream input = process.getInputStream();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int readSize = 0;
			while ((readSize = input.read(buffer)) > 0) {
				baos.write(buffer, 0, readSize);
			}
			res = baos.toString();
			baos.close();
			input.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return res;
	}
}
