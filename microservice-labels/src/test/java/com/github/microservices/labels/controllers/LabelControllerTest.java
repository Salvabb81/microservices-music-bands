package com.github.microservices.labels.controllers;

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
import com.github.microservices.labels.model.Label;
import com.github.microservices.labels.services.LabelService;

@WebMvcTest(LabelController.class)
class LabelControllerTest {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	ObjectMapper objectMapper;

	@MockBean
	LabelService labelService;

	@Test
	void testListLabels() throws Exception {
		Label label = Label.builder().name("Warner Music Group").build();
		Page<Label> page = new PageImpl<>(Collections.singletonList(label));
		given(labelService.findAll(any())).willReturn(page);

		mockMvc.perform(get("/api/label").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.content[0].name", is(label.getName())));
	}

	@Test
	void testGetLabelById() throws Exception {
		given(labelService.findById(any())).willReturn(Optional.of(Label.builder().build()));

		mockMvc.perform(get("/api/label/1").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	void testCreateLabel() throws Exception {
		String labelJson = objectMapper.writeValueAsString(getValidLabel());

		mockMvc.perform(post("/api/label/").contentType(MediaType.APPLICATION_JSON).content(labelJson))
				.andExpect(status().isCreated());
	}

	@Test
	void testEditLabel() throws Exception {
		String labelJson = objectMapper.writeValueAsString(getValidLabel());
		Optional<Label> opt = Optional.ofNullable(Label.builder().name("Warner Music Group").build());

		when(labelService.findById(anyLong())).thenReturn(opt);

		mockMvc.perform(put("/api/label/1").contentType(MediaType.APPLICATION_JSON).content(labelJson))
				.andExpect(status().isCreated());
	}

	@Test
	void testDeleteLabel() throws Exception {
		when(labelService.deleteById(anyLong())).thenReturn("Band deleted");
		MvcResult requestResult = mockMvc.perform(delete("/api/label/1")).andExpect(status().isNoContent())
				.andExpect(status().isNoContent()).andReturn();
		String result = requestResult.getResponse().getContentAsString();
		assertEquals("Band deleted", result);
	}

	private Band getValidLabel() {
		return Band.builder().name("Warner Music Group").start(LocalDate.of(2001, Month.JANUARY, 1)).build();
	}

}
