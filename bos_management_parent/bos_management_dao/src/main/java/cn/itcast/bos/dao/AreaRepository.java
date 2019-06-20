package cn.itcast.bos.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import cn.itcast.bos.domain.base.Area;

/**
 * <p>Title: cn.itcast.bos.dao.AreaRepository.java
 *	泛型1:实体类
 *	泛型2：实体中主键的类型
 * @author Administrator
 */
public interface AreaRepository extends JpaRepository<Area, String>, JpaSpecificationExecutor<Area> {

	Area findByProvinceAndCityAndDistrict(String province, String city, String district);
	
}
