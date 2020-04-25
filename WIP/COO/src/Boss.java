import java.awt.*;
import java.util.ArrayList;

public class Boss extends Spaceship{
	private int hp;
	private double rotacao;
	private double angulo;
	int state=0;
	int radius = 20;
	long lastwave;
	int n=0;
	long hitStart;
	long hitEnd;
	long walkStart;
	Color cor;
	int index;
	double x = getCoordenadaX();
	double y = 150;
	int hpi;
	//states: 1 vivo, 2 morto, 3 atingido, 4 nascendo/andando
	//type:15 (mother), 25(children)
	
	Boss(int type, int life, long time, double x, double y){
		setAngle(3 * Math.PI / 2);
		setTime(time);
		setVel(0.20 + Math.random() * 0.15);
		radius = type;
		this.hp=life;
		this.hpi = life;
		if (type==15) this.cor = Color.RED; else this.cor = Color.GREEN;
		setcoordX(x);					
		setcoordY(y);
		this.walkStart = time;
		
		ArrayList<Projectile> pr = new ArrayList<Projectile>();
		if(type==15){
			for(int i = 0; i<100;i++){
				pr.add(new Projectile(2));
			}
		}
		else
		{
			for(int i = 0; i<16;i++){
				pr.add(new Projectile(2));
				
			}
		}
		setProjectiles(pr);
	}
	
	public double getAngle() {
		return angulo;
	}
	
	public double getRadius(){
		return radius;		
	}
	
	public double getRotacao(){
		return rotacao;
	}

	public void setAngle(double d) {
		angulo = d;
	}
	
	public void setRotacao(double r){
		rotacao = r;
	}
	
	public void decHp(){
		hp--;
	}
	public int getHp(){
		return hp;
	}
	
	@Override
	void damage() {
		if(hp<=0){
			explode();
		}else{
			decHp();
		}
	}
	
	@Override
	void explode(){
		GameLib.drawExplosion(this.getCoordenadaX(), this.getCoordenadaY(),1);
		
		state = 2;
		RunGame.levelTime = System.currentTimeMillis();
		RunGame.change = true;
	}
	
	@Override
	void collision(double dist, double radius){
		if(state!=4 && state != 0){
			if (dist < this.radius){
				state = 3;
				hitStart = System.currentTimeMillis();
				damage();
			}
		}
	}
	
	@Override
	public void draw(long currentTime) {
		
		if(state==1||state==4){
			
			GameLib.setColor(cor);		
			GameLib.drawCircle(this.getCoordenadaX(), this.getCoordenadaY(), radius);
		}
		else if(state==3){
			if((int) (Math.random() * 101)%2==0){
				GameLib.setColor(Color.GREEN);
			}else{
				GameLib.setColor(Color.RED);
			}
			GameLib.drawCircle(this.getCoordenadaX(), this.getCoordenadaY(), this.radius);
			
			
			if(System.currentTimeMillis() >= hitStart + 1000)
				state=1;
			
		}	
		
		if(state!=2 && state!=4 && state != 0){
			GameLib.setColor(Color.RED);
			for(Projectile p : getProjectiles()){
				p.draw(System.currentTimeMillis());
			}			
			
			double scrratio = (double)GameLib.WIDTH/hpi;
			
			GameLib.setColor(Color.RED);
			
			for(int i = this.hp,k = 25;i>0;i--){
				GameLib.drawLine(0, 25, ( hp*scrratio ), 25);
				GameLib.drawLine(0, 26, ( hp*scrratio ), 26);
			}
			
		}
	}
	
	@Override
    public void atualiza(long delta) {
		if(state==4){
			
			if(radius==15){
				
				setcoordY(getCoordenadaY()+(getVel()*Math.sin(getAngle())*delta*(-1.0)));
			
				if(this.getCoordenadaY()>=y){
						state = 1;
				}
			}
			else
			{
				this.setcoordY(this.getCoordenadaY()+2);
				if(this.getCoordenadaY()>=200)
				state = 1;
				
				setcoordY(getCoordenadaY()+(getVel()*Math.sin(getAngle())*delta*(-1.0)));
			
				if(this.getCoordenadaY()>=200)
				{
					if(this.getCoordenadaX()<x)							
						this.setcoordX(this.getCoordenadaX()+1);
					if(this.getCoordenadaX()>x)
						this.setcoordX(this.getCoordenadaX()-1);
					else if (this.getCoordenadaX()==x)
						state = 1;
					
				}
				
			}
			
			
			
		}
	
			if(state == 2)
                getProjectiles().clear();
           
            if(state!=4&& state != 0){
                for(Projectile p : getProjectiles()){
                p.atualiza(delta);         
            }
           
           
            if(state!=2&& state != 0){
           
            lastwave+=delta;
            boolean shootNow = false;
           
            if(lastwave>1000 ){
                shootNow=true;             
                lastwave = 0;
            }
           
            if(shootNow){
               
                double [] angles = { Math.PI/2 + Math.PI/8, Math.PI/2, Math.PI/2 - Math.PI/8 };
               
                int [] freeArray = Component.findFreeIndex(this.getProjectiles(), angles.length);
                int c=0;
                int constante=20;
               
                if(radius!=15)
                    constante=3;
               
                    for(int k=n; k < n+constante; k++){
                   
                        Projectile free = this.getProjectiles().get(k);
                        if(free != null){
                           
                            double a =  Math.PI/9;                     
                            double vx = Math.cos(a);
                            double vy = Math.sin(a);
                           
                            free.setcoordX(this.getCoordenadaX());
                            free.setcoordY(this.getCoordenadaY());
                            free.setVelocidadeX(c*0.01+ k*-0.0018);
                            free.setVelocidadeY(vy*0.35+((c*c)-14*c)*0.0001);
                            free.setState(1);
                       
                        }
                        c++;
                    }
               
               
               
                if(n==constante*4)
                    n=0;
                else   
                    n=n+constante;
               
            }
            }
            }
    }
	
	@Override
	public void verifica(long levelTime) {
		if(state == 0){
			if(System.currentTimeMillis()>(getTime()+levelTime)){
				state = 4;
			}
		}
	}
}