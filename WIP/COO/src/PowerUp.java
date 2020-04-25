import java.awt.Color;

interface PowerUpType{
	void draw();
}

class PowerUp1 implements PowerUpType{

	@Override
	public void draw() {
		if((int) (Math.random() * 101)%2==0){
			GameLib.setColor(Color.ORANGE);
		}else{
			GameLib.setColor(Color.RED);
		}
	}
	
}

class PowerUp2 implements PowerUpType{

	@Override
	public void draw() {
		if((int) (Math.random() * 101)%2==0){
			GameLib.setColor(Color.WHITE);
		}else{
			GameLib.setColor(Color.GREEN);
		}
	}
	
}

public class PowerUp extends Component{
	
	private PowerUpType type;
	private long nextPower;
	
	PowerUp(int type, long time, double x, double y){
		setState(0);
		setType(type);
		setPowerUpType(type);
		setTime(time);
		setcoordX(x);
		setcoordY(y);
		setVel(0.15);
		setRadius(10);
		setPowerUpType(type);
		
	}

	public long getNextPower() {
		return nextPower;
	}

	public void setNextPower(long nextPower) {
		this.nextPower = nextPower;
	}

	@Override
	void atualiza(long delta) {
		if(getState() == 1){
			if(getCoordenadaY() < -15){
				setState(0);
			}else{
				setcoordY(getCoordenadaY()+getVel() * delta);
			}
		}
	}
	
	void verifica(long levelTime){
		if(System.currentTimeMillis()>(getTime()+levelTime)){
			setState(1);
			setPowerUpType(getType());
		}
	}
	
	private void setPowerUpType(int i) {
		if(i ==1){
			this.type = new PowerUp1();
		}else if(i==2){
			this.type = new PowerUp2();
		}
	}

	void draw(long currentTime){
		if(getState() == ACTIVE){
			type.draw();
			GameLib.drawDiamond(getCoordenadaX(), getCoordenadaY(), getRadius());
		}
	}

	boolean checkCollision(Player player){
		double dx = player.getCoordenadaX() - this.getCoordenadaX();
		double dy = player.getCoordenadaY() - this.getCoordenadaY();
		double dist = Math.sqrt(dx * dx + dy * dy);
		return (dist < (player.getRadius() + getRadius()) * 0.8);	
	}

	public PowerUpType getPowerUpType() {
		return type;
	}

}