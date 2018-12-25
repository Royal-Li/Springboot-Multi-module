package com.lzs.sys.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lzs.sys.entity.SysUser;

@Repository
public interface SysUserRepository extends JpaRepository<SysUser, Integer>{

	SysUser findByName(String name);
	
}
