package BrickBracker;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class Main extends JFrame {

	public static void main(String[] args) {
	JFrame obj = new JFrame();
	 Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
     int x =screenSize.width;
	 int y = screenSize.height;
	GamePlay gamePlay=new GamePlay();
	obj.setBounds(0, 0, x,y);
	obj.setTitle("Brick Break");
	obj.setResizable(false);
	obj.setVisible(true);
//	obj.setLocation(300,100);
	obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	obj.add(gamePlay);
	}

}
