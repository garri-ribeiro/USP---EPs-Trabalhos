package processamento;

import java.util.ArrayList;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import CPU.*;


public class CicloInstruçao {	
	int ICC;
	static public ArrayList<String> passo = new ArrayList<String>();
	boolean comandoConcluido;

	CicloInstruçao(){
		ICC = 00;
	}
	
	void proximoCiclo(String[] comando) {
		if(ICC == 00){
			ciclo_busca();
		}else if(ICC == 01){
			ciclo_indireto();
		}
		else{
			ciclo_execucao(comando);
		}
	}

	
	public void ciclo_busca() {
		passo.add("\n---------Ciclo de busca---------");
		
		
		passo.add("\n Endereço PC: " + Simulador.IR);

		passo.add("\n T1: MAR <-- PC");
		passo.add("Sinal de controle: " + Simulador.sinaisControle[0] + "000000000");
		CPU.busIn.enviarMAR(CPU.pc.getAdress(), CPU.pc.getOut());

		// MAR envia endereço para memoria e a memoria devolve o dado para o MBR
		passo.add("\n T2: MBR <-- Memoria ");
		passo.add("Sinal de controle: " + Simulador.sinaisControle[1] + "000100000");
		CPU.busOut.enviarMemoriaAdress(CPU.mar.getAdress());
		CPU.busOut.enviarMBR();
		
		
		passo.add("\nT3: IR <-- MBR ");
		passo.add("Sinal de controle: " + Simulador.sinaisControle[2] + "000000000");
		passo.add("PC <-- PC + 1");
		passo.add("Sinal de controle: " + Simulador.sinaisControle[3] + "000000000");
		CPU.busIn.enviarIR(CPU.mbr.getData(), CPU.mbr.getOut());
		// JOGAR PC MAIS 1
	
		
		if(CPU.ir.getData().charAt(0)=='['){
			ICC = 01;
		}else{
			ICC = 10;
		}
	}
	
	public void ciclo_indireto() {
		passo.add("---------Ciclo de indireção---------\n"); 
		passo.add("\n T1: MAR <-- IR\n");
		passo.add("Sinal de controle: " + Simulador.sinaisControle[4] + "000000000");
		CPU.busIn.enviarMAR(CPU.ir.getAdress(), CPU.ir.getOut());

		passo.add("\n T2: MBR <-- Memoria \n");
		passo.add("Sinal de controle: " + Simulador.sinaisControle[1] + "000100000");
		CPU.busOut.enviarMemoriaAdress(CPU.mar.getAdress());
		CPU.busOut.enviarMBR();
		
		
		passo.add("\n T3: IR <-- MBR \n "); 
		passo.add("Sinal de controle: " + Simulador.sinaisControle[2] + "000000000");
		CPU.busIn.enviarIR(CPU.mbr.getData(), CPU.mbr.getOut());
		
		ICC = 10;
	}
	
	public void ciclo_execucao(String[] comando) {
		passo.add("\n---------Ciclo de execução---------");

		boolean jump = false;
		if(comando.length ==3){
			if(comando[2].charAt(0)=='['){
				ICC = 11;
				proximoCiclo(comando);
			}else{
				
				//Cobrindo MOV AX,BX | MOV AX,2 | MOV [AX],BX | MOV[AX],2 | MOV [2],AX | MOV[2],2
				if(comando[0].equalsIgnoreCase("MOV")){
					executaMOV(comando);
				}
				
				// Cobrindo ADD AX,BX | ADD AX,2 | ADD [AX],BX | ADD [2],BX | ADD [AX],2 | ADD [2],2
				else if(comando[0].equalsIgnoreCase("ADD")|| comando[0].equalsIgnoreCase("SUB")){
					executaADD(comando);
				}
				
				//Cobrindo MUL AX | MUL 2 | MUL [AX] | MUL 2 | DIV AX | DIV 2 | DIV [AX] | DIV 2 
				else if(comando[0].equalsIgnoreCase("MUL")|| comando[0].equalsIgnoreCase("DIV")){
					executaMUL(comando);
				}
								
				//Cobrindo INC AX | INC [AX] | INC [2]				
				else if(comando[0].equalsIgnoreCase("INC")||comando[0].equals("DEC")){
					executaINC(comando);
				}
				
				
				//Cobrindo CMP REG,REG e CMP REG,CONSTANTE
				else if(comando[0].equalsIgnoreCase("CMP")){
					executaCMP(comando);
				}
				
				//Não sei se existe mudança no ciclo do pulo ou se é o mesmo, com alteração nas flags somente
				else if(comando[0].equalsIgnoreCase("JMP") ){
					jump = true;
				}else if((comando[0].equalsIgnoreCase("JZ" )) && CPU.ula.flagZero == 1){
					jump = true;
				}else if((comando[0].equalsIgnoreCase("JNZ")) && CPU.ula.flagZero == 0){
					jump = true;
				}else if((comando[0].equalsIgnoreCase("je"))  && CPU.ula.flagZero == 1){
					jump = true;
				}else if(comando[0].equalsIgnoreCase("JNE") && CPU.ula.flagZero == 1){
					jump = true;
				}else if(comando[0].equalsIgnoreCase("JG")  && CPU.ula.flagSinal ==0){
					jump = true;
				}else if(comando[0].equalsIgnoreCase("JB")&& CPU.ula.flagSinal ==1){
					jump = true;
				}else if(comando[0].equalsIgnoreCase("JGE") && CPU.ula.flagZero == 1 && CPU.ula.flagSinal ==0){
					jump = true;
				}else if(comando[0].equalsIgnoreCase("JBE") && CPU.ula.flagZero == 1 && CPU.ula.flagSinal ==1){
					jump = true;
				}
				
				if(jump){
					executaJMP(comando);
				}
				
				comandoConcluido = true;
				ICC = 00;	
				
			}
		}
	}
	
	private void executaMOV(String[] comando) {
		//MOV alterando na memória
		if(comando[1].charAt(0)=='['){
			
			String s = (comando[1].substring(1));
			s = s.substring(0, s.length()-1);
			
			//Checando se o endereço de memória vem do IR  ou de outro registrador
			if(CPU.checkIfRegister(s)){
				passo.add("\n T1: MAR <-- " + s);
				if(s.equalsIgnoreCase("ax")){
					passo.add("Sinal de controle: " + Simulador.sinaisControle[5] + "000000000");
				}else if(s.equalsIgnoreCase("bx")){
					passo.add("Sinal de controle: " + Simulador.sinaisControle[6] + "000000000");
				}else if(s.equalsIgnoreCase("cx")){
					passo.add("Sinal de controle: " + Simulador.sinaisControle[7] + "000000000");
				}else if(s.equalsIgnoreCase("dx")){
					passo.add("Sinal de controle: " + Simulador.sinaisControle[8] + "000000000");
				}
				CPU.chooseToMAR(s);
			}
					
			else{
				passo.add("\n T1: MAR <-- IR");
				passo.add("Sinal de controle: " + Simulador.sinaisControle[4] + "000000000");
				CPU.busIn.enviarMAR(CPU.ir.getAdress(), CPU.ir.getOut()); // adress ou data
			}
			
			//MOV [AX],BX ou MOV [2],BX
			if(CPU.checkIfRegister(comando[2])){
				passo.add("\n T2: MBR <-- " + comando[2]);
				if(s.equalsIgnoreCase("ax")){
					passo.add("Sinal de controle: " + Simulador.sinaisControle[9] + "000000000");
				}else if(s.equalsIgnoreCase("bx")){
					passo.add("Sinal de controle: " + Simulador.sinaisControle[10] + "000000000");
				}else if(s.equalsIgnoreCase("cx")){
					passo.add("Sinal de controle: " + Simulador.sinaisControle[11] + "000000000");
				}else if(s.equalsIgnoreCase("dx")){
					passo.add("Sinal de controle: " + Simulador.sinaisControle[12] + "000000000");
				}
				CPU.chooseToMBR(comando[2]);
			}
			 //MOV [AX],2 ou MOV [2],2
			else{
				passo.add("\n T2: MBR <-- IR");
				passo.add("Sinal de controle: " + Simulador.sinaisControle[4] + "000000000");
				CPU.busIn.enviarMBR(CPU.ir.getData(), CPU.ir.getIn());
			}
			passo.add("\n T3: Memoria <--- MBR" );
			passo.add("Sinal de controle: " + Simulador.sinaisControle[12] + "000000000");
			CPU.busOut.enviarMemoria(CPU.mbr.getData());
		}
		
		//MOV AX,BX
		else if(CPU.checkIfRegister(comando[2])){
			passo.add("\n T1: " + comando[1] + " <-- " + comando[2]);
			if(comando[1].equalsIgnoreCase("ax")){
				if(comando[2].equalsIgnoreCase("bx")){
					passo.add("Sinal de controle: " + Simulador.sinaisControle[15] + "000000000");
				}else if(comando[2].equalsIgnoreCase("cx")){
					passo.add("Sinal de controle: " + Simulador.sinaisControle[16] + "000000000");
				}else if(comando[2].equalsIgnoreCase("dx")){
					passo.add("Sinal de controle: " + Simulador.sinaisControle[17] + "000000000");
				}
			}else if(comando[1].equalsIgnoreCase("bx")){
				if(comando[1].equalsIgnoreCase("ax")){
					passo.add("Sinal de controle: " + Simulador.sinaisControle[18] + "000000000");
				}else if(comando[1].equalsIgnoreCase("cx")){
					passo.add("Sinal de controle: " + Simulador.sinaisControle[19] + "000000000");
				}else if(comando[1].equalsIgnoreCase("dx")){
					passo.add("Sinal de controle: " + Simulador.sinaisControle[20] + "000000000\n");
				}
			}else if(comando[1].equalsIgnoreCase("cx")){
				if(comando[1].equalsIgnoreCase("ax")){
					passo.add("Sinal de controle: " + Simulador.sinaisControle[21] + "000000000");
				}else if(comando[1].equalsIgnoreCase("bx")){
					passo.add("Sinal de controle: " + Simulador.sinaisControle[22] + "000000000");
				}else if(comando[1].equalsIgnoreCase("dx")){
					passo.add("Sinal de controle: " + Simulador.sinaisControle[23] + "000000000\n");
				}
			}else if(comando[1].equalsIgnoreCase("dx")){
				if(comando[1].equalsIgnoreCase("ax")){
					passo.add("Sinal de controle: " + Simulador.sinaisControle[24] + "000000000");
				}else if(comando[1].equalsIgnoreCase("bx")){
					passo.add("Sinal de controle: " + Simulador.sinaisControle[25] + "000000000");
				}else if(comando[1].equalsIgnoreCase("cx")){
					passo.add("Sinal de controle: " + Simulador.sinaisControle[26] + "000000000");
				}
			}
			CPU.chooseToREG(comando[1], comando[2]);
			
		}
		
		//MOV AX,2
		else{
			passo.add("\n T1: " + comando[1] + " <-- IR");
			if(comando[1].equalsIgnoreCase("ax")){
				passo.add("Sinal de controle: " + Simulador.sinaisControle[27] + "000000000");
			}else if(comando[1].equalsIgnoreCase("bx")){
				passo.add("Sinal de controle: " + Simulador.sinaisControle[28] + "000000000");
			}else if(comando[1].equalsIgnoreCase("cx")){
				passo.add("Sinal de controle: " + Simulador.sinaisControle[29] + "000000000");
			}else if(comando[1].equalsIgnoreCase("dx")){
				passo.add("Sinal de controle: " + Simulador.sinaisControle[30] + "000000000");
			}
			CPU.chooseFromIR(comando[1]);
			
		}
		
		CPU.setRegister(comando[1],comando[2]);
	}
	
	private void executaADD(String[] comando){
		
		if(comando[1].charAt(0)=='['){
			String s = (comando[1].substring(1));
			s = s.substring(0, s.length()-1);
			
			if(CPU.checkIfRegister(s)){
				
				// Enviar endereço de algum registrado para MAR
				passo.add("\n T1: MAR <-- " + s);
				if(s.equalsIgnoreCase("ax")){
					passo.add("Sinal de controle: " + Simulador.sinaisControle[5] + "000000000");
				}else if(s.equalsIgnoreCase("bx")){
					passo.add("Sinal de controle: " + Simulador.sinaisControle[6] + "000000000");
				}else if(s.equalsIgnoreCase("cx")){
					passo.add("Sinal de controle: " + Simulador.sinaisControle[7] + "000000000");
				}else if(s.equalsIgnoreCase("dx")){
					passo.add("Sinal de controle: " + Simulador.sinaisControle[8] + "000000000");
				}
				CPU.chooseToMAR(s);
				
			}
			else{
				passo.add("\n T1: MAR <-- IR");
				passo.add("Sinal de controle: " + Simulador.sinaisControle[4] + "000000000");
				CPU.busIn.enviarMAR(CPU.ir.getAdress(), CPU.ir.getOut());
			}
			
			passo.add("\n T2: MBR <-- Memoria");
			passo.add("Sinal de controle: " + Simulador.sinaisControle[1] + "000100000");
			CPU.busOut.enviarMemoriaAdress(CPU.mar.getAdress());
			CPU.busOut.enviarMBR();
			
			
			passo.add("\n T3: X <-- MBR");
			passo.add("Sinal de controle: " + Simulador.sinaisControle[31] + "000000000");
			// Envia dado para X
			CPU.busIn.enviarX(CPU.mbr.getData(), CPU.mbr.getOut());
			
			//ADD [AX],BX | ADD [2],BX
			if(CPU.checkIfRegister(comando[2])){
				
				//Envia dado de algum REG para ULA
				passo.add("\n T4: ULA <-- " + comando[2]);
				if(comando[0].equalsIgnoreCase("add")){
					if(s.equalsIgnoreCase("ax")){
						passo.add("Sinal de controle: " + Simulador.sinaisControle[32] + "111000000");
					}else if(s.equalsIgnoreCase("bx")){
						passo.add("Sinal de controle: " + Simulador.sinaisControle[33] + "111000000");
					}else if(s.equalsIgnoreCase("cx")){
						passo.add("Sinal de controle: " + Simulador.sinaisControle[34] + "111000000");
					}else if(s.equalsIgnoreCase("dx")){
						passo.add("Sinal de controle: " + Simulador.sinaisControle[35] + "111000000");
					}
					CPU.chooseToULA(comando[2], "111");
				}else{
					if(s.equalsIgnoreCase("ax")){
						passo.add("Sinal de controle: " + Simulador.sinaisControle[32] + "001000000");
					}else if(s.equalsIgnoreCase("bx")){
						passo.add("Sinal de controle: " + Simulador.sinaisControle[33] + "001000000");
					}else if(s.equalsIgnoreCase("cx")){
						passo.add("Sinal de controle: " + Simulador.sinaisControle[34] + "001000000");
					}else if(s.equalsIgnoreCase("dx")){
						passo.add("Sinal de controle: " + Simulador.sinaisControle[35] + "001000000");
					}CPU.chooseToULA(comando[2], "001");
				}
			}
			
			//ADD [AX],2 | ADD [2],2
			else{
				passo.add("\n T4: ULA <-- IR");
				if(comando[0].equalsIgnoreCase("add")){
					passo.add("Sinal de controle: " + Simulador.sinaisControle[36] + "111000000");
					CPU.busIn.enviarULA(CPU.ir.getData(), CPU.ir.getOut(), "111");
				}else{
					passo.add("Sinal de controle: " + Simulador.sinaisControle[36] + "001000000");
					CPU.busIn.enviarULA(CPU.ir.getData(), CPU.ir.getOut(), "001");
				}
			}
			passo.add("\n T5: MBR <-- AC");
			passo.add("Sinal de controle: " + Simulador.sinaisControle[37] + "000000000");
			CPU.busIn.enviarMBR(CPU.AC.getData(), CPU.AC.getOut());
			
			passo.add("\n T6: Memoria <-- MBR"); 
			passo.add("Sinal de controle: " + Simulador.sinaisControle[14] + "000110000");
			CPU.busOut.enviarMemoria(CPU.mbr.getData());
			
		}
		else{
			// Enviar dado de um REG para X
			passo.add("\n T1: X <-- " + comando[1]);
			if(comando[1].equalsIgnoreCase("ax")){
				passo.add("Sinal de controle: " + Simulador.sinaisControle[42] + "000000000");
			}else if(comando[1].equalsIgnoreCase("bx")){
				passo.add("Sinal de controle: " + Simulador.sinaisControle[43] + "000000000");
			}else if(comando[1].equalsIgnoreCase("cx")){
				passo.add("Sinal de controle: " + Simulador.sinaisControle[44] + "000000000");
			}else if(comando[1].equalsIgnoreCase("dx")){
				passo.add("Sinal de controle: " + Simulador.sinaisControle[45] + "000000000");
			}
			CPU.chooseToX(comando[1]); /* Ver com a Larica se falta coisa nesse switch*/
			
			//ADD AX,BX
			if(CPU.checkIfRegister(comando[2])){
				
				// Envia dado de algum REG para ULA
				passo.add("\n T2: ULA <-- " + comando[2]);
				if(comando[0].equalsIgnoreCase("ADD")){
					if(comando[2].equalsIgnoreCase("ax")){
						passo.add("Sinal de controle: " + Simulador.sinaisControle[32] + "111000000");
					}else if(comando[2].equalsIgnoreCase("bx")){
						passo.add("Sinal de controle: " + Simulador.sinaisControle[33] + "111000000");
					}else if(comando[2].equalsIgnoreCase("cx")){
						passo.add("Sinal de controle: " + Simulador.sinaisControle[34] + "111000000");
					}else if(comando[2].equalsIgnoreCase("dx")){
						passo.add("Sinal de controle: " + Simulador.sinaisControle[35] + "111000000");
					}
					CPU.chooseToULA(comando[2], "111");
				}else{
					if(comando[2].equalsIgnoreCase("ax")){
						passo.add("Sinal de controle: " + Simulador.sinaisControle[32] + "001000000");
					}else if(comando[2].equalsIgnoreCase("bx")){
						passo.add("Sinal de controle: " + Simulador.sinaisControle[33] + "001000000");
					}else if(comando[2].equalsIgnoreCase("cx")){
						passo.add("Sinal de controle: " + Simulador.sinaisControle[34] + "001000000");
					}else if(comando[2].equalsIgnoreCase("dx")){
						passo.add("Sinal de controle: " + Simulador.sinaisControle[35] + "001000000");
					}
					CPU.chooseToULA(comando[2], "001");
				}
				
			}//add AX,2
			else{
				passo.add("\n T2: ULA <-- IR");
				if(comando[0].equalsIgnoreCase("add")){
					passo.add("Sinal de controle: " + Simulador.sinaisControle[36] + "111000000");	
					CPU.busIn.enviarULA(CPU.ir.getData(), CPU.ir.getOut(), "111");
				}else{
					passo.add("Sinal de controle: " + Simulador.sinaisControle[36] + "001000000");
					CPU.busIn.enviarULA(CPU.ir.getData(), CPU.ir.getOut(), "001");
				}
			}
			passo.add("\n T3: " + comando[1] + " <-- AC");
			if(comando[1].equalsIgnoreCase("ax")){
				passo.add("Sinal de controle: " + Simulador.sinaisControle[38] + "000000000");
			}else if(comando[1].equalsIgnoreCase("bx")){
				passo.add("Sinal de controle: " + Simulador.sinaisControle[39] + "000000000");
			}else if(comando[1].equalsIgnoreCase("cx")){
				passo.add("Sinal de controle: " + Simulador.sinaisControle[40] + "000000000");
			}else if(comando[1].equalsIgnoreCase("dx")){
				passo.add("Sinal de controle: " + Simulador.sinaisControle[41] + "000000000");
			}
			CPU.chooseFromAC(comando[1]);
		}
	}
		
	private void executaMUL(String[] comando){
		
		if(comando[1].charAt(0)=='['){
			
			String s = (comando[1].substring(1));
			s = s.substring(0, s.length()-1);
			
			//Checando se o endereço de memória vem do IR  ou de outro registrador

			//MUL [BX]
			if(CPU.checkIfRegister(s)){
				passo.add("\n T1: MAR <-- " + s);
				if(s.equalsIgnoreCase("ax")){
					passo.add("Sinal de controle: " + Simulador.sinaisControle[5] + "000000000");
				}else if(s.equalsIgnoreCase("bx")){
					passo.add("Sinal de controle: " + Simulador.sinaisControle[6] + "000000000");
				}else if(s.equalsIgnoreCase("cx")){
					passo.add("Sinal de controle: " + Simulador.sinaisControle[7] + "000000000");
				}else if(s.equalsIgnoreCase("dx")){
					passo.add("Sinal de controle: " + Simulador.sinaisControle[8] + "000000000");
				}
				CPU.chooseToMAR(s);
			}
			//MUL [2]
			else{ // REVER
				passo.add("\n T1: MAR <-- IR");
				passo.add("Sinal de controle: " + Simulador.sinaisControle[4] + "000000000");
				CPU.busIn.enviarMAR(CPU.ir.getData(), CPU.ir.getOut());
			}
			passo.add("\n T2: MBR <-- Memoria");
			passo.add("Sinal de controle: " + Simulador.sinaisControle[1] + "000100000");
			CPU.busOut.enviarMemoriaAdress(CPU.mar.getAdress());
			CPU.busOut.enviarMBR();
			
			passo.add("\n T3: X <-- MBR");
			passo.add("Sinal de controle: " + Simulador.sinaisControle[31] + "000000000");
			CPU.busIn.enviarX(CPU.mbr.getData(), CPU.mbr.getOut());
			
			passo.add("\n T4: ULA <-- AX");
			if(comando[0].equalsIgnoreCase("mul")){
				passo.add("Sinal de controle: " + Simulador.sinaisControle[32] + "010000000");
				CPU.busIn.enviarULA(CPU.ax.getData(), CPU.ax.getOut(), "010");
			}else{
				passo.add("Sinal de controle: " + Simulador.sinaisControle[32] + "011000000");
				CPU.busIn.enviarULA(CPU.ax.getData(), CPU.ax.getOut(), "011");
			}
						
			
			passo.add("\n T5: AX <--- AC" );
			passo.add("Sinal de controle: " + Simulador.sinaisControle[38] + "000000000");
			CPU.busIn.enviarAX(CPU.AC.getData(), CPU.AC.getOut());
		}
		else{
			//MUL BX
			if(CPU.checkIfRegister(comando[1])){
				passo.add("\n T1: X <-- " + comando[1]);
				if(comando[1].equalsIgnoreCase("ax")){
					passo.add("Sinal de controle: " + Simulador.sinaisControle[42] + "000000000");
				}else if(comando[1].equalsIgnoreCase("bx")){
					passo.add("Sinal de controle: " + Simulador.sinaisControle[43] + "000000000");
				}else if(comando[1].equalsIgnoreCase("cx")){
					passo.add("Sinal de controle: " + Simulador.sinaisControle[44] + "000000000");
				}else if(comando[1].equalsIgnoreCase("dx")){
					passo.add("Sinal de controle: " + Simulador.sinaisControle[45] + "000000000");
				}	
				CPU.chooseToX(comando[1]);
			}
			//MUL 2
			else{
				passo.add("\n T1: X <-- IR");
				passo.add("Sinal de controle: " + Simulador.sinaisControle[46] + "000000000");
				CPU.busIn.enviarX(CPU.ir.getData(), CPU.ir.getOut());
			}
			passo.add("\n  T2: ULA <-- AX");
			if(comando[0].equalsIgnoreCase("mul")){
				passo.add("Sinal de controle: " + Simulador.sinaisControle[32] + "010000000");
				CPU.busIn.enviarULA(CPU.ax.getData(), CPU.ax.getOut(), "010");
			}else{
				passo.add("Sinal de controle: " + Simulador.sinaisControle[32] + "011000000");
				CPU.busIn.enviarULA(CPU.ax.getData(), CPU.ax.getOut(), "011");	
			}
			passo.add("\n T3: AX <-- AC");
			passo.add("Sinal de controle: " + Simulador.sinaisControle[38] + "000000000");
			CPU.busIn.enviarAX(CPU.AC.getData(), CPU.AC.getOut());
			
			
			if(comando[0].equals("DIV")){
				passo.add("\n T4: DX <-- AC");
				passo.add("Sinal de controle: " + Simulador.sinaisControle[41] + "000000000");
				CPU.busIn.enviarDX(CPU.AC.getAdress(), CPU.AC.getOut());
			}
		}
	}
	
	private void executaINC(String[] comando){
		if(comando[1].charAt(0)=='['){
			
			String s = (comando[1].substring(1));
			s = s.substring(0, s.length()-1);
			
			//Checando se o endereço de memória vem do IR  ou de outro registrador

			//INC [BX]
			if(CPU.checkIfRegister(s)){
				passo.add("\n T1: MAR <-- " + s);
				if(s.equalsIgnoreCase("ax")){
					passo.add("Sinal de controle: " + Simulador.sinaisControle[5] + "000000000");
				}else if(s.equalsIgnoreCase("bx")){
					passo.add("Sinal de controle: " + Simulador.sinaisControle[6] + "000000000");
				}else if(s.equalsIgnoreCase("cx")){
					passo.add("Sinal de controle: " + Simulador.sinaisControle[7] + "000000000");
				}else if(s.equalsIgnoreCase("dx")){
					passo.add("Sinal de controle: " + Simulador.sinaisControle[8] + "000000000");
				}
				CPU.chooseToMAR(s);
			}
			//INC [2]
			else{
				passo.add("\n T1: MAR <-- IR");
				passo.add("Sinal de controle: " + Simulador.sinaisControle[4] + "000000000");
				CPU.busIn.enviarMAR(CPU.ir.getData(), CPU.ir.getOut());
			}
			passo.add("\n T2: MBR <-- Memoria"); // REVER
			passo.add("Sinal de controle: " + Simulador.sinaisControle[1] + "000000000");
			CPU.busOut.enviarMemoriaAdress(CPU.mar.getAdress());
			CPU.busOut.enviarMBR();
			
			passo.add("\n T3: ULA <-- MBR");
			if(comando[0].equalsIgnoreCase("inc")){
				passo.add("Sinal de controle: " + Simulador.sinaisControle[31] + "100000000");
				CPU.busIn.enviarULA(CPU.mbr.getData(), CPU.mbr.getOut(),"100");
			}else{
				passo.add("Sinal de controle: " + Simulador.sinaisControle[31] + "101000000");
				CPU.busIn.enviarULA(CPU.mbr.getData(), CPU.mbr.getOut(),"101");				
			}
				
			passo.add("\n T4: MBR <-- AC");
			passo.add("Sinal de controle: " + Simulador.sinaisControle[37] + "000000000");
			CPU.busIn.enviarMBR(CPU.AC.getData(), CPU.AC.getOut()); // REVER
			
			passo.add("\n T5: Memoria <--- MBR" );
			passo.add("Sinal de controle: " + Simulador.sinaisControle[14] + "000000000");

		}else{
			//INC AX
			passo.add("\n T1: ULA <-- " + comando[1]);
			if(comando[0].equalsIgnoreCase("inc")){
				if(comando[1].equalsIgnoreCase("ax")){
					passo.add("Sinal de controle: " + Simulador.sinaisControle[42] + "100000000");
				}else if(comando[1].equalsIgnoreCase("bx")){
					passo.add("Sinal de controle: " + Simulador.sinaisControle[43] + "100000000");
				}else if(comando[1].equalsIgnoreCase("cx")){
					passo.add("Sinal de controle: " + Simulador.sinaisControle[44] + "100000000");
				}else if(comando[1].equalsIgnoreCase("dx")){
					passo.add("Sinal de controle: " + Simulador.sinaisControle[45] + "100000000");
				}
				CPU.chooseToULA(comando[1],"100");
			}else{
				if(comando[1].equalsIgnoreCase("ax")){
					passo.add("Sinal de controle: " + Simulador.sinaisControle[42] + "101000000");
				}else if(comando[1].equalsIgnoreCase("bx")){
					passo.add("Sinal de controle: " + Simulador.sinaisControle[43] + "101000000");
				}else if(comando[1].equalsIgnoreCase("cx")){
					passo.add("Sinal de controle: " + Simulador.sinaisControle[44] + "101000000");
				}else if(comando[1].equalsIgnoreCase("dx")){
					passo.add("Sinal de controle: " + Simulador.sinaisControle[45] + "101000000");
				}
				CPU.chooseToULA(comando[1],"101");
			}
			
			
			passo.add("\n T2: " + comando[1] + " <-- AC");
			if(comando[1].equalsIgnoreCase("ax")){
				passo.add("Sinal de controle: " + Simulador.sinaisControle[38] + "000000000");
			}else if(comando[1].equalsIgnoreCase("bx")){
				passo.add("Sinal de controle: " + Simulador.sinaisControle[39] + "000000000");
			}else if(comando[1].equalsIgnoreCase("cx")){
				passo.add("Sinal de controle: " + Simulador.sinaisControle[40] + "000000000");
			}else if(comando[1].equalsIgnoreCase("dx")){
				passo.add("Sinal de controle: " + Simulador.sinaisControle[41] + "000000000");
			}
			CPU.chooseFromAC(comando[1]);
		}
	}
	
	private void executaCMP(String[] comando){
		if(comando[1].charAt(0)=='['){
			String s = (comando[1].substring(1));
			s = s.substring(0, s.length()-1);
			
			if(CPU.checkIfRegister(s)){
				passo.add("\n T1: MAR <-- " + comando[1]);
				if(s.equalsIgnoreCase("ax")){
					passo.add("Sinal de controle: " + Simulador.sinaisControle[5] + "000000000");
				}else if(s.equalsIgnoreCase("bx")){
					passo.add("Sinal de controle: " + Simulador.sinaisControle[6] + "000000000");
				}else if(s.equalsIgnoreCase("cx")){
					passo.add("Sinal de controle: " + Simulador.sinaisControle[7] + "000000000");
				}else if(s.equalsIgnoreCase("dx")){
					passo.add("Sinal de controle: " + Simulador.sinaisControle[8] + "000000000");
				}
				CPU.chooseToMAR(comando[1]);
			}else{
				passo.add("\n T1: MAR <-- IR");
				passo.add("Sinal de controle: " + Simulador.sinaisControle[4] + "000000000");
				CPU.busIn.enviarMAR(CPU.ir.getData(), CPU.ir.getOut());
			}
			
			passo.add("\n T2: MBR <-- Memoria");
			passo.add("Sinal de controle: " + Simulador.sinaisControle[1] + "000000000");
			CPU.busOut.enviarMemoriaAdress(CPU.mar.getAdress());
			CPU.busOut.enviarMBR();
			
			passo.add("\n T3: X <-- MBR");
			passo.add("Sinal de controle: " + Simulador.sinaisControle[31] + "000000000");
			CPU.busIn.enviarX(CPU.mbr.getData(), CPU.mbr.getOut());
			
			//CMP [AX],BX | CMP [2],BX
			if(CPU.checkIfRegister(comando[2])){
				passo.add("T4: ULA <-- + "+comando[2]);
				if(comando[2].equalsIgnoreCase("ax")){
					passo.add("Sinal de controle: " + Simulador.sinaisControle[32] + "110000000");
				}else if(comando[2].equalsIgnoreCase("bx")){
					passo.add("Sinal de controle: " + Simulador.sinaisControle[33] + "110000000");
				}else if(comando[2].equalsIgnoreCase("cx")){
					passo.add("Sinal de controle: " + Simulador.sinaisControle[34] + "110000000");
				}else if(comando[2].equalsIgnoreCase("dx")){
					passo.add("Sinal de controle: " + Simulador.sinaisControle[35] + "110000000");
				}
				CPU.chooseToULA(comando[2], "110");
			}
			
			//CMP [AX],2 | CMP [2],2
			else{
				passo.add("\n T4: ULA <-- IR");
				passo.add("Sinal de controle: " + Simulador.sinaisControle[36] + "110000000");
				CPU.busIn.enviarULA(CPU.ir.getData(), CPU.ir.getOut(), "110");
			}
		}
		
		passo.add("\n T1: X <-- " + comando[1]);
		if(comando[1].equalsIgnoreCase("ax")){
			passo.add("Sinal de controle: " + Simulador.sinaisControle[42] + "000000000");
		}else if(comando[1].equalsIgnoreCase("bx")){
			passo.add("Sinal de controle: " + Simulador.sinaisControle[43] + "000000000");
		}else if(comando[1].equalsIgnoreCase("cx")){
			passo.add("Sinal de controle: " + Simulador.sinaisControle[44] + "000000000");
		}else if(comando[1].equalsIgnoreCase("dx")){
			passo.add("Sinal de controle: " + Simulador.sinaisControle[45] + "00000000");
		}
		CPU.chooseToX(comando[1]);
		
		if(CPU.checkIfRegister(comando[2])){
			passo.add("\n T2: ULA <-- " + comando[2] );
			if(comando[2].equalsIgnoreCase("ax")){
				passo.add("Sinal de controle: " + Simulador.sinaisControle[32] + "110000000");
			}else if(comando[2].equalsIgnoreCase("bx")){
				passo.add("Sinal de controle: " + Simulador.sinaisControle[33] + "110000000");
			}else if(comando[2].equalsIgnoreCase("cx")){
				passo.add("Sinal de controle: " + Simulador.sinaisControle[34] + "110000000");
			}else if(comando[2].equalsIgnoreCase("dx")){
				passo.add("Sinal de controle: " + Simulador.sinaisControle[35] + "110000000");
			}
			CPU.chooseToULA(comando[2], "110");
			
		}else{
			passo.add("T2: ULA <-- IR");
			passo.add("Sinal de controle: " + Simulador.sinaisControle[36] + "110000000");
			CPU.busIn.enviarULA(CPU.ir.getData(), CPU.ir.getOut(), "110");
			
		}
		//O truque no compare é atualizar as flags
	}
	
	private void executaJMP(String[] comando){
		passo.add("\n T1: PC <-- IR" + comando[1]);
		if(comando[0].equals("JMP")){
			passo.add("Sinal de controle: " + Simulador.sinaisControle[47] + "000000001");
		}else if(comando.equals("JZ") && CPU.ula.flagZero == 1){
			passo.add("Sinal de controle: " + Simulador.sinaisControle[47] + "000000010");
		}else if(comando.equals("JNZ") && CPU.ula.flagZero == 0){
			passo.add("Sinal de controle: " + Simulador.sinaisControle[47] + "000000011");
		}else if(comando.equals("JE") && CPU.ula.flagZero == 1){
			passo.add("Sinal de controle: " + Simulador.sinaisControle[47] + "000000100");
		}else if(comando.equals("JNE") && CPU.ula.flagZero == 1){
			passo.add("Sinal de controle: " + Simulador.sinaisControle[47] + "000000101");
		}else if(comando.equals("JG")  && CPU.ula.flagSinal ==0){
			passo.add("Sinal de controle: " + Simulador.sinaisControle[47] + "000000110");
		}else if(comando.equals("JB")  && CPU.ula.flagSinal ==1){
			passo.add("Sinal de controle: " + Simulador.sinaisControle[47] + "000000111");
		}else if(comando.equals("JGE") && CPU.ula.flagZero == 1 && CPU.ula.flagSinal ==0){
			passo.add("Sinal de controle: " + Simulador.sinaisControle[47] + "000001000");
		}else if(comando.equals("JBE") && CPU.ula.flagZero == 1 && CPU.ula.flagSinal ==1){
			passo.add("Sinal de controle: " + Simulador.sinaisControle[47] + "000001001");
		}
		CPU.busIn.enviarPC(CPU.ir.getData(), CPU.ir.getOut());
	}
}