package com.nju.edu.citibackend.util;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.util.regex.Pattern;

/**
 * 用于筛选正确的用户信息
 *
 * @author Garzhi
 */
public class UserRecordFileFilter implements FilenameFilter {
	private Integer userId;

	public UserRecordFileFilter(Integer userId) {
		this.userId = userId;
	}

	@Override
	public boolean accept(File file, String s) {
		String pattern = String.format(".*%d_info_[0-9]+\\.txt", userId);
//		Pattern p = Pattern.compile(pattern);
		return Pattern.matches(pattern, s);
	}
}
