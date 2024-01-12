package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.init.RepositoriesPopulatedEvent;
import org.springframework.stereotype.Service;
import com.example.demo.entities.Playlist;
import com.example.demo.entities.Song;
import com.example.demo.repositories.PlaylistRepository;

@Service
public class PlaylistServiceImp implements PlaylistService {
	
	@Autowired
	PlaylistRepository repo;
	@Override
	public void addPlayList(Playlist playlist) {
		repo.save(playlist);	
	}
	@Override
	public List<Playlist> fetchAllPlaylist() {
		return repo.findAll();
	}

}
