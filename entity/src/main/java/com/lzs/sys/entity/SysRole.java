package com.lzs.sys.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="sys_role")
public class SysRole {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="role_id")
	private Integer id;
	@Column(name = "role_name")
	private String name;
	private String description;
	private String createDate;
	private String updateDate;

	@ManyToMany(mappedBy = "roles")
	@JsonIgnoreProperties(value = { "roles" })
	private List<SysUser> users = new ArrayList<>();
	
	@ManyToMany(cascade=CascadeType.PERSIST)
	@JoinTable(name = "sys_role_resource", joinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "role_id")},
	inverseJoinColumns = {@JoinColumn(name = "resource_id", referencedColumnName = "resource_id")})
	@JsonIgnoreProperties(value = { "roles" })
	private List<SysResource> resources ;
	
}
