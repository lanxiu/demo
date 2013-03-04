package com.sac.platform.util.ftp;

import com.sac.platform.util.FtpUtil;
/**
 * ftp文件删除类
 * @author tangming
 */
public class FtpDelete implements IFtpOperator {

	/**
	 * 执行操作
	 * @param ftpUtil 通用类
	 * @throws Exception
	 */
	public void execute(FtpUtil ftpUtil) throws Exception {

		if (!ftpUtil.exists(ftpUtil.ftpparam.remotePath, ftpUtil.ftpparam.fileName)) {
			throw new Exception("删除失败 原因：文件不存在");
		}

		try {

			if (!ftpUtil.connectServer(ftpUtil.ftpparam)) {
				throw new Exception("删除失败 原因：FTP服务器连接失败");
			}

			if (ftpUtil.ftpparam.remotePath.length() != 0)
				ftpUtil.cd(ftpUtil.ftpparam.remotePath);
			ftpUtil.issueCommand("DELE " + ftpUtil.ftpparam.fileName + "\r\n");

		} finally {

			ftpUtil.closeServer();
		}

	}
}
