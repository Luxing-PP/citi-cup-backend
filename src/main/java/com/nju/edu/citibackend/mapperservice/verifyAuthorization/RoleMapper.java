package com.nju.edu.citibackend.mapperservice.verifyAuthorization;

import com.nju.edu.citibackend.po.verifyAuthorization.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(Role record);

	Role selectByPrimaryKey(Integer id);

//    List<Role> selectByIds(List<Integer> id);

	List<Role> selectAll();

	int updateByPrimaryKey(Role record);
}
