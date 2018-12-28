package com.lzs.sys.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lzs.sys.entity.SysLog;

@Repository
public interface SysLogRepository extends JpaRepository<SysLog, Long> {

}
