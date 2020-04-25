import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
	// Método principal
	
	/* PARA RODAR O JOGO */
	/* QUANDO DAR RUN NO APLICAÇÃO INSERIR NO CONSOLE -> input\conf.txt */
	public static void main(String [] args) throws IOException{
		
		Scanner sc = new Scanner(System.in);
		File file = new File(sc.next());
		sc.close();
		CreateGame start = new CreateGame();
		start.iniciaFunctions(file);
		start.run(true);

	}
}