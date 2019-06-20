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
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;

import cn.itcast.bos.domain.base.Menu;
import cn.itcast.bos.domain.base.Permission;
import cn.itcast.bos.service.IPermissionService;

@ParentPackage("struts-default")
@Namespace("/")
@Actions
@Controller
@Scope("prototype")
public class PermissionAction extends BaseAction<Permission> {
	
	@Resource
	private IPermissionService permissionService;
	
	@Action(value="permissionAction_pageQuery")
	public String pageQuery() {
 		Pageable pageable = new PageRequest(page-1, rows);
		Specification<Permission> spec = new Specification<Permission>() {
			@Override
			public Predicate toPredicate(Root<Permission> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				//只查询父节点是null的根节点数据
				return null;
			}
		};
		//执行分页
		 Page<Permission> page = permissionService.pageQuery(spec,pageable);
		 String[] excludes ={"roles"};
		 super.page2JsonObject(page, excludes);
		return NONE;
	}
	
	//查询所有的权限
	@Action(value="permissionAction_findAll")
	public String findAll() {
		List<Permission> list =  permissionService.findAll();
		 String[] excludes ={"roles"};
		 super.page2JsonArray(list, excludes);
		return NONE;
	}
	
	//添加权限
	@Action(value="permissionAction_add",results={@Result(name="success",location="/pages/system/permission.jsp",type="redirect")})
	public String add() {
		permissionService.save(model);
		return SUCCESS;
	}
}
