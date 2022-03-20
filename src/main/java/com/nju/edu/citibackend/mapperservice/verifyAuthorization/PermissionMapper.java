package com.nju.edu.citibackend.mapperservice.verifyAuthorization;

import com.nju.edu.citibackend.po.verifyAuthorization.Permission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PermissionMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(Permission record);

	Permission selectByPrimaryKey(Integer id);

	List<Permission> selectAll();

	int updateByPrimaryKey(Permission record);
}
