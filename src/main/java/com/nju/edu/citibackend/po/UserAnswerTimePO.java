package com.nju.edu.citibackend.po;

import com.nju.edu.citibackend.vo.UserAnswerTimeVO;
import lombok.*;
import org.springframework.beans.BeanUtils;

import java.sql.Date;

/**
 * 封装了用户的答题时间
 *
 * @author Zyi
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@ToString
public class UserAnswerTimePO {

	private Integer id;
	private Integer userID;
	private Integer answerTimes;
	private Date answerTime;

	public UserAnswerTimePO(UserAnswerTimeVO userAnswerTimeVO) {
		BeanUtils.copyProperties(userAnswerTimeVO, this);
	}
}
