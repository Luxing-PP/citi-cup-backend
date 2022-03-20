package com.nju.edu.citibackend.po;

import com.nju.edu.citibackend.po.verifyAuthorization.Permission;
import com.nju.edu.citibackend.po.verifyAuthorization.Role;
import com.nju.edu.citibackend.vo.UserVO;
import lombok.*;
import org.springframework.beans.BeanUtils;

import java.util.Set;

/**
 * User PO
 *
 * @author Zyi
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class UserPO {

	private Integer id;
	private String phone;
	private String userName;
	private String email;
	private String password;

	public UserPO(UserVO userVO) {
		BeanUtils.copyProperties(userVO, this);
	}
}
