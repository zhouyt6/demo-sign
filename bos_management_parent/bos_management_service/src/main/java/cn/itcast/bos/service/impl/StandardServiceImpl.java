package cn.itcast.bos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.bos.dao.StandardRepository;
import cn.itcast.bos.domain.base.Standard;
import cn.itcast.bos.service.StandardService;

@Service
@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
public class StandardServiceImpl implements StandardService {
	
	@Autowired
	private StandardRepository dao;
	@Override
	public void add(Standard model) {
		dao.save(model);
	}
	@Override
	public Page<Standard> pageQuery(Pageable pageable) {
		Page<Standard> pageQuery = dao.findAll(pageable);
		return pageQuery;
	}
	@Override
	public List<Standard> findAll() {
		return dao.findAll();
	}

}
