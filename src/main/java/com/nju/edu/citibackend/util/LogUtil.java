package com.nju.edu.citibackend.util;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class LogUtil {

	private static final String LOG_PATH = "/root/server-data/log/";
	private static final String FILE_NAME = "log.txt";

	private static OutputStream inputStream;

	static {
		try {
			inputStream = new FileOutputStream(LOG_PATH + FILE_NAME, true);
			inputStream.write("\r\n".getBytes(StandardCharsets.UTF_8));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void writeLog(String logMsg) {
		try {
			inputStream.write(logMsg.getBytes(StandardCharsets.UTF_8));
			inputStream.write("\r\n".getBytes(StandardCharsets.UTF_8));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
