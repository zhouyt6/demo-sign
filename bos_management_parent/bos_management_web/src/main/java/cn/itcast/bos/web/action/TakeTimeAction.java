package cn.itcast.bos.web.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.itcast.bos.domain.base.Courier;
import cn.itcast.bos.domain.base.TakeTime;
import cn.itcast.bos.service.TakeTimeService;
@ParentPackage("struts-default")
@Namespace("/")
@Actions
@Controller//相当于bean标签
@Scope("prototype")  //声明多例
public class TakeTimeAction extends BaseAction<TakeTime> {
	@Resource
	private TakeTimeService takeTimeService;
	//查询取派时间,.转成json返回到前台
	@Action(value="taketimeAction_listajax")
	public String listajax(){
		//1.查询所有的取派标准数据
		List<TakeTime> list = takeTimeService.findAll();
		//2.将数据转换成json，返回到前台
		String[] excludes= {};
		super.page2JsonArray(list, excludes);
		return super.NONE;
	}
}
