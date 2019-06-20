package cn.itcast.bos.web.action;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.itcast.bos.domain.base.WorkBill;
import cn.itcast.bos.service.IWorkBillService;

@ParentPackage("struts-default")
@Namespace("/")
@Actions
@Controller
@Scope("prototype")
public class WorkBillAction extends BaseAction<WorkBill> {
	
	@Resource
	private IWorkBillService workBillService;
	
}
