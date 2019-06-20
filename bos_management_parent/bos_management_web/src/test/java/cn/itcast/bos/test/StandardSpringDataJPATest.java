package cn.itcast.bos.test;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.itcast.bos.dao.StandardRepository;
import cn.itcast.bos.domain.base.Standard;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class StandardSpringDataJPATest {
	
	@Autowired
	private StandardRepository standardRepository;
	
	@Test
	public void testDemo01() {
		List<Standard> findAll = standardRepository.findAll();
		for (Standard standard : findAll) {
			System.out.println(standard.getName());
		}
	}
	
	@Test
	public void testDemo02() {
		List<Standard> findAll = standardRepository.findByName("10-20公斤");
		for (Standard standard : findAll) {
			System.out.println(standard.getName());
		}
	}
	
	@Test
	public void testDemo021() {
		List<Standard> findAll = standardRepository.readByName("10-20公斤");
		for (Standard standard : findAll) {
			System.out.println(standard.getName());
		}
	}
	
	@Test
	public void testDemo03() { //模糊查询
		List<Standard> findAll = standardRepository.findByNameLike("%20%");
		for (Standard standard : findAll) {
			System.out.println(standard.getName());
		}
	}
	//query注解查询
	@Test
	public void testDemo04() { //模糊查询
		List<Standard> findAll = standardRepository.getNameLike("%10%","%20%");
		for (Standard standard : findAll) {
			System.out.println(standard.getId()+"=========="+standard.getName()+"=========="+standard.getOperator());
		}
	}
}
