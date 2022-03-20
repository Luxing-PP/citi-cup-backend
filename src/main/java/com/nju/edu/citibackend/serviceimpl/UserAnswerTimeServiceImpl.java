package com.nju.edu.citibackend.serviceimpl;

import com.nju.edu.citibackend.mapperservice.UserAnswerTimeMapper;
import com.nju.edu.citibackend.po.AnswerPO;
import com.nju.edu.citibackend.po.UserAnswerTimePO;
import com.nju.edu.citibackend.service.UserAnswerTimeService;
import com.nju.edu.citibackend.vo.AnswerVO;
import com.nju.edu.citibackend.vo.UserAnswerTimeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Zyi
 */
@Service
public class UserAnswerTimeServiceImpl implements UserAnswerTimeService {

	private UserAnswerTimeMapper userAnswerTimeMapper;

	@Autowired
	public UserAnswerTimeServiceImpl(UserAnswerTimeMapper userAnswerTimeMapper) {
		this.userAnswerTimeMapper = userAnswerTimeMapper;
	}

	@Override
	public UserAnswerTimeVO startQuiz(int userID, Date answerTime) {
		// 获取当前用户的所有answerTimes
		// 找到其中最大的并加一，就是第几次回答
		List<UserAnswerTimePO> answerTimesList = userAnswerTimeMapper.queryAllUserAnswerTime(userID);
		int maxTimes = -1;

		for (UserAnswerTimePO userAnswerTimePO : answerTimesList) {
			if (userAnswerTimePO.getAnswerTimes() > maxTimes) {
				maxTimes = userAnswerTimePO.getAnswerTimes();
			}
		}

		UserAnswerTimePO userAnswerTimePO = UserAnswerTimePO.builder()
			.userID(userID)
			.answerTimes(maxTimes + 1)
			.answerTime(answerTime)
			.build();

		userAnswerTimeMapper.insertUserAnswerTime(userAnswerTimePO);
		return new UserAnswerTimeVO(userAnswerTimePO);
	}

	@Override
	public UserAnswerTimeVO queryUserAnswerTime(int userID, int answerTimes) {

		return new UserAnswerTimeVO(userAnswerTimeMapper.queryUserAnswerTime(userID, answerTimes));
	}

	@Override
	public List<Date> queryUserAllAnswerTime(int userID) {
		List<Date> res = new ArrayList<>();
		for (UserAnswerTimeVO userAnswerTimeVO : PO2VO(userAnswerTimeMapper.queryAllUserAnswerTime(userID))) {
			res.add(userAnswerTimeVO.getAnswerTime());
		}
		return res;
	}

	private List<UserAnswerTimeVO> PO2VO(List<UserAnswerTimePO> UserAnswerTimePOList) {
		List<UserAnswerTimeVO> list = new ArrayList<>();

		for (UserAnswerTimePO userAnswerTimePO : UserAnswerTimePOList) {
			list.add(new UserAnswerTimeVO((userAnswerTimePO)));
		}

		return list;
	}

}
