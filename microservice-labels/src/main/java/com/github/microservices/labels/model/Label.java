package com.github.microservices.labels.model;

import java.util.Set;

import com.github.microservices.country.model.Country;
import com.github.microservices.record.model.Record;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Label {

	private String name;
	private Set<Record> records;
	private Country country;

}
