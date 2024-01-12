package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entities.Playlist;
import com.example.demo.entities.Song;
import com.example.demo.services.PlaylistService;
import com.example.demo.services.SongService;


@Controller
public class PlayListController {
	
	@Autowired
	SongService songService;
	
	@Autowired
	PlaylistService playlistService;
	
	@GetMapping("/createPlayList")
	public String createPlayList(Model model) {
		List<Song>songList=songService.fetchAllSongs();
		model.addAttribute("songs", songList);
		return "createPlayList";
	}
	@PostMapping("/addPlayList")
	public String addPlayList(@ModelAttribute Playlist playlist) {
		playlistService.addPlayList(playlist);
		
		List<Song>list=playlist.getSongs();
		for(Song s:list) {
			s.getPlaylists().add(playlist);
			songService.updateSong(s);
		}
		return "adminhome";
	}
	@GetMapping("/viewPlayList")
	public String viewPlayList(Model model) {
		
		List<Playlist>allPlaylist=playlistService.fetchAllPlaylist();
		model.addAttribute("allPlaylist", allPlaylist);
		return "displayPlayList";
	}
}
