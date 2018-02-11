package BrickBracker;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import com.oracle.webservices.internal.api.EnvelopeStyle.Style;

public class GamePlay extends JPanel implements KeyListener , ActionListener {
	 Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
     int x =screenSize.width;
	 int y = screenSize.height;
	private boolean play = false;
	private int score = 0;
	private int level =1;
	private int TotalBricks = 45;
	
	private Timer time;
	
	private int delay = 8;
	private int playerX = 710;
	private int ballPosX = 120;
	private int ballPosY = 350;
	private int ballXdir = -1;
	private int ballYdir = -2;
	 private MapGenerator mapG;
	 
	
	public GamePlay(){
		mapG = new MapGenerator(5,9);
	addKeyListener(this);
	setFocusable(true);
	setFocusTraversalKeysEnabled(false);
	time = new Timer(delay , this);
	time.start();
	
	}
	
	public void paint( Graphics g){
		//background
		g.setColor(Color.black);
		
		g.fillRect(1, 1, x-1, y-1);
		
		//map Generate
		mapG.draw((Graphics2D)g);
		
		//scores
		g.setColor(Color.CYAN);
		g.setFont(new Font("serif", Font.BOLD,25));
		g.drawString("Score : " +score, x-150, 30);
		
		//borders
		g.setColor(Color.yellow);
		g.fillRect(0, 0, 5, x);
		g.fillRect(0, 0,y+y, 5);
		g.fillRect(x-10, 0, 5, x);
		
		//paddle
		g.setColor(Color.GREEN);
		g.fillRect(playerX, 730, 100, 8);
		
		//ball
		g.setColor(Color.yellow);
		g.fillOval(ballPosX, ballPosY, 20, 20);
		
		//level
		g.setColor(Color.CYAN);
		g.setFont(new Font("serif", Font.BOLD,25));
		g.drawString("Level : " +level, 30, 30);
		
		//game over
		if(TotalBricks<=0){
			play = false;
			ballXdir = 0;
			ballYdir = 0;
			level += 1;
			g.setColor(Color.red);
			g.setFont(new Font("serif", Font.ITALIC, 30));
			g.drawString("You won with Score: "+score, 190,300);
			
			g.setFont(new Font("serif", Font.ITALIC, 20));
			g.drawString("Press Enter To level next", 250,400);
			
		}
		if(ballPosY > 730){
			play = false;
			ballXdir = 0;
			ballYdir = 0;
			g.setColor(Color.red);
			g.setFont(new Font("serif", Font.ITALIC, 30));
			g.drawString("Game Over  with Score: "+score, 190,300);
			
			g.setFont(new Font("serif", Font.ITALIC, 20));
			g.drawString("Press Enter To Restart", 250,400);
			
		}
		
		g.dispose();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		time.start();
		if(play){
			if(new Rectangle(ballPosX, ballPosY , 20, 20).intersects(new Rectangle(playerX, 730, 100,8))){
				ballYdir = -ballYdir;
			}
			A: for(int i =0; i<mapG.map.length;i++){
				for(int j =0; j<mapG.map[0].length;j++){
					if(mapG.map[i][j]>0){
						int brickX=j* mapG.brickWidth +80;
						int brickY = i*mapG.brickHeight +40;
						int brickWidth=mapG.brickWidth;
						int brickHeight=mapG.brickHeight;
						
						Rectangle rect= new Rectangle(brickX, brickY, brickWidth, brickHeight);
						Rectangle ballRect = new Rectangle(ballPosX, ballPosY , 20, 20);
						Rectangle brickRect = rect;
						
						if(ballRect.intersects(brickRect)){
							mapG.setBrickValue(0, i, j);
							TotalBricks --;
							score +=5;
							
							if(ballPosX + 19 <=brickRect.x || ballPosX + 1 >=brickRect.x + brickRect.width){
								ballXdir = -ballXdir;
							}else{
								ballYdir = -ballYdir;
							}
							break A;
						}
					}
				}
			}
			ballPosX += ballXdir;
			ballPosY += ballYdir;
			if(ballPosX < 0){
				ballXdir = -ballXdir;
			}
			if(ballPosY < 0){
				ballYdir = -ballYdir;
			}
			if(ballPosX > x-10){
				ballXdir = -ballXdir;
			}

		}
		repaint();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT){
			if(playerX >=x-10){
				playerX=x-10;
			}else{
				moveRight();
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT){
			if(playerX <=10){
				playerX=10;
			}else{
				moveLeft();
			}
	}
		if(e.getKeyCode()== KeyEvent.VK_ENTER){
			if(TotalBricks>0){
			play= true;
			score = 0 ;
			 playerX = 310;
			 ballPosX = 120;
			 ballPosY = 350;
			 ballXdir = -1;
			 ballYdir = -2;
			 TotalBricks=21;
		 mapG= new MapGenerator(5,9);
			 repaint();
			}
			else{
				play= true;
				score = 0 ;
				 playerX = 310;
				 ballPosX = 120;
				 ballPosY = 350;
				 ballXdir = -1;
				 ballYdir = -2;
				 TotalBricks=21;
			 mapG= new MapGenerator(5,9);
			 time = new Timer(delay+2, this);
				//time.start();
				 repaint();	
			}
			if(e.getKeyCode()==KeyEvent.VK_ESCAPE)System.exit(score);
				
			
		}
	}

	public void moveLeft() {
		play = true;
		playerX -= 20;
	}

	public void moveRight() {
		play = true;
		playerX +=20;
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {}

	@Override
	public void keyTyped(KeyEvent arg0) {}

}
