package com.github.microservices.labels.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.github.microservices.labels.model.Label;

public interface LabelRepository extends PagingAndSortingRepository<Label, Long> {

}
