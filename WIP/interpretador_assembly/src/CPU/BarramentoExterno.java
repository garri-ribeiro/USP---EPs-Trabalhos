package CPU;

import processamento.CicloInstru�ao;
import processamento.Memoria;

public class BarramentoExterno {
	private String data;
	private String title = " Portas ->  ";
	
	// Procura o endere�o na memoria a partir do MAR
	public void enviarMemoriaAdress(String adress) {
		CicloInstru�ao.passo.add(title + CPU.mar.getOut() + ", " + Memoria.getIn() + " | Endere�o Memoria: " + adress);
		data = Memoria.buscaDado(adress);
	}
	
	// Envia dados para memoria a partir do MBR
	public void enviarMemoria(String dataOut) {
		CicloInstru�ao.passo.add(title + "21" + ", " + Memoria.getIn() + " | Dado Memoria: " + data);
		Memoria.guardaDado(dataOut);
	}

	// Envia dados da Memoria para o MBR
	public void enviarMBR(){
		CicloInstru�ao.passo.add(title + Memoria.getOut() + ", " + "22" + " | Dado MBR: " + data); // Gambiarra
		CPU.mbr.setData(data);
	};
	
};