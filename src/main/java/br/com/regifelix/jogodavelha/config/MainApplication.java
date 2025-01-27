package br.com.regifelix.jogodavelha.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import lombok.Generated;

@SpringBootApplication
@ComponentScan({ "br.com.regifelix.*" })
@Generated
public class MainApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(MainApplication.class, args);
	}

}
