import java.awt.Color;
import java.util.ArrayList;

interface EnemyType{
	void atualiza(Enemy enemy, long delta);
	void verifica(Enemy enemy);
	void draw(Enemy enemy);
}

class Enemy1 implements EnemyType{

	@Override
	public void atualiza(Enemy enemy, long delta) {
		if(enemy.getCoordenadaY() > GameLib.HEIGHT + 10) {
			enemy.setState(0);
		}
		else {
			enemy.setcoordX(enemy.getCoordenadaX()+(enemy.getVel() * Math.cos(enemy.getAngle()) * delta));
			enemy.setcoordY(enemy.getCoordenadaY()+(enemy.getVel()*Math.sin(enemy.getAngle())*delta*(-1.0)));
			enemy.setAngle(enemy.getAngle()+(enemy.getRotacao()*delta));
			
			if(System.currentTimeMillis() > enemy.getNextShot()){
				
				Projectile free = (Projectile) Component.findFreeIndex(enemy.getProjectiles());
			
				if(free != null){
					free.setcoordX(enemy.getCoordenadaX());
					free.setcoordY(enemy.getCoordenadaY());
					free.setVelocidadeX( Math.cos(enemy.getAngle()) * 0.45);
					free.setVelocidadeY(Math.sin(enemy.getAngle()) * 0.45 * (-1.0));
					free.setState(1);
					enemy.setNextShot((long) (System.currentTimeMillis() + 200 + Math.random() * 500));
				}
			}
		}	}

	@Override
	public void verifica(Enemy enemy) {
		if(System.currentTimeMillis()>enemy.getTime()){
			enemy.setVel(0.20 + Math.random() * 0.15);
			enemy.setNextShot(System.currentTimeMillis() + 100);
		}
	}

	@Override
	public void draw(Enemy enemy) {
		GameLib.setColor(Color.CYAN);
		GameLib.drawCircle(enemy.getCoordenadaX(), enemy.getCoordenadaY(), enemy.getRadius());
	}
	
}

class Enemy2 implements EnemyType{

	@Override
	public void atualiza(Enemy enemy, long delta) {
		if(enemy.getCoordenadaX() < -10 || enemy.getCoordenadaY() > GameLib.WIDTH + 10 ) {
			enemy.setState(0);
		}
		else {

			boolean shootNow = false;
			double previousY = enemy.getCoordenadaY();
			
			enemy.setcoordX(enemy.getCoordenadaX() + (enemy.getVel() * Math.cos(enemy.getAngle()) * delta));
			enemy.setcoordY(enemy.getCoordenadaY() + (enemy.getVel() * Math.sin(enemy.getAngle()) * delta * (-1.0)));
			enemy.setAngle(enemy.getAngle()+(enemy.getRotacao() * delta));

			double threshold = GameLib.HEIGHT * 0.30;
			
			if(previousY < threshold && enemy.getCoordenadaY() >= threshold) {
				
				if(enemy.getCoordenadaX() < GameLib.WIDTH / 2) enemy.setRotacao(0.003);
				else enemy.setRotacao(-0.003);
			}
			
			if(enemy.getRotacao() > 0 && Math.abs(enemy.getAngle() - 3 * Math.PI) < 0.05){
				enemy.setRotacao(0);
				enemy.setAngle(3 * Math.PI);
				shootNow = true;
			}
			
			if(enemy.getRotacao() < 0 && Math.abs(enemy.getAngle()) < 0.05){
				enemy.setRotacao(0);
				enemy.setAngle(0);
				shootNow = true;
			}
															
			if(shootNow){

				double [] angles = { Math.PI/2 + Math.PI/8, Math.PI/2, Math.PI/2 - Math.PI/8 };
				int [] freeArray = Component.findFreeIndex(enemy.getProjectiles(), angles.length);

				for(int k = 0; k < freeArray.length; k++){
					
					Projectile free = enemy.getProjectiles().get(freeArray[k]);
					
					if(free != null){
						
						double a = angles[k] + Math.random() * Math.PI/6 - Math.PI/12;
						double vx = Math.cos(a);
						double vy = Math.sin(a);
						
						free.setcoordX(enemy.getCoordenadaX());
						free.setcoordY(enemy.getCoordenadaY());
						free.setVelocidadeX(vx*0.3);
						free.setVelocidadeY(vy*0.3);
						free.setState(1);
					}
				}
			}
		}
	}

	@Override
	public void verifica(Enemy enemy) {
		if(System.currentTimeMillis()>enemy.getTime()){
			enemy.setState(1);
			enemy.setVel(0.42);
		}
	}

	@Override
	public void draw(Enemy enemy) {
		GameLib.setColor(Color.MAGENTA);
		GameLib.drawDiamond(enemy.getCoordenadaX(), enemy.getCoordenadaY(), enemy.getRadius());
	}
	
}

public class Enemy extends Spaceship {
	EnemyType type;
	double angulo;
	double rotacao;
	
	Enemy(int type, long time, double x, double y){
		setType(type);
		setTime(time);
		setcoordX(x);
		setcoordY(y);
		setState(0);
		if(type == 1){
			this.type = new Enemy1();
			setRadius(9.0);						
			
		}else{
			this.type = new Enemy2();
			setRadius(12.0);						
		}setProjectiles(new ArrayList<Projectile>(10));
		for(int i = 0; i<10;i++){
			getProjectiles().add(new Projectile(2));
		}
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

	public void setRotacao(double d) {
		rotacao = d;
	}

	@Override
	void damage() {
		if(getState() == 1){
			explode();
		}
	}

	@Override
	void explode() {
		setState(2);
		setExStart(System.currentTimeMillis());
		setExEnd(System.currentTimeMillis() + 500);
	}

	@Override
	void atualiza(long delta) {
		updateShots(delta);
		if(getState() == 2){
			if(System.currentTimeMillis() > getExEnd()){
				setState(3);
			}
		}
		
		if(getState() == 1){
			type.atualiza(this,delta);
		}
	}

	void verifica(long levelTime) {
		if(System.currentTimeMillis()>(getTime()+levelTime)){
			setState(1);
			setAngle(3 * Math.PI / 2);
			setRotacao(0);
			type.verifica(this);

		}
	}

	@Override
	void draw(long currentTime) {
		if(getState() == EXPLODING){
			double alpha = (currentTime - getExStart()) / (getExEnd() - getExStart());
			GameLib.drawExplosion(getCoordenadaX(), getCoordenadaY(), alpha);
		}
		if(getState() == ACTIVE){
			type.draw(this);
		}
		drawShots(currentTime);
	}

	@Override
	void collision(double dist, double radius) {
		if(dist<this.getRadius()){
			damage();
		}
	}

}