import java.util.ArrayList;

interface State{
	int getState();
	void draw(Component c, long currentTime);
	void verifica(Component c, long levelTime);
	void atualiza(Component c, long delta);
	void collision(Spaceship c, double dist, double radius);
}
// Estados possiveis para Component (Inactive | Active | Exploding)
class Inactive implements State{

	@Override
	public int getState() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void draw(Component c, long currentTime) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void verifica(Component c, long levelTime) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void atualiza(Component c, long delta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void collision(Spaceship c, double dist, double radius) {
		// TODO Auto-generated method stub
		
	}
	
}

class StandBy implements State{
	public int getState(){
		return 0;
	}

	@Override
	public void draw(Component c, long currentTime) {
		if(c instanceof Spaceship){
			Spaceship s = (Spaceship)c;
			s.drawShots(currentTime);
		}
	}

	@Override
	public void verifica(Component c, long levelTime) {
		c.verifica(levelTime);
	}

	@Override
	public void atualiza(Component c, long delta) {
		c.atualiza(delta);
	}

	@Override
	public void collision(Spaceship c, double dist, double radius) {
		
	}
}

class Active implements State{
	public int getState(){
		return 1;
	}

	@Override
	public void draw(Component c, long currentTime) {
		c.draw(currentTime);
	}

	@Override
	public void verifica(Component c, long levelTime) {
	}

	@Override
	public void atualiza(Component c, long delta) {
		c.atualiza(delta);
	}

	@Override
	public void collision(Spaceship c, double dist, double radius) {
		if(c.getCoordenadaY()>0){
			c.collision(dist, radius);
		}
	}
}

class Exploding implements State{
	public int getState(){
		return 2;
	}

@Override
	public void draw(Component c, long currentTime) {
		c.draw(currentTime);
	}

	@Override
	public void verifica(Component c, long levelTime) {
		
	}

	@Override
	public void atualiza(Component c, long delta) {
		c.atualiza(delta);
	}

	@Override
	public void collision(Spaceship c, double dist, double radius) {
	}
	
}

abstract class Component {
	/* Constantes relacionadas aos estados que os elementos   */
	/* do jogo (player, projeteis ou inimigos) podem assumir. */
	public static final int INACTIVE = 0;
	public static final int ACTIVE = 1;
	
	private State state = new StandBy();
	private int type;
	private long time;
	private double coordenadaX;
	private double coordenadaY;
	private double velocidade;
	private double radius;
	
	abstract void atualiza(long delta);
	abstract void verifica(long levelTime);
	abstract void draw(long currentTime);
	
	void drawState(long currentTime){
		state.draw(this, currentTime);
	}

	void verificaState(long levelTime){
		state.verifica(this,levelTime);
	}

	void atualizaState(long delta){
		state.atualiza(this, delta);
	}
	
		
	/* Encontra e devolve o primeiro índice do  */
	/* array referente a uma posição "inativa". */
	
	static Object findFreeIndex(ArrayList<?> array) {
		for(Object o : array){
			Component c = (Component) o;
			if(c.getState() == 0)return c;
		}return null;
	}
	
	/* Encontra e devolve o conjunto de índices (a quantidade */
	/* de índices é defnida através do parâmetro "amount") do */
	/* array, referentes a posições "inativas".               */ 

	static int [] findFreeIndex(ArrayList<Projectile> stateArray, int amount){

		int i, k;
		int [] freeArray = { stateArray.size(), stateArray.size(), stateArray.size()};
		
		for(i = 0, k = 0; i < stateArray.size() && k < amount; i++){
				
			if(stateArray.get(i).getState() == 0) { 
				
				freeArray[k] = i; 
				k++;
			}
		}
		
		return freeArray;
	}
	
	int getState(){
		return state.getState();
	}
	
	double getCoordenadaX(){
		return coordenadaX;
	}
	
	double getCoordenadaY(){
		return coordenadaY;
	}
	
	
	double getVel(){
		return velocidade;
	}
	
	void setState(int s){
		if(s == 0){
			state = new StandBy();
		}else if(s == 1){
			state = new Active();
		}else if(s==2){
			state = new Exploding();
		}else{
			state = new Inactive();
		}
	}
	
	void setcoordX(double x){
		coordenadaX = x;
	}
	
	void setcoordY(double y){
		coordenadaY = y;
	}
	
	void setVel(double v){
		velocidade = v;
	}
	
	public double getRadius() {
		return radius;
	}

	
	void verifica(Player player) {
		
	}
	
	public int getType() {
		return type;
	}

	public void setType(int tipo) {
		this.type = tipo;
	}
	
	public void setRadius(double r){
		this.radius = r;
	}
	
	public State state(){
		return state;
	}
	public long getTime() {
		return time;
	}
	void setTime(long time){
		this.time = time;
	}
}

abstract class Spaceship extends Component {
	public static final int EXPLODING = 2;
	private double explosion_start;
	private double explosion_end;
	private long nextShot;
	private ArrayList<Projectile> projectiles;
	
	abstract void damage();
	abstract void explode();
	abstract void collision(double dist, double radius);
	
	void collisionState(double dist, double radius){
		state().collision(this, dist, radius);
	}
	
	void shotCollision(Spaceship hit){
		for(Projectile p : this.getProjectiles()){
			hit.checkCollision(p);
		}
	}
	
	void checkCollision(Component c){
		double dx = c.getCoordenadaX() - this.getCoordenadaX();
		double dy = c.getCoordenadaY() - this.getCoordenadaY();
		double dist = Math.sqrt(dx * dx + dy * dy);
		if(this instanceof Player){
			collision(dist,c.getRadius());
		}
		else if(this instanceof Boss)
		{
			collision(dist,c.getRadius());
		}
		else{
			collisionState(dist,c.getRadius());
		}
	}
	
	void updateShots(long delta){
		for(Projectile p : getProjectiles()){
			p.atualiza(delta);
		}
	}
	
	void drawShots(long currentTime){
		for(Projectile p : getProjectiles()){
			p.draw(currentTime);
		}
	}
	
	void drawExp(){
		double x = System.currentTimeMillis() - getExStart();
		double y = getExEnd() - getExStart();
		double alpha = x/y;
		GameLib.drawExplosion(getCoordenadaX(),getCoordenadaY(), alpha);
	}
	
	double getExEnd(){
		return explosion_end;
	}
	
	double getExStart(){
		return explosion_start;
	}
	
	long getNextShot(){
		return nextShot;
	}
	void setNextShot(long s){
		nextShot = s;
	}
	
	public void setExStart(long l) {
		explosion_start = l;
	}

	public void setExEnd(long l) {
		explosion_end = l;
	}
	
	public ArrayList<Projectile> getProjectiles(){
		return projectiles;
	}
	
	void setProjectiles(ArrayList<Projectile> arrayList) {
		projectiles = arrayList;		
	}
}