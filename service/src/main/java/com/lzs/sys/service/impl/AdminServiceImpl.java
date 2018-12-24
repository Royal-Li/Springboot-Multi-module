package com.lzs.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lzs.sys.entity.Admin;
import com.lzs.sys.repository.AdminRepository;
import com.lzs.sys.service.AdminService;

/**
 * @class AdminServiceImpl
 * @author Jason
 * @description
 * @date Dec 17, 2018 3:58:07 PM
 */
@Service
@Transactional
public class AdminServiceImpl implements AdminService {

	@Autowired
	AdminRepository adminRepository;
	

	@Override
	public Page<Admin> getAllAdmin(String order, Integer offset, Integer pageSize) {
		
		int pageNum = offset/pageSize;
		Pageable pageable = new PageRequest(pageNum, pageSize, Sort.Direction.ASC,"id");
		
		return adminRepository.findAll(pageable);
	}

}
