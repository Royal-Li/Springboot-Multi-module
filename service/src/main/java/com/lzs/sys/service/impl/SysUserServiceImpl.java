package com.lzs.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lzs.sys.entity.SysUser;
import com.lzs.sys.repository.SysUserRepository;
import com.lzs.sys.service.SysUserService;

/**
 * @class AdminServiceImpl
 * @author Jason
 * @description
 * @date Dec 17, 2018 3:58:07 PM
 */
@Service
@Transactional
public class SysUserServiceImpl implements SysUserService {

	@Autowired
	SysUserRepository sysUserRepository;
	

	@Override
	public Page<SysUser> getAllAdmin(String order, Integer offset, Integer pageSize) {
		
		int pageNum = offset/pageSize;
		Pageable pageable = new PageRequest(pageNum, pageSize, Sort.Direction.ASC,"id");
		
		return sysUserRepository.findAll(pageable);
	}


	@Override
	public void add(SysUser user1) {
		SysUser user = sysUserRepository.save(user1);
		
	}


	@Override
	public SysUser findByName(String name) {
		
		return sysUserRepository.findByName(name);
	}

}
