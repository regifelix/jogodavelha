package br.com.regifelix.jogodavelha.business.dto;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * Objeto DTO para o tabuleiro com as marcações do jogo
 * @author Reginaldo Donizete Felix
 *
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel
public class TabuleiroDTO {
	
	@Valid
	@NotNull
	@NotEmpty
	@ApiModelProperty(value = "jogo", example = "[\"XOO\", \"OXO\", \"OOX\"]")
	List<String> jogo;

}
