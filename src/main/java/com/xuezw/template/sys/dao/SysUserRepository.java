package com.xuezw.template.sys.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xuezw.template.sys.entity.SysUser;

public interface SysUserRepository extends JpaRepository<SysUser, Long> {

	SysUser findByUsername(String username);
}
