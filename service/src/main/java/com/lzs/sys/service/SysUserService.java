package com.lzs.sys.service;

import org.springframework.data.domain.Page;

import com.lzs.sys.entity.SysUser;

public interface SysUserService {

	Page<SysUser> getAllAdmin(SysUser user, String order, Integer offset, Integer pageSize);

	void add(SysUser user1);

	SysUser findByName(String name);

}
