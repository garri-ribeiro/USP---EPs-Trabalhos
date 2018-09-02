// Gabriel Ribeiro da Silva - nºUSP: 9277812

import java.util.Scanner;

public class Boliche {

	public static void main (String[]args){

		
		// INSERIR NOME --------------------------------------------------------------------------------------------------

		System.out.println("Insira seu nome");
		Scanner inNome = new Scanner(System.in);
		String nome = inNome.nextLine();

		System.out.println("<<<<< INICIANDO O JOGO >>>>>");
		System.out.println("Nome: " + nome);

		
		// TABELA DE PONTOS DA JOGADA (APENAS ARRAY) ----------------------------------------------------------------------
		int[][] tabela = new int[12][4];
		

		// JOGADAS ( É REALIZADO 10 RODADAS ) , começa no array [2] e termina no [12] -------------------------------------
		int pontuacao=0;
		for( int j=2; j<12; j++) {
			int rodada = j-1;
			
			
			// 1 e 2 BOLA DA RODADA ---------------------------------------------------------------------------------------
			for( int i=1; i<3; i++) {
					
				int valor=20;
				
				while( valor > 10 || (tabela[j][1] + tabela[j][2] ) > 10 || valor < 0) {
					System.out.print("Jogada " + rodada + ", Bola " + i + ":  ");
					Scanner pt = new Scanner(System.in);
					valor = pt.nextInt();
					tabela[j][i] = valor;
					
					// VALOR  INVALIDO
					if (valor > 10 || (tabela[j][1] + tabela[j][2] ) > 10 || valor < 0){
						System.out.println("VALOR INVALIDO");
					}
					
				}
						
			// IMPRIMIR NA TELA (SPARE e STRIKE) ----
			if ( tabela[j][1] == 10){ 
				System.out.println("<-------------------- STRIKEEEE -------------------->");
			}
			
			else if ( (tabela[j][2] + tabela[j][1]) == 10){ 
				System.out.println("<-------------------- SPAAAARE -------------------->");
			}
			

			// CONTAGEM PONTOS ------------------------------------------------------------------------------------------------------

			pontuacao += valor;	
			// STRIKE
			if (tabela[j-2][1] == 10 && tabela[j-1][1] == 10  ){
				pontuacao += valor;	
			}
			if (tabela[j-1][1] == 10 ){
				pontuacao += valor;	
			}
			// SPARE
			else if ( (tabela[j-1][2] + tabela[j-1][1] == 10 ) && (tabela[j-1][2] != 0) && i == 1 ){
				pontuacao += tabela[j][1];	
			}
			
			// SE FOR STRIKE, PULA a 2 RODADA
					if(tabela[j][1] == 10) {
						i++;
					}
			// PLACAR PONTUAÇÃO A CADA LANCE
			System.out.println("PLACAR: " + pontuacao + "pontos    ");
			
		}
			
						
					
		//-------------------------------------------------------------------------------------------------------------------------
		

		// BOLA EXTRA NA ULTIMA RODADA 
		if ( tabela[11][1] ==10 ) {
			
			// STRIKE ( GANHA DUAS JOGADAS )
			for (int y=2;y<4;y++){
				int ex=20;
				while( ex > 10 || ex < 0 ) {
					System.out.print("Jogada " + j + ", Bola " + "EXTRA" + ":  ");
					Scanner pt = new Scanner(System.in);
					ex = pt.nextInt();
					if (tabela[11][2] == 10 ){
						pontuacao += ex;
					}
					tabela[j][y] = ex;
					pontuacao += ex;	
					
					// PLACAR PONTUAÇÃO A CADA LANCE (RODADA EXTRA)		
					System.out.println("PLACAR: " + pontuacao + "pontos    ");
					
					// VALOR  INVALIDO
					if (ex > 10 || ex < 0){
						System.out.println("VALOR INVALIDO");
					}
				}
			}
		}
			// SPARE ( UMA RODADA EXTRA)
		else if ((tabela[11][2] + tabela[11][1] ) == 10 ) {
			int ex=20;
				while( ex > 10 ) {
					System.out.print("Jogada " + j + ", Bola " + "EXTRA" + ":  ");
					Scanner pt = new Scanner(System.in);
					ex = pt.nextInt();
					tabela[j][3] = ex;
					pontuacao += ex;

					// PLACAR PONTUAÇÃO A CADA LANCE (RODADA EXTRA)		
					System.out.println("PLACAR: " + pontuacao + "pontos    ");

					// VALOR  INVALIDO
					if (ex > 10 || ex < 0){
						System.out.println("VALOR INVALIDO");
					}
				}
			}
		}
		
		// PONTUAÇÃO FINAL --------------------------------------------------------------------
		System.out.println("PARABENS " + nome + "\r\n" + "|------- Voce fez " + pontuacao + " pontos !!! -------|");
	}
}