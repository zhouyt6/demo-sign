package cn.itcast.bos.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import cn.itcast.bos.domain.base.WorkBill;

public interface WorkBillRepository extends JpaRepository<WorkBill, Integer>, JpaSpecificationExecutor<WorkBill>{
	
	@Query("from WorkBill where pickstate ='未取件' and type = '新单'")
	List<WorkBill> findPickstateAndType();

}
