package com.github.microservices.country.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Country {

	private String name;
	private String iso;
	
}
