package com.github.microservices.bands.model;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Record {

	private String name;
	private LocalDate release;

}
