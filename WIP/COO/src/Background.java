import java.util.ArrayList;
public class Background{
	private ArrayList<BackgroundElement> elementos = new ArrayList<BackgroundElement>();
	private double speed;
	private double count;
	private int height;
	private int width;
	private int density;
	
	Background(double s, double c, int d, int w, int h){
		this.speed = s;
		this.count = c;
		this.height = h;
		this.width = w;
		this.density = d;
		
		adicionarElementos();
		
	}
	
	void adicionarElementos(){		
		for(int i = 0; i <getDensity();i++){
			elementos.add(new BackgroundElement());
		}	
	}
	
	void incCount(long delta){
		this.count += this.speed*delta;
	}
	
	double getCount(){
		return this.count;
	}
	
	int getHeight(){
		return this.height;
	}
	
	int getWidth(){
		return this.width;
	}
	
	int getDensity(){
		return this.density;
	}
	
	void desenhaElementos(){
		for(BackgroundElement elemento: elementos)
		{
			//GameLib.fillRect(background1_X[i], (background1_Y[i] + background1_count) % GameLib.HEIGHT, 3, 3);
			GameLib.fillRect(elemento.getBackgroundX(), (elemento.getBackgroundY() + this.getCount()) % GameLib.HEIGHT, getWidth(), getHeight());
		}
	}
}

class BackgroundElement {
	private double backgroundX;
	private double backgroundY;
	
	BackgroundElement(){
		setBackgroundX(Math.random() * GameLib.WIDTH);
		setBackgroundY(Math.random() * GameLib.HEIGHT);
	}

	public double getBackgroundX() {
		return backgroundX;
	}

	public void setBackgroundX(double backgroundX) {
		this.backgroundX = backgroundX;
	}

	public double getBackgroundY() {
		return backgroundY;
	}

	public void setBackgroundY(double backgroundY) {
		this.backgroundY = backgroundY;
	}
}