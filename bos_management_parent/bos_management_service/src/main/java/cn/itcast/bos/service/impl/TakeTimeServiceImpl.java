package cn.itcast.bos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.bos.dao.StandardRepository;
import cn.itcast.bos.dao.TakeTimeRepository;
import cn.itcast.bos.domain.base.Standard;
import cn.itcast.bos.domain.base.TakeTime;
import cn.itcast.bos.service.StandardService;
import cn.itcast.bos.service.TakeTimeService;

@Service
@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
public class TakeTimeServiceImpl implements TakeTimeService {
	
	@Autowired
	private TakeTimeRepository dao;
	@Override
	public void add(TakeTime model) {
		dao.save(model);
	}
	@Override
	public Page<TakeTime> pageQuery(Pageable pageable) {
		Page<TakeTime> pageQuery = dao.findAll(pageable);
		return pageQuery;
	}
	@Override
	public List<TakeTime> findAll() {
		return dao.findAll();
	}

}
