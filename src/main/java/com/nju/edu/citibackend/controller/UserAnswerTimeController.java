package com.nju.edu.citibackend.controller;

import com.nju.edu.citibackend.enums.StatusCode;
import com.nju.edu.citibackend.service.UserAnswerTimeService;
import com.nju.edu.citibackend.vo.ResultVO;
import com.nju.edu.citibackend.vo.UserAnswerTimeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
//import java.sql.Date;
import java.util.Date;
import java.util.List;

/**
 * @author Zyi
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/answerTime")
public class UserAnswerTimeController {

	private UserAnswerTimeService userAnswerTimeService;

	@Autowired
	public UserAnswerTimeController(UserAnswerTimeService userAnswerTimeService) {
		this.userAnswerTimeService = userAnswerTimeService;
	}

	@PostMapping("/start/{userID}")
	public ResultVO<UserAnswerTimeVO> startQuiz(@PathVariable Integer userID) {
		// 使用Date创建日期对象
		Date date = new Date();
		java.sql.Date curDate = new java.sql.Date(date.getTime());

		UserAnswerTimeVO userAnswerTimeVO = userAnswerTimeService.startQuiz(userID, curDate);

		return new ResultVO<>(StatusCode.OK, "开始答题成功", userAnswerTimeVO);
	}

	@GetMapping("/query")
	public ResultVO<String> queryUserAnswerTime(@RequestParam("userID") Integer userID, @RequestParam("answerTimes") Integer answerTimes) {
		String date = userAnswerTimeService.queryUserAnswerTime(userID, answerTimes).getAnswerTime().toString();

		return new ResultVO<>(StatusCode.OK, "获取答题日期成功", date);
	}

	/**
	 * 获得用户所有答题的时间
	 *
	 * @param userID
	 * @return
	 */
	@GetMapping("/query/all/{userID}")
	public ResultVO<List<java.sql.Date>> queryUserAllAnswerTime(@PathVariable Integer userID) {
		List<java.sql.Date> dateList = userAnswerTimeService.queryUserAllAnswerTime(userID);

		return new ResultVO<>(StatusCode.OK, "获取用户答题时间成功", dateList);
	}
}
