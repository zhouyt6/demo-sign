package cn.itcast.bos.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import cn.itcast.bos.domain.base.SubArea;

public interface ISubAreaService{

	public void save(SubArea model);

	public Page<SubArea> pageQuery(Specification<SubArea> spec, Pageable pageable);

	public List<SubArea> findAll();

	public List<Object> findGroupedProvincesAndNum();

	public List<SubArea> findNoAssociateSubArea();

	public List<SubArea> findAssociateSubArea();

}
