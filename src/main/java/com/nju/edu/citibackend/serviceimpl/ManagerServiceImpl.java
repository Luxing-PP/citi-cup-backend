package com.nju.edu.citibackend.serviceimpl;

import com.nju.edu.citibackend.mapperservice.ManagerMapper;
import com.nju.edu.citibackend.service.ManagerService;
import com.nju.edu.citibackend.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author kevin
 */
@Service
public class ManagerServiceImpl implements ManagerService {

	ManagerMapper mapper;

	@Autowired
	public ManagerServiceImpl(ManagerMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public List<UserVO> getAllUsers() {
		List<UserVO> ans = mapper.getAllUsers();
		return ans;
	}
}
