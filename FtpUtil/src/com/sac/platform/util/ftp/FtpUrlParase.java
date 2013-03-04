package com.sac.platform.util.ftp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.sac.platform.util.FtpUtil;

/**
 * ftp url解析类
 * @author tangming
 */
public class FtpUrlParase implements IFtpOperator {

	/**
	 * 执行操作
	 * @param ftpUtil 通用类
	 * @throws Exception
	 */
	public void execute(FtpUtil ftpUtil) throws Exception {
		
		FtpParamBean ftpParamBean = ftpUtil.ftpparam;
		// 分组ftp网址
		Pattern p = Pattern.compile("^(\\w+://)*(\\w+:\\w*@)*(\\d+.\\d+.\\d+.\\d+)(:\\d+)*((/\\w*)*)$");
		String ftpUrl = ftpParamBean.ftpUrl.replaceFirst("^\\w://", "");
		Matcher m = p.matcher(ftpUrl);

		if (m.find()) {

			String userPass = m.group(2);
			if (userPass != null) {
				userPass = userPass.replaceFirst("@$", "");
				ftpParamBean.setUser(userPass.split(":")[0]);
				ftpParamBean.setPwd(userPass.split(":")[1]);
			}
			ftpParamBean.setIp(m.group(3));
			if (m.group(4) != null) {
				ftpParamBean.setPort(Integer.parseInt(m.group(4).replaceFirst(":", "")));
			}
			if (m.group(5) != null) {
				ftpParamBean.setRemotePath(m.group(5));
			}
		}
		if (m.matches() == false) {
			throw new Exception("FTP地址格式不正确，应为ftp://user:password@10.11.12.132:21/folder");
		}}
}
