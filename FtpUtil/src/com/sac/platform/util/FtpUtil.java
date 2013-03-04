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
 * FTP�ļ��ϴ�����ɾ��������
 * 
 * @author tangming
 * 
 */
public class FtpUtil extends FtpClient {

	public FtpParamBean ftpparam = new FtpParamBean();

	/**
	 * Ĭ������
	 */
	public FtpUtil() {
		this.ftpparam.setPort(21);
		this.ftpparam.setRemotePath("/");
		this.ftpparam.setEncodingLocal("GBK");
	}

	/**
	 * ����URL��ַ������������Ϣ
	 * 
	 * @param ftpUrl
	 *            �����û��� ���� Ŀ��·����ftp��ַ
	 * @throws Exception
	 */
	public FtpUtil(String ftpUrl) throws Exception {
		this();
		this.ftpparam.setFtpUrl(ftpUrl);
		this.execute(new FtpUrlParase());
	}

	/**
	 * ����ftp������
	 * 
	 * @param ftpParamBean ftp����bean
	 * @return �Ƿ����ӳɹ�
	 * @throws Exception
	 */
	public boolean connectServer(FtpParamBean ftpParamBean) throws Exception {
		return this.connectServer(ftpParamBean.getIp(), ftpParamBean.getPort(), ftpParamBean.getUser(), ftpParamBean
				.getPwd());
	}

	/**
	 * ����ftp������
	 * 
	 * @param ip ftp��ַ
	 * @param port ftp�˿ں�
	 * @param user ftp�û�
	 * @param pwd ftp����
	 * @return �Ƿ����ӳɹ�
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
	 * �����ļ�
	 * 
	 * @param remotePath
	 *            ftp������·����ַ�������ļ���
	 * @param localPath
	 *            ����·����ַ
	 * @param fileName
	 *            �ļ���
	 * @throws Exception
	 */
	public void downloadFile(String remotePath, String localPath, String fileName) throws Exception {

		this.ftpparam.setRemotePath(remotePath);
		this.ftpparam.setLocalPath(localPath);
		this.ftpparam.setFileName(fileName);

		this.execute(new FtpDownLoad());
	}

	 

	/**
	 * �����ļ�
	 * 
	 * @param remotePath
	 *            ftp������·����ַ�����ļ���
	 * @param localPath
	 *            ����·����ַ
	 * @throws Exception
	 */
	public void downloadFile(String remotePath, String localPath) throws Exception {

		String fileName = remotePath.substring(remotePath.lastIndexOf('/'));
		String remotePathNoFile = remotePath.replaceFirst("/" + fileName, "");

		this.downloadFile(remotePathNoFile, localPath, fileName);
	}

	/**
	 * ����FTP����URL�еĵ�ַ�����ļ�
	 * 
	 * @param filePath
	 *            ����·����ַ
	 * @param fileName
	 *            �ļ���
	 * @throws Exception
	 */
	public void downLoadFileByUrl(String filePath, String fileName) throws Exception {

		filePath = filePath.replaceAll("\\\\|\\|//|/", "/");
		this.ftpparam.setLocalPath(filePath);
		this.ftpparam.setFileName(fileName);
		this.downloadFile(this.ftpparam.getRemotePath(), this.ftpparam.getLocalPath(), this.ftpparam.getFileName());
	}

	/**
	 * �ϴ��ļ����Զ���洢·��
	 * 
	 * @param remotePath
	 *            ftp������·����ַ
	 * @param localPath
	 *            ����·����ַ
	 * @param fileName
	 *            �ļ���
	 * @throws Exception
	 */
	public void uploadFile(String remotePath, String localPath, String fileName) throws Exception {
		this.ftpparam.setRemotePath(remotePath);
		this.ftpparam.setLocalPath(localPath);
		this.ftpparam.setFileName(fileName);
		this.execute(new FtpUpload());
	}

	/**
	 * �ϴ��ļ���ʹ�÷�����Ĭ��·��
	 * 
	 * @param localPath
	 *            ����·����ַ
	 * @param fileName
	 *            �ļ���
	 *         @return String �ļ����·��
	 * @throws Exception
	 */
	public String uploadFile(String localPath, String fileName) throws Exception {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		String remotePath = "/upload/" + sdf.format(new Date());
		this.uploadFile(remotePath, localPath, fileName);
		return remotePath + "/" + fileName;
	}

	/**
	 * �ϴ��ļ���ʹ�÷�����Ĭ��·��
	 * 
	 * @param localPath
	 *            ����·����ַ�����ļ���
	 *         @return String �ļ����·��
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
	 * ����FTP����URL�ĵ�ַ�ϴ��ļ�
	 * 
	 * @param filePath
	 *            ����·����ַ
	 * @param fileName
	 *            �ļ���
	 * @throws Exception
	 */
	public void uploadFileByUrl(String filePath, String fileName) throws Exception {

		filePath = filePath.replaceAll("\\\\|\\|//|/", "/");
		this.ftpparam.setLocalPath(filePath);
		this.ftpparam.setFileName(fileName);
		this.uploadFile(this.ftpparam.getRemotePath(), this.ftpparam.getLocalPath(), this.ftpparam.getFileName());
	}

	/**
	 * ɾ���ļ�
	 * 
	 * @param remotePath
	 *            ftp������·����ַ
	 * @param fileName
	 *            �ļ���
	 * @throws Exception
	 */
	public void deleteFile(String remotePath, String fileName) throws Exception {
		
		this.ftpparam.setRemotePath(remotePath);
		this.ftpparam.setFileName(fileName);
		this.execute(new FtpDelete());
	}

	/**
	 * ɾ���ļ�
	 * 
	 * @param remotePath
	 *            ftp������·����ַ�����ļ���
	 * @throws Exception
	 */
	public void deleteFile(String filePath) throws Exception {

		String fileName = filePath.substring(filePath.lastIndexOf('/'));
		String remotePath = filePath.replaceFirst("/" + fileName, "");
		this.deleteFile(remotePath, fileName);
	}

	/**
	 * ����ftpUrl��ַɾ���ļ�
	 * 
	 * @param fileName
	 *            �ļ���
	 * @throws Exception
	 */
	public void deleteFileByUrl(String fileName) throws Exception {

		this.deleteFile(this.ftpparam.getRemotePath(), fileName);

	}

	/**
	 * �ж��ļ�����
	 * 
	 * @param remotePath
	 *            ftp������·����ַ
	 * @param fileName
	 *            �ļ���
	 * @throws Exception
	 */
	public boolean exists(String remotePath, String fileName) throws Exception {

		this.ftpparam.setRemotePath(remotePath);
		this.ftpparam.setFileName(fileName);
		this.execute(new FtpExist());
		return ftpparam.isFlag();
	}

	/**
	 * �ж��ļ�����
	 * 
	 * @param remotePath
	 *            ftp������·����ַ�����ļ���
	 * @throws Exception
	 */
	public boolean exists(String remotePath) throws Exception {

		String fileName = remotePath.substring(remotePath.lastIndexOf('/'));
		String remotePathNoFile = remotePath.replaceFirst("/" + fileName, "");
		this.exists(remotePathNoFile, fileName);
		return ftpparam.isFlag();
	}

	/**
	 * ����ftp����url��ַ�ж��ļ�����
	 * 
	 * @param remotePath
	 *            �ļ���
	 * @throws Exception
	 */
	public boolean existsByUrl(String remotePath) throws Exception {
		return this.exists(this.ftpparam.getRemotePath(), remotePath);
	}

	/**
	 * ͬ������������֮����ļ�
	 * @param source Դ��ַ
	 * @param target Ŀ���ַ
	 * @param filePath �ļ��ڷ�������·��
	 * @param fileName �ļ���
	 * @return �Ƿ�ɹ�
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
	 * ͬ������������֮����ļ�
	 * @param source Դ��ַ
	 * @param target Ŀ���ַ
	 * @param filePath �ļ��ڷ�������·�������ļ���
	 * @return �Ƿ�ɹ�
	 * @throws Exception
	 */
	public boolean syncFile(String source, String target, String filePath) throws Exception {

		String fileName = filePath.substring(filePath.lastIndexOf('/'));
		String remotePath = filePath.replaceFirst("/" + fileName, "");
		this.syncFile(source, target, remotePath, filePath);

		return true;
	}

	/**
	 * ִ��ftp��dos����
	 * @param command �����ַ���
	 * @return int
	 */
	public int issueCommand(String command) throws IOException {
		return super.issueCommand(command);
	}

	/**
	 * ִ�и���������
	 * @param ftpOperator ������ӿ�
	 * @throws Exception
	 */
	public void execute(IFtpOperator ftpOperator) throws Exception {
		ftpOperator.execute(this);
	}
}