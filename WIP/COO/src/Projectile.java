import java.awt.Color;

public class Projectile extends Component {
	private double velocidadeX;
	private double velocidadeY;
	private double rotacao;
	private double angulo;
	
	Projectile(int n){
		setState(0);
		setRadius(2.0);
		setType(n);
	}
	
	public double getAngle() {
		return angulo;
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
	
	public double getVelocidadeX() {
		return velocidadeX;
	}

	public void setVelocidadeX(double velocidade) {
		this.velocidadeX = velocidade;
	}

	public double getVelocidadeY() {
		return velocidadeY;
	}

	public void setVelocidadeY(double velocidade) {
		this.velocidadeY = velocidade;
	}
	
	@Override
	void atualiza(long delta) {
		if(getState() == 1){
			if(getCoordenadaY() > GameLib.HEIGHT){
				setState(0);
			}
			if(getCoordenadaY() < 0){
				setState(0);
			}else{
				setcoordX(getCoordenadaX()+getVelocidadeX() * delta);
				setcoordY(getCoordenadaY()+getVelocidadeY() * delta);
			}
			
		}
	}
	
	@Override
	void verifica(Player player) {
		if(getType() == 3 || getState() == 1){
			setcoordX(player.getCoordenadaX() + (player.getRadius()*4*Math.cos(player.getAngle())));
			setcoordY(player.getCoordenadaY() + (player.getRadius()*4*Math.sin(player.getAngle()) * (-1.0)));
			player.setAngle(player.getAngle()+(player.getRotacao()));	
			setRadius(player.getRadius());
			player.setRotacao(0.06);
			setState(1);
			draw(System.currentTimeMillis());
		}
	}

	@Override
	void draw(long currentTime) {
		if(getType() == 1){
			if(getState() == ACTIVE){
				GameLib.setColor(Color.GREEN);
				GameLib.drawLine(getCoordenadaX(), getCoordenadaY() - 5, getCoordenadaX(), getCoordenadaY() + 5);
				GameLib.drawLine(getCoordenadaX()-1, getCoordenadaY() - 3, getCoordenadaX()-1, getCoordenadaY() + 3);
				GameLib.drawLine(getCoordenadaX()+1, getCoordenadaY() - 3, getCoordenadaX()+1, getCoordenadaY() + 3);
				
			}
		}else if(getType() == 2){
			GameLib.setColor(Color.RED);
			GameLib.drawCircle(getCoordenadaX(), getCoordenadaY(), getRadius());
		}else{
			GameLib.setColor(Color.RED);
			GameLib.drawCircle(getCoordenadaX(), getCoordenadaY(), getRadius());
		}
	}

	@Override
	void verifica(long levelTime) {
	}
}