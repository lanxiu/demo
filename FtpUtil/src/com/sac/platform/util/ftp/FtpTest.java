package com.sac.platform.util.ftp;

import static java.lang.System.out;

import com.sac.platform.util.FtpUtil;
public class FtpTest {

	/**
	 * @param args
	 */
	public static void main(String[] args)throws Exception {

		String remote = "/bb/dd";
		String local = "D:/";
		String fileName = "变电设备规范明细表.xls";
		FtpUtil ftpUtil = new FtpUtil("ftp://ftp:ftp@172.18.25.217/bb/dd");
//		ftpUtil.downloadFile(remote,local,fileName);
		
//		ftpUtil.downLoadFileByUrl ( local,fileName);
//		ftpUtil.deleteFile(fileName);
//		ftpUtil.deleteFile(remote,fileName);
//		ftpUtil.uploadFileByUrl(local, fileName);
//		ftpUtil.uploadFile (remote,local, fileName);
		
		ftpUtil.exists("aa");
//		ftpUtil.syncFile(  "ftp://ftp:ftp@172.18.25.217/bb" ,"ftp://ftpuser:ftpuser@172.18.25.94", remote, fileName);
		 out.print( ftpUtil.exists("aa"));
//		 out.print(System.getProperty("java.io.tmpdir"));
		
		
	}

}
