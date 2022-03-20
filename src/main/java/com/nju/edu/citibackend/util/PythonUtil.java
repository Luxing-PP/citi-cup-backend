package com.nju.edu.citibackend.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.util.Arrays;

/**
 * @author Zyi, cjz
 */
public class PythonUtil {
	private static final Logger logger = LoggerFactory.getLogger(PythonUtil.class);
	private static Process proc;
	private static final String FILE_SEPARATOR = File.separator;
	private static final String ROOT_PATH = FILE_SEPARATOR + "root" + FILE_SEPARATOR + "pylib" + FILE_SEPARATOR + "package" + FILE_SEPARATOR;
	private static final String STOCK_API_ROOT_PATH = FILE_SEPARATOR + "root" + FILE_SEPARATOR + "pylib" + FILE_SEPARATOR + "stockPackage" + FILE_SEPARATOR;
	private static final String ERR_PATH = FILE_SEPARATOR + "root" + FILE_SEPARATOR + "server-data" + FILE_SEPARATOR + "log" + FILE_SEPARATOR + "log.txt";
	private static final String OUTPUT_PATH = FILE_SEPARATOR + "root" + FILE_SEPARATOR + "server-data" + FILE_SEPARATOR + "log" + FILE_SEPARATOR + "output.txt";
	private static final String LINE_SEPARATOR = System.lineSeparator();
	private static final String PREDICT_PY_NAME = "predict_allocation.py";

	private static final String TEST_PATH = "D:\\MM";

	/**
	 * 执行Python程序, 参数为当前题目答案的语音文件路径
	 *
	 * @param filePath   答案语音文件路径
	 * @param questionID 当前题目序号
	 * @return 下一道题目序号
	 */
	public static String executeQuestion(String filePath, Integer questionID, Integer userID, Integer answerTimes) {
		// 该算法程序的格式: python speech_parser.py xxx.wav 12345(usrID) 3(题号)
//		return execute( "python3 " + ROOT_PATH + "speech_parser.py " + filePath + " " + userID + " " + questionID);
		String content = execute("python3 " + "speech_parser.py " + filePath + " " + userID + " " + questionID + " " + answerTimes);

		return content;
	}

	/**
	 * 执行python程序，参数为当前题目的内容
	 *
	 * @param para 参数
	 * @return 语音文件的路径
	 */
	public static String executeVoice(String para) {
		return execute("python3 " + ROOT_PATH + "xxx" + para);
	}


	public static String[] getAllUserAnswerRecord(Integer userId) {
		UserRecordFileFilter userRecordFileFilter = new UserRecordFileFilter(userId);
		String[] rootDir = new File(ROOT_PATH).list(userRecordFileFilter);
		Arrays.sort(rootDir);
		return rootDir;
	}

	public static String executePredictAndAllocate(String fileName) {
		String userInfo = ROOT_PATH + fileName.replace(".txt", "");
		// logger.info("Execute For:" + userInfo);
		String command = String.format("python3 %s predict %s", PREDICT_PY_NAME, userInfo);
		// System.out.println("Cmd:" + command);
		return execute(command, STOCK_API_ROOT_PATH);
	}

	public static String executePredictAndAllocate(Integer userId, Integer times) {
		String userInfo = ROOT_PATH + String.format("%d_info_%d", userId, times);
		// logger.info("Execute For:" + userInfo);
		String command = String.format("python3 %s predict %s", PREDICT_PY_NAME, userInfo);
		System.out.println("Cmd:" + command);
		return execute(command, STOCK_API_ROOT_PATH);
	}

	private static void errOutput(BufferedReader err) throws IOException {
		String line;
		BufferedWriter writer = new BufferedWriter(new FileWriter(ERR_PATH));

		try {
			while ((line = err.readLine()) != null) {
				writer.write(line);
				writer.write(LINE_SEPARATOR);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (writer != null) {
					writer.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 输出不一样，这个带换行
	 *
	 * @param command
	 * @param workDir
	 * @return
	 */
	private static String execute(String command, String workDir) {
		StringBuilder res = new StringBuilder();

		try {
			proc = Runtime.getRuntime().exec(command, null, new File(workDir));
			// 错误信息流
			BufferedReader err = new BufferedReader(new InputStreamReader(proc.getErrorStream()));
			BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
			String line;
			errOutput(err);

			while ((line = in.readLine()) != null) {
				res.append(line).append(System.lineSeparator());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return res.toString();
	}

	private static String execute(String command) {
		StringBuilder res = new StringBuilder();

		try {
			proc = Runtime.getRuntime().exec(command, null, new File(ROOT_PATH));
			// 错误信息流
			BufferedReader err = new BufferedReader(new InputStreamReader(proc.getErrorStream()));
			BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
			String line;
			errOutput(err);

			while ((line = in.readLine()) != null) {
				res.append(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return res.toString();
	}

	/**
	 * 解析 形如 [ xxx, xxx,xxx ]的python输出
	 * 只支持两层嵌套
	 *
	 * @param pythonList
	 * @return
	 */
	public static String[] resolvePythonList(String pythonList) {
		//1. remove [ ]
		pythonList = pythonList.substring(1, pythonList.length() - 1);
		pythonList = pythonList.replace(" ", "");
		if (pythonList.contains("],")) {
			pythonList = pythonList.replace("],", "] ");
			return pythonList.split(" ");
		}

//		logger.info("pythonList:" + pythonList);
		return pythonList.split(",");
	}
}
