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
		return musicianRepository.findAll(pageable);
	}

	@Override
	public Optional<Musician> findById(Long id) {
		return musicianRepository.findById(id);
	}

	@Override
	public Musician save(Musician musician) {
		return musicianRepository.save(musician);
	}

	@Override
	public String deleteById(Long id) {
		musicianRepository.deleteById(id);
		return "Musician deleted";
	}

}
