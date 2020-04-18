class Carta {
	int naipe;
	int numero;	

	public Carta() {

		this.numero = numero;
		this.naipe = naipe;
		
	}

	
	public static int getValor(int numero) {

		int valorCarta;

		switch (numero) {

			case 11 :  valorCarta = 10; break;
			case 12 :  valorCarta = 10; break;
			case 13 :  valorCarta = 10; break;
			default: valorCarta = numero; break;
		}

		return valorCarta;
	}

	String comoString() {

		String nomeNumero, nomeNaipe, nomeCarta ;
		nomeNumero = "Inicializado";
		nomeNaipe = "Inicializado";
		nomeCarta = "Inicializado";
		

		switch (numero) {

			case 1 : nomeNumero = "As"; break;
			case 2 : nomeNumero = "Dois"; break;
			case 3 : nomeNumero = "Três"; break;
			case 4 : nomeNumero = "Quatro"; break;
			case 5 : nomeNumero = "Cinco"; break;
			case 6 : nomeNumero = "Seis"; break;
			case 7 : nomeNumero = "Sete"; break;
			case 8 : nomeNumero = "Oito"; break; 
			case 9 : nomeNumero = "Nove"; break;
			case 10 : nomeNumero = "Dez"; break;
			case 11 : nomeNumero = "Rainha"; break;
			case 12 : nomeNumero = "Valete"; break;
			case 13 : nomeNumero = "Rei"; break;

			
		}
	
		switch (naipe) {

			case 1 : nomeNaipe = " de Ouros"; break;
			case 2 : nomeNaipe = " de Espadilhas"; break;
			case 3 : nomeNaipe = " de Copas"; break;
			case 4 : nomeNaipe = " de Arvorezinha"; break;
			
			
		}

		
		return nomeCarta = nomeNumero + nomeNaipe;
	}

	
	
}
