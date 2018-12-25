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

import lombok.Data;

@Entity
@Data
@Table(name="sys_user")
public class SysUser {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="user_id")
	private int id;
	@Column(name = "user_name")
	private String name;
	private String email;
	private int mobile;
	private String password;
	private String remake;
	private String createDate;
	private String updateDate;
	
	@ManyToMany(cascade=CascadeType.PERSIST)
	@JoinTable(name = "sys_user_role",joinColumns = {@JoinColumn(name = "user_id",referencedColumnName = "user_id")},
	inverseJoinColumns = {@JoinColumn(name = "role_id",referencedColumnName = "role_id")})
	private List<SysRole> roles = new ArrayList<>();

}
