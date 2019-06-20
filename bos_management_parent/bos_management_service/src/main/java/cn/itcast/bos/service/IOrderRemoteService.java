package cn.itcast.bos.service;

import javax.jws.WebService;

import cn.itcast.bos.domain.base.Order;

@WebService
public interface IOrderRemoteService {
	public void saveOrder(Order order);
}
