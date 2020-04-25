package execuçao;
import java.util.*;

import processamento.*;
public class Main {	

	public static void main(String [] args){
		
		System.out.println("Insira o programa dessa forma e de ENTER: \n VAR AX,BX");
		System.out.println("Digite executa para rodar finalizar");
		Simulador simulador = new Simulador(lerComandos());
		simulador.executa();
	}
	
	public static String[] lerComandos(){
		Scanner sc = new Scanner(System.in);
		String[] comando = new String[100];
		String[] comandos;
		String line = sc.nextLine();
		int i = 0;
		while(!(line.equalsIgnoreCase("executa"))){
			comando[i] = line;
			i++;
			line = sc.nextLine();
		}sc.close();
		comandos = new String[i];
		for(int k = 0; k<comandos.length;k++){
			comandos[k] = comando[k];
		}
		return comandos;		
	}
}