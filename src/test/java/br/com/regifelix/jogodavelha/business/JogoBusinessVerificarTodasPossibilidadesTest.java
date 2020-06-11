package br.com.regifelix.jogodavelha.business;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import br.com.regifelix.jogodavelha.business.dto.TabuleiroDTO;
import br.com.regifelix.jogodavelha.business.impl.JogoBusinessImpl;

@SpringBootTest
@ContextConfiguration(classes = {JogoBusinessImpl.class})
public class JogoBusinessVerificarTodasPossibilidadesTest {
	
	@Autowired
	private IJogoBusiness jogoBusiness;
	
	
	@Test
	public void verificarTodasPossibilidades(){
		
	    //Posições:
	    // 0 1 2
	    // 3 4 5
	    // 6 7 8
	    char[] pc = {' ' ,'O', 'X' };

	    char[] tabuleiroTest = new char[9];
	    
	    int contTests = 0;

	    // inicializa tabuleiroTest

	    for (int i = 0; i < 9; i++)
	        tabuleiroTest[i] = pc[0];

	    for (int i = 0; i < 3; i++) {
	        tabuleiroTest[0] = pc[i];
	        for (int j = 0; j < 3; j++) {
	            tabuleiroTest[1] = pc[j];
	            for (int k = 0; k < 3; k++) {
	                tabuleiroTest[2] = pc[k];
	                for (int l = 0; l < 3; l++) {
	                    tabuleiroTest[3] = pc[l];
	                    for (int m = 0; m < 3; m++) {
	                        tabuleiroTest[4] = pc[m];
	                        for (int n = 0; n < 3; n++) {
	                            tabuleiroTest[5] = pc[n];
	                            for (int o = 0; o < 3; o++) {
	                                tabuleiroTest[6] = pc[o];
	                                for (int p = 0; p < 3; p++) {
	                                    tabuleiroTest[7] = pc[p];
	                                    for (int q = 0; q < 3; q++) {
	                                        tabuleiroTest[8] = pc[q];

	                                        int countx = 0;
	                                        int county = 0;

	                                        for(int r = 0 ; r<9 ; r++){
	                                            if(tabuleiroTest[r] == 'X'){

	                                                countx = countx + 1;
	                                            }
	                                            else if(tabuleiroTest[r] == 'O'){

	                                                county = county + 1;

	                                            }


	                                        }

	                                        if(Math.abs(countx - county) < 2){
	                                        	contTests += 1;
	                                            if(ganhou(tabuleiroTest, pc[2])+ganhou(tabuleiroTest, pc[1]) == 1 ){
	                                            	List<String> jogo = new ArrayList<String>();
	                                            	jogo.add("" + tabuleiroTest[0] + tabuleiroTest[1] + tabuleiroTest[2]);
	                                            	jogo.add("" + tabuleiroTest[3] + tabuleiroTest[4] + tabuleiroTest[5]);
	                                            	jogo.add("" + tabuleiroTest[6] + tabuleiroTest[7] + tabuleiroTest[8]);
	                                            	TabuleiroDTO tabuleiro = TabuleiroDTO.builder().jogo(jogo).build();
	                                            	
	                                            		
	                                            	assertTrue(!jogoBusiness.isVelha(tabuleiro), "Falhou isvelha, esperado que retornasse false para o jogo " + jogo.toString());
	                                                

	                                                


	                                            }else {
	                                            	List<String> jogo = new ArrayList<String>();
	                                            	jogo.add("" + tabuleiroTest[0] + tabuleiroTest[1] + tabuleiroTest[2]);
	                                            	jogo.add("" + tabuleiroTest[3] + tabuleiroTest[4] + tabuleiroTest[5]);
	                                            	jogo.add("" + tabuleiroTest[6] + tabuleiroTest[7] + tabuleiroTest[8]);	                                            	
	                                            	TabuleiroDTO tabuleiro = TabuleiroDTO.builder().jogo(jogo).build();	 
	                                            	assertTrue(jogoBusiness.isVelha(tabuleiro), "Falhou isvelha, esperado que retornasse true para o jogo " + jogo.toString());	                                           	                                                

		                                            
	                                            }


	                                        }



	                                    }
	                                }
	                            }
	                        }
	                    }
	                }
	            }
	        }
	    }
	}

	public static int ganhou(char[] c, char n) {

	    if ((c[0] == n) && (c[1] == n) && (c[2] == n))
	        return 1;
	    else if ((c[3] == n) && (c[4] == n) && (c[5] == n))
	        return 1;
	    else if ((c[6] == n) && (c[7] == n) && (c[8] == n))
	        return 1;
	    else if ((c[0] == n) && (c[3] == n) && (c[6] == n))
	        return 1;
	    else if ((c[1] == n) && (c[4] == n) && (c[7] == n))
	        return 1;
	    else if ((c[2] == n) && (c[5] == n) && (c[8] == n))
	        return 1;
	    else if ((c[0] == n) && (c[4] == n) && (c[8] == n))
	        return 1;
	    else if ((c[2] == n) && (c[4] == n) && (c[6] == n))
	        return 1;
	    else
	        return 0;

	}

}
