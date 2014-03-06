package com.sophieher.squaregame;

public class Line {
	Points [] points;
	Boolean on;
	int color;
	
	public Line(){
		this.points = new Points[2];
		points[0] = new Points(0, 0);
		points[1] = new Points(0, 0);
		on = false;
		color = 0;
	}
	
	public Line(int x1, int y1, int x2, int y2){
		this.points = new Points[2];
		points[0] = new Points(x1, y1);
		points[1] = new Points(x2, y2);
		on = true;
	}
}
