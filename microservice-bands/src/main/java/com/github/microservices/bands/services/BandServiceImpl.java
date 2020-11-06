package com.github.microservices.bands.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.github.microservices.band.model.Band;

@Service
public class BandServiceImpl implements BandService {

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
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		
	}

}
