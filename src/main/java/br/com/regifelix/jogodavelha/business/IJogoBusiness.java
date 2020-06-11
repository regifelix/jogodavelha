package br.com.regifelix.jogodavelha.business;

import br.com.regifelix.jogodavelha.business.dto.TabuleiroDTO;
/**
 * Interface das regras do processamento do Jogo da Velha
 * @author Reginaldo Donizete Felix
 *
 */
public interface IJogoBusiness {
	/**
	 * Método que retorna se o jogo do tabuleiro informado é válido ou não
	 * 
	 * @param tabuleiro
	 * @return Retorna verdadeiro se for um jogo válido
	 */
	boolean isJogoValido(TabuleiroDTO tabuleiro);
	
	

	/**
	 * Verifica se houve ocorreu velha, ou seja se não houve nenhum jogador vencedor
	 * 
	 * @param tabuleiro
	 * @return Retorna true caso não tenham vencedores e false caso tenha um
	 *         vencedor.
	 * @throws CustomBusinessException Retorna erro caso o jogo seja inválido.
	 */
	boolean isVelha(TabuleiroDTO tabuleiro);
	
	

	/**
	 * Retorna o vencedor caso houver
	 * 
	 * @param tabuleiro
	 * @return São houver vencedor retora vazio, caso contrario se o X for o
	 *         vencedor retorna o valor X ou senão retora O como vencedor.
	 */
	char obterVencedor(TabuleiroDTO tabuleiro);
	
	

	/**
	 * Verifica se o jogador fez uma sequencia numa das linhas, colunas ou na
	 * transversal
	 * 
	 * Posições na matriz do jogo da velha: 
	 * |0 1 2| 
	 * |3 4 5| 
	 * |6 7 8|
	 * 
	 * @param c Matriz do tabuleiro do jogo
	 * @param n jogador
	 * @return True se o jogador fez uma sequência
	 */
	public boolean isJogadorFezSequencia(char[] c, char n);
	
	

	/**
	 * Converte o jogo em um array de caracteres com as seguintes posições
	 * 
	 * |0 1 2| |3 4 5| |6 7 8|
	 * 
	 * @param tabuleiro
	 * @return
	 */
	char[] conveterEmArrayOfChar(TabuleiroDTO tabuleiro);

}
