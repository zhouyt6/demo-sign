package cn.itcast.bos.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import cn.itcast.bos.domain.base.User;

public interface IUserService{

	User findByUsername(String username);

	Page<User> pageQuery(Specification<User> spec, Pageable pageable);

	void add(User model, Integer[] roleIds);

}
