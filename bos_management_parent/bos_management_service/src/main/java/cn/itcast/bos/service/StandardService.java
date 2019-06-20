package cn.itcast.bos.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import cn.itcast.bos.domain.base.Standard;

public interface StandardService {

	public void add(Standard model);

	public Page<Standard> pageQuery(Pageable pageable);

	public List<Standard> findAll();

}
