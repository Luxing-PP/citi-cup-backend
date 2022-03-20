package com.nju.edu.citibackend.mapperservice.verifyAuthorization;

import com.nju.edu.citibackend.po.verifyAuthorization.UserRole;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserRoleMapper {
	int deleteByPrimaryKey(@Param("userId") Integer userId, @Param("roleId") Integer roleId);

	int insert(UserRole record);

	List<UserRole> selectAll();

	/**
	 * 根据userId获得roleId
	 *
	 * @param userId
	 * @return
	 */
	List<Integer> selectRoleIdByUserId(Integer userId);
}
