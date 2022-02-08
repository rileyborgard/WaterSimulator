package main;

import java.awt.event.KeyEvent;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.henagongames.game.GameApplet;
import com.henagongames.game.Menu;
import com.henagongames.input.Mouse;

public class Main extends GameApplet {
	
	Menu menu = new Screen();
	
	public static int pw = 40, ph = 40;
	public static Point points[][] = new Point[pw][ph];
	
	public static int w = 400, h = 400;
	public static int rw = w/points.length, rh = h/points[0].length;
	public static int loops = 1;
	public static double k = 0.025;
	
	public void begin(){
		setAppletSize(w, h);
		setMenu(menu);
		for(int i = 0; i < points.length; i++){
			for(int j = 0; j < points[0].length; j++){
				points[i][j] = new Point(0);
			}
		}
		startTimer(15);
	}
	
	public static void gameLoop(){
		if(keyPressed(KeyEvent.VK_SPACE)){
			try {
				ImageIO.write((RenderedImage) getBufferedImage(), "png", new File("thing.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.exit(0);
		}
//		int x = (int) (0.75*(mouseX()/rw)+5.5);
//		int y = (int) (0.75*(mouseY()/rh)+5.5);
		int x = mouseX()/rw;
		int y = mouseY()/rh;
		x = x < 0 ? 0:x;
		x = x > pw-1 ? pw-1:x;
		y = y < 0 ? 0:y;
		y = y > ph-1 ? ph-1:y;
		if(mousePressed(Mouse.BUTTON_1)){
			points[x][y].alpha += 10;
		}else if(mousePressed(Mouse.BUTTON_3)){
			points[x][y].alpha -= 10;
		}
		
		for(int i = 0; i < points.length; i++){
			for(int j = 0; j < points[0].length; j++){
				points[i][j].update();
			}
		}
		
		for(int j = 0; j < points[0].length; j++){
		
			double leftDeltas[] = new double[points.length];
			double rightDeltas[] = new double[points.length];
		
			for(int n = 0; n < loops; n++){
				for(int i = 0; i < points.length; i++){
					if(i > 0){
						leftDeltas[i] = k*(points[i][j].z-points[i-1][j].z);
						points[i-1][j].alpha += leftDeltas[i];
					}
					if(i < points.length-1){
						rightDeltas[i] = k*(points[i][j].z-points[i+1][j].z);
						points[i+1][j].alpha += rightDeltas[i];
					}
				}
				for(int i = 0; i < points.length; i++){
					if(i > 0)
						points[i-1][j].z += leftDeltas[i];
					if(i < points.length-1)
						points[i+1][j].z += rightDeltas[i];
				}
			}
		}
		for(int i = 0; i < points.length; i++){
			
			double upDeltas[] = new double[points.length];
			double downDeltas[] = new double[points.length];
		
			for(int n = 0; n < loops; n++){
				for(int j = 0; j < points.length; j++){
					if(j > 0){
						upDeltas[j] = k*(points[i][j].z-points[i][j-1].z);
						points[i][j-1].alpha += upDeltas[j];
					}
					if(j < points[0].length-1){
						downDeltas[j] = k*(points[i][j].z-points[i][j+1].z);
						points[i][j+1].alpha += downDeltas[j];
					}
				}
				for(int j = 0; j < points.length; j++){
					if(j > 0)
						points[i][j-1].z += upDeltas[j];
					if(j < points.length-1)
						points[i][j+1].z += downDeltas[j];
				}
			}
		}
	}
	
}
