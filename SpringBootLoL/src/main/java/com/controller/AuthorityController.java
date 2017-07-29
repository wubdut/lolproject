package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dao.AuthorityDao;
import com.models.Authority;


/**
 * Class AuthorityController
 */
@Controller
public class AuthorityController {

  // ------------------------
  // PUBLIC METHODS
  // ------------------------

  /**
   * Create a new user with an auto-generated id and email and name as passed 
   * values.
   */
  @RequestMapping(value="/createAuthority")
  @ResponseBody
  public String create(@RequestBody Authority authority) {
    try {
      authorityDao.create(authority);
    }
    catch (Exception ex) {
      return "Error creating the user: " + ex.toString();
    }
    return "User succesfully created!";
  }
  
  /**
   * Delete the user with the passed id.
   */
  @RequestMapping(value="/deleteAuthority")
  @ResponseBody
  public String delete(@RequestBody long id) {
    try {
      Authority authority = new Authority(id);
      authorityDao.delete(authority);
    }
    catch (Exception ex) {
      return "Error deleting the user: " + ex.toString();
    }
    return "User succesfully deleted!";
  }
  
  
  /**
   * Update the email and the name for the user indentified by the passed id.
   */
  @RequestMapping(value="/updateAuthority")
  @ResponseBody
  public String updateName(@RequestBody Authority authority) {
    try {
      authorityDao.update(authority);
    }
    catch (Exception ex) {
      return "Error updating the user: " + ex.toString();
    }
    return "User succesfully updated!";
  } 

  // ------------------------
  // PRIVATE FIELDS
  // ------------------------
  
  // Wire the AuthorityDao used inside this controller.
  @Autowired
  private AuthorityDao authorityDao;
  
} // class AuthorityController
