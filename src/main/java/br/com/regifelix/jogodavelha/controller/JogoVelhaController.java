package br.com.regifelix.jogodavelha.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin
@RestController
@Api(value = "Jogo da Velha")
@RequestMapping(value = "/jogodavelha")
public class JogoVelhaController {

	
	@PostMapping("/jogo")	
	@ApiOperation(value = "Processar integração", response = Boolean.class)
	public ResponseEntity<Boolean> isVelha (Integer[] jogo) {		
		return new ResponseEntity<>(true, HttpStatus.OK);
		
	}

}
