package com.cos.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.cos.blog.repository.UserRepository;

@Controller
public class UserController {
	@Autowired
	private UserRepository userepository;

	@GetMapping("/user/loginForm")
	public String loginForm() {
		return "user/loginForm";
	}
	
	
	@GetMapping("/user/joinForm")
	public String joinForm() {
		
		return "user/joinForm";
	}
	
	
}
