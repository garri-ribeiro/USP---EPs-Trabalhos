package CPU;

public class ULA {
	private String portIn;
	public int flagZero = 0;
	public int flagSinal = 0;

/*Sinais de controle
 *	000 - null
 	001 - SUB
	010 - MUL
	011 - DIV
	100 - INC
	101 - DEC
	110 - CMP
	111 - ADD
 */
	
	ULA(String port){
		portIn = port;
	}
	
	// get das portas
	public String getIn()  {
		return portIn;
	}
	
	public void operacao(String num, String controle){
		if(controle.equals("111")){
			add(num);
		}else if(controle.equals("001")){
			sub(num);
		}else if(controle.equals("010")){
			mul(num);
		}else if(controle.equals("011")){
			div(num);
		}else if(controle.equals("100")){
			inc(num);
		}else if(controle.equals("101")){
			dec(num);
		}else if(controle.equals("110")){
			cmp(num);
		}
	}
	
	private void add(String a){
		int soma = Integer.parseInt(a) + Integer.parseInt(CPU.x.getData());
		atualizaFlags(soma);
		CPU.AC.setAdress(String.valueOf(soma));
	}
	
	private void sub(String a){
		int sub = Integer.parseInt(a) - Integer.parseInt(CPU.x.getData());
		atualizaFlags(sub);
		CPU.AC.setAdress(String.valueOf(sub)); 
	}
	
	private void mul(String a){
		int mul = Integer.parseInt(a) * Integer.parseInt(CPU.x.getData());
		atualizaFlags(mul);
		CPU.AC.setAdress(String.valueOf(mul));
	}
	
	private void div(String a){
		int div = Integer.parseInt(a) / Integer.parseInt(CPU.x.getData());
		atualizaFlags(div);
		CPU.AC.setAdress(String.valueOf(div));
	}
	
	private void inc(String a){
		int sub = Integer.parseInt(a) + 1;
		atualizaFlags(sub);
		CPU.AC.setAdress(String.valueOf(sub)); 
	}
	
	private void dec(String a){
		int sub = Integer.parseInt(a) - 1;
		atualizaFlags(sub);
		CPU.AC.setAdress(String.valueOf(sub)); 
	}
	
	private void cmp(String a){
		int sub = Integer.parseInt(a) - Integer.parseInt(CPU.x.getData());
		atualizaFlags(sub);
	}
	
	private void atualizaFlags(int num){
	
		if(num == 0){
			flagZero = 1;
		}else{
			flagZero = 0;
		}
		
		if(num<0){
			flagSinal = 1;
		}else{
			flagSinal = 0;
		}
		
	}
}
