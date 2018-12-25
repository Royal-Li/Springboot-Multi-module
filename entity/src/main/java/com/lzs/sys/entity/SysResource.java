package com.lzs.sys.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="sys_resource")
public class SysResource {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="resource_id")
	private int id;
	@Column(name = "resource_name")
	private String name;			//s目录名称
	private String url;				//s请求地址
	private String permission;		//s权限
	private String type;  			//s目录级别
	private int parentId;			//s父级目录
	private String description;		//s描述
	private String createDate;
	private String updateDate;
	
	@ManyToMany(mappedBy = "resources")
	private List<SysRole> roles = new ArrayList<>();

}
