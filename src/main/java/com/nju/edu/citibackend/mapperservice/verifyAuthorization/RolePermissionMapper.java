package com.nju.edu.citibackend.mapperservice.verifyAuthorization;

import com.nju.edu.citibackend.po.verifyAuthorization.RolePermission;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface RolePermissionMapper {
	int deleteByPrimaryKey(@Param("roleId") Integer roleId, @Param("permissionId") Integer permissionId);

	int insert(RolePermission record);


	/**
	 * 根据RoleIds来获得权限ID
	 *
	 * @param roleIds
	 * @return
	 */
	List<Integer> getPermissionIdsByRoleIds(List<Integer> roleIds);

	List<RolePermission> selectAll();
}
