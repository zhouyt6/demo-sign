package cn.itcast.bos.web.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import cn.itcast.bos.domain.base.Standard;
import cn.itcast.bos.service.StandardService;

@ParentPackage("struts-default")
@Namespace("/")
@Actions
@Controller//相当于bean标签
@Scope("prototype")  //声明多例
public class StandardAction extends BaseAction<Standard>{
	
	@Autowired
	private StandardService service;
	
	@Action(value="standard_add",results={@Result(name="success",location="/pages/base/standard.jsp")})
	public String add() {
		service.add(model);
		return super.SUCCESS;
	}
	
	@Action(value="standard_pageQuery")
	public String pageQuery(){
		//1.封装分页查询的参数  
		//参数1：当前页-1
		//参数2： 每页条数
		Pageable pageable = new PageRequest(page-1, rows);
		//2.执行分页查询（总条数和每页数据的集合）
		Page<Standard> page = service.pageQuery(pageable);
		//3.将查询结果转成json数据
		String[] excludes = {};
		super.page2JsonObject(page, excludes);
		return super.NONE;
	}
	@Action(value="standard_findAll")
	public String findAll(){
		//1.查询所有的取派标准数据
		List<Standard> list = service.findAll();
		//2.将数据转换成json，返回到前台
		String jsonString = JSON.toJSONString(list); 
		//4.、通过reponse对象讲json字符串返回到前台
		ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
		try {
			ServletActionContext.getResponse().getWriter().print(jsonString);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return super.NONE;
	}

}
