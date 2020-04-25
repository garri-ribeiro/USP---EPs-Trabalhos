import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;
/*************************************************************************************************/
			/*                                                                                               */
			/* Main loop do jogo                                                                             */
			/*                                                                                               */
			/* O main loop do jogo possui executa as seguintes operações:                                    */
			/*                                                                                               */
			/* 1) Verifica se há colisões e atualiza estados dos elementos conforme a necessidade.           */
			/*                                                                                               */
			/* 2) Atualiza estados dos elementos baseados no tempo que correu desde a última atualização     */
			/*    e no timestamp atual: posição e orientação, execução de disparos de projéteis, etc.        */
			/*                                                                                               */
			/* 3) Processa entrada do usuário (teclado) e atualiza estados do player conforme a necessidade. */
			/*                                                                                               */
			/* 4) Desenha a cena, a partir dos estados dos elementos.                                        */
			/*                                                                                               */
			/* 5) Espera um período de tempo (de modo que delta seja aproximadamente sempre constante).      */
			/*                                                                                               */
			/*************************************************************************************************/
	/* Indica que o jogo está em execução */

public class RunGame {

	private Player player;
	public static final int INACTIVE = 0;
	public static final int ACTIVE = 1;
	public static final int EXPLODING = 2;
	public static final int HIT = 3;
	private ArrayList<Background> fundos = new ArrayList<Background>();
	private Color corFundo = new Color(150,150,150);
	static long delta;
	static long currentTime = System.currentTimeMillis();
	static long levelTime;
	static ArrayList<Level> game = new ArrayList<Level>();
	static boolean change = false;
	private Level current;
	
	// Construtor cria o background
	public RunGame(){
		createBackground(0.070,0,10,3,3);		
		createBackground(0.050,0,15,2,2);
		createBackground(0.010,0,150,1,1);	
	}
	//Cria a fase do jogo
	void createLevel(File level, int i) throws IOException{
		Scanner sc = new Scanner(level);
		Level fase = new Level(i);
		while(sc.hasNext()){
			String s = sc.next();
			if(s.equalsIgnoreCase("powerup")){
				fase.add(createPowerUp(sc.nextInt(), sc.nextLong(),Double.parseDouble(sc.next()),Double.parseDouble(sc.next())));
			}else if(s.equalsIgnoreCase("inimigo")){
				fase.add(createEnemy(sc.nextInt(), sc.nextLong(),Double.parseDouble(sc.next()),Double.parseDouble(sc.next())));
			}else if(s.equalsIgnoreCase("chefe")){
				if(sc.nextInt() == 1)
				fase.add(createBoss(15,sc.nextInt(), sc.nextLong(), Double.parseDouble(sc.next()),Double.parseDouble(sc.next())));
				else 
					fase.add(createFinalBoss(0,sc.nextInt(), sc.nextLong(), Double.parseDouble(sc.next()),Double.parseDouble(sc.next())));
			}
		}sc.close();
		game.add(fase);
	}
	// Métodos que criam elementos do jogo	
	public Player createPlayer(int n) {
		this.player = new Player(n);
		return player;
	}

	public Enemy createEnemy(int type, long time, double x, double y) {
		Enemy enemy = new Enemy(type,time,x,y);
		return enemy;
	}
	
	public Boss createBoss(int type, int life, long time, double x, double y){
		Boss b = new Boss(type,life,time,x,y);
		return b;
	}
	
	public BossFinal createFinalBoss(double a, int f, long c, double d, double e){
		BossFinal b = new BossFinal(a,f,c,d,e);
		return b;
	}
	
	public PowerUp createPowerUp(int type, long time, double x, double y){
		PowerUp p = new PowerUp(type,time,x,y);
		return p;
	}
	
	public void createBackground(double speed, double count, int density, int width, int height) {
		Background bg = new Background(speed, count, density, width,height);
		this.fundos.add(bg);
	}
	
	// Guarda tempo inicial da fase
	void setCurrent(Level level){
		if(current == null){
			levelTime = System.currentTimeMillis();
		}
		current = level;
	}
	
	// Roda o jogo
	public void run(boolean running) {	
		
		/* Loop para rodar o jogo*/
		while(running){
			for(int i = 0; i<game.size();){
				setCurrent(game.get(i));
				if(change){
					i++;
					change=false;
					if (i == 4){
						JOptionPane.showConfirmDialog(null, "VOCÃŠ GANHOU!!", "GANHOU!", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);
						running = false;
					}
				}
			
				
			/* Usada para atualizar o estado dos elementos do jogo    */
			/* (player, projéteis e inimigos) "delta" indica quantos  */
			/* ms se passaram desde a última atualização.             */
			delta = System.currentTimeMillis() - currentTime;
			
			// Timestamp atual do sistema
			currentTime = System.currentTimeMillis();
				
			// Verifica colisões
			for(Component c : current.elementos){
				checkCollision(player,c);
			}
			
			// Atualiza estados dos elementos
			atualiza(delta,currentTime,levelTime);
					
			// Verifica entrada do úsuario (teclado)
			player.key(delta);
			if(GameLib.iskeyPressed(GameLib.KEY_ESCAPE)) running = false;
;
			GameLib.setColor(corFundo);
			
			// Desenha fundo
			for(Background bg : fundos){
				bg.incCount(delta);
				bg.desenhaElementos();
			}
		}}
		return;
	}
	
	// Atualiza status dos elementos do jogo
	void atualiza(long delta,long currentTime, long levelTime){
		player.atualiza(delta);
		player.verifica(levelTime);
		player.draw(currentTime);
		
		for(Component c : current.elementos){
			if(c instanceof Boss || c instanceof BossFinal){
				c.atualiza(delta);
				c.verifica(levelTime);
				c.draw(currentTime);
			}else{
				c.atualizaState(delta);
				c.verificaState(levelTime);
				c.drawState(currentTime);
			}
		}
		
		// Chama display() da classe GameLib atualiza o desenho exibido pela interface do jogo.
		GameLib.display();
		
		/* faz uma pausa de modo que cada execução do laço do main loop demore aproximadamente 5 ms. */
		busyWait(currentTime + 5);
	}
	
	public static void busyWait(long time){
		while(System.currentTimeMillis() < time) Thread.yield();
	}
	
	// Checa colisões de elementos
	void checkCollision(Player player, Component obj2){
		if(obj2 instanceof PowerUp){
			int tempo = 7000;
			PowerUp pow = (PowerUp) obj2;
			if(pow.checkCollision(player)){
				player.setPowerUp(pow);
				player.setPlayerState(new PlayerPowerUp());
				pow.setState(3);
				if(player.getPowerUp().getPowerUpType() instanceof PowerUp2){
					for(Component enemy: current.elementos){
						if(enemy instanceof Enemy){
							Spaceship e = (Spaceship) enemy;
							e.damage();
						}
					}
					tempo = 1000;
					}
				player.setPowerUpEnd(System.currentTimeMillis()+tempo);
			}
		}
		else if(obj2 instanceof Boss){
			Spaceship boss = (Spaceship) obj2;
			if(boss.getState() != 2){
				player.checkCollision(boss);
				boss.shotCollision(player);
				player.shotCollision(boss);
				if(player.getPowerUp() != null){
					if(player.getBola().getState() == 1){
						boss.checkCollision(player.getBola());
					}
				}
			}
		}
		else if(obj2 instanceof BossFinal){
			BossFinal b = (BossFinal)obj2;	
			for(Boss f : b.filhos){
				if(f.state != 2){
					
					player.checkCollision(f);
					f.shotCollision(player);
					player.shotCollision(f);
					if(player.getPowerUp() != null){
						if(player.getBola().getState() == 1){
							f.checkCollision(player.getBola());
						}
					}			
				}
				
			}
			
			if(b.mother.state != 2){
					player.checkCollision(b.mother);
					b.mother.shotCollision(player);
					player.shotCollision(b.mother);
					if(player.getPowerUp() != null){
						if(player.getBola().getState() == 1){
							b.mother.checkCollision(player.getBola());
						}
					}
			}						
			
		}		
		else{
			for(Component c : current.elementos){
				if(c.getState() == 1&&c instanceof Enemy){
					Spaceship e = (Spaceship) c;
					if(player.getPowerUp() != null){
						if(player.getBola().getState() == 1){
							e.checkCollision(player.getBola());
						}
					}
					e.shotCollision(player);
					player.shotCollision(e);
					player.checkCollision(e);
				}
			}
		}
	}
}