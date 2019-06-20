package cn.itcast.bos.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.bos.dao.MenuRepository;
import cn.itcast.bos.dao.PermissionRepository;
import cn.itcast.bos.dao.RoleRepository;
import cn.itcast.bos.domain.base.Menu;
import cn.itcast.bos.domain.base.Permission;
import cn.itcast.bos.domain.base.Role;
import cn.itcast.bos.domain.base.User;
import cn.itcast.bos.service.IRoleService;

@Service
@Transactional
public class RoleServiceImpl implements IRoleService {

	@Resource
	private RoleRepository roleRepository;
	@Resource
	private MenuRepository menuRepository;
	@Resource
	private PermissionRepository permissionRepository;

	@Override
	public Page<Role> pageQuery(Specification<Role> spec, Pageable pageable) {
		return roleRepository.findAll(spec, pageable);
	}

	/**
	 * 添加一个角色.同时关联权限和菜单
	 */
	public void add(Role model, String menuIds, Integer[] permissionIds) {
		roleRepository.save(model);//持久态对象
		if (StringUtils.isNotBlank(menuIds)) {  //关联菜单
			//切割字符串
			String[] split = menuIds.split(",");
			for (String id : split) {
				Menu menu = menuRepository.findOne(Integer.parseInt(id));
				model.getMenus().add(menu);  //角色关联菜单
			}
		}
		//角色关联权限
		if (permissionIds != null && permissionIds.length > 0) {
			for (Integer id : permissionIds) {
				Permission permission = permissionRepository.findOne(id);
				model.getPermissions().add(permission);//角色关联权限
			}
		}
	}

	@Override
	public List<Role> findAll() {
		return roleRepository.findAll();
	}

	@Override
	public List<Role> findRolesByUser(User user) {
		String username = user.getUsername();
		//1判断是否是超级管理员admin
		if ("admin".equals(username)) {
			//2如果是admin,查询所有权限
			return roleRepository.findAll();
		} else {
			//3如果不是admin,根据用户id查询用户的权限
			return roleRepository.findRolesByUserId(user.getId());
		}
	}
}
