package com.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.google.gson.Gson;
import com.models.Authority;
import com.models.Play;
import com.models.Register;
import com.models.Team;
import com.models.User;
import com.dao.AuthorityDao;
import com.dao.DepartmentDao;
import com.dao.PlayDao;
import com.dao.RegisterDao;
import com.dao.TeamDao;
import com.dao.UserDao;

/**
 * Class PlayController
 */
@Controller
public class PlayController {

	// ------------------------
	// PUBLIC METHODS
	// ------------------------

	/**
	 * Create a new user with an auto-generated id and email and name as passed
	 * values.
	 */
	@RequestMapping(value = "/createPlay")
	@ResponseBody
	public List<Play> create() {
		System.out.println("创建比赛安排");
		
		List<ArrayList<Team>> groupList = new ArrayList<ArrayList<Team>>();
		
		for (int i = 0; i < 4; i++) {
			groupList.add(new ArrayList<Team>());
		}
		
		for (Team team : teamDao.getAll()) {
			System.out.println("team.name" + team.getName());
			int groupId = Integer.parseInt(team.getGroup_id());
			groupList.get(groupId).add(team);
		}
		
		for (Play play : playDao.getAll()) {
			playDao.delete(play);
		}
		
		for (int i = 0; i < groupList.size(); i++) {
			List<Team> teamList = groupList.get(i);
			for (int j = 0; j < teamList.size(); j++) {
				for (int k = j+1; k < teamList.size(); k++) {
					playDao.create(new Play(""+i, teamList.get(j), teamList.get(k), "2017/8/"+((int)(Math.random()*30+1)), "no"));
				}
			}
		}
			
		return playDao.getAll();
	}
	
	@RequestMapping(value="/getMatchTable/{name}&{date}")
	  @ResponseBody
	  public List<Play> getAddPlayer(@PathVariable String name, @PathVariable String date) {
		return playDao.getByNameAndDate(name, date);
	  }

	// ------------------------
	// PRIVATE FIELDS
	// ------------------------

	// Wire the UserDao used inside this controller.
	@Autowired
	private PlayDao playDao;
	
	@Autowired
	private TeamDao teamDao;

} // class PlayController
