package com.lzs.sys.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.lzs.sys.entity.SysUser;

public interface SysUserService {

	Page<SysUser> getAllUser(SysUser user, PageRequest pageRequest);

	void add(SysUser user1);

	SysUser findByName(String name);

	

}
