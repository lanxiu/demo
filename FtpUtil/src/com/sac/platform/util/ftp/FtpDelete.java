package com.sac.platform.util.ftp;

import com.sac.platform.util.FtpUtil;
/**
 * ftp�ļ�ɾ����
 * @author tangming
 */
public class FtpDelete implements IFtpOperator {

	/**
	 * ִ�в���
	 * @param ftpUtil ͨ����
	 * @throws Exception
	 */
	public void execute(FtpUtil ftpUtil) throws Exception {

		if (!ftpUtil.exists(ftpUtil.ftpparam.remotePath, ftpUtil.ftpparam.fileName)) {
			throw new Exception("ɾ��ʧ�� ԭ���ļ�������");
		}

		try {

			if (!ftpUtil.connectServer(ftpUtil.ftpparam)) {
				throw new Exception("ɾ��ʧ�� ԭ��FTP����������ʧ��");
			}

			if (ftpUtil.ftpparam.remotePath.length() != 0)
				ftpUtil.cd(ftpUtil.ftpparam.remotePath);
			ftpUtil.issueCommand("DELE " + ftpUtil.ftpparam.fileName + "\r\n");

		} finally {

			ftpUtil.closeServer();
		}

	}
}
