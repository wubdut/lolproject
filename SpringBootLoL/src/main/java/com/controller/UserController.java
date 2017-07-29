package com.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dao.RegisterDao;
import com.dao.TeamDao;
import com.dao.UserDao;
import com.models.Authority;
import com.models.Register;
import com.models.TeamId;
import com.models.User;

/**
 * Class UserController
 */
@Controller
public class UserController {

  // ------------------------
  // PUBLIC METHODS
  // ------------------------

  /**
   * Create a new user with an auto-generated id and email and name as passed 
   * values.
   */
  @RequestMapping(value="/createUser")
  @ResponseBody
  public String create(String email, String name) {
    try {
      User user = new User();
      userDao.create(user);
    }
    catch (Exception ex) {
      return "Error creating the user: " + ex.toString();
    }
    return "User succesfully created!";
  }
  
  /**
   * Delete the user with the passed id.
   */
  @RequestMapping(value="/deleteUser")
  @ResponseBody
  public String delete(long id) {
    try {
      User user = new User(id);
      userDao.delete(user);
    }
    catch (Exception ex) {
      return "Error deleting the user: " + ex.toString();
    }
    return "User succesfully deleted!";
  }
  
  /**
   * Update the email and the name for the user indentified by the passed id.
   */
  @RequestMapping(value="/updateUser")
  @ResponseBody
  public String updateName(long id, String email, String name) {
    try {
      User user = userDao.getById(id);
//      user.setEmail(email);
//      user.setName(name);
      userDao.update(user);
    }
    catch (Exception ex) {
      return "Error updating the user: " + ex.toString();
    }
    return "User succesfully updated!";
  } 
  
  @RequestMapping(value="/getTeamUser/{teamId}")
  @ResponseBody
  public List<User> getTeamUser(@PathVariable String teamId) {
	List<User> res = new ArrayList<User>();
	System.out.println(teamId);
    try {
       res = userDao.getByTeamId(Integer.parseInt(teamId));
       System.out.println(res.size());
    }
    catch (Exception ex) {
      return null;
    }
    return res;
  }
  
  @RequestMapping(value="/captainEdit/{userId}")
  @ResponseBody
  public List<User> captainEdit(@PathVariable String userId) {
	List<User> res = new ArrayList<User>();
	System.out.println(userId);
    try {
       User user = userDao.getById(Integer.parseInt(userId));
       userDao.update(user);
       res = userDao.getByTeamId(user.getTeam().getId());
       System.out.println(res.size());
    }
    catch (Exception ex) {
      return null;
    }
    return res;
  }
  
  @RequestMapping(value="/captainDelete/{userId}")
  @ResponseBody
  public List<User> captainDelete(@PathVariable String userId) {
	List<User> res = new ArrayList<User>();
	System.out.println(userId);
    try {
       User user = userDao.getById(Integer.parseInt(userId));
       userDao.delete(user);
       res = userDao.getByTeamId(user.getTeam().getId());
       System.out.println(res.size());
    }
    catch (Exception ex) {
      return null;
    }
    return res;
  }
  
  @RequestMapping(value="/getMyTeam/{register_name}")
  @ResponseBody
  public List<User> getMyTeam(@PathVariable String register_name) {
	List<User> res = new ArrayList<User>();
	System.out.println(register_name);
	
	Register register = registerDao.getByUsername(register_name);
	
	User user = userDao.getByRegister(register);
	
    try {
       res = userDao.getByTeamId(user.getTeam().getId());
       System.out.println(res.size());
    }
    catch (Exception ex) {
      return null;
    }
    return res;
  }
  
  @RequestMapping(value="/getAllUser")
  @ResponseBody
  public List<User> getAllUser() {
	List<User> res = new ArrayList<User>();
	
    try {
       res = userDao.getAll();
       System.out.println(res.size());
    }
    catch (Exception ex) {
      return null;
    }
    return res;
  }

  @RequestMapping(value="/getCheckName/{name}")
  @ResponseBody
  public List<User> getCheckName(@PathVariable String name) {
	  
	List<User> res = userDao.getByName(name);
    return res;
  }
  
  @RequestMapping(value="/getCheckDepa/{department_id}")
  @ResponseBody
  public List<User> getCheckDepa(@PathVariable String department_id) {
	List<User> res = new ArrayList<User>();
	long id = Integer.parseInt(department_id);
	System.out.println("user.size: " + userDao.getAll().size());
	for (User user : userDao.getAll()) {
		System.out.println("id: " + user.getTeam().getDepartment().getId());
		if (user.getTeam().getDepartment().getId() == id) {
			res.add(user);
		}
	}
    return res;
  }
  
  @RequestMapping(value="/getCheckDepaAndName/{department_id}&{name}")
  @ResponseBody
  public List<User> getCheckDepaAndName(@PathVariable String department_id, @PathVariable String name) {
	List<User> res = new ArrayList<User>();
	
	long id = Integer.parseInt(department_id);
	
	for (User user : userDao.getByName(name)) {
		if (user.getTeam().getDepartment().getId() == id) {
			res.add(user);
		}
	}
    return res;
  }
  
  @RequestMapping(value="/getAddTable")
  @ResponseBody
  public List<Register> getAddTable() {
	List<Register> res = new ArrayList<Register>();
	
	Set<Long> set = new HashSet<Long>();
	for (User user : userDao.getAll()) {
		set.add(user.getRegister().getId());
	}
	
	System.out.println("set.seze : " + set.size());
	
	for (Register register : registerDao.getAll()) {
		if (!set.contains(register.getId()))
			res.add(register);
	}
	
	System.out.println("res.seze : " + res.size());
	
    return res;
  }
  
  @RequestMapping(value="/getAddPlayer/{name}&{id}")
  @ResponseBody
  public List<Register> getAddPlayer(@PathVariable String name, @PathVariable String id) {
	List<Register> res = new ArrayList<Register>();
	
	User captain = userDao.getByUserName(name);
	
	Register register = registerDao.getById(Integer.parseInt(id));
	
	userDao.create(new User(register, captain.getTeam(), new Authority(2)));
	Set<Long> set = new HashSet<Long>();
	
	for (User user : userDao.getAll()) {
		set.add(user.getRegister().getId());
	}
	for (Register regi : registerDao.getAll()) {
		if (!set.contains(regi.getId()))
			res.add(regi);
	}
	
    return res;
  }
  // --------------
  // ------------------------
  // PRIVATE FIELDS
  // ------------------------
  
  // Wire the UserDao used inside this controller.
  @Autowired
  private UserDao userDao;
  
  @Autowired
  private TeamDao teamDao;
  @Autowired
  private RegisterDao registerDao;
} // class UserController
