package cn.itcast.bos.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import cn.itcast.bos.domain.base.SubArea;

public interface SubAreaRepository extends JpaRepository<SubArea, String>, JpaSpecificationExecutor<SubArea>{
	
	@Query("select a.province,count(1) from SubArea s join s.area a group by a.province")
	List<Object> findGroupedProvincesAndNum();

	List<SubArea> findByfixedAreaIsNull();

	List<SubArea> findByfixedAreaIsNotNull();

}
