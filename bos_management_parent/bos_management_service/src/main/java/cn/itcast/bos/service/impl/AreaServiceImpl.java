package cn.itcast.bos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.bos.dao.AreaRepository;
import cn.itcast.bos.domain.base.Area;
import cn.itcast.bos.service.AreaService;

@Service
@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
public class AreaServiceImpl implements AreaService {
	
	@Autowired
	private AreaRepository dao;
	
	@Override
	public void save(List<Area> list) {
		for(Area area : list){
			dao.save(area);
		}
	}
	
	@Override
	public Page<Area> pageQuery(Specification<Area> spec,Pageable pageable) {
		Page<Area> pageQuery = dao.findAll(spec,pageable);
		return pageQuery;
	}
	@Override
	public List<Area> findAll() {
		return dao.findAll();
	}

}
