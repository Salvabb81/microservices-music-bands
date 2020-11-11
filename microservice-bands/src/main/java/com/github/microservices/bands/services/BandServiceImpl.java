package com.github.microservices.bands.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.github.microservices.band.model.Band;
import com.github.microservices.bands.repository.BandRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BandServiceImpl implements BandService {

	private BandRepository bandrepository;

	public BandServiceImpl(BandRepository bandrepository) {
		this.bandrepository = bandrepository;
	}

	@Override
	public Page<Band> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Band> findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Band save(Band band) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteById(Long id) {
		// TODO Auto-generated method stub
		return "Band deleted.";
	}

}
