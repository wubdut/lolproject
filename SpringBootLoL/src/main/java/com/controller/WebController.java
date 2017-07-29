package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.models.Logger;
import com.models.Register;
import com.models.User;
import com.dao.LoggerDao;
import com.dao.RegisterDao;
import com.dao.UserDao;

@Controller
public class WebController {

	@RequestMapping(value="/",method = RequestMethod.GET)
	public String index() {
		return "index";
	}
	
	@RequestMapping("/video")
	public String getVideo() {
		return "video";
	}
	
	@GetMapping("/login")
	public String getLoginPage(Model model) {
		model.addAttribute("logger", new Logger());
		return "login";
	}
	
	@PostMapping("/login")
	public String login(@ModelAttribute("logger") Logger logger) {
//		System.out.println("用户：" + logger.getUsername() + "; 密码：" + logger.getPassword());
		if (loggerDao.checker(logger)) {
			return "log_success";
		} else {
			return "login2";
		}
	}
	
	@GetMapping("/register")
	public String getRegister(Model model) {
		model.addAttribute("register", new Register());
		return "new_account";
	}
	
	@PostMapping("/register")
	public String submitRegister(@ModelAttribute("register") Register register) {
//		System.out.println(register.getUsername());
//		System.out.println(register.getName());
//		System.out.println(register.getWorkerid());
//		System.out.println(register.getEmail());
//		System.out.println(register.getMobile());
//		System.out.println(register.getPosition());
//		System.out.println(register.getPassword());
		
		if (loggerDao.getAll().size() == 0) {
			loggerDao.create(new Logger(register.getUsername(), register.getPassword()));
			registerDao.create(register);
			return "redirect:/login";
		}
		
		return "redirect:/new_account2";
	}
	
	@GetMapping("/new_account2")
	public String reRegister(Model model) {
		model.addAttribute("register", new Register());
		return "new_account2";
	}
	
	// Wire the UserDao used inside this controller.
	  @Autowired
	  private RegisterDao registerDao;
	  @Autowired
	  private LoggerDao loggerDao;
}
