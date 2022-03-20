package com.nju.edu.citibackend.serviceimpl;

import com.nju.edu.citibackend.enums.StatusCode;
import com.nju.edu.citibackend.mapperservice.UserMapper;
import com.nju.edu.citibackend.po.UserPO;
import com.nju.edu.citibackend.service.UserService;
import com.nju.edu.citibackend.util.Format.EmailFormatUtil;
import com.nju.edu.citibackend.util.EncryptionUtil;
import com.nju.edu.citibackend.util.Format.PasswordFormatUtil;
import com.nju.edu.citibackend.util.Format.PhoneFormatUtil;
import com.nju.edu.citibackend.util.Format.UserNameFormatUtil;
import com.nju.edu.citibackend.vo.ResultVO;
import com.nju.edu.citibackend.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Zyi
 */
@Service
public class UserServiceImpl implements UserService {

	private final UserMapper userMapper;

	@Autowired
	public UserServiceImpl(UserMapper userMapper) {
		this.userMapper = userMapper;
	}

	@Override
	public UserVO selectUserById(int id) {
		return new UserVO(userMapper.selectUserById(id));
	}

	@Override
	public ResultVO<UserVO> login(UserVO userVO) {
		if (userVO == null) {
			return new ResultVO<>(StatusCode.BAD_REQUEST, "请求用户为空");
		}
		String phone = userVO.getPhone();
		String password = userVO.getPassword();

		UserPO userPOData = userMapper.selectUserByPhone(phone);
		if (userPOData == null) {
			return new ResultVO<>(StatusCode.BAD_REQUEST, "该用户未注册");
		} else {
			userVO.setId(userPOData.getId());
			if (EncryptionUtil.match(password, userPOData.getPassword())) {
				return new ResultVO<>(StatusCode.OK, "登录成功", new UserVO(userPOData));
			} else {
				return new ResultVO<>(StatusCode.BAD_REQUEST, "密码错误");
			}
		}
	}

	@Override
	public ResultVO<UserVO> register(UserVO userVO) {
		if (userVO == null || !PhoneFormatUtil.isCorrectPhoneFormat(userVO.getPhone())) {
			// userVO为空情况归结为手机号格式不正确的情况
			return new ResultVO<>(StatusCode.BAD_REQUEST, "手机号格式不正确！");
		} else if (!EmailFormatUtil.isCorrectEmailFormat(userVO.getEmail())) {
			return new ResultVO<>(StatusCode.BAD_REQUEST, "邮箱格式不正确");
		} else if (!PasswordFormatUtil.isCorrectPasswordFormat(userVO.getPassword())) {
			return new ResultVO<>(StatusCode.BAD_REQUEST, "密码格式不正确");
		} else if (!UserNameFormatUtil.isCorrectUserNameFormat(userVO.getUserName())) {
			return new ResultVO<>(StatusCode.BAD_REQUEST, "用户名格式不正确");
		}

		// vo到po的转换
		UserPO userPO = new UserPO(userVO);

		// 首先需要判断数据库中是否已经存在该账户
		if (userMapper.selectUserByPhone(userPO.getPhone()) != null) {
			return new ResultVO<>(StatusCode.BAD_REQUEST, "该手机号已经注册！");
		}

		String rawPassword = userPO.getPassword();
		// todo: 判断密码格式是否正确
		String encryptPassword = EncryptionUtil.encrypt(rawPassword);
		// 更新加密后的密码
		userPO.setPassword(encryptPassword);
		int i = userMapper.insertUser(userPO);

		if (i > 0) {
			// id代表的是insert操作影响的数据库行数，这里肯定为1
			userVO.setId(userPO.getId());
		}

		return i > 0 ? new ResultVO<>(StatusCode.OK, "注册成功！", new UserVO(userPO)) : new ResultVO<>(StatusCode.INTERNAL_SERVER_ERROR, "注册失败");
	}

	@Override
	public ResultVO<String> getUserName(Integer userID) {
		String userName = userMapper.selectUserName(userID);

		return userName != null ? new ResultVO<>(StatusCode.OK, "获取用户名成功", userName) : new ResultVO<>(StatusCode.BAD_REQUEST, "查无用户");
	}

	@Override
	public boolean delete(Integer id) {
		int i = userMapper.deleteUser(id);

		return i > 0;
	}
}
