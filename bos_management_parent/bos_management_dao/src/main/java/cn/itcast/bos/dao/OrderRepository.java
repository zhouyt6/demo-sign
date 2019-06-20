package cn.itcast.bos.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import cn.itcast.bos.domain.base.Area;
import cn.itcast.bos.domain.base.Order;

/**
 * <p>Title: cn.itcast.bos.dao.AreaRepository.java
 *	泛型1:实体类
 *	泛型2：实体中主键的类型
 * @author Administrator
 */
public interface OrderRepository extends JpaRepository<Order, Integer>, JpaSpecificationExecutor<Order> {
	
}
