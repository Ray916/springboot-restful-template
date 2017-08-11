package com.xuezw.template.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="heroesinfo")
public class Heroes {

	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="nick")
	private String nick;
	
	@Column(name="star")
	private String star;
	
	@Column(name="pos")
	private String pos;
	
	@Column(name="weapon")
	private String weapon;
	
	@Column(name="skill")
	private String skill;
	
	@Column(name="skillExt")
	private String skillExt;
	
	@Column(name="attack")
	private Integer attack;
	
	@Column(name="attackArea")
	private Integer attackArea;
	
	@Column(name="defence")
	private Integer defence;
	
	@Column(name="detail")
	private String detail;
	
	public Heroes(){
		super();
	}

	public Heroes(int id, String name, String nick, String star, String pos, String weapon, String skill,
			String skillExt, int attack, int attackArea, int defence, String detail) {
		super();
		this.id = id;
		this.name = name;
		this.nick = nick;
		this.star = star;
		this.pos = pos;
		this.weapon = weapon;
		this.skill = skill;
		this.skillExt = skillExt;
		this.attack = attack;
		this.attackArea = attackArea;
		this.defence = defence;
		this.detail = detail;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getStar() {
		return star;
	}

	public void setStar(String star) {
		this.star = star;
	}

	public String getPos() {
		return pos;
	}

	public void setPos(String pos) {
		this.pos = pos;
	}

	public String getWeapon() {
		return weapon;
	}

	public void setWeapon(String weapon) {
		this.weapon = weapon;
	}

	public String getSkill() {
		return skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
	}

	public String getSkillExt() {
		return skillExt;
	}

	public void setSkillExt(String skillExt) {
		this.skillExt = skillExt;
	}

	public Integer getAttack() {
		return attack;
	}

	public void setAttack(Integer attack) {
		this.attack = attack;
	}

	public Integer getAttackArea() {
		return attackArea;
	}

	public void setAttackArea(Integer attackArea) {
		this.attackArea = attackArea;
	}

	public Integer getDefence() {
		return defence;
	}

	public void setDefence(Integer defence) {
		this.defence = defence;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}	
	
}
