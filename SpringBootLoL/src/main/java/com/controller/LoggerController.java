package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dao.LoggerDao;
import com.dao.UserDao;
import com.models.Logger;
import com.models.User;

/**
 * Class LoggerController
 */
@Controller
public class LoggerController {

	  // ------------------------
	  // PUBLIC METHODS
	  // ------------------------

	  /**
	   * Create a new user with an auto-generated id and email and name as passed 
	   * values.
	   */
	  @RequestMapping(value="/createLogger")
	  @ResponseBody
	  public String create(@RequestBody Logger logger) {
	    try {
	      loggerDao.create(logger);
	    }
	    catch (Exception ex) {
	      return "Error creating the user: " + ex.toString();
	    }
	    return "User succesfully created!";
	  }
	  
	  /**
	   * Delete the user with the passed id.
	   */
	  @RequestMapping(value="/deleteLogger")
	  @ResponseBody
	  public String delete(@RequestBody Logger logger) {
	    try {
	      loggerDao.delete(logger);
	    }
	    catch (Exception ex) {
	      return "Error deleting the user: " + ex.toString();
	    }
	    return "User succesfully deleted!";
	  }
	  
	  
	  /**
	   * Update the email and the name for the user indentified by the passed id.
	   */
	  @RequestMapping(value="/updateLogger")
	  @ResponseBody
	  public String updateName(@RequestBody Logger logger) {
	    try {
	      loggerDao.update(logger);
	    }
	    catch (Exception ex) {
	      return "Error updating the user: " + ex.toString();
	    }
	    return "User succesfully updated!";
	  } 

	  // ------------------------
	  // PRIVATE FIELDS
	  // ------------------------
	  
	  // Wire the UserDao used inside this controller.
	  @Autowired
	  private LoggerDao loggerDao;
	  
	} // class LoggerController

