import java.util.ArrayList;

// Na tabela de processos, está sendo trabalhado como um lista de processos, no qual
// cada item(processo) é uma linha da tabela. Já as colunas são trabalhadas dentro de cada Objeto BCP
// em seu construtor.
public class ProcessTable {
      ArrayList<BCP> processList = new ArrayList<BCP>();
      private int processNum = 0;

      // Adicionar processos
      public void addProcess(BCP pr) {
        processList.add(pr);
        setProcessNum(getProcessNum() + 1);
      }

      // Remove processo da tabela após o comando "SAIDA"
      public void removeProcess(BCP pr) {
    	  processList.remove(pr.getMemoryRef());
    	  for(int i = 0; i<processList.size();i++){
  			BCP p = processList.get(i);
  			p.setMemoryRef(i);
  		}
      }

      // Atualiza algum dado do processo após escalonar
      // buscar processo a partir do nome
      public void refreshProcess(BCP pr) {
        String prName = pr.getName();
        String processIntoList;
    	  for(int i = 0; i<processList.size(); i++) {
    		  processIntoList = processList.get(i).getName(); // retorna o nome de um processo da tabela de processos
    		  if(processIntoList.equalsIgnoreCase(prName)) { // se o nome for igual, troca o da tabela pelo pr(atualizado)
    			  processList.set(i,pr);
    		  }
    	  }
      }
      
      public boolean isEmpty(){
    	  return processList.isEmpty();
      }

	public int getProcessNum() {
		return processNum;
	}

	public void setProcessNum(int processNum) {
		this.processNum = processNum;
	}
  }
	