package com.github.microservices.bands.controllers;

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

import java.time.LocalDate;
import java.time.Month;
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
import com.github.microservices.band.model.Band;
import com.github.microservices.bands.services.BandService;

@WebMvcTest(BandController.class)
class BandControllerTest {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	ObjectMapper objectMapper;

	@MockBean
	BandService bandService;

	@Test
	void testListBands() throws Exception {
		Band band = Band.builder().name("Pvris").build();
		Page<Band> page = new PageImpl<>(Collections.singletonList(band));
		given(bandService.findAll(any())).willReturn(page);

		mockMvc.perform(get("/api/band").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.content[0].name", is(band.getName())));
	}

	@Test
	void testGetBandById() throws Exception {
		given(bandService.findById(any())).willReturn(Optional.of(Band.builder().build()));

		mockMvc.perform(get("/api/band/1").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	void testCreateBand() throws Exception {
		String bandJson = objectMapper.writeValueAsString(getValidBand());

		mockMvc.perform(post("/api/band/").contentType(MediaType.APPLICATION_JSON).content(bandJson))
				.andExpect(status().isCreated());
	}

	@Test
	void testEditBand() throws Exception {
		String bandJson = objectMapper.writeValueAsString(getValidBand());
		Optional<Band> opt = Optional
				.ofNullable(Band.builder().name("Pvris").start(LocalDate.of(2012, Month.JANUARY, 1)).build());

		when(bandService.findById(anyLong())).thenReturn(opt);

		mockMvc.perform(put("/api/band/1").contentType(MediaType.APPLICATION_JSON).content(bandJson))
				.andExpect(status().isCreated());
	}

	@Test
	void testDeleteBand() throws Exception {
		when(bandService.deleteById(anyLong())).thenReturn("Band deleted");
		MvcResult requestResult = mockMvc.perform(delete("/api/band/1")).andExpect(status().isNoContent())
				.andExpect(status().isNoContent()).andReturn();
		String result = requestResult.getResponse().getContentAsString();
		assertEquals("Band deleted", result);
	}

	private Band getValidBand() {
		return Band.builder().name("Pvris").start(LocalDate.of(2012, Month.JANUARY, 1)).build();
	}

}
