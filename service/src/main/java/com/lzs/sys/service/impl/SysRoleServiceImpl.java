package com.lzs.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lzs.sys.entity.SysRole;
import com.lzs.sys.repository.SysRoleRepository;
import com.lzs.sys.service.SysRoleService;
@Service
@Transactional
public class SysRoleServiceImpl implements SysRoleService {

	@Autowired
	SysRoleRepository sysRoleRepository;
	
	@Override
	public void add(SysRole role1) {
		
		sysRoleRepository.save(role1);
	}

}
