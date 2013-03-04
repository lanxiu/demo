package com.sac.platform.util.ftp;

/**
 * ftp连接参数
 * @author tangming
 */
public class FtpParamBean {
	//	 ftp url
	String ip;

	// ftp port
	int port;

	// ftp user
	String user;

	// ftp password
	String pwd;

	// ftp path
	String remotePath;

	// local path
	String localPath;

	// 编码方式
	String encodingLocal;

	// 文件名称
	String fileName;

	// ftp url
	String ftpUrl;

	// 标识
	boolean flag;

	public String getEncodingLocal() {
		return encodingLocal;
	}

	public void setEncodingLocal(String encodingLocal) {
		this.encodingLocal = encodingLocal;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getLocalPath() {
		return localPath;
	}

	public void setLocalPath(String localPath) {
		this.localPath = localPath;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getRemotePath() {
		return remotePath;
	}

	public void setRemotePath(String remotePath) {
		this.remotePath = remotePath;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getFtpUrl() {
		return ftpUrl;
	}

	public void setFtpUrl(String ftpUrl) {
		this.ftpUrl = ftpUrl;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

}
