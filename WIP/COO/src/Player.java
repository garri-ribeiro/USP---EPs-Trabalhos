import java.awt.*;
import java.util.ArrayList;

interface PlayerState{
	void draw(Player player, long currentTime); 
	void verifica(Player player);
	void PlayerKey(Player player,long delta);
	boolean collision(Player player, double dist, double radius);
}


// Estados do Player (PlayerActive | PlayerExploding | PlayerPowerUp) e Classe principal player
class PlayerActive implements PlayerState{
	@Override
	public void draw(Player player,long currentTime) {
		for(Projectile p : player.getProjectiles()){
			p.draw(System.currentTimeMillis());
		}
		GameLib.setColor(Color.RED);
		for(float i = player.getBarra(),k = 25;i>0;i--){
			GameLib.drawLine(player.getCoordenadaX()-k, player.getCoordenadaY()+20, player.getCoordenadaX()-k, player.getCoordenadaY()+22);
			k -= 1;
		}if(System.currentTimeMillis() > player.getHitEnd()){
			GameLib.setColor(Color.BLUE);
		}else{
			if((int) (Math.random() * 101)%2==0){
				GameLib.setColor(Color.WHITE);
				GameLib.setColor(Color.WHITE);
			}else{
				GameLib.setColor(Color.RED);
			}
		}
		GameLib.drawPlayer(player.getCoordenadaX(), player.getCoordenadaY(), player.getRadius());
	}

	@Override
	public void verifica(Player player) {
	}

	@Override
	public void PlayerKey(Player player,long delta) {
		player.key(delta);
	}

	@Override
	public boolean collision(Player player, double dist, double radius) {
		if(System.currentTimeMillis() > player.getHitEnd()){
			return dist < (player.getRadius() + radius) * 0.8;
		}return false;
	}
}

class PlayerExploding implements PlayerState{
	@Override
	public void draw(Player player, long currentTime) {
		player.drawShots(currentTime);
		player.drawExp();
	}

	@Override
	public void verifica(Player player) {
		if(System.currentTimeMillis() > player.getExEnd()){
			player.setPlayerState(new PlayerActive());
		}		
	}

	@Override
	public void PlayerKey(Player player, long delta) {
		
	}

	@Override
	public boolean collision(Player player, double dist, double radius) {
		return false;
	}
}

class PlayerPowerUp implements PlayerState{
	@Override
	public void draw(Player player,long currentTime){
		player.drawShots(currentTime);
		if(player.getPowerUp().getType()==0){
			if((int) (Math.random() * 101)%2==0){
				GameLib.setColor(Color.ORANGE);
			}else{
				GameLib.setColor(Color.RED);
			}
			
		}
		if(System.currentTimeMillis() < player.getHitEnd()){
			if((int) (Math.random() * 101)%2==0){
				GameLib.setColor(Color.WHITE);
			}else{
				GameLib.setColor(Color.RED);
			}
		}
		GameLib.drawPlayer(player.getCoordenadaX(), player.getCoordenadaY(), player.getRadius());
	}

	@Override
	public void verifica(Player player) {
		if(System.currentTimeMillis()>player.getPowerUpEnd()){
			player.setPowerUp(null);
			player.setPlayerState(new PlayerActive());
			player.getBola().setState(0);
		}else{
			if(player.getPowerUp().getPowerUpType() instanceof PowerUp1){
				Projectile b = player.getBola();
				b.verifica(player);
			}
		}		
	}

	@Override
	public void PlayerKey(Player player, long delta) {
		player.key(delta);
	}
	
	@Override
	public boolean collision(Player player, double dist, double radius) {
		if(System.currentTimeMillis() > player.getHitEnd()){
			return dist < (player.getRadius() + radius) * 0.8;
		}return false;
	}

}

public class Player extends Spaceship{
	private PlayerState state = new PlayerActive();
	private int life;
	private int lifePoints;
	private long hitStart;
	private long hitEnd;
	private PowerUp powerUp;
	private long powerUpEnd;
	private Projectile bola;
	private double rotacao;
	private double angulo;
	private float barra;
	private float ajuda;
	Player(int l){
		setNextShot(System.currentTimeMillis());
		lifePoints=l;
		life=l;
		//lifePoints=50;
		//life=50;
		setBarra((float)50.0);	
		state = new PlayerActive();								
		setcoordX(GameLib.WIDTH / 2);					
		setcoordY(GameLib.HEIGHT * 0.90);				
		setVel(0.25);
		setRadius(12);
		setExStart(0);
		setExEnd(0);
		setProjectiles(new ArrayList<Projectile>(10));
		for(int i = 0; i<10;i++){
			getProjectiles().add(new Projectile(1));
		}
		hitEnd = 0;
		bola = new Projectile(3);
	}
	
	public double getAngle() {
		return angulo;
	}
	
		public float getAjuda() {
		return ajuda;
	}

	public double getRotacao(){
		return rotacao;
	}
	
	public float getBarra() {
		return barra;
	}
	
	public void setBarra(float barra) {
		this.barra = barra;
	}
	
	
	public void setAngle(double d) {
		angulo = d;
	}
	
	public void setRotacao(double r){
		rotacao = r;
	}
	
	void setPlayerState(PlayerState state){
		this.state = state;
	}
	
	PlayerState getPlayerState(){
		return state;
	}
	
	public int getLifePoints() {
		return lifePoints;
	}

	public void setLifePoints(int lifePoints) {
		this.lifePoints = lifePoints;
	}
	
	
	public long getHitStart() {
		return hitStart;
	}

	public void setHitStart(long hitStart) {
		this.hitStart = hitStart;
	}

	public long getHitEnd() {
		return hitEnd;
	}

	public void setHitEnd(long hitEnd) {
		this.hitEnd = hitEnd;
	}

	public PowerUp getPowerUp() {
		return powerUp;
	}
	
	public Projectile getBola(){
		return bola;
	}

	public void setPowerUp(PowerUp powerUp) {
		bola.setcoordX(bola.getCoordenadaX()+4*getRadius());
		bola.setcoordY(bola.getCoordenadaY()+4*getRadius());
		this.powerUp = powerUp;
	}

	public long getPowerUpEnd() {
		return powerUpEnd;
	}

	public void setPowerUpEnd(long powerUpEnd) {
		this.powerUpEnd = powerUpEnd;
	}

	@Override
	void damage() {
		if(lifePoints<=0){
			explode();
		}else{
			setBarra(barra-(float)((float)10/(float)150)*50);
			lifePoints-=10;
			hitEnd = System.currentTimeMillis()+1500;
		}
	}

	@Override
	void explode() {
		lifePoints = life;
		barra = 50;
		state = new PlayerExploding();
		setExStart(System.currentTimeMillis());
		setExEnd(System.currentTimeMillis()+2000);
	}

	

	@Override
	void atualiza(long delta) {
		updateShots(delta);
	}
	
	void verifica(long levelTime){
		/*if(System.currentTimeMillis()>time){
		setState(1);
		}*/
		state.verifica(this);
	}

	@Override
	void draw(long currentTime) {
		state.draw(this, currentTime);
	}
	
	void PlayerKey(long delta){
		state.PlayerKey(this,delta);
	}

	@Override
	void collision(double dist, double radius) {
		if(state.collision(this, dist, radius)){
			damage();
		}
	}
	
	protected void key(long delta){
		if(GameLib.iskeyPressed(GameLib.KEY_UP)) setcoordY(getCoordenadaY()-delta*getVel());
		if(GameLib.iskeyPressed(GameLib.KEY_DOWN)) setcoordY(getCoordenadaY()+delta*getVel()); 
		if(GameLib.iskeyPressed(GameLib.KEY_LEFT)) setcoordX(getCoordenadaX()-delta*getVel());
		if(GameLib.iskeyPressed(GameLib.KEY_RIGHT)) setcoordX(getCoordenadaX()+delta*getVel());
		if(GameLib.iskeyPressed(GameLib.KEY_CONTROL)) {
			if(System.currentTimeMillis() > getNextShot()){
				Projectile free =(Projectile) findFreeIndex(getProjectiles());
				if(free != null){
					free.setcoordX(getCoordenadaX());
					free.setcoordY(getCoordenadaY() - 2 * getRadius());
					free.setVelocidadeX(0.0);
					free.setVelocidadeY(-1.0);
					free.setState(1);
					setNextShot(System.currentTimeMillis()+100);
				}
			}	
		}
		if(getCoordenadaX() < 0.0) setcoordX(0.0);
		if(getCoordenadaX() >= GameLib.WIDTH) setcoordX(GameLib.WIDTH - 1);
		if(getCoordenadaY() < 25.0) setcoordY(25.0);
		if(getCoordenadaY() >= GameLib.HEIGHT) setcoordY(GameLib.HEIGHT - 1);
	}
	
	public void drawHit(Player player,long currentTime){
		if((int) (Math.random() * 101)%2==0){
			GameLib.setColor(Color.WHITE);
		}else{
			GameLib.setColor(Color.BLUE);
		}
		GameLib.drawPlayer(player.getCoordenadaX(), player.getCoordenadaY(), player.getRadius());
	}
}