package com.sys.test;

import static org.junit.Assert.fail;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import com.lzs.sys.Application;
import com.lzs.sys.entity.SysRole;
import com.lzs.sys.entity.SysUser;
import com.lzs.sys.service.SysRoleService;
import com.lzs.sys.service.SysUserService;

@SpringBootTest(classes = Application.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class UserTest {
	
	@Resource
	SysUserService sysUserService;
	@Resource
	SysRoleService sysRoleService;

	@Before
	public void setUp() throws Exception {
		System.out.println("before测试");
	}

	@Test
	public void test1() {
		//fail("Not yet implemented");
		System.out.println("fuck you");
		
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
		role3.getUsers().add(user1);
		role3.getUsers().add(user2);
		
		sysUserService.add(user1);
		//sysRoleService.add(role1);
	}

}
