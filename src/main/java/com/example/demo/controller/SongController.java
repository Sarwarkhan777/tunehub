package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.entities.Song;
import com.example.demo.services.SongService;


@Controller
public class SongController {
	@Autowired
	SongService service;
	@PostMapping("/addSong")
	public String addSong(@ModelAttribute Song song) {
		boolean isExist=service.isNameExist(song.getName());
		if(isExist==false) {
			service.addSong(song);
			System.out.println("Song added successfully");
			return "adminhome";
		}
		else {
			System.out.println("Song Already added");
			return "adminhome";
		}
	}
	@GetMapping("/viewSongs")
	public String viewSongs(Model model) {
		List<Song> songsList=service.fetchAllSongs();
		model.addAttribute("songs",songsList);
		return "displaySongs";
	}
	@GetMapping("/playSongs")
	public String playSongs(Model model) {
		boolean premiumUser=true;
		if(premiumUser==true) {
			List<Song> songsList=service.fetchAllSongs();
			model.addAttribute("songs",songsList);
			return "displaySongs";
		}
		else {
			return "makePayment";
		}
		
	}
}
