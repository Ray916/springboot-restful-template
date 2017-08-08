package com.xuezw.template.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.xuezw.template.domain.Heroes;

public interface HeroesRepository extends JpaRepository<Heroes, Integer> {

	@Query("select h from Heroes h where name like %?1%")
	List<Heroes> withNameLikeQuery(String name);
}
