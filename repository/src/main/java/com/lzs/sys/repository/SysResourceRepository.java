package com.lzs.sys.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.lzs.sys.entity.SysResource;

public interface SysResourceRepository extends JpaRepository<SysResource, Integer> {

	@Query(value="SELECT * "
			+ "FROM sys_resource sre "
			+ "INNER JOIN sys_role_resource srr ON sre.resource_id = srr.resource_id "
			+ "INNER JOIN sys_role sro ON sro.role_id = srr.role_id "
			+ "INNER JOIN sys_user_role sur ON sur.role_id = sro.role_id "
			+ "INNER JOIN sys_user sus ON sus.user_id = sur.user_id "
			+ "AND sus.user_id =:id", nativeQuery=true)
	List<SysResource> findBySysUserId(@Param("id")Integer id);

}
