package com.nju.edu.citibackend.util;


import java.io.*;

/**
 * @author Zyi
 */
public class SqlHelper {

	public static final String READ_PATH = "src/main/resources/question/question.txt";
	public static final String WRITE_PATH = "src/main/resources/question/ans.txt";

	/**
	 * 将txt文件转成sql
	 */
	public static void transfer() throws IOException {
		InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(READ_PATH));
		BufferedReader in = new BufferedReader(inputStreamReader);
		BufferedWriter out = new BufferedWriter(new FileWriter(WRITE_PATH, true));
		String line;
		int flag = 0;

		String content;
		String option;
		StringBuilder res = new StringBuilder();

		while ((line = in.readLine()) != null) {
			if (flag % 2 == 0) {
				res = new StringBuilder();
				// 题干信息
				res.append("(0, ").append("'").append(line.split("\\.")[1]).append("'").append(", ");
			} else {
				if ("".equals(line)) {
					// 说明是问答题
					res.replace(1, 2, "1");
					res.append("''), ");
				} else {
					// todo: line对选择题的处理
					line = getOption(line);
					res.append("'");
					res.append(line).append("'").append("),");
				}
				out.write(res.toString());
				out.write(System.getProperty("line.separator"));


			}
			out.flush();

			flag++;
		}

		out.close();
	}

	private static String getOption(String rawOption) {
		String[] options = rawOption.substring(1, rawOption.length() - 1).split("、");
		StringBuilder res = new StringBuilder();

		char beginChar = 'A';

		for (int i = 0; i < options.length; i++) {
			res.append((char) (beginChar + i)).append(".");
			res.append(options[i]);
			if (i != options.length - 1) {
				res.append(" ");
			}
		}

		return res.toString();
	}

	public static void main(String[] args) throws IOException {
		SqlHelper.transfer();
	}
}
