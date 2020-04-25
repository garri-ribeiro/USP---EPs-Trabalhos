import java.util.ArrayList;

public class BossFinal extends Component{
	
	ArrayList<Boss> filhos = new ArrayList<Boss>();
	Boss mother;
	
	
	BossFinal(double a, int f, long c, double d, double e){
		setState(0);
		setTime(c);
		mother = new Boss(15,f,c,((GameLib.WIDTH/2)),e);
		for(int i=1;i<5;i++){
				filhos.add(new Boss(25,f,c,(((double)GameLib.WIDTH/5)*i),e));
		}
	}
	
	
	
	void damage() {
		
	}
	
	
	void explode(){
		
	}
	
	
	void collision(double dist, double radius){
		
		
		
		if (dist < mother.radius){
			
			mother.state = 3;
			mother.hitStart = System.currentTimeMillis();
			mother.damage();
		}
		
		for(Boss b : filhos){
			
			if (dist < b.radius){
				b.state = 3;
				b.hitStart = System.currentTimeMillis();
				b.damage();
			}
			
		}
		
		
	}
	
	@Override
	public void draw(long currentTime) {
		
		mother.draw(currentTime);
		for(Boss b: filhos)
			b.draw(currentTime);
	}
	
	@Override
	public void atualiza(long delta){
		mother.atualiza(delta);
		for(Boss b: filhos)
			b.atualiza(delta);
	}
	
	@Override
	public void verifica(long levelTime){
		if(System.currentTimeMillis()>(getTime()+levelTime)){
			setState(1);
			mother.verifica(levelTime);
			for(int i=0;i<4;i++){
					filhos.get(i).verifica(levelTime);
			}			
		}
	}
	
	
}