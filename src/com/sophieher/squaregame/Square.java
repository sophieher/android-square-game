package com.sophieher.squaregame;

public class Square {
	Line [] lines;
	Boolean on;
	int color;
	
	public Square(){
		this.lines = new Line[4];
		for(int i = 0; i < 4; ++i)
			lines[i] = new Line();
		on = false;
		color = 0;
	}

	/*
	 * Sets the current Square to on or off. If it is off
	 * then the drawn color goes to default 0
	 */
	public void setOn(boolean b){
		this.on = b;
		if(!this.on){
			this.color = 0;
		}
	}
}
