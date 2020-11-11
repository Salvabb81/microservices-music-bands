package com.github.microservices.labels.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.github.microservices.labels.model.Label;
import com.github.microservices.labels.repository.LabelRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class LabelServiceImpl implements LabelService {

	private LabelRepository labelRepository;

	public LabelServiceImpl(LabelRepository labelRepository) {
		this.labelRepository = labelRepository;
	}

	@Override
	public Page<Label> findAll(Pageable pageable) {
		return labelRepository.findAll(pageable);
	}

	@Override
	public Optional<Label> findById(Long id) {
		return labelRepository.findById(id);
	}

	@Override
	public Label save(Label label) {
		return labelRepository.save(label);
	}

	@Override
	public String deleteById(Long id) {
		labelRepository.deleteById(id);
		return "Label deleted";
	}

}
