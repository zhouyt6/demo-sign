package cn.itcast.bos.realm;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.digester.RegexRules;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAccount;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import cn.itcast.bos.domain.base.Permission;
import cn.itcast.bos.domain.base.Role;
import cn.itcast.bos.domain.base.User;
import cn.itcast.bos.service.IPermissionService;
import cn.itcast.bos.service.IRoleService;
import cn.itcast.bos.service.IUserService;

public class BosLoginRealm	extends AuthorizingRealm {
	
	@Resource
	private IUserService userService;
	@Resource
	private IPermissionService permissionService;
	@Resource
	private IRoleService roleService;
	//授权方法
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		//给当前登录用户授权
		//1.获取当前用户
		User user = (User) principalCollection.getPrimaryPrincipal();
		//2.根据用户查询用户的角色和权限
		List<Role> roleList = roleService.findRolesByUser(user);
		List<Permission> permissionList = permissionService.findPermissionsByUser(user);
		//3.将角色和权限的关键字放到授权对象
		//使用AuthorizationInfo授权当前用户权限,返回给安全管理器
		SimpleAuthorizationInfo sai = new SimpleAuthorizationInfo();
		//为当前用户授权,以后查询数据库获得用户的对应权限
		if (permissionList != null && permissionList.size() > 0) {
			//查询到该用户的权限
			for (Permission permission : permissionList) {
				sai.addStringPermission(permission.getKeyword());
			}
		}
		if (null != roleList && roleList.size() > 0) {
			//查询到该用户的角色
			for (Role role : roleList) {
				sai.addRole(role.getKeyword());
			}
		}
//		sai.addStringPermission("courier");
		return sai;
	}

	//认证方法
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) 
			throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
		String username = token.getUsername();
		//根据页面提供的用户名查询数据库中的密码,将查询到的密码提供给shiro框架(认证器对象),由认证器进行比较
		User user =	userService.findByUsername(username);
		if (null == user) {
			//查询不到用户对象,直接返回null.安全管理器会认为账号不存在,直接抛异常
			return null;
		}
		//认证信息对象
		//参数1:可以是任意数据,该数据可以在项目中任意位置获取到,一般放用户对象
		//参数2:必须存放从数据中查询的密码
		//参数3:当期的realm的名称
		return new SimpleAuthenticationInfo(user, user.getPassword(), this.getName());
	}
}
