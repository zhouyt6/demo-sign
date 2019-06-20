package cn.itcast.bos.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.bos.dao.SubAreaRepository;
import cn.itcast.bos.domain.base.SubArea;
import cn.itcast.bos.service.ISubAreaService;

@Service
@Transactional
public class SubAreaServiceImpl implements ISubAreaService {

	@Resource
	private SubAreaRepository subAreaRepository;

	@Override
	public void save(SubArea model) {
		subAreaRepository.save(model);
	}
	
	//分页查询分区数据
	@Override
	public Page<SubArea> pageQuery(Specification<SubArea> spec, Pageable pageable) {
		return subAreaRepository.findAll(spec, pageable);
	}

	@Override
	public List<SubArea> findAll() {
		return subAreaRepository.findAll();
	}

	@Override
	public List<Object> findGroupedProvincesAndNum() {
		return subAreaRepository.findGroupedProvincesAndNum();
	}

	@Override
	public List<SubArea> findNoAssociateSubArea() {
		return subAreaRepository.findByfixedAreaIsNull();
	}

	@Override
	public List<SubArea> findAssociateSubArea() {
		return subAreaRepository.findByfixedAreaIsNotNull();
	}

}
