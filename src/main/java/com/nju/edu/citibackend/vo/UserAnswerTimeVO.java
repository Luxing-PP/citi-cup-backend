package com.nju.edu.citibackend.vo;

import com.nju.edu.citibackend.po.UserAnswerTimePO;
import lombok.*;
import org.springframework.beans.BeanUtils;

import java.sql.Date;

/**
 * @author Zyi
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@ToString
public class UserAnswerTimeVO {

	private Integer id;
	private Integer userID;
	private Integer answerTimes;
	private Date answerTime;

	public UserAnswerTimeVO(UserAnswerTimePO userAnswerTimePO) {
		BeanUtils.copyProperties(userAnswerTimePO, this);
	}

}
