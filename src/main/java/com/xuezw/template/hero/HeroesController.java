package com.xuezw.template.hero;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/heroes")
@PreAuthorize("hasAuthority('USER')")
public class HeroesController {
	
	@Autowired
	HeroesRepository heroRepository;
	
	private final static Logger logger = Logger.getLogger(HeroesController.class);
	
	@RequestMapping("/getall")
	public String getAllHeroes(Integer id){
		
		logger.info("get请求获取所有的heroes.");
		ObjectMapper objectMapper = new ObjectMapper();
		List<Heroes> list = heroRepository.findAll();
		String listJson = null;
		try {
			listJson =  objectMapper.writeValueAsString(list);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listJson;
	}
	
	@RequestMapping("/gethero")
	public Heroes getHero(Integer id){
		
		logger.info("get请求根据id=" + id + "获取英雄。");
		Heroes h = heroRepository.findOne(id);
		return h;
	}
	//模糊查询
	@RequestMapping(value="/getherobyname", method=RequestMethod.GET)
	public String getHeroByName(String name){
		
		logger.info("get请求根据name模糊查询英雄，name=" + name);
		List<Heroes> hl = heroRepository.withNameLikeQuery(name);
		ObjectMapper objectMapper = new ObjectMapper();
		String listJson = null;
		try {
			listJson = objectMapper.writeValueAsString(hl);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listJson;
	}
}
