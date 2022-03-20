package com.nju.edu.citibackend.util;

import com.nju.edu.citibackend.po.QuestionPO;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Zyi
 */
public class ImageHelper {

	private static final Map<QuestionPO, String> map = new HashMap<>();

	public static String getImage(QuestionPO questionPO) {
		return map.get(questionPO);
	}

	public static String getImage(Integer answerID) {
		for (QuestionPO questionPO : map.keySet()) {
			if (questionPO.getId().equals(answerID)) {
				return map.get(questionPO);
			}
		}

		return null;
	}

	/**
	 * 插入问题对应的图片
	 *
	 * @param questionPO 问题
	 * @param url        图片对应的url
	 */
	public static void insertImage(QuestionPO questionPO, String url) {
		map.put(questionPO, url);
	}
}
