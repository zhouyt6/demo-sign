package cn.itcast.bos.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import cn.itcast.bos.domain.base.WayBill;

public interface WayBillRepository extends JpaRepository<WayBill, Integer>, JpaSpecificationExecutor<WayBill>{

}
