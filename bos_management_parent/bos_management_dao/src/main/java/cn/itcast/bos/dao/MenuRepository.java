package cn.itcast.bos.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import cn.itcast.bos.domain.base.Menu;

public interface MenuRepository extends JpaRepository<Menu, Integer>, JpaSpecificationExecutor<Menu>{

	List<Menu> findByParentMenuIsNull();
	
	@Query("select distinct m from Menu m join m.roles r join r.users u where u.id = ?1 order by m.priority")
	List<Menu> findMenusByUser(Integer id);

}
