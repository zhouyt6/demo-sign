package cn.itcast.bos.service.impl;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.bos.dao.CourierRepository;
import cn.itcast.bos.dao.FixedAreaRepository;
import cn.itcast.bos.dao.SubAreaRepository;
import cn.itcast.bos.dao.TakeTimeRepository;
import cn.itcast.bos.domain.base.Courier;
import cn.itcast.bos.domain.base.FixedArea;
import cn.itcast.bos.domain.base.SubArea;
import cn.itcast.bos.domain.base.TakeTime;
import cn.itcast.bos.service.IFixedAreaService;

@Service
@Transactional
public class FixedAreaServiceImpl implements IFixedAreaService {

	@Resource
	private FixedAreaRepository fixedAreaRepository;
	@Resource
	private SubAreaRepository subAreaRepository;
	@Resource
	private CourierRepository courierRepository; 
	@Resource
	private TakeTimeRepository takeTimeRepository; 
	
	@Override
	public void save(FixedArea model) {
		fixedAreaRepository.save(model);
	}

	@Override
	public Page<FixedArea> pageQuery(Specification<FixedArea> spec, Pageable pageable) {
		return fixedAreaRepository.findAll(spec, pageable);
	}

	@Override
	public void associationCourierToFixedArea(String id, Integer courierId, Integer takeTimeId) {
		//快递员和定区关联
		FixedArea fixedArea = fixedAreaRepository.findOne(id);  //findOne相当于finfById
		Courier courier =  courierRepository.findOne(courierId);
		TakeTime takeTime =  takeTimeRepository.findOne(takeTimeId);
		//快递员和定区关联  Courier 放弃维护权
		fixedArea.getCouriers().add(courier);
		//快递员和上班时间关联
		courier.setTakeTime(takeTime);
	}

	@Override
	public void assignSubArea2FixedArea(String id, List<String> subareaIds) {
		//分区和定区关联/取消原来的关联
		FixedArea fixedArea = fixedAreaRepository.findOne(id);  //findOne相当于finfById
		Set<SubArea> subareas = fixedArea.getSubareas();
		if (subareas != null && subareas.size()>0) {
			for (SubArea subArea : subareas) {
				subArea.setFixedArea(null);
			}
		}
		//绑定新的关联
		if (subareaIds!= null && subareaIds.size()>0) {
			for (String subAreaId : subareaIds) {
				SubArea subArea = subAreaRepository.findOne(subAreaId);
				subArea.setFixedArea(fixedArea);
			}
		}
	}
}
