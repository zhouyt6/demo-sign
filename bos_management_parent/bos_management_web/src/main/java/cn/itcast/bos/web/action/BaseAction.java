package cn.itcast.bos.web.action;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.crm.service.ICustomerService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

public class BaseAction<T> extends ActionSupport implements ModelDriven<T> {
	@Autowired
	protected ICustomerService customerService;

	protected T model;   //new Area();
	public T getModel() {
		// TODO Auto-generated method stub
		return model;
	}
	public BaseAction() {
		//this --- CouriserAction
		//this.getClass() -- CouriserAction.class
		//this.getClass().getGenericSuperclass()-- BaseAction<Couriser>.class
		ParameterizedType parameterizedType = (ParameterizedType) this.getClass().getGenericSuperclass();
		//----[Courise.class]
		 Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
		 //Couriser.class
		 Class<T> entityClass = (Class<T>) actualTypeArguments[0];
		 //new Couriser
		 try {
			model = entityClass.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	//提取分页查询的操作
	protected Integer page;  //当前页
	protected Integer rows;  //每页条数
	
	public void setPage(Integer page) {
		this.page = page;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}
	//page转化为json对象
	public void page2JsonObject(Page<T> page,String[] excludes) {
		
		//3.将查询结果转成json数据
		Map<String, Object> map = new HashMap<>();
		map.put("total", page.getTotalElements()); //总条数
		map.put("rows", page.getContent());  //每页数据的集合
		//JSONObject：将java数据转换成json对象
		//JSONArray：将java数据转换成json数组、

		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(excludes);
		JSONObject jsonObject = JSONObject.fromObject(map, jsonConfig);
		String jsonString = jsonObject.toString();
       //4.、通过reponse对象讲json字符串返回到前台
		 ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
		 try {
			ServletActionContext.getResponse().getWriter().print(jsonString);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 将java数据转换成json数组
	 */
	public void page2JsonArray(List<?> list, String[] excludes) {
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(excludes);
		JSONArray jsonArray = JSONArray.fromObject(list, jsonConfig);
		String json = jsonArray.toString();
		// 4.通过response将json返回到前台
		ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
		try {
			ServletActionContext.getResponse().getWriter().print(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

}
