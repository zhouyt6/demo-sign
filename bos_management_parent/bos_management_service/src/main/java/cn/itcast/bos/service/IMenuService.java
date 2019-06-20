package cn.itcast.bos.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import cn.itcast.bos.domain.base.Menu;

public interface IMenuService{

	Page<Menu> pageQuery(Specification<Menu> spec, Pageable pageable);

	List<Menu> findByParentMenuIsNull();

	void save(Menu model);

	List<Menu> findMenus();

}
