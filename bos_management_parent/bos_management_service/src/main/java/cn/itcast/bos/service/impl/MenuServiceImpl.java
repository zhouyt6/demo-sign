package cn.itcast.bos.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.components.If;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.bos.dao.MenuRepository;
import cn.itcast.bos.domain.base.Menu;
import cn.itcast.bos.domain.base.User;
import cn.itcast.bos.service.IMenuService;

@Service
@Transactional
public class MenuServiceImpl implements IMenuService {

	@Resource
	private MenuRepository menuRepository;

	@Override
	public Page<Menu> pageQuery(Specification<Menu> spec, Pageable pageable) {
		return menuRepository.findAll(spec, pageable);
	}

	@Override
	public List<Menu> findByParentMenuIsNull() {
		return menuRepository.findByParentMenuIsNull();
	}

	@Override
	public void save(Menu model) {
		Menu parentMenu = model.getParentMenu();
		if (parentMenu != null && parentMenu.getId() == null) {
			model.setParentMenu(null);
		}
		menuRepository.save(model);
	}

	//根据用户查询菜单
	public List<Menu> findMenus() {
		//1,获取用户
		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getPrincipal();
		//2.如果是内置超级管理员,查询所有
		if ("admin".equals(user.getUsername())) {
			return menuRepository.findAll();
		} else {
			//3.否则,根据用户查询菜单
			return menuRepository.findMenusByUser(user.getId());
		}
	}

}
