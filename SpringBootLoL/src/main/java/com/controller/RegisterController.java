package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dao.RegisterDao;
import com.models.Register;

/**
 * Class RegisterController
 */

@Controller
public class RegisterController {

	  // ------------------------
	  // PUBLIC METHODS
	  // ------------------------

	  /**
	   * Create a new user with an auto-generated id and email and name as passed 
	   * values.
	   */
	  @RequestMapping(value="/createRegister")
	  @ResponseBody
	  public String create(@RequestBody Register register) {
	    try {
	      registerDao.create(register);
	    }
	    catch (Exception ex) {
	      return "Error creating the user: " + ex.toString();
	    }
	    return "User succesfully created!";
	  }
	  
	  /**
	   * Delete the user with the passed id.
	   */
	  @RequestMapping(value="/deleteRegister")
	  @ResponseBody
	  public String delete(@RequestBody Register register) {
	    try {
	      registerDao.delete(register);
	    }
	    catch (Exception ex) {
	      return "Error deleting the user: " + ex.toString();
	    }
	    return "User succesfully deleted!";
	  }
	  
	  
	  /**
	   * Update the email and the name for the user indentified by the passed id.
	   */
	  @RequestMapping(value="/updateRegister")
	  @ResponseBody
	  public String updateName(@RequestBody Register register) {
	    try {
	      registerDao.update(register);
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
	  private RegisterDao registerDao;
	  
	} // class RegisterController

