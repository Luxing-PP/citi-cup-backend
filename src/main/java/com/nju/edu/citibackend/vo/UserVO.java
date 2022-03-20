package com.nju.edu.citibackend.vo;

import com.nju.edu.citibackend.po.UserPO;
import com.nju.edu.citibackend.po.verifyAuthorization.Permission;
import com.nju.edu.citibackend.po.verifyAuthorization.Role;
import lombok.*;
import org.springframework.beans.BeanUtils;

import java.util.Set;

/**
 * User VO
 *
 * @author Zyi
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class UserVO {

	private Integer id;
	private String phone;
	private String password;
	private String userName;
	private String email;

	public UserVO(UserPO po) {
		BeanUtils.copyProperties(po, this);
	}
}
