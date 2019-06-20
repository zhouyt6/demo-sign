package cn.itcast.jobs;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import cn.itcast.bos.domain.base.WorkBill;
import cn.itcast.bos.service.IWorkBillService;
import cn.itcast.bos.utils.MailUtils;

public class MailJob {
	
	@Resource
	private IWorkBillService workBillService;
	//发送邮件的job工作
	public void sendMail() {
		//查询工单中类型是"新单",状态是"未取件"的工单
		List<WorkBill> workBills = workBillService.findPickstateAndType();
		if (workBills != null && workBills.size() > 0) {
			for (WorkBill workBill : workBills) {
				String content = "请速去取件";
				MailUtils.sendMail("速运快递：新单通知", content, workBill.getCourier().getEmail());
			}
		}
	}
}
