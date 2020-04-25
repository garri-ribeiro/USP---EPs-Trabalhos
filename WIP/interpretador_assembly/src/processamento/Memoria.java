package processamento;
import java.util.Scanner;

public class Memoria {
	static private String portIn = "23";
	static private String portOut = "24";
	static private String adress;
	static private String data;
	static private String[] comandos;
	static private String[] dados = new String[100];
	

	static public void recebeComandos(String[] com) {
		comandos = com;
	}
	
	static public String[] proximaInstrucao(int adress){
		Scanner sc = new Scanner(comandos[adress]);
		String[] instrucao;
		String comando = sc.next();
		sc.useDelimiter(",");
		String reg = sc.next().substring(1);
		if(sc.hasNext()){
			instrucao = new String[3];
			instrucao[0] = comando;
			instrucao[1] = reg;
			instrucao[2] = sc.next();
		}else{
			instrucao = new String[2];
			instrucao[0] = comando;
			instrucao[1] = reg;
		}sc.close();
		return instrucao;	
	}
	
	// Busca o dados na memoria a partir do endereço fornecido
	static public String buscaDado(String end){
		return "0";
	}
	
	// Guarda dados na memoria
	static public void guardaDado(String dat){
		
	}
	
	// get e set dos Endereços
	static public void setAdress(String end) {
		adress = end;
		
	}
	static public String getAdress(){
		return adress;
	}
	
	// get e set dos Dados
	static public void setData(String dat) {
		data = dat;
		
	}
	static public String getData(){
		return data;
	}
	
	// get e set das Portas
	static public String getIn()  {
		return portIn;
	}
	static public String getOut()  {
		return portOut;
	}
}
