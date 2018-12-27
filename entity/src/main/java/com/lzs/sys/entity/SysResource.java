package com.lzs.sys.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="sys_resource")
public class SysResource {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="resource_id")
	private Integer id;
	@Column(name = "resource_name")
	private String name;			//s目录名称
	private String url;				//s请求地址
	private String permission;		//s权限
	private String type;  			//s目录级别
	@Column(name = "parent_id",nullable= true)
	private Integer parentId;			//s父级目录
	private String description;		//s描述
	private String createDate;
	private String updateDate;
	/*@Transient  此注解表示该属性并非是一个要映射到数据库表中的字段,只是起辅助作用.ORM框架将会忽略该属性*/
	@ManyToMany(mappedBy = "resources")
	@JsonIgnoreProperties(value = { "resources" })
	private List<SysRole> roles = new ArrayList<>();

}
