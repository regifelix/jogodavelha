package br.com.regifelix.jogodavelha.business.dto;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO de retorno para apresentar o usuario que ganhou o jogo ou a descrição do erro ocorrido
 * @author Reginaldo Donizete Felix
 *
 */
@Getter
@Setter
@Builder
@ApiModel
public class RetornoDTO {
	boolean sucesso;
	String mensagem;
	

}
