package com.lzs.sys.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.lzs.sys.entity.Admin;

public interface AdminService {

	Page<Admin> getAllAdmin(String order, Integer offset, Integer pageSize);

}
