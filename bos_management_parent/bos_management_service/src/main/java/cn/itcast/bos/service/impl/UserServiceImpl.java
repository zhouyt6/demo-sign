package cn.itcast.bos.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.poi.POIOLE2TextExtractor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.bos.dao.UserRepository;
import cn.itcast.bos.domain.base.Role;
import cn.itcast.bos.domain.base.User;
import cn.itcast.bos.service.IUserService;
import cn.itcast.bos.utils.MD5Utils;

@Service
@Transactional
public class UserServiceImpl implements IUserService {

	@Resource
	private UserRepository userRepository;

	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public Page<User> pageQuery(Specification<User> spec, Pageable pageable) {
		return userRepository.findAll(spec, pageable);
	}

	//添加一个用户,同时需要角色
	public void add(User model, Integer[] roleIds) {
		String password = MD5Utils.md5(model.getPassword()); // 获取密码,然后用MD5加密存入model
		model.setPassword(password);
		userRepository.save(model); //持久态
		//循环用户关联角色
		if (null != roleIds && roleIds.length > 0) {
			for (Integer id : roleIds) {
				Role role = new Role(); //瞬时态
				role.setId(id); // 游离态(托管态)
				model.getRoles().add(role); //用户关联角色
			}
		}
	}

}
