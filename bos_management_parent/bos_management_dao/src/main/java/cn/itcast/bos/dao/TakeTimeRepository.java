package cn.itcast.bos.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import cn.itcast.bos.domain.base.TakeTime;
/**
 * <p>Title: cn.itcast.bos.dao.TakeTimeRepository.java
 *	泛型1:实体类
 *	泛型2：实体中主键的类型
 * @author Administrator
 */
public interface TakeTimeRepository extends JpaRepository<TakeTime, Integer>,JpaSpecificationExecutor<TakeTime> {
	
	public List<TakeTime> findByName(String string);
	public List<TakeTime> readByName(String string);
	
	public List<TakeTime> findByNameLike(String string);
	
	@Query("from TakeTime where name like ?1 and operator like ?2")
	public List<TakeTime> getNameLike(String name,String operator);
}
