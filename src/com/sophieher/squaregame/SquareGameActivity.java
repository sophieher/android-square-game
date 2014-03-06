package com.sophieher.squaregame;

import java.util.HashMap;

import android.app.Activity;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class SquareGameActivity extends Activity {

	private static final String TAG = "SquareGameActivity";

	private static final int num_players = 2;

	private SquareGameLogic mGame;
	private BoardView mBoard;

	// Who's Turn is it?
	private int mTurn;
	private boolean mGameOver; 

	private SoundPool mSounds; // sound pool for user interaction
	private HashMap<Integer, Integer> mSoundMap;

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
	protected void onResume() {		
		super.onResume();
		createSoundPool();
	}


	private void createSoundPool() {
		int[] soundIds = {R.raw.move};
		mSoundMap = new HashMap<Integer, Integer>();
		mSounds = new SoundPool(2, AudioManager.STREAM_MUSIC, 0);
		for(int id : soundIds) 
			mSoundMap.put(id, mSounds.load(this, id, 1));
	}


	@Override
	protected void onPause() {
		super.onPause();

		Log.d(TAG, "in onPause");

		if(mSounds != null) {
			mSounds.release();
			mSounds = null;
		}		
	}

	// Set up the game baord. 
	private void startNewGame(boolean first) {
		// check if new game after a complete game and if so swap who goes first
		if(mGameOver) {
			mTurn = 0;
			//				mGoesFirst = (mGoesFirst == TicTacToeGame.COMPUTER_PLAYER) ? 
			//						TicTacToeGame.HUMAN_PLAYER : TicTacToeGame.COMPUTER_PLAYER;
		}

		mGameOver = false;

		mGame.new_game();  
		mBoard.invalidate();   // Redraw the board    

		// Who starts?
		//			if (mTurn == TicTacToeGame.COMPUTER_PLAYER) {
		//				Log.d(TAG, "Computers turn!!!");
		//				mInfoTextView.setText(R.string.first_computer);
		//				int move = mGame.getComputerMove();
		//				setMove(TicTacToeGame.COMPUTER_PLAYER, move);
		//			}
		//			else {
		//				mInfoTextView.setText(R.string.first_human); 
		//			}	
	}

	// Make a move, location is where the move goes
	private boolean setMove(int player, int location) {
		if (mGame.setMove(player, location)) { 
			if(++player >= num_players)
				mTurn = player = 0;
			else
				mTurn = player;
			Log.d(TAG, "Player's turn: " + mTurn);
			mBoard.invalidate();   // Redraw the board
			mSounds.play(mSoundMap.get(R.raw.move), 1, 1, 1, 0, 1);	    	   	
			return true;
		}
		// still need to pick an end point
		return false;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.square_game, menu);
		return true;
	}

	// Game is over logic
	private void endGame(int winner) {
		// Depends on who won. Create a winner's screen with their color.
		mGameOver = true;
	}


	private OnTouchListener mTouchListener = new OnTouchListener() {
		public boolean onTouch(View v, MotionEvent event) {

			// Find out which circleGrid area was picked first
			// wait for second?
			// depending on the player number whose turn it is, make the move using that color

			//			// Determine which cell was touched	    	
			int col = (int) event.getX() / mBoard.getBoardGridSize();
			int row = (int) event.getY() / mBoard.getBoardGridSize();
			int pos = row * 4 + col;

			// Only register a touch if it falls within the board
			if(pos < 16){
				mSounds.play(mSoundMap.get(R.raw.move), 1, 1, 1, 0, 1);

				Log.d(TAG, "row " + row + " col " + col + ", pos " + pos);
				//
				if (!mGameOver && setMove(mTurn, pos)){

					// If no winner yet, turns proceed
					int winner = mGame.checkForWinner();
					if (winner != -1) { // if there is a winner
						endGame(winner);            		
					} 
					else{
						/* Do Something */
					}

				} 
			}
			// So we aren't notified of continued events when finger is moved
			//TODO: Use drag events rather than two individual touches
			return false;
		} 
	};

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		//		outState.putCharArray("board", mGame.getBoardState());
		//		outState.putBoolean("mGameOver", mGameOver);
		//		outState.putInt("mHumanWins", Integer.valueOf(mHumanWins));
		//		outState.putInt("mComputerWins", Integer.valueOf(mComputerWins));
		//		outState.putInt("mTies", Integer.valueOf(mTies));
		//		outState.putCharSequence("info", mInfoTextView.getText());
		//		outState.putChar("mTurn", mTurn);
		//		outState.putChar("mGoesFirst", mGoesFirst);
	}

}

