import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import javax.swing.*;
import java.util.Random;
import javax.swing.JPanel;




public class GamePanel extends JPanel implements ActionListener{
	
	// game parts 
	
	
	static final int SCREEN_WIDTH = 600;
	static final int SCREEN_HEIGHT = 600;
	static final int UNIT_SIZE = 50 ;
	static final int GAME_UNITS = (SCREEN_WIDTH*SCREEN_HEIGHT)/UNIT_SIZE;
	static final int DELAY = 75;
	
	final int x[] = new int[GAME_UNITS];
	final int y[] = new int[GAME_UNITS];
	
	int bodyParts = 6;
	int applesEaten;
	int appleX;
	int appleY;
	
	char dierection = 'R';
	boolean running = false;
	Timer timer;
	Random random;
	
	//X game parts X//
	
	
	
	
	
	
	
	
	
	GamePanel(){
		
		random = new Random();
		this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
		this.setBackground(Color.black);
		this.setFocusable(true);
		this.addKeyListener(new MyKeyAdapter());
		startGame();
		
		
	}
	
	
	
	
	
	
	public void startGame() {
		newApple();
		running = true;
		timer = new Timer(DELAY,this);
		timer.start();
	}
	
	
	
	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
	}
	
	
	
	
	
	public void draw(Graphics g) {
		
		if (running) {
		
		for(int i=0;i<SCREEN_HEIGHT/UNIT_SIZE;i++) {
			g.drawLine(i*UNIT_SIZE,0,i*UNIT_SIZE,SCREEN_HEIGHT);
			g.drawLine(0,i*UNIT_SIZE,SCREEN_WIDTH,i*UNIT_SIZE);
		}
		g.setColor(Color.red);
		g.fillOval(appleX,appleY,UNIT_SIZE,UNIT_SIZE);
		
		for (int i = 0;i<bodyParts;i++) {
			if (i==0) {
				g.setColor(Color.green);
				g.fillRect(x[i],y[i],UNIT_SIZE,UNIT_SIZE);
			}else {
				g.setColor(new Color(45,180,0));
				g.fillRect(x[i],y[i],UNIT_SIZE,UNIT_SIZE);
			}
		}
		}else {
			gameover(g);
		}
	}
	
	
	
	
	
	public void newApple() {
		
		appleX=random.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE;
		appleX=random.nextInt((int)(SCREEN_HEIGHT/UNIT_SIZE))*UNIT_SIZE;
		
		
	}
	
	
	
	
	public void move() {
		for (int i=bodyParts;i>0;i--) {
			x[i] = x[i-1];
			y[i] = y[i-1];
		}
		
		switch(dierection) {
		case 'U':
			y[0] = y[0] - UNIT_SIZE;
			break;
		case 'D':
			y[0] = y[0] + UNIT_SIZE;
			break;
		case 'L':
			x[0] = x[0] - UNIT_SIZE;
			break;
		case 'R':
			x[0] = x[0] + UNIT_SIZE;
			break;
			
		}
	}
	
	
	
	
	
	public void checkApple() {
		if ((x[0] == appleX) && (y[0] == appleY)) {
			bodyParts++;
			applesEaten++;
			newApple();
		}
	}
	
	
	
	
	
	// checks if headcollides with body
	
	public void checkCollisions() {
			for (int i = bodyParts;i>0;i--) {
				if ((x[0] == x[i]) && (y[0] == y[i])) {
					running = false;
				}
			}
			
			
			// check if head thouch the left the boarder
			
			if ((x[0] < 0)) {
				running = false;
			}
			
			// check if head thouch the right the boarder
			
			if ((x[0] > SCREEN_WIDTH)) {
				running = false;
			}
			
			// check if head thouch the TOP boarder
			

			if ((y[0] < 0)) {
				running = false;
			}
			
			// check if head thouch the down boarder
			

			if ((y[0] > SCREEN_HEIGHT)) {
				running = false;
			}
			if (!running) {
				timer.stop();
			}
	}
	
	
	
	
	
	
	public void gameover(Graphics g) {
		// game over text
		
		g.setColor(Color.red);
		g.setFont(new Font("ink Free",Font.BOLD,75));
		FontMetrics metrics = getFontMetrics(g.getFont());
		g.drawString("Game Over",(SCREEN_WIDTH - metrics.stringWidth("Game over"))/2,SCREEN_WIDTH/2);
	}
	
	
	
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (running) {
			move();
			checkApple();
			checkCollisions();
		}
		repaint();
		
	}
	
	
	
	
	
	public class MyKeyAdapter extends KeyAdapter{
		
		public void keyPressed(KeyEvent e) {
			
			switch(e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				if (dierection != 'R') {
					dierection = 'L';
				}
				break;
			case KeyEvent.VK_RIGHT:
				if (dierection != 'L') {
					dierection = 'R';
				}
				break;
			case KeyEvent.VK_UP:
				if (dierection != 'D') {
					dierection = 'U';
				}
				break;
			case KeyEvent.VK_DOWN:
				if (dierection != 'U') {
					dierection = 'D';
				}
				break;
			}
			
			
			
			}
			}
			

			
		}
		
	


