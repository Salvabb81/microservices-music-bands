package com.github.microservices.band.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity
@Table(name = "records")
public class Record {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty
	private String name;
	
	@NotEmpty
	private Date release;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "band_id")
	private Band band;

}
