package cn.itcast.bos.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import cn.itcast.bos.domain.base.Courier;
/**
 * <p>Title: cn.itcast.bos.dao.StandardRepository.java
 *	泛型1:实体类
 *	泛型2：实体中主键的类型
 * @author Administrator
 */
public interface CourierRepository extends JpaRepository<Courier, Integer>,JpaSpecificationExecutor<Courier>
{
	
	//修改快递员为已作废状态
	@Query("update Courier set deltag ='1' where id=?1")
	@Modifying
	public void deleteById(Integer id);
	//修改快递员为已作废状态
	@Query("update Courier set deltag ='0' where id=?1")
	@Modifying
	public void restoreById(Integer id);
	
	@Query("from Courier where deltag ='0'")
	public List<Courier> findNoDel();
}
