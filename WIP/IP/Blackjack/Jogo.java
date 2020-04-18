import java.util.*;


public class Jogo {
	public static void main (String[]args) {

		
		// Criar Baralho
		Baralho red = new Baralho();

		// Cria jogador
		Jogador jaiminho = new Jogador (10);
		System.out.println(" ______________ ||*** COMEÇANDO O JOGO ***|| ______________" + "\r\n" + "\r\n");
			
			// Não deixar o jogador apostar com menos de 2 fichas
			while( jaiminho.getFichas() > 1){
				
				int options = 0;
				int somaMesa = 0;
				int somaJogador =0;
				boolean derrota = false; 
				boolean vitoria = false;
				boolean blackjack = false;
				boolean vezMesa = false;


				System.out.println("______________ || - PRÓXIMA RODADA - || ______________ "+  "\r\n"+  "\r\n");			
				
				// Aposta duas fichas
				jaiminho.ganhaFichas(-2);
				System.out.println("<----- Você apostou duas fichas ----->" + "\r\n");
				
				// Compra e mostra carta
				Carta compra;
				for (int i=0; i<2; i++) {
					compra = red.compra();
					System.out.println("Carta : " + compra.comoString());
					somaJogador +=  compra.getValor(compra.numero);

					// BlackjackSparrow
					if ((somaJogador == 11) && (compra.getValor(compra.numero) == 1 || compra.getValor(compra.numero) == 10)) { // BlackJackSparrow
							System.out.println("\r\n" + "______________ ||||| BLACKJACK ||||| ______________");
							vitoria = true;
							blackjack= true;
							
					}
				}
				
				// Não mostrar pontuação, caso for Blackjack
				if (blackjack == false) {
					System.out.println("Sua pontuação : " + somaJogador);
					options = Possibilidades();
				}
									
				// Comprar			
				while ( options == 1) {
					
					
					System.out.println(" ||# CARTA COMPRADA #|| " + "\r\n");
					compra = red.compra();
					System.out.println("Carta : " + compra.comoString());
					somaJogador +=  compra.getValor(compra.numero);
					System.out.println("Sua pontuação: " + somaJogador);
					
					
					if (somaJogador> 21) {
						System.out.println("\r\n" + " ______________ ## ESTOROU ## ______________ ");
						derrota = true;
						break;
					}
					else if (somaJogador == 21) {
						System.out.println("\r\n" + "______________ VINTE UM [<  21  >] VINTE UM ______________");
						vitoria = true;
						break;
					}
					else {
						System.out.println(somaJogador);
						options = Possibilidades();
					}
						
				}
				
				// Parar
				if (options == 2) {
					vezMesa = true;
				}

				//Não deixar dobrar, caso tenha menos que 4 fichas
				if ((jaiminho.getFichas() < 4) && options == 3) {
					System.out.println("Você não tem fichas sulficientes para dobrar a aposta");
					options = Possibilidades();
				}
				
				// Dobrar
				if (options == 3 && (jaiminho.getFichas() >= 4)) { 	
				 	System.out.println(" ______________ $$ APOSTA DOBRADA $$ ______________" +  "\r\n");
					jaiminho.ganhaFichas(-2);
					compra = red.compra();
					System.out.println("Carta : " + compra.comoString());
					somaJogador +=  compra.getValor(compra.numero);
					System.out.println("Sua pontuação: " + somaJogador);
								
					vezMesa = true;
				}
							
				// Desistir
				if (options == 4) {
					System.out.println("______________ XXX DESISTÊNCIA XXX ______________" + "\r\n");
					jaiminho.ganhaFichas(1);
				}
				
				// Rodada da Mesa
				if ( vezMesa == true) {
					System.out.println("\r\n" + "|< VEZ DA MESA >|" + "\r\n" );
					for (int z=0; z<2 ; z++ ) {
						compra = red.compra();
						System.out.println("Carta da Mesa : " + compra.comoString());
						somaMesa +=  compra.getValor(compra.numero);
						
					}
					System.out.println("Pontuação da Mesa: " + somaMesa);
					if (somaMesa > somaJogador) {
						derrota = true;
					}
					else if (somaMesa < somaJogador) {
						vitoria = true;
					}
					else {
						vitoria = true; 
					}
				}

				// Derrota
				if ( derrota == true) {
					System.out.println("\r\n" + "             ------ VOCÊ PERDEU ------             ");
					
				}

				// Vitória
				if ( vitoria == true) {
					System.out.println("\r\n" + "             ++++ VOCÊ GANHOU ++++             ");
					
					//Fichas ganhadas
					if ( options == 2)
						jaiminho.ganhaFichas(4);
					if ( options == 3 || blackjack == true)
						jaiminho.ganhaFichas(8);
				}
				

				System.out.println("\r\n" + "Total de fichas:" + jaiminho.getFichas() + "\r\n" + "----------------------------------------------------------- " + "\r\n");
			}
			System.out.println("\r\n"+ "\r\n"+ "VOCÊ NÃO FICHAS NECESSARIAS PARA APOSTAR" + "   " + "             | | FIM DE JOGO | |             ");

	}

	



	// Possibilidades do jogo
	static int Possibilidades () throws IllegalArgumentException {
		while (true) {
			try { 
				Scanner inOptions = new Scanner(System.in);
				System.out.println(  "\r\n" + "Comprar=1 | Parar=2 | Dobrar=3 | Desistir=4");
				System.out.print("Digite: ");
				int options = inOptions.nextInt(); 
					if (options < 1 || options > 4)
						throw new IllegalArgumentException();
				return options;
			}
			catch (InputMismatchException e ) {
				System.out.println("Por favor. digite um número entre 1 a 4");
			}
			catch (IllegalArgumentException e1) {
				System.out.println("Por favor. digite um número entre 1 a 4");
			}
		}
	}
	

}

	

