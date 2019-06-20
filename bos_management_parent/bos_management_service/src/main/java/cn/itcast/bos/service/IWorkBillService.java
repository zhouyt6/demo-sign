package cn.itcast.bos.service;

import java.util.List;

import cn.itcast.bos.domain.base.WorkBill;

public interface IWorkBillService{

	List<WorkBill> findPickstateAndType();

}
