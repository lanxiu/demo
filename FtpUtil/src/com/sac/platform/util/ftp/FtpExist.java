package com.sac.platform.util.ftp;

import com.sac.platform.util.FtpUtil;

/**
 * ftp�ļ������ж���
 * @author tangming
 */
public class FtpExist implements IFtpOperator {

	/**
	 * ִ�в���
	 * @param ftpUtil ͨ����
	 * @throws Exception
	 */
	public void execute(FtpUtil ftpUtil) throws Exception {
		ftpUtil.ftpparam.setFlag(true);
		try {

			if (!ftpUtil.connectServer(ftpUtil.ftpparam)) {
				throw new Exception("��ѯʧ�� ԭ��FTP����������ʧ��");
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
