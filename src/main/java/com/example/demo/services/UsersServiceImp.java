package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Users;
import com.example.demo.repositories.UsersRepository;

@Service
public class UsersServiceImp implements UsersServices {
	@Autowired
	UsersRepository repo;
	@Override
	public String addUsers(Users user) {
		repo.save(user);
		return "User successfully added";
	}
	@Override
	public boolean emailExist(String email) {
		if(repo.findByEmail(email)==null) {
			return false;
		}
		else {
			return true;
		}
	}
	@Override
	public boolean validateUser(String email, String password) {
		Users user=repo.findByEmail(email);
		if(user==null) {
			return false;
		}
		if(user.getPassword().equals(password)) {
			return true;
		}
		return false;
	}
	@Override
	public String getRole(String email) {
		Users user=repo.findByEmail(email);
		String role=user.getRole();
		return role;
	}
	@Override
	public Users getUsers(String email) {
		return repo.findByEmail(email);
	}
	@Override
	public void updatUsers(Users user) {
		repo.save(user);
		
	}

}
