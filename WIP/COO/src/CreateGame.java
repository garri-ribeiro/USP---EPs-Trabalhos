import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class CreateGame {
	private RunGame runGame = new RunGame();
	
	// Inicia funções do jogo
	public void iniciaFunctions(File file) throws IOException {
		
		Scanner sc = new Scanner(file);
		runGame.createPlayer(sc.nextInt());
		int numeroFases = sc.nextInt();
		for(int i = 1; i<=numeroFases;i++){
			File arquivo = new File(sc.next());
			runGame.createLevel(arquivo,i);
		}
		sc.close();
		
		
		/* Inicializa blilioteca GameLib*/		
		GameLib.initGraphics();

	}
	
	/* Inicializa Jogo */
	public void run(boolean status) {
		runGame.run(status);
		System.exit(0);
	}	
}