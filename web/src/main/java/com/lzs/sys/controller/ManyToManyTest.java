package com.lzs.sys.controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.lzs.sys.entity.SysRole;
import com.lzs.sys.entity.SysUser;
import com.lzs.sys.repository.SysRoleRepository;
import com.lzs.sys.repository.SysUserRepository;

public class ManyToManyTest {
	
	@Autowired
	SysUserRepository sysUserRepository;
	
	@Autowired
	SysRoleRepository sysRoleRepository;
	
	public static void save() {
		SysUser user1 = new SysUser();
		user1.setName("user1");
		SysUser user2 = new SysUser();
		user2.setName("user2");
		SysUser user3 = new SysUser();
		user3.setName("user3");
		SysUser user4 = new SysUser();
		user4.setName("user4");
		
		SysRole role1 = new SysRole();
		role1.setName("role1");
		SysRole role2 = new SysRole();
		role2.setName("role2");
		SysRole role3 = new SysRole();
		role3.setName("role3");
		SysRole role4 = new SysRole();
		role4.setName("role4");
		
		
		user1.getRoles().add(role1);
		user1.getRoles().add(role2);
		
	}
	
	public static void main(String[] args) {
		save();
	}

}
