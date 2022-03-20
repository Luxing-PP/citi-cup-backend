package com.nju.edu.citibackend.controller.authorizationcontroller;

import com.nju.edu.citibackend.enums.StatusCode;
import com.nju.edu.citibackend.mapperservice.verifyAuthorization.RoleMapper;
import com.nju.edu.citibackend.po.verifyAuthorization.Role;
import com.nju.edu.citibackend.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Kevin
 * @Description
 */
@RestController
@RequestMapping("/role")
public class RoleController {
	private final RoleMapper RoleMapper;

	@Autowired
	RoleController(RoleMapper RoleMapper) {
		this.RoleMapper = RoleMapper;
	}

	@PostMapping
	public ResultVO<Boolean> save(@RequestBody Role Role) {
		if (RoleMapper.insert(Role) > 0) {
			return new ResultVO<Boolean>(StatusCode.OK, Role.getComment() + "插入成功", true);
		} else
			return new ResultVO<Boolean>(StatusCode.BAD_REQUEST, Role.getComment() + "插入失败", false);
	}

	@PutMapping
	public ResultVO<Boolean> update(@RequestBody Role Role) {
		if (RoleMapper.updateByPrimaryKey(Role) > 0) {
			return new ResultVO<Boolean>(StatusCode.OK, Role.getComment() + "更新成功", true);
		} else
			return new ResultVO<Boolean>(StatusCode.BAD_REQUEST, Role.getComment() + "更新失败", false);
	}

	@DeleteMapping("/{id}")
	public ResultVO<Boolean> delete(@PathVariable Integer id) {
		if (RoleMapper.deleteByPrimaryKey(id) > 0) {
			return new ResultVO<Boolean>(StatusCode.OK, "删除成功", true);
		} else
			return new ResultVO<Boolean>(StatusCode.BAD_REQUEST, "删除失败", false);
	}

	@GetMapping("/{id}")
	public ResultVO<Role> getById(@PathVariable Integer id) {
		Role Role = RoleMapper.selectByPrimaryKey(id);
		if (Role != null) {
			return new ResultVO<Role>(StatusCode.OK, "查找成功", Role);
		} else return new ResultVO<Role>(StatusCode.BAD_REQUEST, "查找失败", null);
	}

	@GetMapping("/all")
	public ResultVO<List<Role>> getAll() {
		List<Role> Roles = RoleMapper.selectAll();
		if (Roles != null) {
			return new ResultVO<List<Role>>(StatusCode.OK, "查找成功", Roles);
		} else
			return new ResultVO<List<Role>>(StatusCode.BAD_REQUEST, "查找失败", null);
	}
}
