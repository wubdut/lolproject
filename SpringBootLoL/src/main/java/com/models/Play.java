package com.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "play")
public class Play {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@NotNull
	private String group_id;

	@OneToOne
	private Team team1;
	
	@OneToOne
	private Team team2;
	
	@NotNull
	private String date;
	
	@NotNull
	private String result;

	
	public Play() {}
	
	

	public Play(long id) {
//		super();
		this.id = id;
	}



	public Play(String group_id, Team team1, Team team2, String date, String result) {
//		super();
		this.group_id = group_id;
		this.team1 = team1;
		this.team2 = team2;
		this.date = date;
		this.result = result;
	}



	public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}



	public String getGroup_id() {
		return group_id;
	}



	public void setGroup_id(String group_id) {
		this.group_id = group_id;
	}



	public Team getTeam1() {
		return team1;
	}



	public void setTeam1(Team team1) {
		this.team1 = team1;
	}



	public Team getTeam2() {
		return team2;
	}



	public void setTeam2(Team team2) {
		this.team2 = team2;
	}



	public String getDate() {
		return date;
	}



	public void setDate(String date) {
		this.date = date;
	}



	public String getResult() {
		return result;
	}



	public void setResult(String result) {
		this.result = result;
	}

	
	
}
