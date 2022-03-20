package com.nju.edu.citibackend.controller;

import com.nju.edu.citibackend.enums.QuestionType;
import com.nju.edu.citibackend.enums.StatusCode;
import com.nju.edu.citibackend.service.QuestionService;
import com.nju.edu.citibackend.vo.QuestionVO;
import com.nju.edu.citibackend.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

/**
 * @author Zyi
 */
@CrossOrigin("*")
@RestController
@RequestMapping("/question")
public class QuestionController {

	private final QuestionService questionService;

	private static final String ROOT_PATH = "src/main/resources/images/";

	@Autowired
	public QuestionController(QuestionService questionService) {
		this.questionService = questionService;
	}

	@GetMapping("/query/question-list")
	public ResultVO<List<QuestionVO>> getQuestionList() {
		return new ResultVO<>(StatusCode.OK, "获取问题列表成功", questionService.queryQuestion());
	}

	@GetMapping("/query/{questionID}")
	public ResultVO<QuestionVO> getQuestionByID(@PathVariable Integer questionID) {
		return new ResultVO<>(StatusCode.OK, "获取问题成功", questionService.queryQuestionByID(questionID));
	}

	/**
	 * 传回题目的图片
	 *
	 * @param questionID 问题id
	 * @return 图片
	 * @throws IOException 找不到图片异常
	 */
	@PostMapping(value = "/query/image/{questionID}", produces = MediaType.IMAGE_PNG_VALUE)
	public ResultVO<BufferedImage> getQuestionImage(@PathVariable Integer questionID) throws IOException {
		String path = questionService.queryQuestionImage(questionID);

		if (path == null) {
			return new ResultVO<>(StatusCode.INTERNAL_SERVER_ERROR, "该问题没有图片", null);
		} else {
			BufferedImage image = ImageIO.read(new FileInputStream(ROOT_PATH + path));
			return new ResultVO<>(StatusCode.OK, "获取图片成功", image);
		}
	}

	/**
	 * 根据问题内容获得对应的语音文件
	 *
	 * @param question 问题
	 * @return 问题对应的语音文件url
	 */
	@PostMapping("/query/voice")
	public ResultVO<String> getVoiceQuestion(@RequestBody String question) {
		String url = questionService.queryVoice(question);

		if (url != null) {
			return new ResultVO<>(StatusCode.OK, "获取语音文件成功", url);
		} else {
			return new ResultVO<>(StatusCode.INTERNAL_SERVER_ERROR, "获取语音文件失败");
		}
	}

	@PutMapping("/insert/question")
	public ResultVO<Integer> insertQuestion(@RequestBody QuestionVO questionVO) {
		// 单个创建问卷
		boolean isInserted = questionService.insertQuestion(questionVO);

		return isInserted ? new ResultVO<>(StatusCode.OK, "插入问题信息成功", questionVO.getId()) : new ResultVO<>(StatusCode.INTERNAL_SERVER_ERROR, "插入问题信息失败");
	}

	@PutMapping("/insert/question-list")
	public ResultVO<Boolean> insertMultiQuestion(@RequestBody List<QuestionVO> questionVOList) {
		// 批量创建题目
		boolean isInserted = true;

		for (QuestionVO questionVO : questionVOList) {
			isInserted = questionService.insertQuestion(questionVO);
			if (!isInserted) {
				break;
			}
		}

		return isInserted ? new ResultVO<>(StatusCode.OK, "插入问题信息成功") : new ResultVO<>(StatusCode.INTERNAL_SERVER_ERROR, "插入问题信息失败");
	}

	@PostMapping("/update")
	public ResultVO<Boolean> updateQuestion(@RequestBody QuestionVO questionVO) {
		// 更新问题信息
		boolean isUpdated = questionService.updateQuestion(questionVO);

		return isUpdated ? new ResultVO<>(StatusCode.OK, "更新问题成功") : new ResultVO<>(StatusCode.INTERNAL_SERVER_ERROR, "更新问题失败");
	}

	@DeleteMapping("/delete/{questionID}")
	public ResultVO<Boolean> deleteQuestion(@PathVariable Integer questionID) {
		boolean isDeleted = questionService.deleteQuestionByID(questionID);

		return isDeleted ? new ResultVO<>(StatusCode.OK, "删除问题成功") : new ResultVO<>(StatusCode.INTERNAL_SERVER_ERROR, "删除问题失败");
	}

	@PostMapping("/question-type")
	public ResultVO<List<QuestionVO>> getQuestionByType(@RequestBody QuestionType questionType) {
		// fixme: 前端如何传一个枚举变量
		List<QuestionVO> list = questionService.queryQuestionByType(questionType);

		return new ResultVO<>(StatusCode.OK, "获取问题成功", list);
	}

}
