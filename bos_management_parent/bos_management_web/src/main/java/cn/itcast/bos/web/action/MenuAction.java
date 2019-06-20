package cn.itcast.bos.web.action;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
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
import cn.itcast.bos.service.IMenuService;

@ParentPackage("struts-default")
@Namespace("/")
@Actions
@Controller
@Scope("prototype")
public class MenuAction extends BaseAction<Menu> {
	
	@Resource
	private IMenuService menuService;
	private Object principal;
	
	//分页
	@Action(value="menuAction_pageQuery")
	public String pageQuery() {
		page = Integer.parseInt(model.getPage());
 		Pageable pageable = new PageRequest(page-1, rows);
		Specification<Menu> spec = new Specification<Menu>() {
			@Override
			public Predicate toPredicate(Root<Menu> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				//只查询父节点是null的根节点数据
				return cb.isNull(root.get("parentMenu").as(Menu.class));
			}
		};
		//执行分页
		 Page<Menu> page = menuService.pageQuery(spec,pageable);
		 String[] excludes ={"childrenMenus","roles","parentMenu"};
		 super.page2JsonObject(page, excludes);
		return NONE;
	}
	
	@Action(value="menuAction_findAll")
	public String findAll() {
		List<Menu> list =  menuService.findByParentMenuIsNull();
		 String[] excludes ={"childrenMenus","roles","parentMenu"};
		 super.page2JsonArray(list, excludes);
		return NONE;
	}
	
	@Action(value="menuAction_add",results={@Result(name="success",location="/pages/system/menu.jsp",type="redirect")})
	public String add() {
		menuService.save(model);
		return SUCCESS;
	}
	//动态显示不同用户得菜单数据
	@Action(value="menuAction_showMenus")
	public String showMenus() {
		//根据用户查询该用户的菜单
		List<Menu> list = menuService.findMenus();
		String[] excludes = {"childrenMenus","roles","parentMenu","children"};
		//2.将菜单集合转成json数组,返回到界面
		this.page2JsonArray(list, excludes );
		return NONE;
	}
}
