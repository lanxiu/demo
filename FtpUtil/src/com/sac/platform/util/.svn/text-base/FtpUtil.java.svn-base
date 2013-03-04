package com.sac.platform.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import sun.net.NetworkClient;
import sun.net.ftp.FtpClient;

import com.sac.platform.util.ftp.FtpDelete;
import com.sac.platform.util.ftp.FtpDownLoad;
import com.sac.platform.util.ftp.FtpExist;
import com.sac.platform.util.ftp.FtpParamBean;
import com.sac.platform.util.ftp.FtpUpload;
import com.sac.platform.util.ftp.FtpUrlParase;
import com.sac.platform.util.ftp.IFtpOperator;

/**
 * FTP文件上传下载删除工具类
 * 
 * @author tangming
 * 
 */
public class FtpUtil extends FtpClient {

	public FtpParamBean ftpparam = new FtpParamBean();

	/**
	 * 默认设置
	 */
	public FtpUtil() {
		this.ftpparam.setPort(21);
		this.ftpparam.setRemotePath("/");
		this.ftpparam.setEncodingLocal("GBK");
	}

	/**
	 * 根据URL地址解析服务器信息
	 * 
	 * @param ftpUrl
	 *            带有用户名 密码 目标路径的ftp地址
	 * @throws Exception
	 */
	public FtpUtil(String ftpUrl) throws Exception {
		this();
		this.ftpparam.setFtpUrl(ftpUrl);
		this.execute(new FtpUrlParase());
	}

	/**
	 * 连接ftp服务器
	 * 
	 * @param ftpParamBean ftp参数bean
	 * @return 是否连接成功
	 * @throws Exception
	 */
	public boolean connectServer(FtpParamBean ftpParamBean) throws Exception {
		return this.connectServer(ftpParamBean.getIp(), ftpParamBean.getPort(), ftpParamBean.getUser(), ftpParamBean
				.getPwd());
	}

	/**
	 * 连接ftp服务器
	 * 
	 * @param ip ftp地址
	 * @param port ftp端口号
	 * @param user ftp用户
	 * @param pwd ftp密码
	 * @return 是否连接成功
	 * @throws Exception
	 */
	public boolean connectServer(String ip, int port, String user, String pwd) throws Exception {
		boolean isSuccess = false;
		try {
			NetworkClient.encoding = this.ftpparam.getEncodingLocal();
			this.openServer(ip, port);
			this.login(user, pwd);
			isSuccess = true;
		} catch (Exception ex) {
			throw ex;
		}
		return isSuccess;
	}

	/**
	 * 下载文件
	 * 
	 * @param remotePath
	 *            ftp服务器路径地址，不含文件名
	 * @param localPath
	 *            本地路径地址
	 * @param fileName
	 *            文件名
	 * @throws Exception
	 */
	public void downloadFile(String remotePath, String localPath, String fileName) throws Exception {

		this.ftpparam.setRemotePath(remotePath);
		this.ftpparam.setLocalPath(localPath);
		this.ftpparam.setFileName(fileName);

		this.execute(new FtpDownLoad());
	}

	 

	/**
	 * 下载文件
	 * 
	 * @param remotePath
	 *            ftp服务器路径地址，含文件名
	 * @param localPath
	 *            本地路径地址
	 * @throws Exception
	 */
	public void downloadFile(String remotePath, String localPath) throws Exception {

		String fileName = remotePath.substring(remotePath.lastIndexOf('/'));
		String remotePathNoFile = remotePath.replaceFirst("/" + fileName, "");

		this.downloadFile(remotePathNoFile, localPath, fileName);
	}

	/**
	 * 根据FTP连接URL中的地址下载文件
	 * 
	 * @param filePath
	 *            本地路径地址
	 * @param fileName
	 *            文件名
	 * @throws Exception
	 */
	public void downLoadFileByUrl(String filePath, String fileName) throws Exception {

		filePath = filePath.replaceAll("\\\\|\\|//|/", "/");
		this.ftpparam.setLocalPath(filePath);
		this.ftpparam.setFileName(fileName);
		this.downloadFile(this.ftpparam.getRemotePath(), this.ftpparam.getLocalPath(), this.ftpparam.getFileName());
	}

	/**
	 * 上传文件，自定义存储路径
	 * 
	 * @param remotePath
	 *            ftp服务器路径地址
	 * @param localPath
	 *            本地路径地址
	 * @param fileName
	 *            文件名
	 * @throws Exception
	 */
	public void uploadFile(String remotePath, String localPath, String fileName) throws Exception {
		this.ftpparam.setRemotePath(remotePath);
		this.ftpparam.setLocalPath(localPath);
		this.ftpparam.setFileName(fileName);
		this.execute(new FtpUpload());
	}

	/**
	 * 上传文件，使用服务器默认路径
	 * 
	 * @param localPath
	 *            本地路径地址
	 * @param fileName
	 *            文件名
	 *         @return String 文件存放路径
	 * @throws Exception
	 */
	public String uploadFile(String localPath, String fileName) throws Exception {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		String remotePath = "/upload/" + sdf.format(new Date());
		this.uploadFile(remotePath, localPath, fileName);
		return remotePath + "/" + fileName;
	}

	/**
	 * 上传文件，使用服务器默认路径
	 * 
	 * @param localPath
	 *            本地路径地址，含文件名
	 *         @return String 文件存放路径
	 * @throws Exception
	 */
	public String uploadFile(String filePath) throws Exception {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		String remotePath = "/upload/" + sdf.format(new Date());

		String fileName = filePath.substring(filePath.lastIndexOf('/'));
		String localPath = filePath.replaceFirst("/" + fileName, "");

		this.uploadFile(remotePath, localPath, fileName);
		return remotePath + "/" + fileName;
	}

	/**
	 * 根据FTP连接URL的地址上传文件
	 * 
	 * @param filePath
	 *            本地路径地址
	 * @param fileName
	 *            文件名
	 * @throws Exception
	 */
	public void uploadFileByUrl(String filePath, String fileName) throws Exception {

		filePath = filePath.replaceAll("\\\\|\\|//|/", "/");
		this.ftpparam.setLocalPath(filePath);
		this.ftpparam.setFileName(fileName);
		this.uploadFile(this.ftpparam.getRemotePath(), this.ftpparam.getLocalPath(), this.ftpparam.getFileName());
	}

	/**
	 * 删除文件
	 * 
	 * @param remotePath
	 *            ftp服务器路径地址
	 * @param fileName
	 *            文件名
	 * @throws Exception
	 */
	public void deleteFile(String remotePath, String fileName) throws Exception {
		
		this.ftpparam.setRemotePath(remotePath);
		this.ftpparam.setFileName(fileName);
		this.execute(new FtpDelete());
	}

	/**
	 * 删除文件
	 * 
	 * @param remotePath
	 *            ftp服务器路径地址，含文件名
	 * @throws Exception
	 */
	public void deleteFile(String filePath) throws Exception {

		String fileName = filePath.substring(filePath.lastIndexOf('/'));
		String remotePath = filePath.replaceFirst("/" + fileName, "");
		this.deleteFile(remotePath, fileName);
	}

	/**
	 * 根据ftpUrl地址删除文件
	 * 
	 * @param fileName
	 *            文件名
	 * @throws Exception
	 */
	public void deleteFileByUrl(String fileName) throws Exception {

		this.deleteFile(this.ftpparam.getRemotePath(), fileName);

	}

	/**
	 * 判断文件存在
	 * 
	 * @param remotePath
	 *            ftp服务器路径地址
	 * @param fileName
	 *            文件名
	 * @throws Exception
	 */
	public boolean exists(String remotePath, String fileName) throws Exception {

		this.ftpparam.setRemotePath(remotePath);
		this.ftpparam.setFileName(fileName);
		this.execute(new FtpExist());
		return ftpparam.isFlag();
	}

	/**
	 * 判断文件存在
	 * 
	 * @param remotePath
	 *            ftp服务器路径地址，含文件名
	 * @throws Exception
	 */
	public boolean exists(String remotePath) throws Exception {

		String fileName = remotePath.substring(remotePath.lastIndexOf('/'));
		String remotePathNoFile = remotePath.replaceFirst("/" + fileName, "");
		this.exists(remotePathNoFile, fileName);
		return ftpparam.isFlag();
	}

	/**
	 * 根据ftp连接url地址判断文件存在
	 * 
	 * @param remotePath
	 *            文件名
	 * @throws Exception
	 */
	public boolean existsByUrl(String remotePath) throws Exception {
		return this.exists(this.ftpparam.getRemotePath(), remotePath);
	}

	/**
	 * 同步两个服务器之间的文件
	 * @param source 源地址
	 * @param target 目标地址
	 * @param filePath 文件在服务器的路径
	 * @param fileName 文件名
	 * @return 是否成功
	 * @throws Exception
	 */
	public boolean syncFile(String source,String target,String filePath,String fileName) throws Exception{
		
		this.ftpparam.setFtpUrl(source);
		this.execute(new FtpUrlParase());
		this.downloadFile(filePath, System.getProperty("java.io.tmpdir"), fileName);
		this.ftpparam.setFtpUrl(target);
		this.execute(new FtpUrlParase());
		this.uploadFile(filePath, System.getProperty("java.io.tmpdir"), fileName);
		
		return true;
	}

	/**
	 * 同步两个服务器之间的文件
	 * @param source 源地址
	 * @param target 目标地址
	 * @param filePath 文件在服务器的路径，含文件名
	 * @return 是否成功
	 * @throws Exception
	 */
	public boolean syncFile(String source, String target, String filePath) throws Exception {

		String fileName = filePath.substring(filePath.lastIndexOf('/'));
		String remotePath = filePath.replaceFirst("/" + fileName, "");
		this.syncFile(source, target, remotePath, filePath);

		return true;
	}

	/**
	 * 执行ftp的dos命令
	 * @param command 命令字符串
	 * @return int
	 */
	public int issueCommand(String command) throws IOException {
		return super.issueCommand(command);
	}

	/**
	 * 执行各个操作类
	 * @param ftpOperator 操作类接口
	 * @throws Exception
	 */
	public void execute(IFtpOperator ftpOperator) throws Exception {
		ftpOperator.execute(this);
	}
}