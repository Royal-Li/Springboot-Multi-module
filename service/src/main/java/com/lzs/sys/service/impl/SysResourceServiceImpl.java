package com.lzs.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.lzs.sys.entity.SysResource;
import com.lzs.sys.repository.SysResourceRepository;
import com.lzs.sys.service.SysResourceService;

@Service
@CacheConfig(cacheNames= {"resourceCache"})
public class SysResourceServiceImpl implements SysResourceService {

	@Autowired
	SysResourceRepository sysResourceRepository;
	/*@Autowired
	SysResource sysResource;*/
	
	@Override
	@Cacheable(key="methodName +#p0")
	public List<SysResource> findBySysUserId(Integer id) {
		
		return sysResourceRepository.findBySysUserId(id);
	}

}
