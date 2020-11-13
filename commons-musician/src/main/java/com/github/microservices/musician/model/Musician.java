package com.github.microservices.musician.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity
@Table(name = "musicians")
public class Musician {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty
	@Size(min = 1, max = 40)
	private String firstName;

	@NotEmpty
	@Size(min = 1, max = 60)
	private String lastName;

	@NotNull
	private Integer age;

	@NotEmpty
	private Boolean active;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private Instrument instrument;

	@Column(name = "create_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createAt;
	
	@Lob
	@JsonIgnore
	private byte[] photo;

	@PrePersist
	public void prePersist() {
		this.createAt = new Date();
	}

}
