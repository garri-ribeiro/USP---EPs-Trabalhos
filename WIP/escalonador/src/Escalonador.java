// Contador de bloqueados para fazer rodas uma entrada e saida
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Escalonador {

	public static void main(String[] args) throws IOException {

		Scheduler scheduler = new Scheduler();

		for(int i = 1; i<=10;i++){

			// Lê o processo e adiciona todos comandos em um ArrayList
			File process;
			process = new File("processos/"+String.format("%02d", i)+".txt");
			Scanner scan = new Scanner(process);
			BCP p = new BCP("Pronto",i-1,Integer.toString(i));
			p.setName(scan.nextLine());
			while(scan.hasNextLine()){
				p.add(scan.nextLine());
			}

			//Adiciona processo na tabela de processos
			scheduler.loadProcess(p);
			scan.close();
		}

		// Lê arquivo de quantun e seta no Scheduler
		File q = new File("processos/quantum.txt");
		Scanner s = new Scanner(q);
		scheduler.setQuantum(s.nextInt());
		s.close();
		scheduler.start();
	}
}	