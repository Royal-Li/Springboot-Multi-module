
package com.lzs.sys.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * @class SysLog
 * @author Jason
 * @description
 * @date Dec 28, 2018 11:33:29 AM
 */
@Entity
@Table(name="sys_log")
@Data
public class SysLog {
	@Id
	@Column(name="id" ,unique=true,nullable=false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String url;
	private String class_method;
	private String method;
	private String ip;
	private String params;
	private Long operation_id;
	private String operation_date;
}
