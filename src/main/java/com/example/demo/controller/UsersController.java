package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entities.Users;
import com.example.demo.services.UsersServices;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UsersController {
	@Autowired
	UsersServices service;

	@PostMapping("/register")
	public String addUsers(@ModelAttribute Users user) {
		boolean userStatus = service.emailExist(user.getEmail());
		if (userStatus == false) {
			service.addUsers(user);
			System.out.println("User data added");
			return "home";
		} else {
			System.out.println("User already exist");
			return "login";
		}

	}

	@PostMapping("/validate")
	public String validate(@RequestParam("email") String email, @RequestParam("password") String password,
			HttpSession session) {
		if (service.validateUser(email, password) == true) {
			String role = service.getRole(email);
			session.setAttribute("email", email);
			if (role.equals("admin")) {
				return "adminhome";
			} else {
				return "cutomerhome";
			}
		} else {
			return "login";
		}
	}

//	@GetMapping("pay")
//	public String pay(@RequestParam String email) {
//		boolean paymentStatus = false;// payment api
//		if (paymentStatus == true) {
//			Users u = service.getUsers(email);
//			u.setPremium(true);
//			service.updatUsers(u);
//		}
//		return "login";
//	}
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "login";
	}
}
