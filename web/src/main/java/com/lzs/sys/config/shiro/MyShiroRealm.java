package com.lzs.sys.config.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.lzs.sys.entity.SysResource;
import com.lzs.sys.entity.SysRole;
import com.lzs.sys.entity.SysUser;
import com.lzs.sys.service.SysUserService;
import com.lzs.sys.util.MD5Utils;
import com.lzs.sys.util.PasswordUtils;

/**
 * 自定义shiroRealm
 * 
 * @class MyShiroRealm
 * @author Jason
 * @description
 * @date Dec 25, 2018 5:26:21 PM
 */

public class MyShiroRealm extends AuthorizingRealm {

	@Autowired
	private SysUserService sysUserService;

	// s后权限授权认证
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

		System.out.println("shiro后权限授权认执行了");
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();

		SysUser user = (SysUser) principals.getPrimaryPrincipal();

		// s这个地方从数据中查询用户，并且关联查询出用户对应的角色以及权限 ，实现权限的认证
		SysUser userWithRoleAndResource = sysUserService.findByName(user.getName());
		System.out.println(userWithRoleAndResource.getRoles().toString());
		for (SysRole role : userWithRoleAndResource.getRoles()) {
			if (null != role) {
				authorizationInfo.addRole(role.getName());
				for (SysResource p : role.getResources()) {
					if (null != p) {
						authorizationInfo.addStringPermission(p.getPermission());
					}
				}
			}
		}

		return authorizationInfo;
	}

	// s先登录认证
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		System.out.println("shiro后权登录认证执行了");
		// s获取用户的输入的用户名.
		String name = (String) token.getPrincipal();
		// s输入的密码
		String password = new String((char[]) token.getCredentials());
		// s通过username从数据库中查找 User对象
		SysUser user = sysUserService.findByName(name);
		if (user == null) {
			throw new UnknownAccountException("账户不存在");
		}
		// s验证密码
		if (!user.getPassword().equals(PasswordUtils.encodPassword(password))) {
			throw new IncorrectCredentialsException("密码不正确");
		}
		/*
		 * SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
		 * user, //用户名 user.getPassword(), //密码 getName() //realm name );
		 */
		/**
		 * 参数详解 第一个参数principal ：认证的实体信息。可以是username,也可以是用户的实体类对象
		 * 第二个参数hashedCredentials：是从数据库中获取的密码
		 * 第三个参数credentialsSalt：盐值，它的作用就是，即是两个人的原明文密码一样，可以通过这个值的控制，使加密后的密码不一样(一般可以使用username或者userid作为盐值)
		 * 第四个参数realmName: 当前 Realm 对象的 name调用父类的getName()方法即可
		 */
		// s第三个参数可以使用用户名作为盐
		// ByteSource credentialsSalt = ByteSource.Util.bytes(name);
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
				user, // s用户名
				user.getPassword(), // s密码
				null, 
				getName() // realm name
		);
		return authenticationInfo;
		// s验证不正确
	}

	public static void main(String[] args) {
		// s加密的方式
		String algorithmName = "MD5";
		// s需要加密的字符串
		Object source = "123456";

		// s盐值，它的作用就是，即是两个人的原明文密码一样，可以通过这个值的控制，使加密后的密码不一样
		Object salt = null;

		// s需要加密的次数
		int hashIterations = 1;

		Object result = new SimpleHash(algorithmName, source, salt, hashIterations);
		System.out.println(result.toString());
	}

}
