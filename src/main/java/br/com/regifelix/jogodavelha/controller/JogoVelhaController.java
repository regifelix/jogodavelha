package br.com.regifelix.jogodavelha.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.regifelix.jogodavelha.business.IJogoBusiness;
import br.com.regifelix.jogodavelha.business.dto.RetornoDTO;
import br.com.regifelix.jogodavelha.business.dto.TabuleiroDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin
@RestController
@Api(value = "Jogo da Velha")
@ApiResponses(value = { 
		@ApiResponse(code = 200, message = "Caso tenha um vencedor", response = RetornoDTO.class),
		@ApiResponse(code = 400, message = "Requisição inválida!"),		
		@ApiResponse(code = 404, message = "Nenhum vencedor ou jogo inválido!", response = RetornoDTO.class) 
})
public class JogoVelhaController {
	
	@Autowired
	IJogoBusiness jogoBusiness;

	@PostMapping(value = "/v1/jogodavelha", consumes = { "application/json" }, 
			produces = { "application/json", "application/problem+json" })	
	@ApiOperation(value = "Serviço para verificar se houve vencedor.", response = RetornoDTO.class ,consumes = "application/json")
	public ResponseEntity<RetornoDTO> isVelha(
			@Valid @ApiParam(value = "Informe o jogo, deverá ser passado no corpo da requisição o jogo," +
									 "são aceitos o caracter X, O ou caracter espaço" +
	                                 " para posições não marcadas no tabuleiro.<br> ") @RequestBody TabuleiroDTO tabuleiro) {
		
		if(!jogoBusiness.isJogoValido(tabuleiro)) {
			RetornoDTO response = RetornoDTO.builder().sucesso(false).mensagem("O jogo informado não é valido!").build();
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
		
		if(jogoBusiness.isVelha(tabuleiro)) {
			RetornoDTO response = RetornoDTO.builder().sucesso(false).mensagem("Não houve vencedor!").build();
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
			
		}else {
			char vencedor = jogoBusiness.obterVencedor(tabuleiro);
			RetornoDTO response = RetornoDTO.builder().sucesso(true).mensagem("Ganhador " + vencedor).build();
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		
		
		
	}

}
