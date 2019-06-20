package cn.itcast.bos.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import cn.itcast.bos.domain.base.Courier;

public interface CourierService {

	public void add(Courier model);

	public Page<Courier> pageQuery(Specification<Courier> spec,Pageable pageable);

	public List<Courier> findAll();
	public List<Courier> findNoDel();
	
	public void delete(String ids);
	public void restore(String ids);

}
