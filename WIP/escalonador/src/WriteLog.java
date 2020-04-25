import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

//Classe responsavel pela criacao do arquivo de log
public class WriteLog {
	FileWriter arq;
	PrintWriter gravarArq;
	int quantum;

	//Criacao do arquivo
	void createFile(int quantum) throws IOException{
		this.quantum = quantum;
		arq = new FileWriter("log" + String.format("%02d", quantum)+ ".txt");
		gravarArq = new PrintWriter(arq);
		arq.flush();
	}

	//Mensagem de t�rmino do processo
	public void writeExit(BCP processoMagaiver) throws IOException {
		gravarArq.println(processoMagaiver.getName() + " terminado. X=" + processoMagaiver.getReg1() + "." + " Y=" + processoMagaiver.getReg2() );
		arq.flush();
	}

	//Mensagem de entrada e sa�da
	public void writeES(BCP processoMagaiver) throws IOException {
		gravarArq.println("E/S iniciada em " + processoMagaiver.getName());
		arq.flush();
	}

	//Mensagem de carregamento
	public void writeIn(BCP p) throws IOException {
		gravarArq.println("Carregando " + p.getName());
		arq.flush();
	}

	//Mensagem de interrup��o
	public void writeInterrupted(BCP p, int n) throws IOException{
		gravarArq.println("Interrompendo " + p.getName()+" ap�s " + n + " instru��es");
		arq.flush();
	}

	//Mensagem de execu��o
	public void writeExecute(BCP p) throws IOException{
		gravarArq.println("Executando " + p.getName());
		arq.flush();
	}
	//Mensagem de execu��o
	public void teste(String oi) throws IOException{
	gravarArq.println(oi);
		arq.flush();
	}

	//Mensagem final
	public void writeEnd(double t, double i) throws IOException{
		gravarArq.println("MEDIA DE TROCAS: " + t );
		gravarArq.println("MEDIA DE INSTRUCOES: " + i);
		gravarArq.println("QUANTUM: " + quantum);
		arq.flush();
	}
}
