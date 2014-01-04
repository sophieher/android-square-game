package com.sophieher.squaregame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class BoardView extends View {
	
	private SquareGameLogic mGame;
	
	private Paint mPaint;

	public BoardView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public BoardView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}
	
	public BoardView(Context context) {
		super(context);
		init();
	}
	
	public void init() {   	
		mPaint = new Paint();
	}
	
	@Override 
	protected void onDraw(Canvas canvas){
		mPaint.setColor(Color.WHITE);
		int w = getWidth();
		int h = getHeight();
		canvas.drawRect(0, 0, w, h, mPaint);
		
		mPaint.setColor(getResources().getColor(R.color.yellow));
		
		int circleRad = w/20;
		int circleGrid = w/4;
		for(int i = 0; i<4; ++i){
			canvas.drawCircle(circleRad, circleRad+i*4*circleRad, circleRad, mPaint);
			canvas.drawCircle(circleRad+circleGrid, circleRad+i*4*circleRad, circleRad, mPaint);
			canvas.drawCircle(circleRad+2*circleGrid, circleRad+i*4*circleRad, circleRad, mPaint);
			canvas.drawCircle(circleRad+3*circleGrid, circleRad+i*4*circleRad, circleRad, mPaint);
		}
	}

	protected void setGame(SquareGameLogic game) {
		mGame = game;
	}
}
