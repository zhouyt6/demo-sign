package cn.itcast.bos.service.impl;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.bos.dao.CourierRepository;
import cn.itcast.bos.domain.base.Courier;
import cn.itcast.bos.service.CourierService;

@Service
@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
public class CourierServiceImpl implements CourierService {
	
	@Autowired
	private CourierRepository dao;
	@Override
	@RequiresPermissions(value="abc")
	public void add(Courier model) {
		dao.save(model);
	}
	//分页查询
	@Override
	public Page<Courier> pageQuery(Specification<Courier> spec,Pageable pageable) {
		return dao.findAll(spec,pageable);
	}
	//查询全部
	@Override
	public List<Courier> findAll() {
		return dao.findAll();
	}
	//删除 .修改为作废
	@Override
	public void delete(String ids) {
		//分割字符串
		String[] idsArr = ids.split(",");
		//循环删除作废快递员
		for (String id : idsArr) {
			dao.deleteById(Integer.valueOf(id));
		}		
	}
	//修改为正常使用中
	@Override
	public void restore(String ids) {
		//分割字符串
		String[] idsArr = ids.split(",");
		//循环删除作废快递员
		for (String id : idsArr) {
			dao.restoreById(Integer.valueOf(id));
		}		
	}
	//查询未作废快递员
	@Override
	public List<Courier> findNoDel() {
		return dao.findNoDel();
	}

}
