package BrickBracker;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Toolkit;

public class MapGenerator {
	 Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
     int x =screenSize.width;
	 int y = screenSize.height;
	public int map[][];
	public int brickWidth;
	public int brickHeight;
	public MapGenerator(int col, int row){
		map = new int[col][row];
		for(int i =0; i<map.length;i++){
			for(int j =0;j<map[0].length;j++){
				map[i][j]=1;
			}
		}
		brickWidth=600/col;
		brickHeight=400/row;
	}
	public void draw(Graphics2D g){
		for(int i =0; i<map.length;i++){
			for(int j =0;j<map[0].length;j++){
				if(map[i][j]>0){
					g.setColor(Color.cyan);
					g.fillRect(j*brickWidth +80, i*brickHeight +40, brickWidth, brickHeight);
					
					g.setStroke(new BasicStroke(3));
					g.setColor(Color.black);
					g.drawRect(j*brickWidth +80, i*brickHeight +40, brickWidth, brickHeight);
				}
			}
	}
}
	public void setBrickValue(int value, int row, int col){
		map[row][col]=value;
		
	}

}
