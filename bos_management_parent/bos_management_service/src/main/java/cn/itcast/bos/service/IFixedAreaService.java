package cn.itcast.bos.service;

import java.util.List;

import org.apache.poi.ss.formula.functions.Fixed;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import cn.itcast.bos.domain.base.FixedArea;

public interface IFixedAreaService{

	public void save(FixedArea model);

	public Page<FixedArea> pageQuery(Specification<FixedArea> spec, Pageable pageable);

	public void associationCourierToFixedArea(String id, Integer courierId, Integer takeTimeId);

	public void assignSubArea2FixedArea(String id, List<String> subareaIds);

}
