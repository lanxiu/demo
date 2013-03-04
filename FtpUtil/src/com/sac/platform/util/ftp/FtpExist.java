package com.sac.platform.util.ftp;

import com.sac.platform.util.FtpUtil;

/**
 * ftp文件存在判断类
 * @author tangming
 */
public class FtpExist implements IFtpOperator {

	/**
	 * 执行操作
	 * @param ftpUtil 通用类
	 * @throws Exception
	 */
	public void execute(FtpUtil ftpUtil) throws Exception {
		ftpUtil.ftpparam.setFlag(true);
		try {

			if (!ftpUtil.connectServer(ftpUtil.ftpparam)) {
				throw new Exception("查询失败 原因：FTP服务器连接失败");
			}

			ftpUtil.sendServer("CWD " + ftpUtil.ftpparam.remotePath + "\r\n");
			if (ftpUtil.readServerResponse() != 250) {
				ftpUtil.ftpparam.setFlag(false);
			}
			ftpUtil.cd(ftpUtil.ftpparam.remotePath);
			ftpUtil.sendServer("SIZE " + ftpUtil.ftpparam.fileName + "\r\n");
			if (ftpUtil.readServerResponse() != 213) {
				ftpUtil.ftpparam.setFlag(false);
			}

		} finally {

			ftpUtil.closeServer();
		}

	}
}
