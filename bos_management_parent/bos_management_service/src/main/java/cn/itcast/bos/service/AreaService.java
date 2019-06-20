package cn.itcast.bos.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import cn.itcast.bos.domain.base.Area;

public interface AreaService {

	public void save(List<Area> list);

	public Page<Area> pageQuery(Specification<Area> spec,Pageable pageable);

	public List<Area> findAll();

}
