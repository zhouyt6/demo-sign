package cn.itcast.bos.service.impl;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.bos.dao.AreaRepository;
import cn.itcast.bos.dao.FixedAreaRepository;
import cn.itcast.bos.dao.OrderRepository;
import cn.itcast.bos.dao.WorkBillRepository;
import cn.itcast.bos.domain.base.Area;
import cn.itcast.bos.domain.base.Courier;
import cn.itcast.bos.domain.base.FixedArea;
import cn.itcast.bos.domain.base.Order;
import cn.itcast.bos.domain.base.SubArea;
import cn.itcast.bos.domain.base.WorkBill;
import cn.itcast.bos.service.IOrderRemoteService;
import cn.itcast.bos.utils.MailUtils;
import cn.itcast.crm.service.ICustomerService;
import net.sf.ehcache.search.expression.And;
@Transactional
public class OrderRemoteServiceImpl implements IOrderRemoteService {
	@Autowired
	private OrderRepository orderRepository;
	
	@Resource
	private ICustomerService customerService;
	
	@Resource
	private FixedAreaRepository fixedAreaRepository;
	@Resource
	private WorkBillRepository workBillRepository;
	
	@Resource
	private AreaRepository areaRepository;
	@Override
	public void saveOrder(Order order) {
		//1.设置订单编号
		order.setOrderNum(UUID.randomUUID().toString());
		//2.设置下单时间
		order.setOrderTime(new Date());
		/**
		 * 保存订单,因为引用了area的瞬时态对象,所以报object references an unsaved transient instance
		 * 解决方式一:需要关联更新
		 * 		根据瞬时态对象中的信息.查询数据库,获得持久态对象,关联到order
		 * 解决方式二:不需要关联更新
		 * 		直接将关联的area设置为null;
		 */
		//下单的时候要使用,所以先临时存储起来
		Area sendArea = order.getSendArea();
		Area recArea = order.getRecArea();
		
		//直接将关联的area设置为null;
		order.setSendArea(null);
		order.setRecArea(null);
		
		orderRepository.save(order);
		
		//4.自动分单方式一:根据客户的详细地址进行分单
		//4.1 获取客户的详细地址
		String sendAddress = order.getSendAddress();
		//4.2 根据详细地址查询定区id
		String fixedAreaId = customerService.findFixedAreaIdByAddress(sendAddress);
		//4.3 如果查询到定区id,根据定区id查询定区对象
		if(StringUtils.isNotBlank(fixedAreaId)) {
			FixedArea fixedArea = fixedAreaRepository.findOne(fixedAreaId);
			//4.4 获取定区对象中关联的快递员集合
			Set<Courier> couriers = fixedArea.getCouriers(); //null
			System.out.println("定区中的快递员集合:couriers----"+couriers);
			for (Courier courier : couriers) {
				//4.5从中获取第一个快递员的分单  2.4获取快递员的取派时间，看当前时间是否在其中
				if (null != courier) {
					//4.6设置订单的分单类型:自动分单
					order.setOrderType("自动分单");
					//4.7 关联订单和快递员
					order.setCourier(courier);
				}
				//4.8 为快递员生成工单并保存
				WorkBill workBill = new WorkBill();
				workBill.setAttachbilltimes(0); //追单次数,新单是0,初始化为0
				workBill.setBuildtime(new Date()); // 创建时间
				workBill.setCourier(courier);  //关联工单和快递员
				workBill.setOrder(order);  // 关联工单和订单
				workBill.setPickstate("未取件");  //取件状态：已取件、未取件
				workBill.setRemark(order.getRemark()); //备注.和订单的保持一致
				workBill.setSmsNumber(RandomStringUtils.randomNumeric(4)); // 取件短信码
				workBill.setType("新单");  //工单类型：新单、追单、销单
				workBillRepository.save(workBill);  //保存
				//4.9 给快递员发送新单通知邮件
				String content = "工单信息：请到"+sendAddress+"取件，客户电话：" + order.getSendMobile();
				MailUtils.sendMail("速运快递：新单通知", content, courier.getEmail());
				return;  //找到快递员直接直接循环
			}
		}
		
		//5自动分单方式二:根据分区关键字匹配自动分单
		//5.1 获取客户所属区域信息sendArea
		//5.2根据区域信息查询区域对象
		Area area = areaRepository.findByProvinceAndCityAndDistrict(
				sendArea.getProvince(), sendArea.getCity(), sendArea.getDistrict());
		//5.3 获取区域信息关联的分区集合
		Set<SubArea> subareas = area.getSubareas();
		if (null != subareas && subareas.size()>0) {
			for (SubArea subArea : subareas) {
				//5.4遍历分区集合，使用分区的关键字和辅助关键字分别和客户的详细地址比对，匹配上，说明找到客户分区
				if (sendAddress.contains(subArea.getKeyWords()) &&
						sendAddress.contains(subArea.getAssistKeyWords())) {
					//5.5根据分区找到定区
					FixedArea fixedArea = subArea.getFixedArea();
					//5.6 获取定区对象中关联的快递员集合
					Set<Courier> couriers = fixedArea.getCouriers();
					for (Courier courier : couriers) {
						//5.7从中获取第一个快递员的分单  2.4获取快递员的取派时间，看当前时间是否在其中
						if (null != courier) {
							//5.8设置订单的分单类型:自动分单
							order.setOrderType("自动分单");
							//5.9 关联订单和快递员
							order.setCourier(courier);
						}
						//4.10 为快递员生成工单并保存
						WorkBill workBill = new WorkBill();
						workBill.setAttachbilltimes(0); //追单次数,新单是0,初始化为0
						workBill.setBuildtime(new Date()); // 创建时间
						workBill.setCourier(courier);  //关联工单和快递员
						workBill.setOrder(order);  // 关联工单和订单
						workBill.setPickstate("未取件");  //取件状态：已取件、未取件
						workBill.setRemark(order.getRemark()); //备注.和订单的保持一致
						workBill.setSmsNumber(RandomStringUtils.randomNumeric(4)); // 取件短信码
						workBill.setType("新单");  //工单类型：新单、追单、销单
						workBillRepository.save(workBill);  //保存
						//4.11 给快递员发送新单通知邮件
						String content = "工单信息：请到"+sendAddress+"取件，客户电话：" + order.getSendMobile();
						MailUtils.sendMail("速运快递：新单通知", content, courier.getEmail());
						return;  //找到快递员直接直接循环
					}
					
				}
			}
		}
		
		//6.如果两种分单方式都失败，手工分单
		//6.1设置订单的分单类型：手工分单
		order.setOrderType("手工分单");
	}

}
