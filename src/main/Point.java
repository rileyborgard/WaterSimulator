package main;

public class Point {
	
	double z;
	double alpha;
	double k = 0.05, d = 0.001;
	
	public Point(double z){
		this.z = z;
	}
	
	public void update(){
		z += alpha;
		alpha += -k*z-d*alpha;
	}
	
}
