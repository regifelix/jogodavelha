package br.com.regifelix.jogodavelha.business;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import br.com.regifelix.jogodavelha.business.dto.TabuleiroDTO;
import br.com.regifelix.jogodavelha.business.impl.JogoBusinessImpl;


public class JogoBusinessTests {	

	
	private IJogoBusiness jogoBusiness = new JogoBusinessImpl();

	@Test
	void tabuleiroValidoTest() {
		
		List<String> jogo = Arrays.asList("XXX", "XXX", "XOX");
		TabuleiroDTO tabuleiroDTO = TabuleiroDTO.builder().jogo(jogo).build();

		assertTrue(jogoBusiness.isJogoValido(tabuleiroDTO), "Esperado retorno do jogo como valido igual a true para " + jogo.toString());
	}
	
	@Test
	void tabuleiroInValidoTest() {
		
		List<String> jogo = Arrays.asList("XXXWWER", "XXX", "XOXSDFF", "OWS");
		TabuleiroDTO tabuleiroDTO = TabuleiroDTO.builder().jogo(jogo).build();

		assertTrue(!jogoBusiness.isJogoValido(tabuleiroDTO), "Esperado que fosse retornado inválido para o jogo " + jogo.toString());
		
		assertTrue(!jogoBusiness.isVelha(tabuleiroDTO), "Esperado que fosse retornado isVelha false para jogo inválido " + jogo.toString());
		
		
		List<String> jogoLinhaNull = Arrays.asList(null, "XXX", null);
		TabuleiroDTO tabuleiroDTOLinhaNull = TabuleiroDTO.builder().jogo(jogoLinhaNull).build();

		assertTrue(!jogoBusiness.isJogoValido(tabuleiroDTOLinhaNull), "Esperado que fosse retornado inválido para o jogo " + jogoLinhaNull.toString());
		
		assertTrue(!jogoBusiness.isVelha(tabuleiroDTOLinhaNull), "Esperado que fosse retornado isVelha false para jogo inválido " + jogoLinhaNull.toString());
	}
	
	@Test
	void tabuleiroLinhaInvalidaTest() {
		
		List<String> jogo = Arrays.asList("0op", "---", "---");
		TabuleiroDTO tabuleiroDTO = TabuleiroDTO.builder().jogo(jogo).build();

		assertTrue(!jogoBusiness.isJogoValido(tabuleiroDTO), "Esperado que fosse retornado inválido para o jogo " + jogo.toString());
	}
	
	@Test
	void naoHouveGanhadorTest() {
		
		List<String> jogo = Arrays.asList("XXO", "XXO", "XOX");
		TabuleiroDTO tabuleiroDTO = TabuleiroDTO.builder().jogo(jogo).build();
		assertTrue(!jogoBusiness.isVelha(tabuleiroDTO), "Esperado is velha igual a false para o jogo " + jogo.toString());
	}
	
	@Test
	void houveGanhadorTest() {		
		List<String> jogo = Arrays.asList("XXX", "XOO", "XOX");
		TabuleiroDTO tabuleiroDTO = TabuleiroDTO.builder().jogo(jogo).build();
		
		assertTrue(!jogoBusiness.isVelha(tabuleiroDTO), "Esperado is velha igual a false para o jogo " + jogo.toString());
	}
	
	@Test
	void ganhadorXTest() {		
		List<String> jogo = Arrays.asList("XXX", "XOO", "XOX");
		TabuleiroDTO tabuleiroDTO = TabuleiroDTO.builder().jogo(jogo).build();
		
		assertTrue(!jogoBusiness.isVelha(tabuleiroDTO), "Esperado is velha igual a false para o jogo " + jogo.toString());
		assertEquals(jogoBusiness.obterVencedor(tabuleiroDTO), 'X', "Falha no teste do vencedor igual a X para o jogo " + jogo.toString());
		
		
	}
	
	@Test
	void ganhadorOTest() {		
		List<String> jogo = Arrays.asList("XXO", "OOX", "OXX");
		TabuleiroDTO tabuleiroDTO = TabuleiroDTO.builder().jogo(jogo).build();
		
		Assert.isTrue(!jogoBusiness.isVelha(tabuleiroDTO), "Esperado is velha igual a false para o jogo " + jogo.toString());
		assertEquals(jogoBusiness.obterVencedor(tabuleiroDTO), 'O', "Falha no teste do vencedor igual a O  para o jogo " + jogo.toString());
		
		
	}
	
	@Test
	void ganhadorVazioTest() {		
		List<String> jogo = Arrays.asList("XOX", "XOX", "OXO");
		TabuleiroDTO tabuleiroDTO = TabuleiroDTO.builder().build();
		tabuleiroDTO.setJogo(jogo);		
		
		assertEquals(jogoBusiness.obterVencedor(tabuleiroDTO), ' ', "Falha no teste do vencedor igual a vazio para o jogo " + jogo.toString());
		
		
	}
	
	@Test
	void ganhadorJogoInvalidoTest() {		
		List<String> jogo = Arrays.asList("XO!", "XOX", "OXO");
		TabuleiroDTO tabuleiroDTO = TabuleiroDTO.builder().build();
		tabuleiroDTO.setJogo(jogo);		
		
		assertEquals(jogoBusiness.obterVencedor(tabuleiroDTO), ' ', "Falha no teste do vencedor igual a vazio para o jogo inválido " + jogo.toString());
		
		
	}
	
	@Test
	void houveDoisGanhadoresJogoInvalidoTest() {		
		List<String> jogo = Arrays.asList("XXX", "OOO", "XOX");
		TabuleiroDTO tabuleiroDTO = TabuleiroDTO.builder().jogo(jogo).build();
		
		assertTrue(jogoBusiness.isVelha (tabuleiroDTO), "Esperado is velha igual a true para o jogo invalido com dois ganhadores " + jogo.toString());
		
		assertEquals(jogoBusiness.obterVencedor(tabuleiroDTO), ' ', "Esperado vencedor vazio para o jogo invalido com dois ganhadores " + jogo.toString());
	}
	
	@Test
	void conveterEmArrayOfCharTest() {
		List<String> jogo = Arrays.asList("XXX", "XOO", "XOX");
		TabuleiroDTO tabuleiroDTO = new TabuleiroDTO();
		tabuleiroDTO.setJogo(jogo);
		char[] arrayValores = jogoBusiness.conveterEmArrayOfChar(tabuleiroDTO);
		
		assertTrue(arrayValores.length == 9, "Esperado que fosse retornado um array de valores com 9 posições");
		List<String> jogo2 = new ArrayList<>();
		jogo2.add(new String(arrayValores, 0, 3));
		jogo2.add(new String(arrayValores, 3, 3));
		jogo2.add(new String(arrayValores, 6, 3));
		TabuleiroDTO tabuleiroDTO2 = new TabuleiroDTO();
		tabuleiroDTO2.setJogo(jogo2);
		assertEquals(tabuleiroDTO , tabuleiroDTO2);
		assertEquals(tabuleiroDTO.hashCode() , tabuleiroDTO2.hashCode());
		
		
	}

}
