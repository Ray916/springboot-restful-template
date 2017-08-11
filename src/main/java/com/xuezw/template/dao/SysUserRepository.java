package com.xuezw.template.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xuezw.template.entity.SysUser;

public interface SysUserRepository extends JpaRepository<SysUser, Long> {

	SysUser findByUsername(String username);
}
