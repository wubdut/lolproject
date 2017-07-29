package com.controller;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.google.gson.Gson;
import com.dao.AuthorityDao;
import com.dao.DepartmentDao;
import com.dao.RegisterDao;
import com.dao.TeamDao;
import com.dao.UserDao;
import com.models.Authority;
import com.models.Department;
import com.models.PostTeamCreate;
import com.models.Register;
import com.models.Team;
import com.models.User;

/**
 * Class TeamController
 */
@Controller
public class TeamController {

	// ------------------------
	// PUBLIC METHODS
	// ------------------------

	/**
	 * Create a new user with an auto-generated id and email and name as passed
	 * values.
	 */
	@RequestMapping(value = "/createTeam")
	@ResponseBody
	public String create(@RequestBody PostTeamCreate postTeamCreate) {
		
		if (userDao.contains(postTeamCreate.getRegister_name()) != 0)
			return "您已经创建了战队";
	
		Register register = registerDao.getByUsername(postTeamCreate.getRegister_name());
		Department department = departmentDao.getById(postTeamCreate.getDepartment_id());
		
		Team team = new Team("" + ((int)(Math.random()*4)), postTeamCreate.getName(),department, register);
		
		teamDao.create(team);
		userDao.create(new User(register, team, authorityDao.getById(1)));
		
		try {
//			teamDao.create(team);
		} catch (Exception ex) {
			return "Error creating the user: " + ex.toString();
		}
		return "创建成功";
	}

	/**
	 * Delete the user with the passed id.
	 */
	@RequestMapping(value = "/deleteTeam")
	@ResponseBody
	public String delete(@RequestBody Team team) {
		try {
			teamDao.delete(team);
		} catch (Exception ex) {
			return "Error deleting the user: " + ex.toString();
		}
		return "User succesfully deleted!";
	}

	/**
	 * Update the email and the name for the user indentified by the passed id.
	 */
	@RequestMapping(value = "/updateTeam")
	@ResponseBody
	public String updateTeam(@RequestBody Team team) {
		try {
			teamDao.update(team);
		} catch (Exception ex) {
			return "Error updating the user: " + ex.toString();
		}
		return "User succesfully updated!";
	}
	
	@RequestMapping(value = "/getAllTeam")
	@ResponseBody
	public List<Team> queryAllTeam() {
		List<Team> teamList;
		try {
			teamList = teamDao.getAll();
		} catch (Exception ex) {
			return null;
		}
		System.out.println("TeamList : " + teamList.size());
		return teamList;
	}

	// ------------------------
	// PRIVATE FIELDS
	// ------------------------

	// Wire the UserDao used inside this controller.
	@Autowired
	private TeamDao teamDao;
	
	@Autowired
	private RegisterDao registerDao;
	
	@Autowired
	private DepartmentDao departmentDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private AuthorityDao authorityDao;

} // class TeamController
