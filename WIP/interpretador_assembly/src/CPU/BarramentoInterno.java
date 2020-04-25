package CPU;

import processamento.CicloInstru�ao;

public class BarramentoInterno {
	private String title = " Portas:  ";

	// Envia dados para os Regstradores e guarda portas
	// utilizadas em cada um dos m�todos
	// Passa parametro portaOut para os registradores
	// que recebem dados de diferentes lugares
	
	public void enviarPC(String data, String portaOut){ 
	CicloInstru�ao.passo.add(title + portaOut + ", " + CPU.pc.getIn() + " | Dado PC: " + data);
		CPU.pc.setData(data);
	};
	
	public void enviarMAR(String adress, String portaOut){
		CicloInstru�ao.passo.add(title + portaOut + ", " + CPU.mar.getIn() + " | Endere�o MAR: " + adress);
		CPU.mar.setAdress(adress);
	};
		
	public void enviarMBR(String data, String portaOut){
		CicloInstru�ao.passo.add(title + portaOut + ", " + CPU.mbr.getIn() + " | Dado MBR: " + data);
		CPU.mbr.setData(data);
	};
	
	public void enviarIR(String data, String portaOut){
		CicloInstru�ao.passo.add(title + portaOut + ", " + CPU.ir.getIn() + " | Dado IR: " + data);
		CPU.ir.setData(data);
	};
	
	public void enviarAX(String data, String portaOut){
		CicloInstru�ao.passo.add(title + portaOut + ", " + CPU.ax.getIn() + " | Dado AX: " + data);
		CPU.ax.setData(data);
	};
	
	public void enviarBX(String data, String portaOut){
		CicloInstru�ao.passo.add(title + portaOut + ", " + CPU.bx.getIn() + " | Dado BX: " + data);
		CPU.bx.setData(data);
	};
	
	public void enviarCX(String data, String portaOut){
		CicloInstru�ao.passo.add(title + portaOut + ", " + CPU.cx.getIn() + " | Dado CX: " + data);
		CPU.cx.setData(data);
	};
	
	public void enviarDX(String data, String portaOut){
		CicloInstru�ao.passo.add(title + portaOut + ", " + CPU.dx.getIn() + " | Dado DX: " + data);
		CPU.dx.setData(data);
	};
	
	public void enviarX(String data, String portaOut){
		CicloInstru�ao.passo.add(title + portaOut + ", " + CPU.x.getIn() + " | Dado X: " + data);
		CPU.x.setData(data);
	};
	
	
	// Recebe dado, porta e realiza a opera��o de soma na ula
	public void enviarULA(String data, String portaOut, String opera�ao){
		CicloInstru�ao.passo.add(title + portaOut + ", " + CPU.ula.getIn() + " | Dado ULA: " + data);
		CPU.ula.operacao(data, opera�ao);
	};
	
}
