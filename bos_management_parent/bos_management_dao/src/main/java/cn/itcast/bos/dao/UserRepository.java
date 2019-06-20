package cn.itcast.bos.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import cn.itcast.bos.domain.base.User;

public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User>{

	User findByUsername(String username);

}
