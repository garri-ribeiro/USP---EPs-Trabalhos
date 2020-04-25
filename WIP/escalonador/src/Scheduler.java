import java.io.IOException;
import java.util.*;

public class Scheduler {
	LinkedList<BCP> blockeds = new LinkedList<BCP>();
	LinkedList<BCP> actives = new LinkedList<BCP>();
	ArrayList<Integer> timeOut = new ArrayList<Integer>();
	WriteLog log = new WriteLog();
	public ProcessTable table = new ProcessTable();
	private BCP processoMagaiver; // usado como variavel global para add e remover na fila
	private int quantum = 0; // Ele receberá um valor de quantum para escalonar
	private int changeNum = 0;
	private int exeNum = 0;

	public void start() throws IOException {
		log.createFile(quantum);
		for(int i = 0; i<table.processList.size();i++){
			BCP p = table.processList.get(i);
			log.writeIn(p);
			addQueueActive(p);
		}
		while(!table.isEmpty()){
			Scheduling();
		}
		log.writeEnd(calcChange(), calcInstructions());
	}

	public void setQuantum(int q) {
		quantum = q;
	}

	public int getQuantum() {
		return quantum;
	}

	// Funções fila de processos ativos
	public void addQueueActive(BCP pr) {
		pr.setStateProcess("Pronto");
		actives.addFirst(pr);
	};

	public void removeQueueActive() {
		actives.removeLast();
	}

	public void sendFinalQueue() { // Joga processo para o final da fila
		removeQueueActive();
		addQueueActive(processoMagaiver);
	}

	// Funções fila de processos bloqueados
	public void addQueueBlocked(BCP pr) {
		pr.setStateProcess("Bloqueado");
		blockeds.addFirst(pr);
	};

	public void removeQueueBlocked() {
		processoMagaiver = blockeds.getLast();
		blockeds.removeLast();
	}
	public void refreshQueueBlocked() throws IOException{

		if(timeOut.isEmpty()) return;
		else {
			for(int i = 0; i<timeOut.size(); i++) {
				Integer oi = new Integer(timeOut.get(i).intValue()-1);
				if(oi.intValue() == 0) {
					removeQueueBlocked();
					addQueueActive(processoMagaiver);
					timeOut.remove(i);
					if(!(timeOut.isEmpty())) i--;

				}
				else timeOut.set(i,oi);

			}

		}
	}

	// Executa proximo processo da fila de ativos
	public void executeProcess()throws IOException {
		if(actives.isEmpty()) {
			refreshQueueBlocked();
			return;
		}
		processoMagaiver = actives.getLast();
	}

	// Carrega processo e ordena
	public void loadProcess(BCP p) {
		table.addProcess(p);

	}

	// Escalonar os processos
	// Passo 1 -> Executa o prox processo da fila, Passo 2 -> Roda o processo até finalizar
	// o quantum, caso o processo finalize mais cedo, finalizar e escalona o próximo, Passo 3 ->
	// após finalizar o processo, joga-lo para o final da fila
	// ( FALTA TRABALHAR COM ENTRADA E SAIDAS, CRIAÇÃO DE LOGS E ETC)
	public void Scheduling() throws IOException {
		//Contagem do n�mero de instru��es
		int executedNumber = 0;
		executeProcess();
		log.writeExecute(processoMagaiver);
		changeNum++;
		//System.out.println(changeNum);
		String command = "";
		for(int i=0; i<quantum; i++) {
			executedNumber++;
			command = processoMagaiver.nextCommand(); // executa comando
			if(command.equalsIgnoreCase("SAIDA") || command.equalsIgnoreCase("E/S")) {
				break;
			}
			if(command.charAt(0) == 'X') {
				processoMagaiver.setReg1(command.substring(2));
			}
			if(command.charAt(0) == 'Y') {
				processoMagaiver.setReg2(command.substring(2));
			}
			table.refreshProcess(processoMagaiver);
		} // Fim for


		if(command.equalsIgnoreCase("SAIDA")) {
			// Finaliza o processo e o retira da tabela de processos
			log.writeExit(processoMagaiver);
			removeQueueActive();
			table.removeProcess(processoMagaiver);
		}

		else if(command.equalsIgnoreCase("E/S")) {
			// Retira da fila de ativos e envia para fila de bloqueados
			log.writeES(processoMagaiver);
			removeQueueActive();
			addQueueBlocked(processoMagaiver);
			timeOut.add(new Integer(3)); // Cria um tempo de espera para o processo



		}
		else {
			sendFinalQueue(); // envia processo que acabou seu quantum para o final da fila
		}
		log.writeInterrupted(processoMagaiver, executedNumber);
		exeNum += executedNumber;
		executedNumber = 0;
		refreshQueueBlocked();

	}

	private int calcChange(){
		return changeNum/table.getProcessNum();
	}

	private double calcInstructions(){
		return exeNum/changeNum;
	}
}
