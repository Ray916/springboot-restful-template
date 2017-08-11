package com.xuezw.template.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xuezw.template.dao.HeroesRepository;
import com.xuezw.template.entity.Heroes;

@RestController
@RequestMapping("/heroes")
@PreAuthorize("hasAuthority('admin')")
public class HeroesController {
	
	@Autowired
	HeroesRepository heroRepository;
	
	@RequestMapping("/getall")
	public String getAllHeroes(Integer id){
		
		ObjectMapper objectMapper = new ObjectMapper();
		List<Heroes> list = heroRepository.findAll();
		String listJson = null;
		try {
			listJson =  objectMapper.writeValueAsString(list);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(listJson);
		return listJson;
	}
	
	@RequestMapping("/gethero")
	public Heroes getHero(Integer id){
		System.out.println("get的id为:" + id);
		Heroes h = heroRepository.findOne(id);
		System.out.println(h.getName() + "#####" + h.getNick());
		return h;
	}
	//模糊查询
	@RequestMapping(value="/getherobyname", method=RequestMethod.GET)
	public String getHeroByName(String name){
		List<Heroes> hl = heroRepository.withNameLikeQuery(name);
		ObjectMapper objectMapper = new ObjectMapper();
		String listJson = null;
		try {
			listJson = objectMapper.writeValueAsString(hl);
			System.out.println("list json is : " + listJson);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listJson;
	}
	
	@RequestMapping("/save")
	public Heroes save(Heroes h){
		Heroes hero = heroRepository.save(h);
		return hero;
	}
}
