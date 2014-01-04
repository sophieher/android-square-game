package com.sophieher.squaregame;


import scottmd3.tictactoe.R;
import scottmd3.tictactoe.TicTacToeGame;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class SquareGameActivity extends Activity {
	
	private SquareGameLogic mGame;
	private BoardView mBoard;

	private int mTurn;
	private boolean mGameOver; 
	
//	private OnTouchListener mTouchListener;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_square_game);
		
		//Initialize game board & touch listener
		mGame = new SquareGameLogic();
		mBoard = (BoardView) findViewById(R.id.boardView);
		mBoard.setGame(mGame);
		mBoard.setOnTouchListener(mTouchListener);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.square_game, menu);
		return true;
	}
	
	
	private OnTouchListener mTouchListener = new OnTouchListener() {
		public boolean onTouch(View v, MotionEvent event) {

			// Find out which circleGrid area was picked first
			// wait for second?
			// depending on the player number whose turn it is, make the move using that color

			// So we aren't notified of continued events when finger is moved
			return false;
		} 
	};

}

