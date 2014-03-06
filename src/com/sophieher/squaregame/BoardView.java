package com.sophieher.squaregame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class BoardView extends View {

	private SquareGameLogic mGame;

	// size of grid
	private int mGridSize;

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

	// Initialize the paint to use for drawing here
	public void init() {   	
		mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		mGridSize = 4;
	}

	@Override 
	protected void onDraw(Canvas canvas){
		// Draw the board with the color specified
		mPaint.setColor(Color.BLACK);
		int w = getWidth();
		int h = w;
		canvas.drawRect(0, 0, w, h, mPaint);

		// Now change the color for the circle grid
		mPaint.setColor(getResources().getColor(R.color.yellow));
		mPaint.setStrokeWidth(24);


		// The following code is just a place holder for the circle grid to be drawn
		// the grid will be drawn dynamically based on getWidth()
		// does not work in landscape mode due to the dependency on getWidth()
		int circleRad = w/20 + 10; // given an offset of 10
		int circleGrid = w/4;
		for(int i = 0; i < mGridSize; ++i){
			//						 cx,                      cy,    radius,  paint 
			canvas.drawCircle(circleRad, circleRad+i*4*circleRad, circleRad, mPaint);
			canvas.drawCircle(circleRad+circleGrid, circleRad+i*4*circleRad, circleRad, mPaint);
			canvas.drawCircle(circleRad+2*circleGrid, circleRad+i*4*circleRad, circleRad, mPaint);
			canvas.drawCircle(circleRad+3*circleGrid, circleRad+i*4*circleRad, circleRad, mPaint);


			/* We will draw the dots as each players's colors, and then go through the lines and 
			 * connect the dots with the player color who holds that line
			 * lines are either on or off, and if they are off then they have a respective color
			 * See: SquareGameLogic.Line
			 */
			if (mGame != null && mGame.getBoardOccupant(i) != -1){
				// startx, starty, stopx, stopy, paint
				canvas.drawLine(0, 0, 1000, 1000, mPaint);
			}

		}
	}

	protected void setGame(SquareGameLogic game) {
		mGame = game;
	}

	// Want to have grid size for determining which circle is chosen
	public int getBoardGridSize() {
		return getWidth() / mGridSize;
	}
	//	
	//protected void getPosition()
}
