package com.github.microservices.musicicians.controllers;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.microservices.musician.model.Musician;
import com.github.microservices.musicicians.services.MusicianService;

@WebMvcTest(MusicianController.class)
class MusicianControllerTest {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	ObjectMapper objectMapper;

	@MockBean
	MusicianService musicianService;
	
	@Test
	void testListMusicians() throws Exception {
		Musician musician = Musician.builder().firstName("Lyndsey").lastName("Gunnulfsen").build();
		Page<Musician> page = new PageImpl<>(Collections.singletonList(musician));
		given(musicianService.findAll(any())).willReturn(page);

		mockMvc.perform(get("/api/musician").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.content[0].firstName", is(musician.getFirstName())));
	}

	@Test
	void testGetMusicianById() throws Exception {
		given(musicianService.findById(any())).willReturn(Optional.of(Musician.builder().build()));

		mockMvc.perform(get("/api/musician/1").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	void testCreateMusician() throws Exception {
		String musicianJson = objectMapper.writeValueAsString(getValidMusician());

		mockMvc.perform(post("/api/musician/").contentType(MediaType.APPLICATION_JSON).content(musicianJson))
				.andExpect(status().isCreated());
	}

	@Test
	void testEditMusician() throws Exception {
		String musicianJson = objectMapper.writeValueAsString(getValidMusician());
		Optional<Musician> opt = Optional.ofNullable(Musician.builder().firstName("Lyndsey").lastName("Gunnulfsen").build());

		when(musicianService.findById(anyLong())).thenReturn(opt);

		mockMvc.perform(put("/api/musician/1").contentType(MediaType.APPLICATION_JSON).content(musicianJson))
				.andExpect(status().isCreated());
	}

	@Test
	void testDeleteMusician() throws Exception {
		when(musicianService.deleteById(anyLong())).thenReturn("Musician deleted");
		MvcResult requestResult = mockMvc.perform(delete("/api/musician/1")).andExpect(status().isNoContent())
				.andExpect(status().isNoContent()).andReturn();
		String result = requestResult.getResponse().getContentAsString();
		assertEquals("Musician deleted", result);
	}
	
	private Musician getValidMusician() {
		return Musician.builder().firstName("Lyndsey").lastName("Gunnulfsen").build();
	}

}
