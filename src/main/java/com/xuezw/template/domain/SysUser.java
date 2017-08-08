package com.xuezw.template.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="sys_user")
public class SysUser{

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name="username")
	private String username;
	
	@Column(name="password")
	private String password;
	
	@Column(name="email")
	private String email;
	
	@Column(name="last_password_reset_date")
	private Date lastPasswordResetDate;

	//@ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name="sys_user_roles", joinColumns={@JoinColumn(name="sys_user_id", referencedColumnName="id")}, 
	inverseJoinColumns={@JoinColumn(name="roles_id", referencedColumnName="id")})
	private List<SysRole> roles;

	@JsonIgnore
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<SysRole> getRoles() {
		return roles;
	}

	public void setRoles(List<SysRole> roles) {
		this.roles = roles;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@JsonIgnore
	public String getPassword() {
		return password;
	}

	public String getUsername() {
		return username;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@JsonIgnore
	public Date getLastPasswordResetDate() {
		return lastPasswordResetDate;
	}

	public void setLastPasswordResetDate(Date lastPasswordResetDate) {
		this.lastPasswordResetDate = lastPasswordResetDate;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		sb.append("username: " + username + " password: " + password);
		return sb.toString();
	}

}
