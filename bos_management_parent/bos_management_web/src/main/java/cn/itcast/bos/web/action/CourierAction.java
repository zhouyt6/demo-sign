package cn.itcast.bos.web.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.bos.domain.base.Courier;
import cn.itcast.bos.domain.base.Standard;
import cn.itcast.bos.service.CourierService;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

@ParentPackage("struts-default")
@Namespace("/")
@Actions
@Controller//相当于bean标签
@Scope("prototype")  //声明多例
public class CourierAction extends BaseAction<Courier> {
	
	@Autowired
	private CourierService service;
	
	private String ids;
	
	public void setIds(String ids) {
		this.ids = ids;
	}
	//添加
	@Action(value="courier_add",results={
			@Result(name="success",location="/pages/base/courier.jsp"),
			@Result(name="failure",location="/unauthorizedUrl.jsp")})
	public String add() {
		try {
			service.add(model);
			return super.SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return "failure";
		}
	}
	//修改快递员作废
	@Action(value="courier_delete",results={@Result(name="success",location="/pages/base/courier.jsp")})
	public String delete() {
		service.delete(ids);
		return super.SUCCESS;
	}
	//修改快递员为正常使用中
	@Action(value="courier_restore",results={@Result(name="success",location="/pages/base/courier.jsp")})
	public String restore() {
		service.restore(ids);
		return super.SUCCESS;
	}
	
	//分页查询
	@Action(value="courier_pageQuery")
	public String pageQuery(){
		//1.封装分页查询的参数  
		//参数1：当前页-1
		//参数2： 每页条数
		Pageable pageable = new PageRequest(page-1, rows);
		Specification<Courier> spec = new Specification<Courier>() {
			/**
			 * root: 保存当前实体类和表字段映射
			 * query:组装多个条件
			 * cb:组装单个查询条件
			 */
			public Predicate toPredicate(Root<Courier> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<>();
				//1.获取查询数据 
				String courierNum = model.getCourierNum(); //工号精确查询
				if (StringUtils.isNotBlank(courierNum)) {
					//参数1:字段名      参数2: 字段查询值
					list.add(cb.equal(root.get("courierNum").as(String.class), courierNum));
				}
				
				String company = model.getCompany(); //收派标准迷糊查询
				if (StringUtils.isNotBlank(company)) {
					//参数1:字段名      参数2: 字段查询值
					list.add(cb.like(root.get("company").as(String.class),  "%"+company+"%"));
				}
				
				Standard standard = new Standard();
				if (null != standard) { //所属单位  .多表查询,左外-JoinType.LEFT可以省略
					String name = standard.getName();
					if(StringUtils.isNotBlank(name)){
						  Join<Courier,Standard> join = root.join(root.getModel().getSingularAttribute("standard",Standard.class));
						  list.add(cb.like(join.get("name").as(String.class), "%"+name+"%"));
					}
				}
				
				String type = model.getType(); //类型查询
				if (StringUtils.isNotBlank(type)) {
					//参数1:字段名      参数2: 字段查询值
					list.add(cb.equal(root.get("type").as(String.class),  type));
				}
				//2.将数据组装成查询条件
				Predicate[] predicates = new Predicate[list.size()];
				//3.通过query将多个条件组装并返回
				return query.where(list.toArray(predicates)).getRestriction();
			}
		};
		
		//2.执行分页查询（总条数和每页数据的集合）
		Page<Courier> page = service.pageQuery(spec,pageable);
		//3.装化成json返回数据
		String[]  excludes ={"fixedAreas"};
		super.page2JsonObject(page,excludes);
		return super.NONE;
	}
	
	@Action(value="courier_findAll")
	public String findAll(){
		//1.查询所有的取派标准数据
		List<Courier> list = service.findAll();
		//2.将数据转换成json，返回到前台
		String[] excludes= {"fixedAreas"};
		super.page2JsonArray(list, excludes);
		return super.NONE;
	}
	
	@Action(value="courierAction_listajax")
	public String listajax(){
		//1.查询所有的取派标准数据
		List<Courier> list = service.findNoDel();
		//2.将数据转换成json，返回到前台
		String[] excludes= {"fixedAreas"};
		super.page2JsonArray(list, excludes);
		return super.NONE;
	}
	
}
