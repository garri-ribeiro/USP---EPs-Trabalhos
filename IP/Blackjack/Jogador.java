
class Jogador {

	private int fichas;

	Jogador (int f) {

		this.fichas = f;
	}

	int getFichas() {
		
		return fichas;
	}
	
	public void ganhaFichas (int f) {

		fichas += f;
	}
}
