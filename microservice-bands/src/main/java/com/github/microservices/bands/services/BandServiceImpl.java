package com.github.microservices.bands.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	@Transactional(readOnly = true)
	public Page<Band> findAll(Pageable pageable) {
		return bandrepository.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Band> findById(Long id) {
		return bandrepository.findById(id);
	}

	@Override
	public Band save(Band band) {
		return bandrepository.save(band);
	}

	@Override
	public String deleteById(Long id) {
		bandrepository.deleteById(id);
		return "Band deleted.";
	}

}
