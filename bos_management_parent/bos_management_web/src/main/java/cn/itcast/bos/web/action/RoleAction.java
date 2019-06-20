package cn.itcast.bos.web.action;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.bouncycastle.jce.provider.JDKDSASigner.noneDSA;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;

import cn.itcast.bos.domain.base.Permission;
import cn.itcast.bos.domain.base.Role;
import cn.itcast.bos.service.IRoleService;

@ParentPackage("struts-default")
@Namespace("/")
@Actions
@Controller
@Scope("prototype")
public class RoleAction extends BaseAction<Role> {
	
	@Resource
	private IRoleService roleService;
	
	//分页查询
	@Action(value="roleAction_pageQuery")
	public String pageQuery() {
 		Pageable pageable = new PageRequest(page-1, rows);
		Specification<Role> spec = new Specification<Role>() {
			@Override
			public Predicate toPredicate(Root<Role> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				//只查询父节点是null的根节点数据
				return null;
			}
		};
		//执行分页
		 Page<Role> page = roleService.pageQuery(spec,pageable);
		 String[] excludes ={"users","permissions","menus"};
		 super.page2JsonObject(page, excludes);
		return NONE;
	}
	
	private String menuIds; //菜单id字符串
	private Integer[] permissionIds;  //权限id数组
	
	public void setMenuIds(String menuIds) {
		this.menuIds = menuIds;
	}

	public void setPermissionIds(Integer[] permissionIds) {
		this.permissionIds = permissionIds;
	}

	//增加角色
	@Action(value="roleAction_add",results={
			@Result(name="success",location="/pages/system/role.jsp")})
	public String add() {
		roleService.add(model,menuIds,permissionIds);
		return SUCCESS;
	}
	
	//查询所有角色
	@Action(value="roleAction_findAll")
	public String findAll() {
	     List<Role> list =  roleService.findAll();
		 String[] excludes ={"users","permissions","menus"};
		 super.page2JsonArray(list, excludes);
		 return NONE;
	}
}
