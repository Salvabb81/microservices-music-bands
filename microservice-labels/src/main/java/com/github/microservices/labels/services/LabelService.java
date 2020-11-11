package com.github.microservices.labels.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.github.microservices.labels.model.Label;

public interface LabelService {

	public Page<Label> findAll(Pageable pageable);

	public Optional<Label> findById(Long id);

	public Label save(Label label);

	public String deleteById(Long id);

}
