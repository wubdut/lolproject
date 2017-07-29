package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dao.DepartmentDao;
import com.dao.UserDao;
import com.models.Department;
import com.models.User;

/**
 * Class DepartmentController
 */
@Controller
public class DepartmentController {

  // ------------------------
  // PUBLIC METHODS
  // ------------------------

  /**
   * Create a new user with an auto-generated id and email and name as passed 
   * values.
   */
  @RequestMapping(value="/createDepartment")
  @ResponseBody
  public String create(@RequestBody Department department) {
    try {
      departmentDao.create(department);
    }
    catch (Exception ex) {
      return "Error creating the user: " + ex.toString();
    }
    return "User succesfully created!";
  }
  
  /**
   * Delete the user with the passed id.
   */
  @RequestMapping(value="/deleteDepartment")
  @ResponseBody
  public String delete(@RequestBody Department department) {
    try {
      departmentDao.delete(department);
    }
    catch (Exception ex) {
      return "Error deleting the user: " + ex.toString();
    }
    return "User succesfully deleted!";
  }
  
  /**
   * Update the email and the name for the user indentified by the passed id.
   */
  @RequestMapping(value="/updateDepartment")
  @ResponseBody
  public String updateName(@RequestBody Department department) {
    try {
      departmentDao.update(department);
    }
    catch (Exception ex) {
      return "Error updating the user: " + ex.toString();
    }
    return "User succesfully updated!";
  }
  
  @RequestMapping(value="/getAllDepartment")
  @ResponseBody
  public List<Department> getAll() {
	  List<Department> res;
    try {
      res = departmentDao.getAll();
      System.out.println("department: " + res.size());
    }
    catch (Exception ex) {
      return null;
    }
    return res;
  } 

  // ------------------------
  // PRIVATE FIELDS
  // ------------------------
  
  // Wire the UserDao used inside this controller.
  @Autowired
  private DepartmentDao departmentDao;
  
} // class UserController

