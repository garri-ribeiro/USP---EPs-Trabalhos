package CPU;

public class CPU { 
	
	// Barramentos
	public static BarramentoInterno busIn = new BarramentoInterno();
	public static BarramentoExterno busOut = new BarramentoExterno();
		
	// ULA -> Unidade BlaBlaBla Aritimetica
	public static ULA ula = new ULA("18");
	
	// Registradores especiais
	public static REG pc = new REG("1", "2", "0", "0");
	public static REG mar = new REG("3", "20", "0", "0");
	public static REG mbr = new REG("4", "5", "0", "0");
	public static REG ir = new REG("14", "15", "0", "0");	
	
	// Registradores de dados
	public static REG ax = new REG("6", "7", "0", "0");
	public static REG bx = new REG("8", "9", "0", "0");
	public static REG cx = new REG("10", "11", "0", "0");
	public static REG dx = new REG("12", "13", "0", "0");
	public static REG x = new REG("17", " ", "0", "0");
	public static REG AC = new REG("0","19","0", "0");
	
	
	public static void setRegister(String string, String string2) {
		REG reg1 = getRegister(string);
		if(checkIfRegister(string2)){
			REG reg2 = getRegister(string2);
			reg1.setAdress(reg2.getAdress());
		}else{
			reg1.setAdress(string2);
		}
		
	}
	
	public static REG getRegister(String string){
		if(string.equals("ax")){
			return CPU.ax;
		}else if(string.equals("bx")){
			return CPU.bx;
		}else if(string.equals("cx")){
			return CPU.cx;
		}else{
			return CPU.dx;
		}
	}
	
	public static boolean checkIfRegister(String string) {
		if(string.equalsIgnoreCase("ax")||string.equalsIgnoreCase("bx")||string.equalsIgnoreCase("cx")||string.equalsIgnoreCase("dx")){
			return true;
		}return false;		
	}
	
	// METODOS PARA ESCOLHER CAMINHOS
	
	
	// ESCOLHA DE REGISTRADORES PARA ALGUMA CELULA
	//////////////////////////////////////////////
	
	public static void chooseToREG(String reg1, String reg2) {
	
		if(reg1 == "ax") {
			
		}
		switch(reg1) {
		case "ax":
			if(reg2 == "bx")	 	CPU.busIn.enviarAX(CPU.bx.getData(), CPU.bx.getOut());
			else if(reg2 =="cx") 	CPU.busIn.enviarAX(CPU.cx.getData(), CPU.cx.getOut());
			else if(reg2 == "dx") 	CPU.busIn.enviarAX(CPU.dx.getData(), CPU.dx.getOut());
			break;
		case "bx":
			if(reg2 == "ax")	 	CPU.busIn.enviarAX(CPU.ax.getData(), CPU.ax.getOut());
			else if(reg2 =="cx") 	CPU.busIn.enviarAX(CPU.cx.getData(), CPU.cx.getOut());
			else if(reg2 == "dx") 	CPU.busIn.enviarAX(CPU.dx.getData(), CPU.dx.getOut());
			break;
		case "cx":
			if(reg2 == "ax")	 	CPU.busIn.enviarAX(CPU.ax.getData(), CPU.ax.getOut());
			else if(reg2 =="bx") 	CPU.busIn.enviarAX(CPU.bx.getData(), CPU.bx.getOut());
			else if(reg2 == "dx") 	CPU.busIn.enviarAX(CPU.dx.getData(), CPU.dx.getOut());
			break;
		case "dx":
			if(reg2 == "ax")	 	CPU.busIn.enviarAX(CPU.ax.getData(), CPU.ax.getOut());
			else if(reg2 =="bx") 	CPU.busIn.enviarAX(CPU.bx.getData(), CPU.bx.getOut());
			else if(reg2 == "cx") 	CPU.busIn.enviarAX(CPU.cx.getData(), CPU.cx.getOut());
			break;				
		}
	} 
	
	public static void chooseToMAR(String s) {
		
		switch(s) {
		case "ax":
			CPU.busIn.enviarMAR(CPU.ax.getAdress(), CPU.ax.getOut());
			break;
		case "bx":
			CPU.busIn.enviarMAR(CPU.bx.getAdress(), CPU.bx.getOut());
			break;
		case "cx":
			CPU.busIn.enviarMAR(CPU.cx.getAdress(), CPU.cx.getOut());
			break;
		case "dx":
			CPU.busIn.enviarMAR(CPU.dx.getAdress(), CPU.dx.getOut());
			break;
			
		}
	}
	
	public static void chooseToMBR(String s) {
		
		switch(s) {
		case "ax":
			CPU.busIn.enviarMBR(CPU.ax.getData(), CPU.ax.getOut());
			break;
		case "bx":
			CPU.busIn.enviarMBR(CPU.bx.getData(), CPU.bx.getOut());
			break;
		case "cx":
			CPU.busIn.enviarMBR(CPU.cx.getData(), CPU.cx.getOut());
			break;
		case "dx":
			CPU.busIn.enviarMBR(CPU.dx.getData(), CPU.dx.getOut());
			break;				
		}
	}
	
	
	public static void chooseToX(String s) {
		
		switch(s) {
		case "ax":
			CPU.busIn.enviarX(CPU.ax.getData(), CPU.ax.getOut());
			break;
		case "bx":
			CPU.busIn.enviarX(CPU.bx.getData(), CPU.bx.getOut());
			break;
		case "cx":
			CPU.busIn.enviarX(CPU.cx.getData(), CPU.cx.getOut());
			break;
		case "dx":
			CPU.busIn.enviarX(CPU.dx.getData(), CPU.dx.getOut());
			break;				
		}
	}
	
	public static void chooseToULA(String s, String operacao) {
		
		switch(s) {
		case "ax":
			CPU.busIn.enviarULA(CPU.ax.getData(), CPU.ax.getOut(), operacao);
			break;
		case "bx":
			CPU.busIn.enviarULA(CPU.bx.getData(), CPU.bx.getOut(), operacao);
			break;
		case "cx":
			CPU.busIn.enviarULA(CPU.cx.getData(), CPU.cx.getOut(), operacao);
			break;
		case "dx":
			CPU.busIn.enviarULA(CPU.dx.getData(), CPU.dx.getOut(), operacao);
			break;				
		}
	}
	
	
	// ESCOLHA DE ALGUMA CELULA PARA REGISTRADORES
	//////////////////////////////////////////////
	public static void chooseFromAC(String s) {
		
		switch(s) {
		case "ax":
			CPU.busIn.enviarAX(CPU.AC.getData(), CPU.AC.getOut());
			break;
		case "bx":
			CPU.busIn.enviarBX(CPU.AC.getData(), CPU.AC.getOut());
			break;
		case "cx":
			CPU.busIn.enviarCX(CPU.AC.getData(), CPU.AC.getOut());
			break;
		case "dx":
			CPU.busIn.enviarDX(CPU.AC.getData(), CPU.AC.getOut());
			break;
		}
	}
	
	public static void chooseFromIR(String s) {
			
			switch(s) {
			case "ax":
				CPU.busIn.enviarAX(CPU.ir.getData(), CPU.ir.getOut());
				break;
			case "bx":
				CPU.busIn.enviarBX(CPU.ir.getData(), CPU.ir.getOut());
				break;
			case "cx":
				CPU.busIn.enviarCX(CPU.ir.getData(), CPU.ir.getOut());
				break;
			case "dx":
				CPU.busIn.enviarDX(CPU.ir.getData(), CPU.ir.getOut());
				break;
			}
	}
	
}
