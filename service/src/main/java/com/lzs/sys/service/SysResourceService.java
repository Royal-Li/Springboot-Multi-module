package com.lzs.sys.service;

import java.util.List;

import com.lzs.sys.entity.SysResource;

public interface SysResourceService {

	List<SysResource> findBySysUserId(Integer id);

}
