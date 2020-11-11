package com.github.microservices.musicicians.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.github.microservices.musician.model.Musician;
import com.github.microservices.musicicians.repository.MusicianRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MusicianServiceImpl implements MusicianService {

	private MusicianRepository musicianRepository;

	public MusicianServiceImpl(MusicianRepository musicianRepository) {
		this.musicianRepository = musicianRepository;
	}

	@Override
	public Page<Musician> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Musician> findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Musician save(Musician musician) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteById(Long id) {
		// TODO Auto-generated method stub
		return "Musician deleted";
	}

}
