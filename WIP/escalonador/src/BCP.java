import java.util.ArrayList;

public class BCP {
    private int programCounter;
    private String statusProcess,reg1,reg2,name;
    private int memoryRef;
    

    // Ao receber ler um arquivo, indexar cada linha do processo em um array.
    // De acordo com o EP, os processos terão no máximo 22 linhas (1ºnome e o resto programa)
    private ArrayList<String> process = new ArrayList<String>();

    BCP(String sp, int mr, String nm) {
      this.programCounter = 0; // todo processo começa na posição 1
      this.statusProcess = sp;
      this.reg1 = "0";
      this.reg2 = "0";
      this.setMemoryRef(mr);
      this.name = nm;
    }

    // Função para varrer o processo
    public String nextCommand() {
      String command = process.get(programCounter);
      if(command.equalsIgnoreCase("SAIDA")) return command;
      programCounter++;
      return command;
    }

	public void add(String nextLine) {
		process.add(nextLine);
	}

    //getters e setters do construtor
    //(não tem PC, porque ele não pode ser alterado por ninguem fora do escopo ATÉ ENTÃO)
    public void setStateProcess(String stateP) {
      statusProcess = stateP;
    }
    public String getStateProcess() {
      return statusProcess;
    }

    public void setReg1(String r1) {
      reg1 = r1;
    }
    public String getReg1() {
      return reg1;
    }

    public void setReg2(String r2) {
      reg2 = r2;
    }
    public String getReg2() {
      return reg2;
    }

    public void setName(String n) {
      name = n;
    }
    public String getName() {
      return name;
    }
    // fim getter e setters

	public int getMemoryRef() {
		return memoryRef;
	}

	public void setMemoryRef(int memoryRef) {
		this.memoryRef = memoryRef;
	}
}
