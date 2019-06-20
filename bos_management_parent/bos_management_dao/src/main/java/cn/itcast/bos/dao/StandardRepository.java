package cn.itcast.bos.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import cn.itcast.bos.domain.base.Standard;
/**
 * <p>Title: cn.itcast.bos.dao.StandardRepository.java
 *	泛型1:实体类
 *	泛型2：实体中主键的类型
 * @author Administrator
 */
public interface StandardRepository extends JpaRepository<Standard, Integer> {
	
	public List<Standard> findByName(String string);
	public List<Standard> readByName(String string);
	
	public List<Standard> findByNameLike(String string);
	
	@Query("from Standard where name like ?1 and operator like ?2")
	public List<Standard> getNameLike(String name,String operator);
}
