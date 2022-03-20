package com.nju.edu.citibackend.controller;

import com.nju.edu.citibackend.enums.StatusCode;
import com.nju.edu.citibackend.service.AnswerService;
import com.nju.edu.citibackend.service.QuestionService;
import com.nju.edu.citibackend.util.Pair;
import com.nju.edu.citibackend.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

/**
 * @author Zyi
 */
@CrossOrigin("*")
@RestController
@RequestMapping("/answer")
public class AnswerController {

	private AnswerService answerService;
	private QuestionService questionService;

	@Autowired
	public AnswerController(AnswerService answerService, QuestionService questionService) {
		this.answerService = answerService;
		this.questionService = questionService;
	}

	@GetMapping("/query/answer-list")
	public ResultVO<List<AnswerVO>> getAnswerList() {
		return new ResultVO<>(StatusCode.OK, "获取答案列表成功", answerService.queryAnswerList());
	}

	@GetMapping("/query/{id}")
	public ResultVO<AnswerVO> getAnswerByID(@PathVariable Integer id) {
		return new ResultVO<>(StatusCode.OK, "获取答案成功", answerService.queryAnswerByID(id));
	}


	@GetMapping("/query/user/{userID}")
	public ResultVO<List<AnswerVO>> getAnswerByUserID(@PathVariable Integer userID) {
		return new ResultVO<>(StatusCode.OK, "获取某个用户的答案成功", answerService.queryAnswerByUserID(userID));
	}

	@PutMapping("/insert")
	public ResultVO<Boolean> insertAnswer(@RequestBody AnswerVO answerVO) {
		boolean isInserted = answerService.insertAnswer(answerVO);

		return isInserted ? new ResultVO<>(StatusCode.OK, "插入答案成功") : new ResultVO<>(StatusCode.INTERNAL_SERVER_ERROR, "插入答案信息失败");
	}

	@DeleteMapping("/delete/{id}")
	public ResultVO<Boolean> deleteAnswer(@PathVariable Integer id) {
		boolean isDeleted = answerService.deleteAnswer(id);

		return isDeleted ? new ResultVO<>(StatusCode.OK, "删除答案成功") : new ResultVO<>(StatusCode.INTERNAL_SERVER_ERROR, "删除答案失败");
	}

	@PostMapping("/update")
	public ResultVO<Boolean> updateAnswer(@RequestBody AnswerVO answerVO) {
		boolean isUpdated = answerService.updateAnswer(answerVO);

		return isUpdated ? new ResultVO<>(StatusCode.OK, "更新答案成功") : new ResultVO<>(StatusCode.INTERNAL_SERVER_ERROR, "更新答案失败");
	}

	/**
	 * 获取下一道题目的内容
	 *
	 * @param answerVO 答案信息
	 * @param postfix  文件后缀
	 */
	@PostMapping("/query/next/{postfix}")
	public ResultVO<Pair<QuestionVO, String>> getNextQuestion(@RequestBody AnswerVO answerVO, @PathVariable String postfix) {
		boolean isSuccess = answerService.insertAnswerByFile(answerVO, postfix);
		Pair<QuestionVO, String> pair;

		if (isSuccess) {
			pair = questionService.queryQuestionByAnswer(answerVO);
		} else {
			return new ResultVO<>(StatusCode.INTERNAL_SERVER_ERROR, "插入答案失败");
		}

		if (pair == null) {
			return new ResultVO<>(StatusCode.INTERNAL_SERVER_ERROR, "Python解析异常");
		}

		return new ResultVO<>(StatusCode.OK, "文件传输成功", pair);
	}

	/**
	 * @param uploadFile 答案语音文件
	 * @return 答案对应的列表
	 */
	@PostMapping("/insert/file")
	public ResultVO<String> insertFile(@RequestBody MultipartFile uploadFile) throws IOException {
		if (uploadFile == null) {
			return new ResultVO<>(StatusCode.BAD_REQUEST, "上传文件失败");
		}

		String fileName = Objects.requireNonNull(uploadFile.getOriginalFilename()).toLowerCase(Locale.ROOT);
		if (!fileName.endsWith(".wav")) {
			return new ResultVO<>(StatusCode.BAD_REQUEST, "文件类型错误！请选择.wav文件");
		}

		String postfix = answerService.insertFile(uploadFile);

		return new ResultVO<>(StatusCode.OK, "获取语音文件成功", postfix);
	}

	/**
	 * 获取用户答题的总数目
	 *
	 * @param userID user id
	 * @return 用户回答的问题总数目
	 */
	@GetMapping("/query/count/{userID}")
	public ResultVO<List<Integer>> queryUserAllAnswerCount(@PathVariable Integer userID) {
		List<Integer> count = answerService.queryUserAllAnswerCount(userID);

		return new ResultVO<>(StatusCode.OK, "获取用户答题数量成功", count);
	}

	/**
	 * 获取用户某次答题的答题数目
	 *
	 * @param userID      user id
	 * @param answerTimes 第几次答题
	 * @return 获取用户某次答题的答题数目
	 */
	@GetMapping("/query/count/all")
	public ResultVO<Integer> queryUserAnswerCount(@RequestParam("userID") Integer userID, @RequestParam("answerTimes") Integer answerTimes) {
		int count = answerService.queryUserAnswerCount(userID, answerTimes);

		return new ResultVO<>(StatusCode.OK, "获取用户答题数量成功", count);
	}
}
