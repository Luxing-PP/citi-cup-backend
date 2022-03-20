package com.nju.edu.citibackend.mapperservice;

import com.nju.edu.citibackend.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ManagerMapper {

	List<UserVO> getAllUsers();
}
