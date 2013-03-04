package com.sac.platform.util.ftp;

import java.io.File;
import java.io.FileOutputStream;

import sun.net.TelnetInputStream;

import com.sac.platform.util.FtpUtil;

/**
 * ftp文件下载类
 * @author tangming
 */
public class FtpDownLoad implements IFtpOperator {

	/**
	 * 执行操作
	 * @param ftpUtil 通用类
	 * @throws Exception
	 */
	public void execute(FtpUtil ftpUtil) throws Exception {

		TelnetInputStream is = null;
		FileOutputStream os = null;
		try {

			if (!ftpUtil.connectServer(ftpUtil.ftpparam)) {
				throw new Exception("下载失败 原因：FTP服务器连接失败");
			}

			if (ftpUtil.ftpparam.remotePath.length() != 0)
				ftpUtil.cd(ftpUtil.ftpparam.remotePath);
			ftpUtil.binary();
			is = ftpUtil.get(ftpUtil.ftpparam.fileName);
			File file_out = new File(ftpUtil.ftpparam.localPath + File.separator + ftpUtil.ftpparam.fileName);
			os = new FileOutputStream(file_out);
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
