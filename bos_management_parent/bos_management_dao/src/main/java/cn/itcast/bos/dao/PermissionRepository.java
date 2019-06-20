package cn.itcast.bos.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import cn.itcast.bos.domain.base.Permission;

public interface PermissionRepository extends JpaRepository<Permission, Integer>, JpaSpecificationExecutor<Permission>{
	
	@Query("select p from Permission p join p.roles r join r.users u where u.id = ?1")
	List<Permission> findPermissionsByUserId(Integer id);

}
