package CPU;

public class REG {
	
	private String portIn;
	private String portOut;
	private String adress;
	private String data;
	
	REG(String portIn, String portOut, String endereco, String dado){
		this.portIn = portIn;
		this.portOut = portOut;
		this.adress = endereco;
		this.data = dado;
	}
	
	// get e get dos endereços
	public void setAdress(String end) {
		adress = end;
		
	}
	public String getAdress(){
		return adress;
	}
	
	// get e set dos dados
	public void setData(String dat) {
		data = dat;
	}
	
	public String getData(){
		return data;
	}
	
	// get das portas
	public String getIn()  {
		return portIn;
	}
	public String getOut()  {
		return portOut;
	}
}