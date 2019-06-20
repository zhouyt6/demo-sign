package cn.itcast.bos.web.action;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.xml.resolver.apps.resolver;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;

import antlr.Token;
import cn.itcast.bos.domain.base.Menu;
import cn.itcast.bos.domain.base.User;
import cn.itcast.bos.service.IUserService;
import cn.itcast.bos.utils.MD5Utils;
import oracle.jdbc.util.Login;

@ParentPackage("struts-default")
@Namespace("/")
@Actions
@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User> {
	
	@Resource
	private IUserService userService;
	//接收验证码
	private String checkcode;
	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}
	
	//基于shiro框架实现认证(登录)
	@Action(value="userAction_login",results={
			@Result(name="login",location="/login.jsp",type="redirect"),
			@Result(name="success",location="/index.jsp",type="redirect")
	})
	public String login() {
		//1.校验验证码是否正确
		//从session中获取生成的验证码
		String checkcodeS = (String) ServletActionContext.getRequest().getSession().getAttribute("key");
			//判断用户是否录入
		if (StringUtils.isNotBlank(checkcode) && checkcode.equals(checkcodeS)) {
			//2.输入验证码正确 , 使用shiro实现账号和密码的认证
			//2.1 获取subject对象
			Subject subject = SecurityUtils.getSubject(); // 未认证
			AuthenticationToken token = new UsernamePasswordToken(model.getUsername(), MD5Utils.md5(model.getPassword() ));
			//2.2 调用subject的认证方法进行认证
			try {
				subject.login(token);
				//2.3登录成功,跳转到成功界面
				User user = (User) subject.getPrincipal();  //不是必须的
				ServletActionContext.getRequest().getSession().setAttribute("loginUser", user); //不是必须的
				return super.SUCCESS;
			} catch (AuthenticationException e) {
				e.printStackTrace();
				//2.4失败,跳转到登录界面
				return "login";
			} 
		} else {
			//3.错误,跳转到登录界面
			return "login";
		}
	}
	
	//用户注销
	@Action(value="userAction_logout",results={@Result(name="login",location="/login.jsp",type="redirect")})
	public String logout() {
		//获得subject对象
		Subject subject = SecurityUtils.getSubject();
		//调用注销的功能
		subject.logout();
		return LOGIN;
	}
	
	@Action(value="userAction_pageQuery")
	public String pageQuery() {
 		Pageable pageable = new PageRequest(page-1, rows);
		Specification<User> spec = new Specification<User>() {
			@Override
			public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				//只查询父节点是null的根节点数据
				return null;
			}
		};
		//执行分页
		 Page<User> page = userService.pageQuery(spec,pageable);
		 String[] excludes ={"roles"};
		 super.page2JsonObject(page, excludes);
		return NONE;
	}
	
	//
	//接收角色复选框数组
	private Integer[] roleIds;
	
	public void setRoleIds(Integer[] roleIds) {
		this.roleIds = roleIds;
	}

	@Action(value="userAction_add",results={
			@Result(name="success",location="/pages/system/user.jsp")
	})
	public String add() {
		userService.add(model,roleIds);
		return SUCCESS;
	}
}
