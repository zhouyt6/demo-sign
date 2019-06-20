package cn.itcast.bos.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import cn.itcast.bos.domain.base.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>, JpaSpecificationExecutor<Role>{
	
	@Query("select r from Role r join r.users u where u.id = ?1")
	List<Role> findRolesByUserId(Integer id);

}
