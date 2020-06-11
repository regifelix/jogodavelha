package br.com.regifelix.jogodavelha.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.regifelix.jogodavelha.business.dto.RetornoDTO;
import br.com.regifelix.jogodavelha.business.dto.TabuleiroDTO;
import br.com.regifelix.jogodavelha.business.impl.JogoBusinessImpl;

@AutoConfigureMockMvc
@ContextConfiguration(classes = {JogoVelhaController.class, JogoBusinessImpl.class})
@WebMvcTest
public class JogoVelhaControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void casoTenhaUmVencedorRetorna200Test() throws Exception {		
		List<String> jogo = Arrays.asList("O X"," OO","XXX");
		TabuleiroDTO tabuleiroDTO = TabuleiroDTO.builder().jogo(jogo).build();

		MvcResult result = mockMvc.perform(
				post("/v1/jogodavelha").contentType("application/json").content(objectMapper.writeValueAsString(tabuleiroDTO)))
				.andExpect(status().isOk()).andReturn();
		String content = result.getResponse().getContentAsString();
		
		RetornoDTO response = objectMapper.readValue(content, RetornoDTO.class);
		
		assertTrue(response.isSucesso()==true);
	}
	
	@Test
	void requisicaoInvalidaRetorna400Test() throws Exception {

		String[] jogo = {"O X"," OO","XXX"};
		

		mockMvc.perform(
				post("/v1/jogodavelha").contentType("application/json").content(objectMapper.writeValueAsString(jogo)))
				.andExpect(status().isBadRequest());
	}
	
	@Test
	void casoJogoInvalidoRetorna404Test() throws Exception {
		
		List<String> jogo = Arrays.asList("","XO12");
		TabuleiroDTO tabuleiroDTO = TabuleiroDTO.builder().jogo(jogo).build();

		mockMvc.perform(
				post("/v1/jogodavelha").contentType("application/json").content(objectMapper.writeValueAsString(tabuleiroDTO)))
				.andExpect(status().is(HttpStatus.NOT_FOUND.value()));
	}
	
	@Test
	void casoNaoTenhaVencedorRetorna404Test() throws Exception {
		
		List<String> jogo = Arrays.asList("OXX", "XOO", "OXX");
		TabuleiroDTO tabuleiroDTO = TabuleiroDTO.builder().jogo(jogo).build();

		mockMvc.perform(
				post("/v1/jogodavelha").contentType("application/json").content(objectMapper.writeValueAsString(tabuleiroDTO)))
				.andExpect(status().is(HttpStatus.NOT_FOUND.value()));
	}
	
	@Test
	void casoTenhaDoisVencedoresRetorna404Test() throws Exception {
		
		List<String> jogo = Arrays.asList("XXO", "OOO", "XXX");
		TabuleiroDTO tabuleiroDTO = TabuleiroDTO.builder().jogo(jogo).build();

		mockMvc.perform(
				post("/v1/jogodavelha").contentType("application/json").content(objectMapper.writeValueAsString(tabuleiroDTO)))
				.andExpect(status().is(HttpStatus.NOT_FOUND.value()));
	}

}
