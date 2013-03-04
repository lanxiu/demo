/**
 * 
 */
package com.sac.platform.util.ftp;

import com.sac.platform.util.FtpUtil;


/**
 * ftp 操作接口类
 * @author tangming
 */
public interface IFtpOperator {
	/**
	 * 执行操作
	 * @param ftpUtil 通用类
	 * @throws Exception
	 */
	public void execute(FtpUtil ftpUtil)throws Exception;
}
