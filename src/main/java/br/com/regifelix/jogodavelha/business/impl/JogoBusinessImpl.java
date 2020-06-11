package br.com.regifelix.jogodavelha.business.impl;

import org.springframework.stereotype.Service;

import br.com.regifelix.jogodavelha.business.IJogoBusiness;
import br.com.regifelix.jogodavelha.business.dto.TabuleiroDTO;

@Service
public class JogoBusinessImpl implements IJogoBusiness {

	private static final char JOGADOR_X = 'X';
	private static final char JOGADOR_O = 'O';
	private static final char VAZIO = ' ';
	private static final int QTD_COLUNAS_TABULEIRO = 3;
	private static final int QTD_LINHAS_TABULEIRO = 3;

	private static final String REGEX_CARACTERES_VALIDOS = "([" + JOGADOR_X + JOGADOR_O + VAZIO + "])+";

	@Override
	public boolean isJogoValido(TabuleiroDTO tabuleiro) {
		if ((tabuleiro == null) || (tabuleiro.getJogo().size() > QTD_LINHAS_TABULEIRO)) {
			return false;
		}
		for (String linha : tabuleiro.getJogo()) {
			if (!isLinhaDoJogoValida(linha)) {
				return false;
			}

		}
		return true;

	}

	private boolean isLinhaDoJogoValida(String linha) {
		if ((linha == null) || (linha != null && (linha.equals("") || (linha.length() > QTD_COLUNAS_TABULEIRO)))) {
			return false;
		}
		if (!linha.matches(REGEX_CARACTERES_VALIDOS)) {
			return false;
		}
		
		return true;
	}

	@Override
	public boolean isVelha(TabuleiroDTO tabuleiro) {
		if (!isJogoValido(tabuleiro)) {
			return false;
		}

		char[] charArray = conveterEmArrayOfChar(tabuleiro);

		boolean isJogodarXVencedor = isJogadorFezSequencia(charArray, JOGADOR_X);
		boolean isJogodarOVencedor = isJogadorFezSequencia(charArray, JOGADOR_O);
		boolean existeUmVencedor = (isJogodarXVencedor || isJogodarOVencedor)
				&& (isJogodarXVencedor != isJogodarOVencedor);
		return !existeUmVencedor;

	}

	@Override
	public char[] conveterEmArrayOfChar(TabuleiroDTO tabuleiro) {
		char[] jogo = { VAZIO, VAZIO, VAZIO, VAZIO, VAZIO, VAZIO, VAZIO, VAZIO, VAZIO };

		int pos = 0;

		for (String linha : tabuleiro.getJogo()) {
			if ((linha != null) && (linha.length() > 0)) {
				char[] characteres = linha.toCharArray();
				int qtdChars = linha.length();
				for (int col = 0; col < 3; col++) {
					jogo[pos] = qtdChars > 0 ? characteres[col] : VAZIO;
					pos += 1;
				}
			}

		}
		return jogo;

	}

	@Override
	public boolean isJogadorFezSequencia(char[] c, char n) {

		if ((c[0] == n) && (c[1] == n) && (c[2] == n))
			return true;
		else if ((c[3] == n) && (c[4] == n) && (c[5] == n))
			return true;
		else if ((c[6] == n) && (c[7] == n) && (c[8] == n))
			return true;
		else if ((c[0] == n) && (c[3] == n) && (c[6] == n))
			return true;
		else if ((c[1] == n) && (c[4] == n) && (c[7] == n))
			return true;
		else if ((c[2] == n) && (c[5] == n) && (c[8] == n))
			return true;
		else if ((c[0] == n) && (c[4] == n) && (c[8] == n))
			return true;
		else if ((c[2] == n) && (c[4] == n) && (c[6] == n))
			return true;
		else
			return false;

	}

	@Override
	public char obterVencedor(TabuleiroDTO tabuleiro) {
		if (!isJogoValido(tabuleiro)) {
			return VAZIO;
		}

		char[] charArray = conveterEmArrayOfChar(tabuleiro);

		boolean isJogodarXVencedor = isJogadorFezSequencia(charArray, JOGADOR_X);
		boolean isJogodarOVencedor = isJogadorFezSequencia(charArray, JOGADOR_O);
		boolean existeUmVencedor = (isJogodarXVencedor || isJogodarOVencedor)
				&& (isJogodarXVencedor != isJogodarOVencedor);

		if (existeUmVencedor) {
			if (isJogodarXVencedor)
				return JOGADOR_X;
			else
				return JOGADOR_O;
		}
		return VAZIO;
	}

}
