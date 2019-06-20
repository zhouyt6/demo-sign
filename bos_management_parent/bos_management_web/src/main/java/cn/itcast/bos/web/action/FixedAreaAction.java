package cn.itcast.bos.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;

import cn.itcast.bos.domain.base.Area;
import cn.itcast.bos.domain.base.FixedArea;
import cn.itcast.bos.domain.base.SubArea;
import cn.itcast.bos.service.IFixedAreaService;
import cn.itcast.crm.service.Customer;

@ParentPackage("struts-default")
@Namespace("/")
@Actions
@Controller
@Scope("prototype")
public class FixedAreaAction extends BaseAction<FixedArea> {
	
	@Resource
	private IFixedAreaService fixedAreaService;
	
	@Action(value="fixedArea_save",results={@Result(name="success",location="/pages/base/fixed_area.jsp")})
	public String fixedArea_save() {
		fixedAreaService.save(model);
		return super.SUCCESS;
	}
	
	//分页数据查询
	//分页查询所有信息
		@Action(value="fixedareaAction_pageQuery")
		public String pageQuery() {
			Pageable pageable = new PageRequest(page-1, rows);
			Specification<FixedArea> spec = new Specification<FixedArea>() {

				@Override
				public Predicate toPredicate(Root<FixedArea> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
					List<Predicate> list = new ArrayList<>();
					 
					String id = model.getId();
					if (StringUtils.isNotBlank(id)) {  // 定区编码
						 list.add(cb.equal(root.get("id").as(String.class), id));
					}
					
					String company = model.getCompany();
					if (StringUtils.isNotBlank(company)) {  //所属单位
						 list.add(cb.like(root.get("company").as(String.class), "%"+company+"%"));
					}
				
					Predicate[] pre = new Predicate[list.size()];
					return query.where(list.toArray(pre)).getRestriction();
				}
			};
			//执行分页
			 Page<FixedArea> page = fixedAreaService.pageQuery(spec,pageable);
			 String[] excludes ={"subareas","couriers"};
			 super.page2JsonObject(page, excludes);
			return NONE;
		}
	
	//查询左边没有关联客户的客户
	@Action(value="fixedAreaAction_findNoAssociateCustomers")
	public String findNoAssociateCustomers(){
		//1,.调用crm接口查询未关联定区的客户
		List<Customer> list = customerService.findNOAssociateCustomers();
		//2.将客户转化为json返回到前台
		String[] excludes = {};
		this.page2JsonArray(list, excludes);
		return NONE;
	}
	//查询右边关联客户的客户
	@Action(value="fixedAreaAction_findAssociateCustomers")
	public String findAssociateCustomers(){
		//1.调用crm的接口查询未关联定区的客户
		List<Customer> list = customerService.findAssociateCustomers(model.getId());
		String[] excludes = {};
		//2.将客户转换成json返回到前台
		this.page2JsonArray(list, excludes);
		return NONE;
	}
	
	private List<Integer> customerIds;
	
	public void setCustomerIds(List<Integer> customerIds) {
		this.customerIds = customerIds;
	}

	@Action(value="fixedAreaAction_assignCustomers2FixedArea",results={
			@Result(name="success",location="/pages/base/fixed_area.jsp")})
	public String assignCustomers2FixedArea() {
		customerService.assignCustomers2FixedArea(customerIds, model.getId());
		return super.SUCCESS;
	}
	
	
	
	private Integer courierId;
	private Integer takeTimeId;
	
	public void setCourierId(Integer courierId) {
		this.courierId = courierId;
	}

	public void setTakeTimeId(Integer takeTimeId) {
		this.takeTimeId = takeTimeId;
	}
	//关联快递员
	@Action(value="fixedAreaAction_associationCourierToFixedArea",results={
			@Result(name="success",type="redirect",location="/pages/base/fixed_area.jsp")
	})
	public String associationCourierToFixedArea() {
		fixedAreaService.associationCourierToFixedArea(model.getId(),courierId,takeTimeId);
		return SUCCESS;
	}
	
	private List<String> subareaIds;
	
	public void setSubareaIds(List<String> subareaIds) {
		this.subareaIds = subareaIds;
	}

	//关联分区
	@Action(value="fixedAreaAction_assignSubArea2FixedArea",results={
			@Result(name="success",type="redirect",location="/pages/base/fixed_area.jsp")
	})
	public String assignSubArea2FixedArea() {
		fixedAreaService.assignSubArea2FixedArea(model.getId(),subareaIds);
		return SUCCESS;
	}
	
	
}
