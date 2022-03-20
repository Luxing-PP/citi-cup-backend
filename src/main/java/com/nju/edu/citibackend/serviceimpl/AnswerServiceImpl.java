package com.nju.edu.citibackend.serviceimpl;

import cn.hutool.core.util.RandomUtil;
import com.nju.edu.citibackend.mapperservice.AnswerMapper;
import com.nju.edu.citibackend.po.AnswerPO;
import com.nju.edu.citibackend.service.AnswerService;
import com.nju.edu.citibackend.util.PythonUtil;
import com.nju.edu.citibackend.vo.AnswerVO;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Zyi
 */
@Service
public class AnswerServiceImpl implements AnswerService {

	private AnswerMapper answerMapper;
	// private static List<MultipartFile> fileList = new CopyOnWriteArrayList<>();

	private static final String FILE_SEPARATOR = File.separator;
	private static final String FILE_PATH = FILE_SEPARATOR + "root" + FILE_SEPARATOR + "server-data" + FILE_SEPARATOR + "voice" + FILE_SEPARATOR;

	@Autowired
	public AnswerServiceImpl(AnswerMapper answerMapper) {
		this.answerMapper = answerMapper;
	}

	@Override
	public List<AnswerVO> queryAnswerList() {
		return convert(answerMapper.queryAnswerList());
	}

	@Override
	public AnswerVO queryAnswerByID(Integer answerID) {
		return new AnswerVO(answerMapper.queryAnswerByID(answerID));
	}

	@Override
	public List<AnswerVO> queryAnswerByUserID(Integer userID) {
		return convert(answerMapper.queryAnswerByUserID(userID));
	}

	@Override
	public int queryUserAnswerCount(Integer userID, Integer answerTimes) {
		return answerMapper.queryUserAnswerCount(userID, answerTimes);
	}

	@Override
	public List<Integer> queryUserAllAnswerCount(Integer userID) {
		return answerMapper.queryUserAllAnswerCount(userID);
	}

	@Override
	public boolean insertAnswer(AnswerVO answerVO) {
		AnswerPO answerPO = new AnswerPO(answerVO);
		int i = answerMapper.insertAnswer(answerPO);
		answerVO.setId(answerPO.getId());

		return i > 0;
	}

	@Override
	public boolean insertAnswerByFile(AnswerVO answerVO, String postfix) {
		// 判断answerVO的answerTimes是不是null
		if (answerVO.getAnswerTimes() == null) {
			return false;
		}
		AnswerPO answerPO = new AnswerPO(answerVO);
		int i = answerMapper.insertAnswer(answerPO);
		answerVO.setId(answerPO.getId());

		String filePath = FILE_PATH + "record" + postfix + ".wav";

		answerPO.setAnswerPath(filePath);
		answerVO.setAnswerPath(filePath);
		// 更新数据库中的answer_path
		int j = answerMapper.updateAnswerPath(answerPO);

		return i > 0 && j > 0;
	}

	@Override
	public String insertFile(MultipartFile uploadFile) throws IOException {
		String randomPostfix = RandomUtil.randomString(20);
		String fileName = "record" + randomPostfix + ".wav";
		File file = new File(FILE_PATH);

		byte[] bs = new byte[1024];
		int len;

		if (!file.exists()) {
			file.mkdirs();
		}
		InputStream inputStream = uploadFile.getInputStream();

		OutputStream outputStream = new FileOutputStream(FILE_PATH + fileName);

		while ((len = inputStream.read(bs)) != -1) {
			outputStream.write(bs, 0, len);
		}

		outputStream.flush();
		inputStream.close();
		outputStream.close();

		return randomPostfix;
	}

	@Override
	public boolean deleteAnswer(Integer id) {
		int i = answerMapper.deleteAnswer(id);
		// 这里删除答案不能为空，i是删除操作影响的数据库条数
		return i > 0;
	}

	@Override
	public boolean updateAnswer(AnswerVO answerVO) {
		int i = answerMapper.updateAnswer(new AnswerPO(answerVO));

		return i > 0;
	}

	private List<AnswerVO> convert(List<AnswerPO> answerPOList) {
		List<AnswerVO> list = new ArrayList<>();

		for (AnswerPO answerPO : answerPOList) {
			list.add(new AnswerVO((answerPO)));
		}

		return list;
	}
}
