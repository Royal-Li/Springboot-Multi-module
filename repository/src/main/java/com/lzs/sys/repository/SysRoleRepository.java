package com.lzs.sys.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lzs.sys.entity.SysRole;
@Repository
public interface SysRoleRepository extends JpaRepository<SysRole, Integer> {

}
