package com.nju.edu.citibackend.controller.authorizationcontroller;

import com.nju.edu.citibackend.enums.StatusCode;
import com.nju.edu.citibackend.mapperservice.verifyAuthorization.PermissionMapper;
import com.nju.edu.citibackend.po.verifyAuthorization.Permission;
import com.nju.edu.citibackend.vo.ResultVO;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Kevin
 * @Description
 */
@RestController
@RequestMapping("/permission")
public class PermissionController {
	private final PermissionMapper permissionMapper;

	@Autowired
	PermissionController(PermissionMapper permissionMapper) {
		this.permissionMapper = permissionMapper;
	}

	@PostMapping
	public ResultVO<Boolean> save(@RequestBody Permission permission) {
		if (permissionMapper.insert(permission) > 0) {
			return new ResultVO<Boolean>(StatusCode.OK, permission.getComment() + "插入成功", true);
		} else
			return new ResultVO<Boolean>(StatusCode.BAD_REQUEST, permission.getComment() + "插入失败", false);
	}

	@PutMapping
	public ResultVO<Boolean> update(@RequestBody Permission permission) {
		if (permissionMapper.updateByPrimaryKey(permission) > 0) {
			return new ResultVO<Boolean>(StatusCode.OK, permission.getComment() + "更新成功", true);
		} else
			return new ResultVO<Boolean>(StatusCode.BAD_REQUEST, permission.getComment() + "更新失败", false);
	}

	@DeleteMapping("/{id}")
	public ResultVO<Boolean> delete(@PathVariable Integer id) {
		if (permissionMapper.deleteByPrimaryKey(id) > 0) {
			return new ResultVO<Boolean>(StatusCode.OK, "删除成功", true);
		} else
			return new ResultVO<Boolean>(StatusCode.BAD_REQUEST, "删除失败", false);
	}

	@GetMapping("/{id}")
	public ResultVO<Permission> getById(@PathVariable Integer id) {
		Permission permission = permissionMapper.selectByPrimaryKey(id);
		if (permission != null) {
			return new ResultVO<Permission>(StatusCode.OK, "查找成功", permission);
		} else
			return new ResultVO<Permission>(StatusCode.BAD_REQUEST, "查找失败", null);
	}

	@GetMapping("/all")
	public ResultVO<List<Permission>> getAll() {
		List<Permission> permissions = permissionMapper.selectAll();
		if (permissions != null) {
			return new ResultVO<List<Permission>>(StatusCode.OK, "查找成功", permissions);
		} else
			return new ResultVO<List<Permission>>(StatusCode.BAD_REQUEST, "查找失败", null);
	}
}
