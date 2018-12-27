package com.lzs.sys.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import com.lzs.sys.entity.SysResource;
import com.lzs.sys.entity.SysRole;
import com.lzs.sys.entity.SysUser;
import com.lzs.sys.repository.SysResourceRepository;
import com.lzs.sys.service.SysResourceService;

@Service
public class SysResourceServiceImpl implements SysResourceService {

	@Autowired
	SysResourceRepository sysResourceRepository;
	/*@Autowired
	SysResource sysResource;*/
	
	@Override
	public List<SysResource> findBySysUserId(Integer id) {
		
		return sysResourceRepository.findBySysUserId(id);
	}

}
