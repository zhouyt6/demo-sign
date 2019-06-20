package cn.itcast.bos.domain.base;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.xmlbeans.impl.regex.REUtil;

/**
 * @description:菜单
 */
@Entity
@Table(name = "T_MENU")
public class Menu implements Serializable{
	@Id
	@GeneratedValue
	@Column(name = "C_ID")
	private Integer id;
	@Column(name = "C_NAME")
	private String name; // 菜单名称
	@Column(name = "C_PAGE")
	private String page; // 访问路径
	@Column(name = "C_PRIORITY")
	private Integer priority; // 优先级
	@Column(name = "C_DESCRIPTION")
	private String description; // 描述

	@ManyToMany(mappedBy = "menus")
	private Set<Role> roles = new HashSet<Role>(0);

	@OneToMany(mappedBy = "parentMenu",fetch=FetchType.EAGER) //立即加载这个集合
	private Set<Menu> childrenMenus = new HashSet<Menu>();

	@ManyToOne
	@JoinColumn(name = "C_PID")
	private Menu parentMenu;
	
	public Integer getpId() {
		if (null != parentMenu) {
			//有父菜单
			return parentMenu.getId();
		}
		//没有父菜单
		return 0;
	}
	
	public Set<Menu> getChildren() {
		return childrenMenus;
	}
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	
	public String getText() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Set<Menu> getChildrenMenus() {
		return childrenMenus;
	}

	public void setChildrenMenus(Set<Menu> childrenMenus) {
		this.childrenMenus = childrenMenus;
	}

	public Menu getParentMenu() {
		return parentMenu;
	}

	public void setParentMenu(Menu parentMenu) {
		this.parentMenu = parentMenu;
	}

}
