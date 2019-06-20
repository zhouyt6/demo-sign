package cn.itcast.bos.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.bos.dao.WayBillRepository;
import cn.itcast.bos.domain.base.WayBill;
import cn.itcast.bos.service.IWayBillService;

@Service
@Transactional
public class WayBillServiceImpl implements IWayBillService {

	@Resource
	private WayBillRepository wayBillRepository;

	@Override
	public void add(WayBill model) {
		wayBillRepository.save(model);
	}

}
