package cn.itcast.bos.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.bos.dao.PermissionRepository;
import cn.itcast.bos.domain.base.Menu;
import cn.itcast.bos.domain.base.Permission;
import cn.itcast.bos.domain.base.User;
import cn.itcast.bos.service.IPermissionService;

@Service
@Transactional
public class PermissionServiceImpl implements IPermissionService {

	@Resource
	private PermissionRepository permissionRepository;

	@Override
	public Page<Permission> pageQuery(Specification<Permission> spec, Pageable pageable) {
		return permissionRepository.findAll(spec, pageable);
	}

	@Override
	public void save(Permission model) {
		permissionRepository.save(model);
	}

	@Override
	public List<Permission> findAll() {
		return permissionRepository.findAll();
	}

	//根据用户查询用户的权限
	public List<Permission> findPermissionsByUser(User user) {
		String username = user.getUsername();
		//1判断是否是超级管理员admin
		if ("admin".equals(username)) {
			//2如果是admin,查询所有权限
			return permissionRepository.findAll();
		} else {
			//3如果不是admin,根据用户id查询用户的权限
			return permissionRepository.findPermissionsByUserId(user.getId());
		}
	}
	
}
