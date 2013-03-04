package com.sac.platform.util.ftp;

import java.io.File;
import java.io.FileInputStream;

import sun.net.TelnetOutputStream;

import com.sac.platform.util.FtpUtil;

/**
 * ftp文件上传类
 * @author tangming
 */
public class FtpUpload implements IFtpOperator {

	/**
	 * 执行操作
	 * @param ftpUtil 通用类
	 * @throws Exception
	 */
	public void execute(FtpUtil ftpUtil) throws Exception {

		FtpParamBean ftpParamBean = ftpUtil.ftpparam;

		FileInputStream is = null;
		TelnetOutputStream os = null;
		try {

			if (!ftpUtil.connectServer(ftpUtil.ftpparam)) {
				throw new Exception("下载失败 原因：FTP服务器连接失败");
			}

			if (ftpParamBean.remotePath.length() != 0) {
				String[] pathArray = ftpParamBean.remotePath.replaceFirst("/", "").split("/");
				int t;
				for (String tmp : pathArray) {
					ftpUtil.sendServer("CWD " + tmp + "\r\n");
					t = ftpUtil.readServerResponse();
					if (t == 250) {
						continue;
					} else {
						ftpUtil.issueCommand("MKD " + tmp + "\r\n");
						ftpUtil.sendServer("PWD " + "" + "\r\n");
						ftpUtil.readServerResponse();
						ftpUtil.issueCommand("CWD " + tmp + "\r\n");
					}
					ftpUtil.sendServer("PWD " + "" + "\r\n");
					ftpUtil.readServerResponse();
				}
				ftpUtil.closeServer();
				ftpUtil.connectServer(ftpParamBean);
				ftpUtil.cd(ftpParamBean.remotePath);
			}
			ftpUtil.binary();
			// filename = URLEncoder.encode(filename);
			// TelnetOutputStream os =
			// ftpClient.put(URLEncoder.encode(filename,"UTF-8"));
			os = ftpUtil.put(ftpParamBean.fileName);
			File file_in = new File(ftpParamBean.localPath + File.separator + ftpParamBean.fileName);
			is = new FileInputStream(file_in);
			byte[] bytes = new byte[1024];
			int c;
			while ((c = is.read(bytes)) != -1) {
				os.write(bytes, 0, c);
			}
		} finally {
			if (is != null)
				is.close();
			if (os != null)
				os.close();
			ftpUtil.closeServer();
		}

	}
}
