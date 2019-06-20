package cn.itcast.bos.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import cn.itcast.bos.domain.base.Menu;
import cn.itcast.bos.domain.base.Permission;
import cn.itcast.bos.domain.base.User;

public interface IPermissionService{

	Page<Permission> pageQuery(Specification<Permission> spec, Pageable pageable);

	void save(Permission model);

	List<Permission> findAll();

	List<Permission> findPermissionsByUser(User user);


}
