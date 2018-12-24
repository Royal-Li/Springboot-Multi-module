package com.lzs.sys.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lzs.sys.entity.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer>{
	
}
