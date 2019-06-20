package cn.itcast.bos.web.action;

import java.io.IOException;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.itcast.bos.domain.base.WayBill;
import cn.itcast.bos.service.IWayBillService;

@ParentPackage("struts-default")
@Namespace("/")
@Actions
@Controller
@Scope("prototype")
public class WayBillAction extends BaseAction<WayBill> {
	
	@Resource
	private IWayBillService wayBillService;
	
	@Action(value="waybillAction_add")
	public String add() throws IOException {
		String flag = "1";  //录入成功标志位  1成功.0 失败
		try {
			wayBillService.add(model);
		} catch (Exception e) {
			e.printStackTrace();
			flag="0";
		}
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		ServletActionContext.getResponse().getWriter().print(flag);
		return NONE;
	}
}
