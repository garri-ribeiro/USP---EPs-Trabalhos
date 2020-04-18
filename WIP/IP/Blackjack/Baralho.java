import java.util.ArrayList;
import java.util.Collections;

class Baralho {
 	ArrayList<Carta> cartas = new ArrayList<Carta> ();

	Baralho () {

		for (int i=1; i<=13; i++) {

			for (int x=1; x<=4; x++) {

				Carta mestre = new Carta();
				mestre.numero = i;
				mestre.naipe = x;

				cartas.add(mestre);

			}
		}
		
		Collections.shuffle(cartas);
	}

	Carta compra(){

		Carta comprada = new Carta();
		comprada = cartas.get(0);
		
		cartas.remove(0);
		


		return comprada;

			
	}



}
