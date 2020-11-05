package com.github.microservices.labels.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Label {
	
	private String name;
	// TODO private Set<Record> records;
	// TODO private Country country;

}
