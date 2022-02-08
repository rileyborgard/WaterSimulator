package main;

import java.awt.Color;
import java.awt.Graphics;

import com.henagongames.game.Menu;

public class Screen implements Menu {
	
	public void draw(Graphics g){
//		((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setColor(new Color(0, 0, 0));
		g.fillRect(0, 0, 400, 400);
//		Transparency.set(g, 20);
//		drawBack(g);
//		drawSides(g);
//		Transparency.set(g, 50);
		drawTop(g);
//		Transparency.set(g, 20);
		drawFront(g);
//		Transparency.reset(g);
	}
	public void gameLoop(){
		Main.gameLoop();
	}
	
	public Tuple plotPerspective(Point p, double x, double y){
		x -= 100;
		x *= 2;
		y += 200;
		double y1 = 4-y/400;
		return new Tuple(((y1 == 0 ? x:(x-200)/(y1)+200)), (y/2+p.z-30));
//		return new Tuple(x/0.75-56, y/0.75-56+p.z);
	}
	
	private class Tuple {
		int x, y;
		public Tuple(double x, double y){
			this.x = (int) x;
			this.y = (int) y;
		}
	}
	
	private void drawTop(Graphics g){
		for(int j = 0; j < Main.ph-1; j++){
			for(int i = 0; i < Main.pw-1; i++){
				g.setColor(rd(Main.points[i][j].z));
				Tuple t = plotPerspective(Main.points[i][j], i*Main.rw, j*Main.rh+2);
				Tuple t1 = plotPerspective(Main.points[i+1][j], (i+1)*Main.rw, j*Main.rh+2);
				Tuple t2 = plotPerspective(Main.points[i+1][j+1], (i+1)*Main.rw, (j+1)*Main.rh+2);
				Tuple t3 = plotPerspective(Main.points[i][j+1], i*Main.rw, (j+1)*Main.rh+2);
				int yClamp = (int) plotPerspective(new Point(75), i*Main.rw, j*Main.rh).y;
				t.y = t.y > yClamp ? yClamp : t.y;
				t1.y = t1.y > yClamp ? yClamp : t1.y;
				t2.y = t2.y > yClamp ? yClamp : t2.y;
				t3.y = t3.y > yClamp ? yClamp : t3.y;
				int[] x = {t.x, t1.x, t2.x, t3.x};
				int[] y = {t.y, t1.y, t2.y, t3.y};
				g.fillPolygon(x, y, 4);
				y = new int[]{yClamp, yClamp, yClamp, yClamp};
				g.fillPolygon(x, y, 4);
			}
		}
	}
	private void drawFront(Graphics g){
		for(int i = 0; i < Main.pw-1; i++){
			int j = Main.ph-1;
			g.setColor(rd(Main.points[i][j].z).darker());
			Tuple t = plotPerspective(Main.points[i][j], i*Main.rw, j*Main.rh+2);
			Tuple t1 = plotPerspective(Main.points[i+1][j], (i+1)*Main.rw, j*Main.rh+2);
			Tuple t2 = plotPerspective(new Point(75), (i+1)*Main.rw, j*Main.rh+2);
			Tuple t3 = plotPerspective(new Point(75), i*Main.rw, j*Main.rh+2);
			t.y = t.y > t2.y ? t2.y : t.y;
			t1.y = t1.y > t3.y ? t3.y : t1.y;
			int[] x = {t.x, t1.x, t2.x, t3.x};
			int[] y = {t.y, t1.y, t2.y, t3.y};
			g.fillPolygon(x, y, 4);
		}
	}
	private void drawBack(Graphics g){
		
	}
	private void drawSides(Graphics g){
		for(int i = 0; i < Main.pw-1; i++){
			int j = 0;
			g.setColor(rd(Main.points[i][j].z));
			Tuple t = plotPerspective(Main.points[i][j], i*Main.rw, j*Main.rh+2);
			Tuple t1 = plotPerspective(Main.points[i+1][j], (i+1)*Main.rw, j*Main.rh+2);
			Tuple t2 = plotPerspective(new Point(75), (i+1)*Main.rw, j*Main.rh+2);
			Tuple t3 = plotPerspective(new Point(75), i*Main.rw, j*Main.rh+2);
			t.y = t.y > t2.y ? t2.y : t.y;
			t1.y = t1.y > t3.y ? t3.y : t1.y;
			int[] x = {t.x, t1.x, t2.x, t3.x};
			int[] y = {t.y, t1.y, t2.y, t3.y};
			g.fillPolygon(x, y, 4);
		}
		for(int j = 0; j < Main.ph-1; j++){
			int i = Main.pw-1;
			g.setColor(rd(Main.points[i][j].z));
			Tuple t = plotPerspective(Main.points[i][j], i*Main.rw, j*Main.rh+2);
			Tuple t1 = plotPerspective(Main.points[i][j+1], i*Main.rw, (j+1)*Main.rh+2);
			Tuple t2 = plotPerspective(new Point(75), i*Main.rw, (j+1)*Main.rh+2);
			Tuple t3 = plotPerspective(new Point(75), i*Main.rw, j*Main.rh+2);
			t.y = t.y > t2.y ? t2.y : t.y;
			t1.y = t1.y > t3.y ? t3.y : t1.y;
			int[] x = {t.x, t1.x, t2.x, t3.x};
			int[] y = {t.y, t1.y, t2.y, t3.y};
			g.fillPolygon(x, y, 4);
			i = 0;
			g.setColor(rd(Main.points[i][j].z));
			t = plotPerspective(Main.points[i][j], i*Main.rw, j*Main.rh+2);
			t1 = plotPerspective(Main.points[i][j+1], i*Main.rw, (j+1)*Main.rh+2);
			t2 = plotPerspective(new Point(75), i*Main.rw, (j+1)*Main.rh+2);
			t3 = plotPerspective(new Point(75), i*Main.rw, j*Main.rh+2);
			t.y = t.y > t2.y ? t2.y : t.y;
			t1.y = t1.y > t3.y ? t3.y : t1.y;
			x = new int[]{t.x, t1.x, t2.x, t3.x};
			y = new int[]{t.y, t1.y, t2.y, t3.y};
			g.fillPolygon(x, y, 4);
		}
	}
	
	private Color rd1(double z){
		int d = (int) z*8, d1 = d+255, d2 = d-255;
		d = d < 0 ? 0:d;
		d = d > 255 ? 255:d;
		d1 = d1 < 0 ? 0:d1;
		d1 = d1 > 255 ? 255:d1;
		d2 = d2 < 0 ? 0:d2;
		d2 = d2 > 255 ? 255:d2;
		return new Color(255-d, d1, d2);
	}
	private Color rd(double z){
		int d = (int) ((z*2)+128);
		d = d < 0 ? 0:d;
		d = d > 255 ? 255:d;
		return new Color(0, 255-d, 255-d);
	}
	
}
