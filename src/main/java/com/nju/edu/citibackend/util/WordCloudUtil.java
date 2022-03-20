package com.nju.edu.citibackend.util;

import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Zyi
 */
public class WordCloudUtil {

	private static List<Map<String, String>> cloudList = new ArrayList<>();

	static {
		// init the list
		Map<String, String> map;
		for (int i = 0; i < 45; i++) {
			map = new HashMap<>();
			switch (i) {
				case 0:
					map.put("0", "单身人士");
					map.put("1", null);
					map.put("2", null);
					break;
				case 1:
					map.put("0", "家长");
					map.put("1", null);
					break;
				case 2:
					map.put("0", null);
					map.put("1", "一定的养老负担");
					break;
				case 3:
					map.put("0", "独生子女");
					map.put("1", null);
					break;
				case 4:
					map.put("0", "两人承担养老负担");
					map.put("1", "三人承担养老负担");
					map.put("2", "养老负担压力分摊女");
					map.put("3", "养老负担压力分摊");
					break;
				case 8:
					map.put("0", "没有房贷压力");
					map.put("1", "背负房贷压力");
					break;
				case 12:
					map.put("0", "流动人口");
					map.put("1", "本地土著");
					break;
				case 14:
					map.put("0", "差旅之行频繁");
					map.put("1", "一地长时定居");
					break;
				case 16:
					map.put("0", "单胎家庭");
					map.put("1", "二胎家庭");
					map.put("2", "三胎家庭");
					map.put("3", "家庭兴旺");
					break;
				case 19:
					map.put("0", null);
					map.put("1", "直接承担养孩压力");
					break;
				case 21:
					map.put("0", "高生活成本");
					map.put("1", null);
					map.put("2", null);
					break;
				case 22:
					map.put("0", "负有短期债");
					map.put("1", null);
					break;
				case 24:
					map.put("0", "工薪族");
					map.put("1", "投资市场大牛");
					map.put("2", "生于业绩,死于业绩");
					map.put("3", "经济状况艰难");
					map.put("4", "退休人士");
					break;
				case 28:
					map.put("0", "极端风险意识");
					map.put("1", null);
					break;
				case 29:
					map.put("0", "Go Getter");
					map.put("1", "Easy & Relax");
					map.put("2", "Life & Work Balance");
					break;
				case 31:
					map.put("0", "剁手党");
					map.put("1", null);
					break;
				case 32:
					map.put("0", "持家好手");
					map.put("1", "收支平衡");
					map.put("2", "消费主义");
					break;
				case 35:
					map.put("0", "基本的投资信息意识");
					map.put("1", null);
					break;
				case 36:
					map.put("0", "实战经验");
					map.put("1", null);
					break;
				default:
					map = null;
					break;
			}
			cloudList.add(map);
		}
	}

	/**
	 * 根据map将value映射到某个词
	 *
	 * @param value      问题的值
	 * @param questionID question id
	 * @return 用户的特征词
	 */
	public static String translate(String value, int questionID) {
		// questionID is range from 1
		// list index is range from 0
		if (cloudList.get(questionID - 1) == null) {
			return null;
		}

		if ("100".equals(value)) {
			return null;
		}
		return cloudList.get(questionID - 1).get(value);
	}
}
